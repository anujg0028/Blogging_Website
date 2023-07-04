package com.anuj.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.anuj.blog.config.AppConstants;
import com.anuj.blog.entities.Role;
import com.anuj.blog.exceptions.ResourceNotFoundException;
import com.anuj.blog.repositories.RoleRepo;
import com.anuj.blog.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anuj.blog.entities.User;
import com.anuj.blog.payloads.UserDto;
import com.anuj.blog.services.UserService;

@Service
public class  UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto registerUser(UserDto userdto) {
		User user = this.modelMapper.map(userdto,User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.roleRepo.findById(AppConstants.ROLE_NORMAL).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		return this.modelMapper.map(newUser,UserDto.class);
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(this::userToDto).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		this.userRepo.delete(user);

	}

	public User dtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		//		user.setId(userDto.getId());
		//		user.setName(userDto.getName());
		//		user.setEmail(userDto.getEmail());
		//		user.setPassword(userDto.getPassword());
		//		user.setAbout(userDto.getAbout());
		return user;
	}

	public UserDto userToDto(User user) {

		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		//		userDto.setId(user.getId());
		//		userDto.setName(user.getName());
		//		userDto.setEmail(user.getEmail());
		//		userDto.setPassword(user.getPassword());
		//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
