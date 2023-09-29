package com.dnb.userservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.userservice.dto.User;
import com.dnb.userservice.exceptions.IdNotFoundException;
import com.dnb.userservice.mapper.RequestToEntityMapper;
import com.dnb.userservice.payload.request.UserRequest;
import com.dnb.userservice.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private RequestToEntityMapper mapper;

	@PostMapping("/create") // combination of @RequestMapping + PostMethod => spring 4.3.
	public ResponseEntity<?> creatUser(@Valid @RequestBody UserRequest userRequest) {

		User user1 = mapper.getUserEntityObject(userRequest);
		try {
			User user2 = userService.createUser(user1);
			return new ResponseEntity<User>(user2, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{userId}") // it should help us to get the specific account details
	public ResponseEntity<?> getUserById(@PathVariable("userId") String userId) throws IdNotFoundException {

		Optional<User> requestedUser = userService.getUserById(userId);
		if (requestedUser.isPresent())
			return ResponseEntity.ok(requestedUser.get());
		else {
			throw new IdNotFoundException("Requested Id Info Not found");
		}
	}

	@GetMapping("/allUsers")
	public ResponseEntity<?> getAllUsers() throws IdNotFoundException {
		List<User> allUsers = (List<User>) userService.getAllUsers();

		if (allUsers.size() != 0)
			return ResponseEntity.ok(allUsers);
		else {
			throw new IdNotFoundException("No Users Found ");
		}
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable String userId) throws IdNotFoundException {

		if (userService.checkUserExistsById(userId)) {
			userService.deleteUserById(userId);
			return ResponseEntity.noContent().build();
		} else {
			throw new IdNotFoundException("Id Not Found");
		}
	}

}
