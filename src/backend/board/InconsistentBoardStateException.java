package backend.board;

public class InconsistentBoardStateException extends RuntimeException {
	public InconsistentBoardStateException() {
		super();
	}
	
	public InconsistentBoardStateException(String message) {
		super(message);
	}
}
