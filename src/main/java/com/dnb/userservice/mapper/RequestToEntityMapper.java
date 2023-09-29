package com.dnb.userservice.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dnb.userservice.dto.User;
import com.dnb.userservice.payload.request.UserRequest;
import com.dnb.userservice.utils.Converters;

@Component
public class RequestToEntityMapper {

	@Autowired
	Converters converter;

	public User getUserEntityObject(UserRequest userRequest) {

		User user = new User();
		user.setUserName(userRequest.getUserName());
		user.setPassword(userRequest.getPassword());
		user.setEmailId(userRequest.getEmailId());

		return user;
	}
}
