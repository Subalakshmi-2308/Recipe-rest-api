package com.receipe_rest_api.receipe_api.entity;

import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
 
@Entity

public class Category {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="category_id")

	private Long id;

	@Column(name="category_name")

	private String name;

	@OneToMany( mappedBy = "category",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Receipe> recipes = new ArrayList<>();
 
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
 
	public List<Receipe> getRecipes() {

		return recipes;

	}
 
	public void setRecipes(List<Receipe> recipes) {

		this.recipes = recipes;

	}
 
	public Category() {

		super();

	}
 
	@Override

	public String toString() {

		return "Category [id=" + id + ", name=" + name + "]";

	}


}


