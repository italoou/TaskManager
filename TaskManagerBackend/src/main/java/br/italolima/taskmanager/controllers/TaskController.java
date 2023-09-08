package br.italolima.taskmanager.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.italolima.taskmanager.dto.TaskDTO;

@RestController()
public class TaskController {

	@GetMapping("/tasks")
	public String getAllTasks() {
		return "ok";
	}
	
	@GetMapping("/task/{id}")
	public String getTask(@PathVariable Long id) {
		return "ok";
	}
	
	@PostMapping("/task")
	public String createTask(@RequestBody TaskDTO taskDto) {
		return "ok";
	}
	
	@PutMapping("/task")
	public String updateTask(@RequestBody TaskDTO taskDto) {
		return "ok";
	}
	
}
