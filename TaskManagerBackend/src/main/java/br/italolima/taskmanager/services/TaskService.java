package br.italolima.taskmanager.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.italolima.taskmanager.dto.TaskDTO;
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
	
	public List<Task> getAllTasks(User user){
		return taskRepository.findAllByUser(user);
	}
	
	public Task getTask(Long id) throws NotFoundException{
		return taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Entity Not Found!"));
	}
	
	public Task createTask(TaskDTO taskDTO, User user){		
		Task task = new Task();
		
		task.setTitle(taskDTO.title());
		task.setDescription(taskDTO.description());
		
		task.setStatus(TaskProgress.NOT_STARTED);
		task.setUser(user);
		task.setCreatedAt(LocalDateTime.now());
		task.setUpdatedAt(LocalDateTime.now());
		
		return taskRepository.save(task);
	}
	
	public Task updateTask(TaskDTO newTask, Long id) throws UpdateTaskException {
		
		Task oldTask = taskRepository.findById(id).orElseThrow();
		
		switch(oldTask.getStatus()) {
			case COMPLETED:
			case ARCHIVED:
				throw new UpdateTaskException("Its Not Possible Update Completed or Archived Tasks");
			default:
				oldTask.setTitle(newTask.title());
				oldTask.setDescription(newTask.description());
				oldTask.setUpdatedAt(LocalDateTime.now());
				return taskRepository.save(oldTask);
		}
	}
	
	public Task updateTaskProgress(Long id, TaskProgress progress) throws UpdateTaskProgressException {
		
		Task task = taskRepository.findById(id).orElseThrow();

		
		switch(task.getStatus()) {
			case ARCHIVED:
				throw new UpdateTaskProgressException("It's Not Possible Update The Progress of Archived Tasks");
			case COMPLETED:
				if(progress != TaskProgress.ARCHIVED) {
					throw new UpdateTaskProgressException("Completed Task Can Only be Archived");	
				}
				break;
			default:
				break;
		}
		
		task.setStatus(progress);
		task.setUpdatedAt(LocalDateTime.now());

		return taskRepository.save(task);
		
	}
	
}
