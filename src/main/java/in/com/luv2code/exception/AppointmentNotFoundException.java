package in.com.luv2code.exception;

public class AppointmentNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public AppointmentNotFoundException()
	{
		super();
	}
	
	public AppointmentNotFoundException(String message)
	{
		super(message);
	}
	

}
