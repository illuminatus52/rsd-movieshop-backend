package com.rsd_movieshop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rsd_movieshop.dto.UserDto;
import com.rsd_movieshop.model.User;
import com.rsd_movieshop.repository.UserRepo;
import com.rsd_movieshop.service.UserService;

@SpringBootTest
@Transactional
public class UserServiceTest {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@BeforeEach
	public void init() {

		UserDto userDto1 = new UserDto("Last1", "First1", "email1@gmail.com", "username1", "123456");
		UserDto userDto2 = new UserDto("Last2", "First2", "email2@gmail.com", "username2", "123455");
		UserDto userDto3 = new UserDto("Last3", "First3", "email3@gmail.com", "username3", "123445");

		userService.saveUser(userDto1);
		userService.saveUser(userDto2);
		userService.saveUser(userDto3);
	}

	@Test
	public void findAllUsers() {
		List<User> users = userRepo.findAll();
		User user = userRepo.findByUserId(10);
		User user2 = userRepo.findByUsername("username2");
		assertTrue(users.get(0).getUsername().equalsIgnoreCase("username1"));
		assertTrue(users.get(1).getUsername().equalsIgnoreCase("username2"));
		assertTrue(users.get(2).getUsername().equalsIgnoreCase("username3"));
		assertTrue(user.getEmail().equalsIgnoreCase("email1@gmail.com"));
		assertTrue(user2.getFirstName().equalsIgnoreCase("First2"));
	}

	@Test
	public void updateUser() {
		UserDto userDtoUpdate = new UserDto("LastName", "FirstName", "newEmail@gmail.com", "username1", null);
		userService.updateUser("username1", userDtoUpdate);
		User user = userRepo.findByUsername("username1");
		assertFalse(user.getEmail().equalsIgnoreCase("email1@gmail.com"));
		assertTrue(user.getFirstName().equalsIgnoreCase("FirstName"));
	}

	@Test
	public void changeRole() {
		userService.changeRole("username1", "ROLE_ADMIN");
		User user = userRepo.findByUsername("username1");
		assertTrue(user.getRole().equalsIgnoreCase("ROLE_ADMIN"));
	}

	@Test
	public void deleteUser() {
		userService.deleteUser(7);
		User user = userRepo.findByUserId(7);
		assertTrue(user == null);
	}
}