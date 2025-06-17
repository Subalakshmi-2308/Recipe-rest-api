package com.receipe_rest_api.receipe_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ingredient_id")
	private Long id;
	@Column(name="ingredient_name")
	private String name;
	@Column(name="quantity")
	private int quantity; // in grams
	
	@ManyToOne
	@JsonIgnore
	private Receipe recipe;
 
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
 
	public int getQuantity() {
		return quantity;
	}
 
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
 
	public Receipe getRecipe() {
		return recipe;
	}
 
	public void setRecipe(Receipe recipe) {
		this.recipe = recipe;
	}
 
	public Ingredient() {
		super();
	}
 
	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", quantity=" + quantity + "]";
	}
	
	
}