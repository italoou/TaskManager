package br.italolima.taskmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.italolima.taskmanager.models.Task;
import br.italolima.taskmanager.models.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findAllByUserOrderByStatusAscCreatedAtAsc(User user);
}
