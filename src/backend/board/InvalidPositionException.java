package backend.board;

import java.awt.Point;

public class InvalidPositionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private Board board;
	private Point position;
	
	public InvalidPositionException(String message) {
		super(message);
	}

	public InvalidPositionException() {
		super();
	}
	
	public InvalidPositionException(Board board, Point position) {
		super(board + " (" + position.x + "," + position.y + ")");
		this.position = position;
		this.board = board;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	
}
