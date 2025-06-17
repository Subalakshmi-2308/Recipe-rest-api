package com.receipe_rest_api.receipe_api.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.receipe_rest_api.receipe_api.entity.Ingredient;
import com.receipe_rest_api.receipe_api.repository.IngredientRepo;
@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {

	@Mock
	private IngredientRepo ingredientRepository;
	
	@InjectMocks
	private IngredientService ingredientService;
	
	
	private Ingredient ingredientOne;
	
	@BeforeEach
	public void setUp() {
		ingredientOne=new Ingredient();
		ingredientOne.setName("Mutton");
		ingredientOne.setQuantity(1);
	}
	
	@Test
	public void testSaveIngredient() {
		when(ingredientRepository.save(ingredientOne)).thenReturn(ingredientOne);
		
		Ingredient ingredient=ingredientService.saveIngredient(ingredientOne);
		
		assertThat(ingredient).isNotNull();
		assertThat(ingredient.getName()).isEqualTo("Mutton");
		verify(ingredientRepository,times(1)).save(ingredientOne);
	}
	
	@Test
	public void testGetById() {
		when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredientOne));
		
		Ingredient ingredient=ingredientService.getIngredient(1L);
		
		assertThat(ingredient).isNotNull();
		assertThat(ingredient.getName()).isEqualTo("Mutton");
		verify(ingredientRepository,times(1)).findById(1L);

	}
	
	@Test
	public void testGetAllIngredients() {
		when(ingredientRepository.findAll()).thenReturn(Arrays.asList(ingredientOne));
		
		List<Ingredient> ingredients=ingredientService.all();
		assertThat(ingredients).isNotNull();
		assertThat(ingredients).hasSize(1);
		verify(ingredientRepository,times(1)).findAll();

	}

}
