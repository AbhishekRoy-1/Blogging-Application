package com.roy.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 10)
	private String categoryDescription;
}
