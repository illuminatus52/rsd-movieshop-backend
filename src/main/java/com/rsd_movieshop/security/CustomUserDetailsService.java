package com.rsd_movieshop.security;

import com.rsd_movieshop.model.User;
import com.rsd_movieshop.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepo.findByUserName(s);
        if (null == user) {
            throw new UsernameNotFoundException(s);
        }
        return new CustomerUserDetails(user);
    }
}
