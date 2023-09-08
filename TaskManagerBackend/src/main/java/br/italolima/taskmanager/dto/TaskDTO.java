package br.italolima.taskmanager.dto;

import java.time.LocalDateTime;

import br.italolima.taskmanager.enums.TaskProgress;

public record TaskDTO(Long id, String title, 
		String description, TaskProgress status, 
		LocalDateTime createdAt, LocalDateTime updatedAt) {
}
