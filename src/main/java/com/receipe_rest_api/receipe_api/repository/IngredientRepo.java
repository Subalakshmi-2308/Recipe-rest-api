package com.receipe_rest_api.receipe_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.receipe_rest_api.receipe_api.entity.Ingredient;

@Repository
public interface IngredientRepo  extends JpaRepository<Ingredient, Long> {

}
