package com.receipe_rest_api.receipe_api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.receipe_rest_api.receipe_api.entity.Category;
import com.receipe_rest_api.receipe_api.repository.CategoryRepo;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
	
	@Mock
	private CategoryRepo categoryRepository;
	
	@InjectMocks
	private CategoryService categoryService;
	
	private Category categoryOne;
	private Category categoryTwo;
	
	//save get delete
	
	@BeforeEach
	public void setUp() {
		categoryOne=new Category();
		categoryOne.setName("Veg");
		
		categoryTwo=new Category();
		categoryTwo.setName("Non veg");
	}
	
	@Test
	public void testSaveCategory() {
		when(categoryRepository.save(categoryOne)).thenReturn(categoryOne);
		
		Category category = categoryService.saveCategory(categoryOne);
		
		assertThat(category).isNotNull();
		assertThat(category.getName()).isEqualTo("Veg");
		verify(categoryRepository,times(1)).save(categoryOne);
	}
	
	@Test
	public void testGetAllCategory() {
		when(categoryRepository.findAll()).thenReturn(Arrays.asList(categoryOne,categoryTwo));
		
		List<Category> categorys=categoryService.getAllCategories();
		
		assertThat(categorys).isNotNull();
		assertThat(categorys).hasSize(2);
		verify(categoryRepository,times(1)).findAll();
	}
	
	@Test
	public void testFindCategoryById() {
		when(categoryRepository.findById(2L)).thenReturn(Optional.of(categoryTwo));
		
		Category category = categoryService.getCategory(2L);
		assertThat(category).isNotNull();
		assertThat(category.getName()).isEqualTo("Non veg");
		verify(categoryRepository,times(1)).findById(2L);
	}
	
	@Test
	public void testDeleteCategory() {
		doNothing().when(categoryRepository).deleteById(1L);
		
		categoryService.deleteById(1L);
		verify(categoryRepository,times(1)).deleteById(1L);;

	}

}
