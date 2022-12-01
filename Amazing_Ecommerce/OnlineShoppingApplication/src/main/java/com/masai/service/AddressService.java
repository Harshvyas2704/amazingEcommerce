package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exception.AddressException;
import com.masai.model.Address;

@Service
public interface AddressService {
	
	public Address addAddress(Address add) throws AddressException;
	
	public Address updateAddress(Address add) throws AddressException;
	
	public Address removeAddress(Integer id) throws AddressException;
	
	public Address viewAddress(Integer addressId) throws AddressException;
	
	public List<Address> viewAllAddress() throws AddressException;

}
