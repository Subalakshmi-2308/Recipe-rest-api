package com.receipe_rest_api.receipe_api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import java.util.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.receipe_rest_api.receipe_api.entity.Receipe;
import com.receipe_rest_api.receipe_api.repository.ReceipeRepo;

@ExtendWith(MockitoExtension.class)
class ReceipeServiceTest {
	
	@Mock
	private ReceipeRepo receiperepository;
	
	@InjectMocks
	private ReceipeService receipeService;
	
	private Receipe receipe;
	@BeforeEach
	public void setUp() {
		receipe = new Receipe();
		receipe.setName("Biryani");
		receipe.setDescription("Mutton Biryani");
		receipe.setTime(120);
	}
	
	@Test
	public void testSaveRecipe() {
		when(receiperepository.save(receipe)).thenReturn(receipe);
		
		Receipe savedReceipe=receipeService.saveRecipe(receipe);
		
		assertThat(savedReceipe).isNotNull();
		assertThat(savedReceipe.getName()).isEqualTo("Biryani");
		verify(receiperepository,times(1)).save(receipe);
	}
	
	@Test
	public void testAllReceipe() {
		when(receiperepository.findAll()).thenReturn(Arrays.asList(receipe));
		
		List<Receipe> allReceipes=receipeService.allReceipe();
		
		assertThat(allReceipes).hasSize(1);
		verify(receiperepository,times(1)).findAll();
	}
	
	@Test
	public void testGetReceipeByID() {
		when(receiperepository.findById(1L)).thenReturn(Optional.of(receipe));
		
		Receipe findReceipeById=receipeService.getReceipe(1L);
		
		assertThat(findReceipeById).isNotNull();
		assertThat(findReceipeById.getName()).isEqualTo("Biryani");
		verify(receiperepository,times(1)).findById(1L);
	}
	
	@Test
	public void testDeleteReceipeById() {
		doNothing().when(receiperepository).deleteById(1L);
		
		receipeService.deleteById(1L);
		
		verify(receiperepository,times(1)).deleteById(1L);
	}
	
	

}
