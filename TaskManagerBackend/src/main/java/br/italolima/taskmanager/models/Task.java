package br.italolima.taskmanager.models;

import br.italolima.taskmanager.enums.TaskProgress;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String title;
	
	private String description;
	
	private TaskProgress status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
