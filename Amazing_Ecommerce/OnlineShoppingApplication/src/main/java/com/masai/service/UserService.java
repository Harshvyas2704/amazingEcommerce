package com.masai.service;

import com.masai.exception.UserExistException;
import com.masai.model.User;

public interface UserService {
	
	public User saveUer(User user) throws UserExistException;
	
	public User updateUserCredential(User user,String key) throws UserExistException;
	
	public String userLogout(String key) throws UserExistException;

}
