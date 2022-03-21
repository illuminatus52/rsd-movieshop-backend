package com.rsd_movieshop.controller;

import com.rsd_movieshop.dto.UserDto;
import com.rsd_movieshop.model.UpdateUserRequest;
import com.rsd_movieshop.responseModels.UserResponse;
import com.rsd_movieshop.service.UserService;

import org.springframework.http.MediaType;
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

	@GetMapping(path = "user/{username}/user/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable String username, @PathVariable long id) {
		return userService.findUserById(id, username);
	}

	@GetMapping(path = "admin/all")
	public ResponseEntity<List<UserResponse>> getUsers() {
		return userService.findAllUser();
	}

	@GetMapping(path = "user/username/{username}")
	public ResponseEntity<UserResponse> getUserbyUsername(@PathVariable String username) {
		return userService.findUserByUsername(username);
	}

	@GetMapping(path = "user/{username}/userImg/{id}", produces = { MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public @ResponseBody byte[] getImg(@PathVariable String username, @PathVariable long id) {
		return userService.getImg(username, id);
	}

	@PostMapping(path = "register")
	public ResponseEntity<UserResponse> addUser(@RequestBody UserDto userDto) {
		return userService.saveUser(userDto);
	}

	@PutMapping(path = "user/{username}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
		return userService.updateUser(username, userDto);
	}

	@PutMapping(path = "admin/updateUsers")
	public ResponseEntity<UserResponse> updateUserAsAdmin(@RequestBody UpdateUserRequest request) {
		return userService.updateUserAsAdmin(request);
	}

	@PostMapping(path = "admin/{username}/")
	public ResponseEntity<UserResponse> enableAndDisableUser(@PathVariable String username,
			@RequestParam boolean isEnable) {
		return userService.enableAndDisablebUser(username, isEnable);
	}

	@DeleteMapping(path = "admin/user/{userID}")
	public ResponseEntity<String> deleteUser(@PathVariable int userID) {
		return userService.deleteUser(userID);
	}
}