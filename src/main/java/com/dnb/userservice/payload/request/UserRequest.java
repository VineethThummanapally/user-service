package com.dnb.userservice.payload.request;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

	@NotBlank(message = "E-Mail should be provided")
	@Column(unique = true)
	private String emailId;

	@Column(nullable = false)
	@NotBlank(message = "Username must be provided")
	private String userName;

	@NotBlank
	@Length(min = 8, max = 16, message = "password must contain 8-16 characters")
	private String password;
}