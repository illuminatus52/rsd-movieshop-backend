package com.rsd_movieshop.service;

import com.rsd_movieshop.dto.UserDto;
import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.CartItem;
import com.rsd_movieshop.model.User;
import com.rsd_movieshop.repository.CartRepo;
import com.rsd_movieshop.repository.UserRepo;
import com.rsd_movieshop.responseModels.CartResponse;
import com.rsd_movieshop.responseModels.UserResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	private final UserRepo userRepo;
	private final CartRepo cartRepo;

	public UserService(UserRepo userRepo, CartRepo cartRepo) {
		super();
		this.userRepo = userRepo;
		this.cartRepo = cartRepo;
	}

	public ResponseEntity<UserResponse> findUserById(long id) {
		if (id <= 0) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}
		if (userRepo.findByUserId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user with id: " + id + " doesn't exist!");
		} else {
			try {
				UserResponse userResponse = getUserResponse(userRepo.findByUserId(id));
				return new ResponseEntity<>(userResponse, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<UserResponse> findUserByUsername(String username) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"The user with the username: " + username + " doesn't exist!");
		}
		try {
			UserResponse userResponse = getUserResponse(user);
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<List<UserResponse>> findAllUser() {
		try {
			List<User> users = userRepo.findAll();
			List<UserResponse> userResponses = new ArrayList<>();
			for (User user : users) {
				UserResponse userResponse = getUserResponse(user);
				userResponses.add(userResponse);
			}
			return new ResponseEntity<>(userResponses, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<UserResponse> updateUser(String username, UserDto userDto) {
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
							throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
									"This username is not available!");
						}
					}
				}
				if (userDto.email != null || userDto.email.isEmpty()) {
					user.setEmail(userDto.email);
				}
				userRepo.save(user);
				UserResponse userResponse = getUserResponse(user);
				return new ResponseEntity<>(userResponse, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user  " + username + " doesn't exist!");
		}
	}

	// FIXME find user by username for save and update
	public ResponseEntity<UserResponse> saveUser(UserDto userDto) {

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
				UserResponse userResponse = getUserResponse(user);
				return new ResponseEntity<>(userResponse, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<UserResponse> changeRole(String username, String role) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user  " + username + " doesn't exist!");
		} else {
			try {
				user.setRole(role);
				userRepo.save(user);
				UserResponse userResponse = getUserResponse(user);
				return new ResponseEntity<>(userResponse, HttpStatus.OK);
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
		return new ResponseEntity<>("The user with the Id = " + id + " is deleted!", HttpStatus.OK);
	}

	public UserResponse getUserResponse(User user) {
		UserResponse userResponse = new UserResponse(user.getFirstName(), user.getFamilyName(), user.getEmail(),
				user.getRole(), null);
		Cart cart = cartRepo.findByCartId(user.getCart().getCartId());
		CartResponse cartResponse = new CartResponse();
		cartResponse.setCartId(cart.getCartId());
		List<String> items = new ArrayList<>();
		for (CartItem item : cart.getCartItems()) {
			items.add(item.getMovie().getTitle());
		}
		cartResponse.setItems(items);
		userResponse.setCartResponse(cartResponse);
		return userResponse;
	}
}