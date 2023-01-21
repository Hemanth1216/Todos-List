package com.hemanth.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@RequestMapping("/list-todos")
	public String listTodos(ModelMap model)
	{
		String username = getLoggedInUser();
		List<Todo> todos = todoService.findTodosByUsername(username);
		model.addAttribute("todos", todos);
		return "listtodos";
	}

	
	@RequestMapping(value="/add-todo",method=RequestMethod.GET)
	public String showNewTodoToAdd(ModelMap model)
	{
		String username = getLoggedInUser();
		Todo todo = new Todo(0,username,"",LocalDate.now().plusWeeks(5),false);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="/add-todo",method=RequestMethod.POST)
	public String addTodo(ModelMap model,@Valid Todo todo,BindingResult result) //form backing bean "or" command bean = todo;//
	{
		if(result.hasErrors())
		{
			return "todo";
		}
		String username = getLoggedInUser();
		todoService.addTodo(username,todo.getDescription(),todo.getTarget(),todo.isDone());
		return "redirect:list-todos";
	}
	
	@RequestMapping("/delete-todo")
	public String deleteTodo(@RequestParam("id") int id)
	{
		todoService.deleteTodoById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam("id") int id,ModelMap model)
	{
		Todo todo = todoService.findTodoById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String UpdateTodo(ModelMap model,Todo todo)
	{
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
	private String getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
