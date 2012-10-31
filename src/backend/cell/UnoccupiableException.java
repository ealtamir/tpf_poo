package backend.cell;

public class UnoccupiableException extends RuntimeException {
	
	public UnoccupiableException() {
	}
	
	public UnoccupiableException(String message) {
		super(message);
	}
}
