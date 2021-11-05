package com.rsd_movieshop.service;

import com.rsd_movieshop.model.User;
import com.rsd_movieshop.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;


    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findUserById(long id){
        return userRepo.findById(id);
    }

    public List<User> findAllUser(){
        return userRepo.findAll();
    }

    public void saveUser(User user){
    	
        userRepo.save(user);
    }

    public void deleteUser(long id){
        userRepo.deleteById(id);
    }

}
