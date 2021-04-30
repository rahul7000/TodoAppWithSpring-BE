package com.rahul.todo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.rahul.todo.dtos.TodoDTO;

@Service
public class TodoService {

	private static List<TodoDTO> todos = new ArrayList<>();
	private static int idCounter = 0;

	static {
		todos.add(new TodoDTO(++idCounter, "rahrajpu", "Learn", new Date(), true));
		todos.add(new TodoDTO(++idCounter, "rahrajpu", "Play", new Date(), false));
		todos.add(new TodoDTO(++idCounter, "rahrajpu", "Dance", new Date(), true));
		todos.add(new TodoDTO(++idCounter, "rahrajpu", "Study", new Date(), true));
	}

	public List<TodoDTO> findAll() {
		return todos;
	}

	public TodoDTO deleteById(long id) {
		TodoDTO tempTodo = findById(id);

		if (tempTodo == null) {
			return null;
		}

		// streams approach
		if (todos.removeIf(todo -> todo.getId() == id)) {
			return tempTodo;
		}

		return null;

	}

	public TodoDTO findById(long id) {
		return todos.stream().filter(todo -> todo.getId() == id).findFirst().get();
	}

	public TodoDTO save(TodoDTO todo) {
		if(todo.getId()==-1 || todo.getId()==0) {
			todo.setId(++idCounter);
			todos.add(todo);
			
		}else {
		
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
}
