package br.italolima.taskmanager.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.italolima.taskmanager.dto.ErrorResponseDTO;
import br.italolima.taskmanager.dto.ResponseDTO;
import br.italolima.taskmanager.exceptions.NotFoundException;
import br.italolima.taskmanager.exceptions.UpdateTaskException;
import br.italolima.taskmanager.exceptions.UpdateTaskProgressException;
@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {
		
		ErrorResponseDTO response = new ErrorResponseDTO(HttpStatus.UNAUTHORIZED.value(), e.getMessage()); 
		
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
	
	@ExceptionHandler({UpdateTaskProgressException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ErrorResponseDTO> onUpdateProgressException(Exception e) {
		ErrorResponseDTO response = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()); 

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
	
	@ExceptionHandler({UpdateTaskException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ErrorResponseDTO> onUpdateTaskException(Exception e) {
		ErrorResponseDTO response = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()); 
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ResponseEntity<ErrorResponseDTO> onNotFoundException(Exception e) {
		ErrorResponseDTO response = new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), e.getMessage()); 

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
	
	@ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ErrorResponseDTO> onIllegalArgumentException(Exception e) {
		ErrorResponseDTO response = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()); 

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
