package com.anuj.blog.services;

import java.util.List;

import com.anuj.blog.payloads.UserDto;

public interface UserService {

	UserDto registerUser(UserDto user);

	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);

}
