package in.com.luv2code.service;

import java.util.Optional;

import in.com.luv2code.entity.User;

public interface IUserService {
	
	 Long saveUser(User user);
	
	Optional<User> findByUsername(String username);
	

}
