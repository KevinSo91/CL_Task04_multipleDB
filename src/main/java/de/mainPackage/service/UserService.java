package de.mainPackage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.mainPackage.model.user.User;
import de.mainPackage.repository.user.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private final UserRepository userRepo;
		
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	public Optional<User> getUser(int id) {
		return userRepo.findById(id);
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public User createUser(User user) {
		return userRepo.save(user);
	}
	
}
