package com.masai.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.masai.exception.UserExistException;
import com.masai.model.CurrentUserSession;
import com.masai.model.User;
import com.masai.repository.CurrentUserSessionDao;
import com.masai.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao uDao;
	
	@Autowired
	private CurrentUserSessionDao cuserDao;

	@Override
	public User saveUer(User user) throws UserExistException {		
		User u = uDao.findByMobile(user.getMobile());
		
		if(u==null) return uDao.save(user);
		
		else throw new UserExistException("User Already Exists");
	}

	@Override
	public User updateUserCredential(User user, String key) throws UserExistException {
		CurrentUserSession cuser =  cuserDao.findByUniqueId(key);
		if(cuser==null) throw new UserExistException("user not loged in");
		Optional<User> opt = uDao.findById(cuser.getUserId());
		if(opt.isEmpty()) throw new UserExistException("user not found");
		User userWithSameNumber = uDao.findByMobile(user.getMobile());
		if(userWithSameNumber!=null) throw new UserExistException("one user find with the same mobile number");
		User saveUser = uDao.save(user);
		cuserDao.delete(cuser);
		uDao.delete(opt.get());
		return saveUser;
	}

	@Override
	public String userLogout(String key) throws UserExistException {
		CurrentUserSession cuser =  cuserDao.findByUniqueId(key);
		if(cuser==null) throw new UserExistException("user not loged in");
		cuserDao.delete(cuser);
		return "Logged Out successfully";
	}


	
}
