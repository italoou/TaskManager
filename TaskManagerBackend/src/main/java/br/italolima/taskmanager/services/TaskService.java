package br.italolima.taskmanager.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.stereotype.Service;

import br.italolima.taskmanager.dto.SearchDTO;
import br.italolima.taskmanager.dto.TaskDTO;
import br.italolima.taskmanager.dto.TaskProgressDTO;
import br.italolima.taskmanager.enums.TaskProgress;
import br.italolima.taskmanager.exceptions.NotFoundException;
import br.italolima.taskmanager.exceptions.UpdateTaskException;
import br.italolima.taskmanager.exceptions.UpdateTaskProgressException;
import br.italolima.taskmanager.models.Task;
import br.italolima.taskmanager.models.User;
import br.italolima.taskmanager.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public List<TaskDTO> getAllTasks(User user, TaskProgressDTO taskProgressDTO){
		
		List<Task> tasks = taskRepository.findAllByUserOrderByStatusAscCreatedAtAsc(user);
				
		try {
			return tasks.stream().filter(c -> taskProgressDTO == null? c.getStatus() != TaskProgress.ARCHIVED : c.getStatus() == TaskProgress.valueOf(taskProgressDTO.progress()) )
	                .map(u -> mapToDTO(u))
	                .collect(Collectors.toList());
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("There Is No Progress With The Given Name");
		}
		
	}
	
	public List<TaskDTO> getAllTasksWithText(User user, SearchDTO searchDTO){
		
		List<Task> tasks = taskRepository.findAllByUserOrderByStatusAscCreatedAtAsc(user);
				
		try {
			return tasks.stream().filter(c -> c.getTitle().contains(searchDTO.text()) || c.getDescription().contains(searchDTO.text()) )
	                .map(u -> mapToDTO(u))
	                .collect(Collectors.toList());
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("There Is No Progress With The Given Name");
		}
		
	}
	
	public TaskDTO getTask(Long id) throws NotFoundException{
		return mapToDTO(taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Entity Not Found!")));
	}
	
	public TaskDTO createTask(TaskDTO taskDTO, User user){		
		Task task = mapToEntity(taskDTO);
		
		task.setStatus(TaskProgress.NOT_STARTED);
		task.setUser(user);
		task.setCreatedAt(LocalDateTime.now());
		task.setUpdatedAt(LocalDateTime.now());
		
		return mapToDTO(taskRepository.save(task));
	}
	
	public TaskDTO updateTask(TaskDTO newTask, Long id) throws UpdateTaskException {

		Task oldTask = taskRepository.findById(id).orElseThrow();
		
		switch(oldTask.getStatus()) {
			case COMPLETED:
			case ARCHIVED:
				throw new UpdateTaskException("Its Not Possible Update Completed or Archived Tasks");
			default:
				oldTask.setTitle(newTask.getTitle());
				oldTask.setDescription(newTask.getDescription());
				oldTask.setDeadline(newTask.getDeadline());
				oldTask.setStatus(newTask.getStatus());
				oldTask.setUpdatedAt(LocalDateTime.now());
				
				return mapToDTO(taskRepository.save(oldTask));
		}
	}
	
	public TaskDTO updateTaskProgress(Long id, TaskProgressDTO taskProgressDTO) throws UpdateTaskProgressException {
		
		Task task = taskRepository.findById(id).orElseThrow();

		try {
		
			switch(task.getStatus()) {
				case ARCHIVED:
					throw new UpdateTaskProgressException("It's Not Possible Update The Progress of Archived Tasks");
				case COMPLETED:
					if(TaskProgress.valueOf(taskProgressDTO.progress()) != TaskProgress.ARCHIVED) {
						throw new UpdateTaskProgressException("Completed Task Can Only be Archived");	
					}
					break;
				default:
					break;
			}
			
			task.setStatus(TaskProgress.valueOf(taskProgressDTO.progress()));
			task.setUpdatedAt(LocalDateTime.now());
	
			return mapToDTO(taskRepository.save(task));
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("There Is No Progress With The Given Name");
		}
	}
	
	private Task mapToEntity(TaskDTO taskDTO) {
		
	    var modelMapper = new ModelMapper();
	
		Task task = modelMapper.map(taskDTO, Task.class);
				
		return task;
	}
	
	private TaskDTO mapToDTO(Task task) {
		
	    var modelMapper = new ModelMapper();
	
	    TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
		
		return taskDTO;
	}
	
}
