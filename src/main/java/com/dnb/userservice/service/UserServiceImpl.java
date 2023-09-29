package com.dnb.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.userservice.dto.User;
import com.dnb.userservice.exceptions.IdNotFoundException;
import com.dnb.userservice.exceptions.UniqueEmailIdException;
import com.dnb.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user) throws UniqueEmailIdException {
		Optional<User> optional = userRepository.findByEmailId(user.getEmailId());
		if (optional.isPresent())
			throw new UniqueEmailIdException("Email Id already Exists");
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(String emailId) {
		return userRepository.findById(emailId);
	}

	@Override
	public boolean deleteUserById(String emaiId) throws IdNotFoundException {
		boolean isExists = userRepository.existsById(emaiId);
		if (!isExists) {
			throw new IdNotFoundException("Email-Id Not Found..");
		}
		userRepository.deleteById(emaiId);

		if (userRepository.existsById(emaiId))
			return false;
		else
			return true;
	}

	@Override
	public boolean checkUserExistsById(String userId) {
		return userRepository.existsById(userId);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

}