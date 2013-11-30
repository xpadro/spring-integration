package xpadro.spring.integration.exception;

/**
 * Exception raised when the order processor cannot process an order
 * 
 * @author xpadro
 *
 */
public class InvalidOrderException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidOrderException() {
		super();
	}
	
	public InvalidOrderException(String message) {
		super(message);
	}
}
