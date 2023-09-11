package br.italolima.taskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.italolima.taskmanager.dto.ResponseDTO;
import br.italolima.taskmanager.dto.UserDTO;
import br.italolima.taskmanager.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/taskmanager/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	private ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO userDTO) throws Exception {
		ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), userService.createUser(userDTO));
	
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
