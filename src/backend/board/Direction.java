package backend.board;

import java.awt.Point;

public enum Direction {
	
	NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);
	
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
