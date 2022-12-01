package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.exception.ProductException;
import com.masai.model.Cart;

@Service
public interface CartService {
	
	//public Cart addProductToCart(Cart cart,String Name, Integer quantity);
	
	public Cart AddProduct(Cart cart,Integer Productid,Integer customerId) throws CustomerException, ProductException;
	
	//public Cart UpdateCartProduct(Cart cart);
	
	public String deleteProductfromCart(Integer id) throws ProductException;
	
	public void deleteAllCart();
	
	//public String DeleteALl();
	
	public List<Cart> ViewAllCart();

}
