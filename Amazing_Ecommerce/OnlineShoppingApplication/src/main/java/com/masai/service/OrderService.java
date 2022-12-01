package com.masai.service;

import java.util.List;

import com.masai.exception.CustomerException;
import com.masai.model.UserOrder;

public interface OrderService {
	
	public List<UserOrder>viewOrder();
	
	public UserOrder viewOrderByCustomerId(Integer custiomerId) throws CustomerException;
	
	public List<UserOrder>findOrderByUserName(String FirstName, String LastName, String mobileNo) throws CustomerException;
	
	public UserOrder updateOrder(Integer custiomerId, UserOrder order) throws CustomerException;
	
	public String removeOrder(Integer custiomerId) throws CustomerException;
	
	public UserOrder addorderFromCart(Integer customerId) throws CustomerException;

}
