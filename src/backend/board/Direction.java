package backend.board;

import java.awt.Point;

public enum Direction {
	
	NORTH(-1, 0), EAST(0, 1), SOUTH(1, 0), WEST(0, -1);
	
	private int incrementX;
	private int incrementY;
	
	private Direction(int incrementX, int incrementY) {
		this.incrementX = incrementX;
		this.incrementY = incrementY;
	}
	
	public Point increment(Point point) {
		return new Point(point.x + this.incrementX, point.y + this.incrementY);
	}
	
}
