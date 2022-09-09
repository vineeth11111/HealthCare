package in.com.luv2code.exception;

public class SpecializationNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public SpecializationNotFoundException()
	{
		super();
	}
	
	public SpecializationNotFoundException(String message)
	{
		super(message);
	}

}
