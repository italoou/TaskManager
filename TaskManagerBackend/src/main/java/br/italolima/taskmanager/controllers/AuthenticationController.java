package br.italolima.taskmanager.controllers;

import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.italolima.taskmanager.dto.LoginDTO;
import br.italolima.taskmanager.models.User;
import br.italolima.taskmanager.services.TokenService;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDTO login) throws Exception {
		
		Objects.requireNonNull(login.username());
        Objects.requireNonNull(login.password());

        if (login.password() == "") {
            throw new Exception("NO_PASSWORD");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(login.username(), login.password());

        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        
        var user = (User) authenticate.getPrincipal(); 

        HashMap<String, String> data = new HashMap<>();
        
        data.put("token", tokenService.createToken(user));
        
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}
	
}
