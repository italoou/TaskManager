package br.italolima.taskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.italolima.taskmanager.dto.ResponseDTO;
import br.italolima.taskmanager.dto.SearchDTO;
import br.italolima.taskmanager.dto.TaskDTO;
import br.italolima.taskmanager.dto.TaskProgressDTO;
import br.italolima.taskmanager.enums.TaskProgress;
import br.italolima.taskmanager.exceptions.NotFoundException;
import br.italolima.taskmanager.exceptions.UpdateTaskException;
import br.italolima.taskmanager.exceptions.UpdateTaskProgressException;
import br.italolima.taskmanager.models.User;
import br.italolima.taskmanager.services.TaskService;

@CrossOrigin("*")
@RestController()
@RequestMapping("/api/taskmanager/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping("")
	public ResponseEntity<ResponseDTO> getAllTasks(Authentication authentication, @RequestParam(required = false) TaskProgressDTO progress) {
		User user = (User) authentication.getPrincipal();
		
		ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), taskService.getAllTasks(user, progress));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/search/{search}")
	public ResponseEntity<ResponseDTO> getAllTasksWithText(Authentication authentication, @PathVariable SearchDTO search) {
		User user = (User) authentication.getPrincipal();
		
		ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), taskService.getAllTasksWithText(user, search));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getTask(@PathVariable Long id, Authentication authentication) throws NotFoundException {
		User user = (User) authentication.getPrincipal();
		
		ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), taskService.getTask(id));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("")
	public ResponseEntity<ResponseDTO> createTask(@RequestBody TaskDTO taskDto, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		
		ResponseDTO response = new ResponseDTO(HttpStatus.CREATED.value(), taskService.createTask(taskDto, user));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO> updateTask(@RequestBody TaskDTO taskDto, @PathVariable Long id, Authentication authentication) throws UpdateTaskException  {
		User user = (User) authentication.getPrincipal();
		
		ResponseDTO response = new ResponseDTO(HttpStatus.CREATED.value(), taskService.updateTask(taskDto, id));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/progress/{id}")
	public ResponseEntity<ResponseDTO> updateTaskProgress(@RequestBody TaskProgressDTO taskProgressDTO, @PathVariable Long id, Authentication authentication) throws UpdateTaskProgressException {
		User user = (User) authentication.getPrincipal();

		ResponseDTO response = new ResponseDTO(HttpStatus.CREATED.value(), taskService.updateTaskProgress(id, taskProgressDTO));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
}
