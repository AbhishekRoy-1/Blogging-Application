package com.roy.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
@EnableWebMvc
public class SpringFoxConfig {
	@Bean
	OpenAPI baseOpenAPI() {
		return new OpenAPI().info(new Info().title("Blogging Application")
				.description("This is a Blogging app Api with all functionality from Authentication to posting Blogs and Images.")
				.contact(getContact())
				.version("1.0.0"));
			
	}

	private Contact getContact() {
		// TODO Auto-generated method stub
		return new Contact().name("Abhishek Roy").email("imroy@gmail.com")
				.url("https://github.com/AbhishekRoy-1");
	}

}
