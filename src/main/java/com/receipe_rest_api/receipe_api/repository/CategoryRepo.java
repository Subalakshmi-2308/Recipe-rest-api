package com.receipe_rest_api.receipe_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.receipe_rest_api.receipe_api.entity.Category;

@Repository
public interface CategoryRepo  extends JpaRepository<Category, Long> {

}
