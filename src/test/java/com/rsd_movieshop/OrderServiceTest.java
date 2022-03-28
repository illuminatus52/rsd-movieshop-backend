package com.rsd_movieshop;

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
import com.rsd_movieshop.model.Orders;
import com.rsd_movieshop.repository.CartRepo;
import com.rsd_movieshop.repository.OrderRepo;
import com.rsd_movieshop.service.CartService;
import com.rsd_movieshop.service.MovieService;
import com.rsd_movieshop.service.OrderService;
import com.rsd_movieshop.service.UserService;

import static org.junit.Assert.*;

@SpringBootTest
@ActiveProfiles("h2")
@Transactional
public class OrderServiceTest {

	
	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private OrderService orderService;

	private final Movie movie01 = new Movie(2000, 2, "Movie-01", null, null, 4.99);
	
	
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
	public void createNewOrder() {
		CartItemRequest request = new CartItemRequest(1L, 1);
		cartService.updateCart(1, "username1", request);
		orderService.createOrderFromCart(1, "username1");
		List<Orders> orders = orderRepo.findAll();
		List<Cart> list = cartRepo.findAll();
		assertNotEquals(orders.size(), 0);
		assertTrue(orders.get(0).getTotalPrice() == 4.99);
		assertEquals(list.get(0).getCartItems().size(), 0);
		
	}
}