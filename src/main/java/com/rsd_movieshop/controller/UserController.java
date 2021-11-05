package com.rsd_movieshop.controller;

import com.rsd_movieshop.dto.UserDto;
import com.rsd_movieshop.model.User;
import com.rsd_movieshop.service.UserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> getUsers() {
        return null;
    }

    @GetMapping("/{userID}")
    public User getUser(@PathVariable int userID) {
        //Informationen zu spezifischem user
        return null;
    }

    @PostMapping
    public void addUser(@RequestBody UserDto userDto) {
    	User user =  new User();
    	user.setEmail(userDto.email);
    	user.setFamilyName(userDto.familyName);
    	user.setFirstName(userDto.firstName);
    	user.setUserName(userDto.userName);
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.password);
        user.setPassword(encodedPassword);
        userService.saveUser(user);
    }

    @PutMapping("/{userID}")
    public void updateUser(@PathVariable int userID) {
        //update user data
    }
    @DeleteMapping("/{userID}")
    public void deleteUser(@PathVariable int userID) {
        userService.deleteUser(userID);
    }
}
