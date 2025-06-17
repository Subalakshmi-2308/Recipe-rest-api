package com.receipe_rest_api.receipe_api.entity;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

 
@Entity

public class Receipe {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="recipe_id")

	private Long id;

	@Column(name="recipe_name")

	private String name;

	@Column(name="recipe_desc")

	private String description;

	@Column(name="cooking_time")

	private int time; //in minutes

	@ManyToOne
	private Category category;

	@OneToMany( mappedBy = "recipe", cascade = CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<>();

 
	public Long getId() {

		return id;

	}
 
	public void setId(Long id) {

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
 
	public int getTime() {

		return time;

	}
 
	public void setTime(int time) {

		this.time = time;

	}
	
	public Category getCategory() {

		return category;

	}
 
	public void setCategory(Category category) {

		this.category = category;

	}
 
	public List<Ingredient> getIngredients() {

		return ingredients;

	}
 
	public void setIngredients(List<Ingredient> ingredients) {

		this.ingredients = ingredients;

	}
 
	public Receipe() {

		super();

	}

	
	
	public Receipe(String name, String description, int time) {
		super();
		this.name = name;
		this.description = description;
		this.time = time;
	}

	@Override

	public String toString() {

		return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", time=" + time + "]";

	}


}
