package com.masai.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.UserExistException;
import com.masai.model.User;
import com.masai.model.UserDTO;
import com.masai.service.UserLoginService;
import com.masai.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService uService;
	
	@Autowired
	private UserLoginService ulogService;
	
	@PostMapping("/regisrtration")

	public ResponseEntity<User> saveUserController(@Valid @RequestBody User user) throws UserExistException {
		User responseUser =  uService.saveUer(user);
		return new  ResponseEntity<User>(responseUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> userLoginController(@Valid @RequestBody UserDTO userDto) throws UserExistException {
		String msg =  ulogService.userLogin(userDto);
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateUser/{key}")
	public ResponseEntity<User> updateUserCredentialController(@Valid @RequestBody User user, @PathVariable String key) throws UserExistException {
		User responseUser =  uService.updateUserCredential(user, key);
		return new ResponseEntity<User>(responseUser,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/logout/{key}")
	public ResponseEntity<String> userLogoutController(@Valid @PathVariable String key) throws UserExistException {
		String msg =  uService.userLogout(key);
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
}
