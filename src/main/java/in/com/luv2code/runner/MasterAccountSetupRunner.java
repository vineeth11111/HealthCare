package in.com.luv2code.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.com.luv2code.constants.UserRoles;
import in.com.luv2code.entity.User;
import in.com.luv2code.service.IUserService;
import in.com.luv2code.util.UserUtil;
@Component
public class MasterAccountSetupRunner implements CommandLineRunner {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserUtil userUtil;
	
	@Value("${master.user.name}")
	private String displayName;
	
	@Value("${master.user.email}")
	private String username;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if(!userService.findByUsername(username).isPresent()) {
			User user = new User();
			user.setDisplayName(displayName);
			user.setUsername(username);
			user.setPassword(UserUtil.genPwd());
			user.setRole(UserRoles.ADMIN.name());
			userService.saveUser(user);
			//TODO: Email part is pending
		}

	}

}
