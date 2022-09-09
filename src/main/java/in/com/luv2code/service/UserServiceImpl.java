package in.com.luv2code.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.com.luv2code.entity.User;
import in.com.luv2code.repo.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;
	
//	@Autowired ,UserDetailsService
//	private BCryptPasswordEncoder passwordEncoder;
//	
	@Override
	public Long saveUser(User user) {
		//read generated pwd
		String pwd = user.getPassword();
		//encode password
		//String encPwd = passwordEncoder.encode(pwd);
		//set back to object
		user.setPassword(pwd);
		return userRepo.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		
		return userRepo.findByUsername(username);
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		Optional<User> opt = findByUsername(username);
//		if(!opt.isPresent())
//		{
//			throw new UsernameNotFoundException(username);
//		}else {
//			User user = opt.get();
//			return new org.springframework.security.core.userdetails.User(
//					user.getUsername(),
//					user.getPassword(),
//					Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
//		}
//		
//	}

	
	
	

}
