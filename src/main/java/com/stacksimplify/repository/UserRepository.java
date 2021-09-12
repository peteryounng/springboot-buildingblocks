package com.stacksimplify.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.entities.User;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{
	
	User findByUsername(String username);
	
	
}
