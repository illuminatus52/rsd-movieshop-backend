package com.rsd_movieshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rsd_movieshop.dto.ImgDto;
import com.rsd_movieshop.responseModels.UserResponse;
import com.rsd_movieshop.service.UserService;

@Controller
@RequestMapping(path = "/api/")
public class ImgController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "user/{username}/img/{id}")
	public ResponseEntity<UserResponse> updateImg(@ModelAttribute ImgDto imgDto, @PathVariable String username,
			@PathVariable long id) {
		return userService.updateImg(username, id, imgDto);
	}
}
