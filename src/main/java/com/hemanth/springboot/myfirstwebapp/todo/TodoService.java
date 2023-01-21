package com.hemanth.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	static int count = 0;

	static {
		todos.add(new Todo(++count, "Hemanth", "Springboot", LocalDate.now().plusWeeks(6), false));
		todos.add(new Todo(++count, "Hemanth", "Cyber Security", LocalDate.now().plusWeeks(6), false));
		todos.add(new Todo(++count, "Srinu", "Database", LocalDate.now().plusWeeks(5), false));
		todos.add(new Todo(++count, "Chanakya", "Python", LocalDate.now().plusWeeks(4), false));
	}

	public List<Todo> findTodosByUsername(String username) {
		Predicate<? super Todo> predicate = todo -> todo.getName().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username,String description,LocalDate target,boolean done)
	{
		todos.add(new Todo(++count,username,description, target, done));
	}
	
	public void deleteTodoById(int id)
	{
//		for(Todo todo : todos)
//		{
//			if(todo.getId()==id)
//			{
//				todos.remove(todo);
//				break;
//			}
//		}
		Predicate<? super Todo> predicate = todo -> todo.getId()==id;
		todos.removeIf(predicate);
	}

	public Todo findTodoById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super Todo> predicate = todo -> todo.getId()==id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(Todo todo) {
		// TODO Auto-generated method stub
		for(Todo t : todos)
			{
				if(todo.getId()==t.getId())
				{
					t.setDescription(todo.getDescription());
					t.setTarget(todo.getTarget());
					break;
				}
			}
	}

	
}
