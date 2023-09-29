package com.dnb.userservice.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.userservice.dto.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	
	public Optional<User> findByEmailId(String emailId); 
}
