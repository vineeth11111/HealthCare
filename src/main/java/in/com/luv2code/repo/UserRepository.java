package in.com.luv2code.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.com.luv2code.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	
	Optional<User> findByUsername(String username);
	
}
