package com.graphy.services;

import com.graphy.exception.UserException;
import com.graphy.models.User;

public interface UserServices {
	
	public User addUser(User user) throws UserException;

}
