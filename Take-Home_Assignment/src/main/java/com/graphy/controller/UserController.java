package com.graphy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.graphy.exception.UserException;
import com.graphy.models.User;
import com.graphy.services.UserServices;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserServices uService;
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUserHandler(@Valid @RequestBody User user) throws UserException{
		
		User savedUser=uService.addUser(user);
		
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
		
	}

}
