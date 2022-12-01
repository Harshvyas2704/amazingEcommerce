package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.UserOrder;
@Repository
public interface OrderDao extends JpaRepository<UserOrder, Integer>{


	public List<UserOrder> findAll();
	
}
