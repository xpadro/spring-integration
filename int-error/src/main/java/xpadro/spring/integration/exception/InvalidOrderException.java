package xpadro.spring.integration.exception;

public class InvalidOrderException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidOrderException() {
		super();
	}
	
	public InvalidOrderException(String message) {
		super(message);
	}
}
