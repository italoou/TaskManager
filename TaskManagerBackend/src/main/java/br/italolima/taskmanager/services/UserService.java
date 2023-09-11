package br.italolima.taskmanager.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.italolima.taskmanager.dto.TaskDTO;
import br.italolima.taskmanager.dto.UserDTO;
import br.italolima.taskmanager.exceptions.NotFoundException;
import br.italolima.taskmanager.models.Task;
import br.italolima.taskmanager.models.User;
import br.italolima.taskmanager.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserDTO createUser(UserDTO userDTO) throws Exception {
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		User user = mapToEntity(userDTO);
		
		if(userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new Exception("Username Not Avaliable");
		}

		return mapToDTO(userRepository.save(user));
		
	}
	
	private User mapToEntity(UserDTO userDTO) {
		
	    var modelMapper = new ModelMapper();
	
	    User user = modelMapper.map(userDTO, User.class);
				
		return user;
	}
	
	private UserDTO mapToDTO(User user) {
		
	    var modelMapper = new ModelMapper();
	
	    UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		
		return userDTO;
	}
}
