package com.receipe_rest_api.receipe_api.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.receipe_rest_api.receipe_api.entity.Ingredient;
import com.receipe_rest_api.receipe_api.repository.IngredientRepo;

import jakarta.transaction.Transactional;

@Service

@Transactional
public class IngredientService {
	@Autowired

	private IngredientRepo ir;

	public Ingredient saveIngredient(Ingredient ingredient) {

		return ir.save(ingredient);

	}
	
	public List<Ingredient> all(){
		return ir.findAll();
	}

	public Ingredient getIngredient(Long id) {

		Ingredient ingredient = ir.findById(id).orElse(null);

        return ingredient;

    }
}

