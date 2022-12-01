package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AddressException;
import com.masai.model.Address;
import com.masai.repository.AddressDao;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDao aRepo;

	
	// to add address 	
	@Override
	public Address addAddress(Address add) throws AddressException {
		
		Address address = aRepo.save(add);
		
		if(address != null) {
			
			return address;
			
		}
		
		else {
			throw new AddressException("address not added...");
		}
		
	}

	
	// to update address
	@Override
	public Address updateAddress(Address add) throws AddressException {
		
		Optional<Address> opt = aRepo.findById(add.getAddressId());
		
		if(opt.isPresent()) {
			
			Address address = aRepo.save(add);
			return  address;
			
		}
		else {
			
			throw new AddressException("address not found...");
			
		}
		
	}

	
	// to remove address
	@Override
	public Address removeAddress(Integer id) throws AddressException {
		
		Address address = aRepo.findByAddressId(id);
		
		if(address != null) {
			
			aRepo.delete(address);
			return address;
			
		}
		
		else {
			
			throw new AddressException("address not found to delete...");
			
		}
	}

	
	// to view address on particular Id
	@Override
	public Address viewAddress(Integer addressId) throws AddressException {
		
		Address address = aRepo.findByAddressId(addressId);
		
		if(address != null) {
			return address;
		}
		
		else {
			throw new AddressException("No address exist in this Id " + addressId);
		}
		
	}

	
	// to get all addresses
	@Override
	public List<Address> viewAllAddress() throws AddressException {
		
		List<Address> addressList = aRepo.findAll();
		
		if(addressList.size() != 0) {
			return addressList;
		}
		
		else {
			throw new AddressException("No address found...");
		}
		
		
	}

}
