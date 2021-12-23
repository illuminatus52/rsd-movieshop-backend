package com.rsd_movieshop.service;

import com.rsd_movieshop.dto.UserDto;
import com.rsd_movieshop.model.User;
import com.rsd_movieshop.repository.UserRepo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

	private final UserRepo userRepo;

	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public ResponseEntity<User> findUserById(long id) {
		if (id <= 0) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}
		if (userRepo.findByUserId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user with id: " + id + " doesn't exist!");
		} else {
			try {
				User user = userRepo.findByUserId(id);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<User> findUserByUsername(String username) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"The user with the username: " + username + " doesn't exist!");
		}
		try {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<List<User>> findAllUser() {
		try {
			List<User> users = userRepo.findAll();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<User> updateUser(String username, UserDto userDto) {
		if (userRepo.findByUsername(username) != null) {
			try {
				User user = userRepo.findByUsername(username);
				if (userDto.firstName != null || userDto.firstName.isEmpty()) {
					user.setFirstName(userDto.firstName);
				}
				if (userDto.familyName != null || userDto.familyName.isEmpty()) {
					user.setFamilyName(userDto.familyName);
				}
				if (userDto.userName != null || userDto.userName.isEmpty()) {
					if (!username.equalsIgnoreCase(userDto.userName)) {
						if (userRepo.findByUsername(userDto.userName) == null) {
							user.setUsername(userDto.userName);
						} else {
							throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This username is not available!");
						}
					}
				}
				if (userDto.email != null || userDto.email.isEmpty()) {
					user.setEmail(userDto.email);
				}
				userRepo.save(user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user  " + username + " doesn't exist!");
		}
	}

	// FIXME find user by username for save and update
	public ResponseEntity<User> saveUser(UserDto userDto) {

		if (userDto.email == null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}
		if (userDto.familyName == null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}
		if (userDto.firstName == null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}
		if (userDto.userName == null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}
		if (userDto.password == null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		} else {
			try {
				User user = new User();
				user.setEmail(userDto.email);
				user.setFamilyName(userDto.familyName);
				user.setFirstName(userDto.firstName);
				user.setUsername(userDto.userName);
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(userDto.password);
				user.setPassword(encodedPassword);
				userRepo.save(user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<User> changeRole(String username, String role) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user  " + username + " doesn't exist!");
		} else {
			try {
				user.setRole(role);
				userRepo.save(user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}

	}

	public ResponseEntity<String> deleteUser(long id) {
		if (userRepo.findByUserId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		userRepo.deleteById(id);
		return new ResponseEntity<String>("The user with the Id = " + id + " is deleted!", HttpStatus.OK);
	}

}
