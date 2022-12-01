package com.masai.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.masai.exception.AddressException;
import com.masai.exception.CustomerException;
import com.masai.model.Address;
import com.masai.model.Cart;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.UserOrder;
import com.masai.model.Products;
import com.masai.model.User;
import com.masai.repository.AddressDao;
import com.masai.repository.CartDao;
import com.masai.repository.CurrentUserSessionDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.OrderDao;
import com.masai.repository.ProductsDao;

import com.masai.repository.UserDao;




@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderdao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CurrentUserSessionDao currUserSessDao;
	
	@Autowired
	private	ProductsDao productDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	 private CartService cartService;

	//parameter customerId,addressId
//	@Override
//	public MyOrder addOrder(MyOrder order, Integer customerId, Integer addressId) {
//		
//		order.setLocaldtetime(LocalDateTime.now());
//		order.setOrderstatus("accepted");
//		Optional< Customer> databaseCustomer = customerDao.findById(customerId);
//		
//		if(databaseCustomer.isEmpty()) {
//			throw new CustomerNotFoundException("customer not found");
//		}
//		Customer customer = databaseCustomer.get();
//		User user=  userDao.findByMobile(databaseCustomer.get().getMobileNumber());
//		String logedinOrNot = currUserSessDao.findByUserId(user.getUserId());
//		if(logedinOrNot==null) {
//			throw new CustomerNotFoundException("Customer not logged in");
//		}
//		
//		List<Products> products = order.getProductlist();
//		List<Products> products2=new ArrayList<Products>();
//		for(Products p:products) {
//			
//			List<Products> pr = productDao.findByProductName(p.getProductName());
//			if(pr.size()<=0) {
//			
//				throw new CustomerNotFoundException("Product not found");
//			}
////			System.out.println(pr);
//			for(Products prd:pr) {
//				if(prd.getPrice().equals(p.getPrice())) {
//					products2.add(prd);
//				}
//				
//			}
//			
//			
//		}
//	
//		 order.setProductlist(products2);
//		
//		List<Address> customerAddr = customer.getAddresslist();
//		int count = 0;
//		for(Address addr: customerAddr) {
//			if(addr.getAddressId()==addressId) {
//				order.setAddress(addr);
//			}else count++;
//		}
//		if(count==customerAddr.size()) throw new AddressNotFound("Address not found with the customer Id"+customerId);
//		order.setCustomer(customer);	
//		return orderdao.save(order);
//	}

	@Override
	public List<UserOrder> viewOrder() {
		
		List<UserOrder> allOrder = orderdao.findAll();
		
		return allOrder;
		
	}

	@Override
	public UserOrder viewOrderByCustomerId(Integer custiomerId) throws CustomerException {
		
		List<UserOrder>allOrder = orderdao.findAll();
		
		for(UserOrder order:allOrder) {
			
			if(order.getCustomer().getCustomerId()==custiomerId) {
				
				return order;
			}
		}
		throw new CustomerException("Order not found");
	}

	@Override
	public List<UserOrder> findOrderByUserName(String FirstName, String LastName,String mobileNo) throws CustomerException {
		List<UserOrder>allOrder=orderdao.findAll();
		List<UserOrder>findAllByName=new ArrayList<UserOrder>();
		for(UserOrder order:allOrder) {
			if(order.getCustomer().getFirstName().equals(FirstName)&& order.getCustomer().getLastName().equals(LastName)&&order.getCustomer().getMobileNumber().equals(mobileNo) ) {
			findAllByName.add(order);
			}
			
		}
//		System.out.println(findAllByName);
		if(findAllByName.size()==0) {
			throw new CustomerException("Order not found");
		}else {
			return findAllByName;
		}
	}

	@Override
	public UserOrder updateOrder(Integer custiomerId, UserOrder order) throws CustomerException {
		
		List<UserOrder>allOrder=orderdao.findAll();
		
		int count=0;
		UserOrder findord=new UserOrder();
		
		for(UserOrder find:allOrder) {
			
			if(find.getCustomer().getCustomerId()==custiomerId) {
				
				findord=find;
				count++;
			}

		}
		if(count == 0) {
			throw new CustomerException("Order not found");
		}
	
		
		
		List<Products> products2=order.getProductlist();
		List<Products> products = findord.getProductlist();
		return findord;
		
		
	}

	@Override
	public String removeOrder(Integer custiomerId) throws CustomerException {
		
		List<UserOrder>allOrder=orderdao.findAll();
		
		
		for(UserOrder order:allOrder) {
			if(order.getCustomer().getCustomerId()==custiomerId) {
//			 orderdao.delete(order);
				
				User user=  userDao.findByMobile(order.getCustomer().getMobileNumber());
				String logedinOrNot = currUserSessDao.findByUserId(user.getUserId());
				if(logedinOrNot==null) {
					throw new CustomerException("Customer not logged in");
				}
				order.setCustomer(null);
				order.setProductlist(null);
				orderdao.save(order);
				System.out.println(order);
				orderdao.delete(order);
				
			 return "order canceled";
			}
		}
		
		throw new CustomerException("Order not found");
	}

	@Override
	public UserOrder addorderFromCart(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		
		UserOrder myOrder = new UserOrder();
		Cart cart=new Cart();
		
		List<Cart> allCartDetails = cartServiceImpl.ViewAllCart();
		
		List<Products>getProducts = new ArrayList<>();
		
		for(Cart newCart:allCartDetails) {
			
			if(newCart.getCustomerList().getCustomerId()==(customerId)) {
				getProducts.add(newCart.getCartItem());
				allCartDetails.remove(newCart.getCartItem());
				
			}
			
		}
		
		System.out.println(getProducts);
		
		myOrder.setLocaldatetime(LocalDateTime.now());
		
		myOrder.setOrderstatus("ORDER PLACED");
		
		Optional<Customer>opt=customerDao.findById(customerId);
		
		if(opt.isEmpty()) {
			throw new CustomerException("Customer not found with this Id"+customerId);
		}
		myOrder.setCustomer(opt.get());
		myOrder.setProductlist(getProducts);

		UserOrder myOrder2= orderdao.save(myOrder);
//		cartService.deleteAllCart();
		
		return myOrder2;
	}



	
}






















