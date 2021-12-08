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
    

    public ResponseEntity<User> findUserById(long id){
    	if (id <= 0) {
    		throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
    	}
    	if (userRepo.getById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user with id: " + id + " doesn't existed!");
    	}else {
    		try {
    			User user = userRepo.getById(id);
    			return new ResponseEntity<User>(user, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
    	}
    }

    public ResponseEntity<List<User>> findAllUser(){
    	try {
    		List<User> users = userRepo.findAll();
    		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
    }

    public ResponseEntity<User> saveUser(UserDto userDto){
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
    			User user =  new User();
    			user.setEmail(userDto.email);
    			user.setFamilyName(userDto.familyName);
    			user.setFirstName(userDto.firstName);
    			user.setUserName(userDto.userName);
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

    public ResponseEntity<String> deleteUser(long id){
    	if (userRepo.getById(id) == null) {
    		throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
    	}
        userRepo.deleteById(id);
        return new ResponseEntity<String>("The user with the Id = " + id + " is deleted!", HttpStatus.OK);
    }
    
}
