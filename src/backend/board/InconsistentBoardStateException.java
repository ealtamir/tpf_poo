package backend.board;

public class InconsistentBoardStateException extends RuntimeException {
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public InconsistentBoardStateException() {
		super();
	}
	
	public InconsistentBoardStateException(String message) {
		super(message);
	}
}
