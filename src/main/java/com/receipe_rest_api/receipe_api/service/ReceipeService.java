package com.receipe_rest_api.receipe_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.receipe_rest_api.receipe_api.entity.Receipe;
import com.receipe_rest_api.receipe_api.repository.ReceipeRepo;

import jakarta.transaction.Transactional;

@Service

@Transactional

public class ReceipeService {

	@Autowired

	private ReceipeRepo rr;

	public Receipe saveRecipe(Receipe receipe) {

		return rr.save(receipe);

	}
	
	public List<Receipe> allReceipe(){
		return rr.findAll();
	}

	public Receipe getReceipe(Long id) {

		Receipe receipe = rr.findById(id).orElse(null);
		return receipe;
		

    }
	
	public void deleteReceipe(Long id) {
		
		rr.deleteById(id);
	}
	
//	public void setIngredients(Receipe r) {
//		
//	}
	public void deleteById(Long id) {
		rr.deleteById(id);
	}

}