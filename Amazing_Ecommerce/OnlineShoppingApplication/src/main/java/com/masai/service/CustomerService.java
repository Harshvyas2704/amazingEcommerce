package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.exception.UserExistException;
import com.masai.model.Address;
import com.masai.model.Customer;

@Service
public interface CustomerService {
	
	public Customer addCustomer(Customer customer) throws CustomerException, UserExistException;
	
	public Customer updateCustomer(Customer customer) throws CustomerException;
	
	public Customer removeCustomer(Customer customer) throws CustomerException;
	
	public Customer viewCustomer(Integer id);
	
	public List<Customer> viewAllCustomerByLocation(String location);
	
	public Customer addAddress(Address address,Integer customerId);

}
