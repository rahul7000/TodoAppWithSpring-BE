package com.rahul.todo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rahul.todo.dtos.TodoDTO;
import com.rahul.todo.services.TodoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@RequestMapping(method = RequestMethod.GET, path = "/users/{userName}/todos")
	public List<TodoDTO> getTodos(@PathVariable String userName) {
		System.out.println(1==1L);
		return todoService.findAll();

	}

	@RequestMapping(method = RequestMethod.GET, path = "/users/{userName}/todos/{todoId}")
	public TodoDTO getTodo(@PathVariable String userName, @PathVariable long todoId) {
		return todoService.findById(todoId);

	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/users/{userName}/todos/{todoId}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String userName, @PathVariable long todoId) {

		TodoDTO todo = todoService.deleteById(todoId);
		if (todo != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();

	}

	@RequestMapping(method = RequestMethod.PUT, path = "/users/{userName}/todos/{todoId}")
	public ResponseEntity<TodoDTO> updateTodo(@PathVariable String userName, @PathVariable long todoId,
			@RequestBody TodoDTO todo) {
		
		TodoDTO updatedTodo = todoService.save(todo);
		
		return new ResponseEntity<TodoDTO>(updatedTodo, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, path = "/users/{userName}/todos")
	public ResponseEntity<TodoDTO> createTodo(@PathVariable String userName, @RequestBody TodoDTO todo) {
		TodoDTO createdTodo = todoService.save(todo);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(createdTodo.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();

	}
}
