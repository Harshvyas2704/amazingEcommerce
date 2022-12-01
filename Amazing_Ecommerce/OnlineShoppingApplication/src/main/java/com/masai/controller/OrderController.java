package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CustomerException;
import com.masai.model.Address;
import com.masai.model.UserOrder;
import com.masai.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderservice;
	
//	@PostMapping("/{customerId}/{addressId}")
//	public ResponseEntity<MyOrder>addorder(@RequestBody MyOrder order,@PathVariable Integer customerId,@PathVariable Integer addressId){
//		MyOrder uporder= orderservice.addOrder(order, customerId, addressId);
//		return new ResponseEntity<MyOrder>(uporder,HttpStatus.ACCEPTED);
//	}
	
	@PostMapping("/addFromCart/{customerId}")
	public ResponseEntity<UserOrder>addOrderFromCart(@PathVariable Integer customerId) throws CustomerException{
		UserOrder myOrder=orderservice.addorderFromCart(customerId);
		return new ResponseEntity<UserOrder>(myOrder,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/findallOrder")
	public ResponseEntity<List<UserOrder>>viewAllOrder(){
		List<UserOrder>allOrder= orderservice.viewOrder();
		return new ResponseEntity<List<UserOrder>>(allOrder,HttpStatus.ACCEPTED);
	}

	@GetMapping("/findByCustomerId/{customerId}")
	public ResponseEntity<UserOrder>viewOrderByCustomerID(@PathVariable Integer customerId) throws CustomerException{
		UserOrder findOrder=orderservice.viewOrderByCustomerId(customerId);
		return new ResponseEntity<UserOrder>(findOrder,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/findOrderByUserName/{FirstName}/{LastName}/{mobileNo}")
	public ResponseEntity<List<UserOrder>>viewByUserName(@PathVariable("FirstName") String FirstName,@PathVariable("LastName") String LastName,@PathVariable("mobileNo") String mobileNo ) throws CustomerException{
		List<UserOrder>getOrderByName= orderservice.findOrderByUserName(FirstName, LastName,mobileNo);
		return new ResponseEntity<List<UserOrder>>(getOrderByName,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("removeOrder/{customerId}")
	public ResponseEntity<String>removeOrderByCustomerID(@PathVariable Integer customerId) throws CustomerException{
		String findOrder=orderservice.removeOrder(customerId);
		
		return new ResponseEntity<String>(findOrder,HttpStatus.ACCEPTED);
	}
}
