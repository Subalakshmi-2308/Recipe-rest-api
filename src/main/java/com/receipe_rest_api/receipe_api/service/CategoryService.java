package com.receipe_rest_api.receipe_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.receipe_rest_api.receipe_api.entity.Category;
import com.receipe_rest_api.receipe_api.repository.CategoryRepo;

import jakarta.transaction.Transactional;

@Service

@Transactional
public class CategoryService {
	
	@Autowired

	private CategoryRepo cr;

	public Category saveCategory(Category category) {

		return cr.save(category);

	}

	public Category getCategory(Long id) {

		Category category = cr.findById(id).orElse(null);

        return category;

    }
	
	public List<Category> getAllCategories() {
		return cr.findAll();
		
	}
	
	public void updateCategory(Long id,Category c) {
		Optional<Category> oldc=cr.findById(id);
		
		if(!oldc.isEmpty()) {
			Category newc = oldc.get();
			newc.setName(c.getName());
			cr.save(newc);
		}
		
	}
	
	public void deleteById(Long id) {
		cr.deleteById(id);
	}
}

