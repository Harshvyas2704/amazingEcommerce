package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.exception.ProductException;
import com.masai.model.Cart;
import com.masai.model.Customer;
import com.masai.model.Products;
import com.masai.model.User;
import com.masai.repository.CartDao;
import com.masai.repository.CurrentUserSessionDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.ProductsDao;
import com.masai.repository.UserDao;

@Service
public class CartServiceImpl implements CartService {

	
	@Autowired
	private ProductsDao pDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private CustomerDao custDao;
	
	// to add product in cart
	@Override
	public Cart AddProduct(Cart cart, Integer Productid, Integer customerId) throws CustomerException, ProductException {
		
		Optional<Products> opt=pDao.findById(Productid);
		Optional<Customer> customer=custDao.findById(customerId);
	
		if(customer.isEmpty()) {
			
			throw new CustomerException("customer not found");
			
		}

		User user=  userDao.findByMobile(customer.get().getMobileNumber());
		
		String logedinOrNot = currentUserSessionDao.findByUserId(user.getUserId());
		
		if(logedinOrNot==null) {
			
			throw new CustomerException("Customer not logged in");
		}
				
		
		if(opt.isPresent()) {
		
			Products prod=opt.get();
			Customer cust=customer.get();
			
			cart.setCartItem(prod);
			cart.setCustomerList(cust);
	
			return cartDao.save(cart);
			
		}else {
			throw new ProductException("Product not available");
		}
		
	}

	// delete product from cart
	@Override
	public String deleteProductfromCart(Integer id) throws ProductException {
		
		Optional<Cart> opt = cartDao.findById(id);
		
		if(opt.isPresent()) {
			
			Cart cart = opt.get();
			cartDao.delete(cart);
			
			return "Product deleted from cart...!";
			
		}
		else {
			
			throw new ProductException("product not found with given id " + id);
			
		}
		
	}

	
	// delete all product from cart
	@Override
	public void deleteAllCart() {
		
		cartDao.deleteAll();
		
	}

	
	
	// view all product from cart
	@Override
	public List<Cart> ViewAllCart() {
		// TODO Auto-generated method stub
		List<Cart> list=cartDao.findAll();
		
		
		return list;
	}

}
