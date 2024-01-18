package com.filiptomanec.shiftcalendarbe.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateRequest {

	@NotEmpty(message = "Username cannot be empty")
	private String username;

	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Email should be valid")
	private String email;

	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 6, message = "Password should have at least 6 characters")
	private String password;
}
