package com.graphy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphy.exception.UserException;
import com.graphy.models.User;
import com.graphy.repo.UserRepository;

@Service
public class UserServiceImplementation implements UserServices {
	
	@Autowired
	private UserRepository uRepo;

	@Override
	public User addUser(User user) throws UserException {
			User existingUser=uRepo.findByEmail(user.getEmail());
			if(existingUser!=null) {
				throw new UserException("User exist with email : "+user.getEmail());
			}
			return uRepo.save(user);
	}
}
