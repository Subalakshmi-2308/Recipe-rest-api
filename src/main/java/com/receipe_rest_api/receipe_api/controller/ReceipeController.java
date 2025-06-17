package com.receipe_rest_api.receipe_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.receipe_rest_api.receipe_api.entity.*;
import com.receipe_rest_api.receipe_api.exception.ResourceNotFoundException;
import com.receipe_rest_api.receipe_api.service.*;

@RestController
public class ReceipeController {
	
	@Autowired
	CategoryService cs;
	@Autowired
	ReceipeService rs;
	@Autowired
	IngredientService is;
	
	@GetMapping("/hello")
	public ResponseEntity<String> test(){
		return new ResponseEntity<String>("Hello Rest API!! ",HttpStatus.OK);
	}
	
	@PostMapping("/category/save")
	public ResponseEntity<Category> addCategory(@RequestBody Category c){
		try {
			Category r= cs.saveCategory(c);
			return new ResponseEntity<Category>(r,HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@GetMapping("/category/all")
	public ResponseEntity<List<Category>> displayAllCategory(){
		List<Category> clist = cs.getAllCategories();
		return new ResponseEntity<List<Category>>(clist,HttpStatus.OK);
	}

	@PostMapping("/receipe/add")
	public ResponseEntity<Receipe> addReceipe(@RequestBody Receipe r){
		if (r.getIngredients() != null) {
	        for (Ingredient ingredient : r.getIngredients()) {
	            ingredient.setRecipe(r);
	        }
	    }
		 r.setIngredients(r.getIngredients());
		List<Receipe> recipes = new ArrayList<Receipe>();
		Category category = cs.getCategory(r.getCategory().getId());
		recipes.add(r);
		category.setRecipes(recipes);
		r.setCategory(category);
		Receipe result = rs.saveRecipe(r);
		return new ResponseEntity<Receipe>(result,HttpStatus.OK);
	}
	
	
	@PutMapping("/category/update/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable("id") Long id,@RequestBody Category c){
		Category catbyid = cs.getCategory(id);
		if(catbyid ==null) {
			throw new ResourceNotFoundException("Category does not exist! "+id);
		}
		catbyid.setName(c.getName());
		cs.saveCategory(catbyid);
		return new ResponseEntity<Category>(catbyid,HttpStatus.OK);
	}
	
	@PutMapping("/receipe/update/{id}")
	public ResponseEntity<?> updateReceipe(@PathVariable("id") Long id, @RequestBody Receipe r) {
	    Receipe rbyid = rs.getReceipe(id);
	    if(rbyid ==null) {
			throw new ResourceNotFoundException("Recipe does not exist! "+id);
		}
	    else {
	    	
	    rbyid.setName(r.getName());
	    rbyid.setTime(r.getTime());
	    rbyid.setDescription(r.getDescription());
	    rbyid.setCategory(r.getCategory());
	    rbyid.setIngredients(r.getIngredients());

	    
	    if (r.getIngredients() != null) {
	        for (Ingredient i : r.getIngredients()) {
	            i.setRecipe(rbyid); // Set the managed recipe entity
	        }
	    }
	    rs.saveRecipe(rbyid); // Save the updated recipe
	    
	    return new ResponseEntity<Receipe>(rbyid, HttpStatus.OK);
	    }
	}
	
	@DeleteMapping("/category/delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
		
		Category categoryById=cs.getCategory(id);
		if(categoryById == null) {
			throw new ResourceNotFoundException("Category does not exist! "+id);
		}
		else {
			cs.deleteById(id);
			return new ResponseEntity<String> ("Category ID Deleted ",HttpStatus.OK);
		}
		
		
	}
	
	@DeleteMapping("/receipe/delete/{id}")
	public ResponseEntity<?> deleteRecipe(@PathVariable("id") Long id){
		Receipe r = rs.getReceipe(id);
		if(r ==null) {
			throw new ResourceNotFoundException("Recipe does not exist! "+id);
		}
		else {
		rs.deleteById(id);
		return new ResponseEntity<String> ("Receipe ID Deleted ",HttpStatus.OK);
		}
	}
	
	
	
}
