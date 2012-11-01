package backend.cell;

public class UnoccupiableException extends RuntimeException {
	
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public UnoccupiableException() {
	}
	
	public UnoccupiableException(String message) {
		super(message);
	}
}
