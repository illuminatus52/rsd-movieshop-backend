package com.rsd_movieshop.controller;

import com.rsd_movieshop.dto.UserDto;
import com.rsd_movieshop.model.UserResponse;
import com.rsd_movieshop.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(path = "user/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable long id) {
		return userService.findUserById(id);
	}

	@GetMapping(path = "admin/all")
	public ResponseEntity<List<UserResponse>> getUsers() {
		return userService.findAllUser();
	}
	
	@GetMapping(path = "user/username/{username}")
	public ResponseEntity<UserResponse> getUserbyUsername(@PathVariable String username) {
		return userService.findUserByUsername(username);
	}

	@PostMapping(path = "register")
	public ResponseEntity<UserResponse> addUser(@RequestBody UserDto userDto) {
		return userService.saveUser(userDto);
	}

	@PutMapping(path = "user/{username}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
		return userService.updateUser(username, userDto);
	}
	
	@PutMapping(path = "admin/changeRole")
	public ResponseEntity<UserResponse> changeRole(String username, String role) {
		return userService.changeRole(username, role);
	}

	@DeleteMapping(path = "admin/user/{userID}")
	public ResponseEntity<String> deleteUser(@PathVariable int userID) {
		return userService.deleteUser(userID);
	}
}