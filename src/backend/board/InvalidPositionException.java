package backend.board;

public class InvalidPositionException extends Exception {
	
	public InvalidPositionException(String message) {
		super(message);
	}

	public InvalidPositionException() {
		super();
	}
	
	
}
