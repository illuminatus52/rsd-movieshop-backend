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

	@GetMapping(path = "admin/all-users")
	public ResponseEntity<List<User>> getUsers() {
		return userService.findAllUser();
	}

	@GetMapping(path = "user/{userID}")
	public ResponseEntity<User> getUser(@PathVariable int userID) {
		return userService.findUserById(userID);
	}

	@PostMapping(path = "user/Register")
	public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
		return userService.saveUser(userDto);
	}

	@PutMapping(path = "user/{userID}")
	public ResponseEntity<User> updateUser(@PathVariable int userID, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String email, @RequestParam String password,
			@RequestParam String username) {

		UserDto user = new UserDto(lastName, firstName, email, username, password);

		return userService.saveUser(user);
	}

	@DeleteMapping("user/{userID}")
	public ResponseEntity<String> deleteUser(@PathVariable int userID) {
		return userService.deleteUser(userID);
	}
}