package com.rsd_movieshop.controller;

import com.rsd_movieshop.dto.UserDto;
import com.rsd_movieshop.model.User;
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
	public ResponseEntity<User> getUser(@PathVariable long id) {
		return userService.findUserById(id);
	}

	@GetMapping(path = "admin/all")
	public ResponseEntity<List<User>> getUsers() {
		return userService.findAllUser();
	}
	
	@GetMapping(path = "user/username/{username}")
	public ResponseEntity<User> getUserbyUsername(@PathVariable String username) {
		return userService.findUserByUsername(username);
	}

	@PostMapping(path = "register")
	public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
		return userService.saveUser(userDto);
	}

	@PutMapping(path = "user/{username}")
	public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
		return userService.updateUser(username, userDto);
	}
	
	@PutMapping(path = "admin/changeRole")
	public ResponseEntity<User> changeRole(String username, String role) {
		return userService.changeRole(username, role);
	}

	@DeleteMapping(path = "admin/user/{userID}")
	public ResponseEntity<String> deleteUser(@PathVariable int userID) {
		return userService.deleteUser(userID);
	}
}