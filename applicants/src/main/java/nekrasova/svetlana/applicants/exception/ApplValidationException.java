package nekrasova.svetlana.applicants.exception;

public class ApplValidationException extends Exception {
	
	private static final long serialVersionUID = 7069903378020755169L;
	private String message;
	
	public ApplValidationException(String message) {
		
	}
	
	public String getMessage() {
		return message;
	}

}
