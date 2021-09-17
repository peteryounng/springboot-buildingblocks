package com.stacksimplify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.entities.Order;


@Repository
public interface OrderRepository extends  JpaRepository<Order, Long>{
	
}
