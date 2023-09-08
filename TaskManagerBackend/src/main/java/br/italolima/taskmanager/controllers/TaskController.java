package br.italolima.taskmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.italolima.taskmanager.dto.TaskDTO;
import br.italolima.taskmanager.dto.TaskProgressDTO;
import br.italolima.taskmanager.exceptions.NotFoundException;
import br.italolima.taskmanager.exceptions.UpdateTaskException;
import br.italolima.taskmanager.exceptions.UpdateTaskProgressException;
import br.italolima.taskmanager.models.Task;
import br.italolima.taskmanager.models.User;
import br.italolima.taskmanager.services.TaskService;

@RestController()
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping("")
	public ResponseEntity<List<Task>> getAllTasks(Authentication authentication) {
		User user = (User) authentication.getPrincipal();

		return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTask(@PathVariable Long id, Authentication authentication) throws NotFoundException {
		User user = (User) authentication.getPrincipal();
		
		return ResponseEntity.status(HttpStatus.OK).body(taskService.getTask(id));
	}
	
	@PostMapping("")
	public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDto, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(taskDto, user));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@RequestBody TaskDTO taskDto, @PathVariable Long id, Authentication authentication) throws UpdateTaskException  {
		User user = (User) authentication.getPrincipal();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.updateTask(taskDto, id));
	}
	
	@PutMapping("/progress/{id}")
	public ResponseEntity<Task> updateTaskProgress(@RequestBody TaskProgressDTO TaskProgress, @PathVariable Long id, Authentication authentication) throws UpdateTaskProgressException {
		User user = (User) authentication.getPrincipal();

		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.updateTaskProgress(id, TaskProgress.progress()));
	}
	
}
