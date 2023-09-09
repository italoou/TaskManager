package br.italolima.taskmanager.dto;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

public record ResponseDTO(int code, Object data) {
}
