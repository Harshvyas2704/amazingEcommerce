package com.masai.service;

import com.masai.exception.UserExistException;
import com.masai.model.UserDTO;

public interface UserLoginService {
	
	public String userLogin(UserDTO userDto) throws UserExistException;

}
