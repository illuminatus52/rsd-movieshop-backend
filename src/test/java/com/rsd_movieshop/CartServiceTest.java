package com.rsd_movieshop;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.rsd_movieshop.dto.UserDto;
import com.rsd_movieshop.model.Cart;
import com.rsd_movieshop.model.CartItemRequest;
import com.rsd_movieshop.model.Genre;
import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.repository.CartRepo;
import com.rsd_movieshop.repository.UserRepo;
import com.rsd_movieshop.service.CartService;
import com.rsd_movieshop.service.MovieService;
import com.rsd_movieshop.service.UserService;

@SpringBootTest
@ActiveProfiles("h2")
@Transactional
public class CartServiceTest {

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	@Autowired
	private MovieService movieService;

	private Movie movie01 = new Movie(2000, 2, "Movie-01", null, null, 4.6);

	@BeforeEach
	public void init() {

		UserDto userDto1 = new UserDto("Last1", "First1", "email1@gmail.com", "username1", "123456", "picture", "Address");
		userService.saveUser(userDto1);

		Genre genre01 = new Genre("Genre-01");
		Genre genre02 = new Genre("Genre-02");
		List<Genre> genres01 = new ArrayList<>();
		genres01.add(genre01);
		genres01.add(genre02);
		movie01.setGenres(genres01);
		movieService.saveNewMovie(movie01);
	}

	@Test
	public void GetCartTest() {
		Cart cart = cartRepo.findByCartId(1);
		assertTrue(cart != null);
		userRepo.deleteAll();
	}

	@Test
	public void addItemTest() {
		CartItemRequest request = new CartItemRequest(2L, 1);
		cartService.addCartItem(2, "username1", request);
		Cart cart = cartRepo.findByCartId(2);
		assertTrue(cart.getCartItems().get(0).getMovie() == movie01);

	}
	
	@Test
	public void deleteItem() {
		CartItemRequest request = new CartItemRequest(3L, 1);
		cartService.addCartItem(3, "username1", request);
		Cart cart = cartRepo.findByCartId(3);
		assertTrue(cart.getCartItems().get(0).getMovie() == movie01);
		cartService.deleteItem(3, "username1", 2);
		assertTrue(cart.getCartItems().size() == 0);
	}
}
