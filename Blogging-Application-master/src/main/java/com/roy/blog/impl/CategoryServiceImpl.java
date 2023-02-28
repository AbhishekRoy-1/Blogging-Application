package com.roy.blog.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roy.blog.entities.Category;
import com.roy.blog.exceptions.ResourceNotFoundException;
import com.roy.blog.payloads.CategoryDto;
import com.roy.blog.repositories.CategoryRepo;
import com.roy.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category= this.modelMapper.map(categoryDto, Category.class);
		Category addedCategory=this.categoryRepo.save(category);
		return this.modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category= this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		Category updatedCategory=this.categoryRepo.save(category);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category= this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepo.delete(category);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		return this.modelMapper.map(category, CategoryDto.class);
		
		
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories= this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos=categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}
	
	

}
