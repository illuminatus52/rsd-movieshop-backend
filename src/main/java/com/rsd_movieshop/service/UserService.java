package com.rsd_movieshop.service;

import com.rsd_movieshop.dto.UserDto;
import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.CartItem;
import com.rsd_movieshop.model.UpdateUserRequest;
import com.rsd_movieshop.model.User;
import com.rsd_movieshop.repository.CartRepo;
import com.rsd_movieshop.repository.UserRepo;
import com.rsd_movieshop.responseModels.CartItemResponse;
import com.rsd_movieshop.responseModels.CartResponse;
import com.rsd_movieshop.responseModels.UserResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

@Service
public class UserService {

	private final UserRepo userRepo;
	private final CartRepo cartRepo;

	@Value("${saved.photos.path}")
	private String photosPath;


	public UserService(UserRepo userRepo, CartRepo cartRepo) {
		super();
		this.userRepo = userRepo;
		this.cartRepo = cartRepo;
	}

	public ResponseEntity<UserResponse> findUserById(long id, String username) {
		if (id <= 0) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}

		if (userRepo.findByUserId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user with id: " + id + " doesn't exist!");
		} else {
			User user = userCheck(username, id);
			try {
				UserResponse userResponse = getUserResponse(user);
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

	public ResponseEntity<UserResponse> updateImg(String username, long id, MultipartFile imgDto) {
		User user = userCheck(username, id);
		String uploadFilePath = photosPath + id + ".jpg";

		try {
			byte[] bytes = imgDto.getBytes();
			Path path = Paths.get(uploadFilePath);
			Files.write(path, bytes);
			user.setPicture(uploadFilePath);
			userRepo.save(user);
			UserResponse userResponse = getUserResponse(user);
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	public ResponseEntity<UserResponse> updateUser(String username, UserDto userDto) {
		if (userRepo.findByUsername(username) != null) {
			try {
				User user = userRepo.findByUsername(username);

				if (userDto.firstName != null && !userDto.firstName.isEmpty()) {
					user.setFirstName(userDto.firstName);
				}
				if (userDto.lastName != null && !userDto.lastName.isEmpty()) {
					user.setFamilyName(userDto.lastName);
				}
				if (userDto.email != null && !userDto.email.isEmpty()) {
					user.setEmail(userDto.email);
				}
				if (userDto.password != null && !userDto.password.isEmpty() && !userDto.password.isBlank()) {
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					String encodedPassword = passwordEncoder.encode(userDto.password);
					user.setPassword(encodedPassword);
				}
				if (userDto.shippingAddress != null && !userDto.shippingAddress.isEmpty()) {
					user.setShippingAddress(userDto.shippingAddress);
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

	public ResponseEntity<UserResponse> saveUser(UserDto userDto) {
		if (userDto.email == null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}

		if (userDto.lastName == null) {
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
				if (userRepo.findByUsername(userDto.userName) != null) {
					throw new Exception("Username: " + userDto.userName + " is not available!");
				}
				if (userRepo.findByEmail(userDto.email) != null) {
					throw new Exception("E-mail: " + userDto.email + " is not available!");
				}
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
			}

			try {
				User user = new User();
				user.setEmail(userDto.email);
				user.setFamilyName(userDto.lastName);
				user.setFirstName(userDto.firstName);
				user.setUsername(userDto.userName);
				user.setShippingAddress(userDto.shippingAddress);
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

	public ResponseEntity<UserResponse> updateUserAsAdmin(UpdateUserRequest request) {
		User user = userRepo.findByUsername(request.getUsername());

		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"The user  " + request.getUsername() + " doesn't exist!");
		} else {
			try {
				if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
					user.setFirstName(request.getFirstName());
				}
				if (request.getLastName() != null && !request.getLastName().isEmpty()) {
					user.setFamilyName(request.getLastName());
				}
				if (request.getUsername() != null && !request.getUsername().isEmpty()) {
					user.setUsername(request.getUsername());
				}
				if (request.getEmail() != null && !request.getEmail().isEmpty()) {
					user.setEmail(request.getEmail());
				}
				if (request.getPassword() != null && !request.getPassword().isEmpty()) {
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					String encodedPassword = passwordEncoder.encode(request.getPassword());
					user.setPassword(encodedPassword);
				}
				if (request.getRole() != null) {
					user.setRole(request.getRole());
				}
				if (!request.isEnabled()) {
					user.setEnabled(false);
				} else {
					user.setEnabled(true);
				}
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

	public ResponseEntity<UserResponse> enableAndDisablebUser(String username, boolean isEnable) {
		if (userRepo.findByUsername(username) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "there is no user with the username: " + username);
		} else {
			User user = userRepo.findByUsername(username);
			user.setEnabled(isEnable);
			userRepo.save(user);
			UserResponse userResponse = getUserResponse(user);
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		}
	}

	public @ResponseBody byte[] getImg(String username, long id) {
		User user = userCheck(username, id);
		
		try {
			BufferedImage originalImage = ImageIO.read(new File(photosPath + user.getUserId() + ".jpg"));
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

			BufferedImage resizeImageBmp = resizeImage(originalImage, type);
			ImageIO.write(resizeImageBmp, "jpg", new File(photosPath + user.getUserId() + ".jpg"));

			File file = new File(photosPath + user.getUserId() + ".jpg");
			FileInputStream inputStream = new FileInputStream(file);
			return IOUtils.toByteArray(inputStream);
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	public UserResponse getUserResponse(User user) {
		UserResponse userResponse = new UserResponse(user.getUserId(), user.getUsername(), user.getFirstName(),
				user.getFamilyName(), user.getEmail(), user.getRole(), null, user.isEnabled(), user.getPicture(),
				user.getShippingAddress());

		Cart cart = cartRepo.findByCartId(user.getCart().getCartId());
		CartResponse cartResponse = new CartResponse();
		cartResponse.setCartId(cart.getCartId());
		List<CartItemResponse> items = new ArrayList<>();

		for (CartItem item : cart.getCartItems()) {
			CartItemResponse itemResponse = new CartItemResponse(item.getMovie().getTitle(), item.getQuantity());
			items.add(itemResponse);
		}
		cartResponse.setItems(items);
		userResponse.setCart(cartResponse);

		return userResponse;
	}

	private BufferedImage resizeImage(BufferedImage originalImage, int type) {
		int IMG_WIDTH = 512;
		int IMG_CLAHEIGHT = 512;
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_CLAHEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_CLAHEIGHT, null);
		g.dispose();
		return resizedImage;
	}

	public User userCheck(String username, long id) {
		User user = userRepo.findByUsername(username);

		if (user != userRepo.findByUserId(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			return user;
		}
	}
}