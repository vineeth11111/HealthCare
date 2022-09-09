package in.com.luv2code.util;

import java.util.UUID;

import org.springframework.stereotype.Component;
@Component
public class UserUtil {
	
	public static String genPwd()
	{
		return UUID.randomUUID().toString().replace("-","").substring(0, 6);
	}

}
