package com.roy.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.roy.blog.payloads.CategoryDto;

@Service
public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	void  deleteCategory(Integer categoryId);
	
	CategoryDto getCategory(Integer categoryId);
	
	List<CategoryDto> getAllCategories();


}
