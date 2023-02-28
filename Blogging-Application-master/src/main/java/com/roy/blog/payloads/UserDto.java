package com.roy.blog.payloads;



import java.util.HashSet;
import java.util.Set;

import com.roy.blog.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {
	private int id;
	
	@NotEmpty
	private String name;
	
	@Email(message = "Email address is not Valid !")
	private String email;
	
	@NotEmpty
	@Size(min = 5, message = "Password must be min. of 5 charecters !")
	private String password;
	
	@NotEmpty
	private String about;

	private Role role;
	
	private Set<CommentDto> comments=new HashSet<>();

	

		

}
