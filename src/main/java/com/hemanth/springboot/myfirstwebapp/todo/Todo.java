package com.hemanth.springboot.myfirstwebapp.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@Size(min=7,message="must be atleast 7 characters")
	private String description;
	private LocalDate target;
	private boolean done;
	
	public Todo() {

	}
	
	public Todo(int id, String name, String description, LocalDate target, boolean done) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.target = target;
		this.done = done;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTarget() {
		return target;
	}

	public void setTarget(LocalDate target) {
		this.target = target;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}


	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", description=" + description + ", target=" + target + ", done="
				+ done + "]";
	}

}
