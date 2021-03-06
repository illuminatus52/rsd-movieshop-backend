package com.rsd_movieshop.repository;

import com.rsd_movieshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);
	User findByUserId(long id);
	User findByCart_CartId(long id);
	User findByEmail(String email);
}