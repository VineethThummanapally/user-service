package com.dnb.userservice.service;

import java.util.Optional;

import com.dnb.userservice.dto.User;
import com.dnb.userservice.exceptions.IdNotFoundException;
import com.dnb.userservice.exceptions.UniqueEmailIdException;

public interface UserService {
	public User createUser(User user) throws UniqueEmailIdException;

	public Optional<User> getUserById(String emaiId);

	public boolean deleteUserById(String emaiId) throws IdNotFoundException;

	public boolean checkUserExistsById(String userId);

	public Iterable<User> getAllUsers();
}
