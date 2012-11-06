package backend.board;

import java.awt.Point;
import java.io.Serializable;

public enum Direction implements Serializable {
	
	NORTH(-1, 0), EAST(0, 1), SOUTH(1, 0), WEST(0, -1);
	
	private int incrementColumn;
	private int incrementRow;
	
	private Direction(int incrementRow, int incrementColumn) {
		this.incrementColumn = incrementColumn;
		this.incrementRow = incrementRow;
	}
	
	public Point increment(Point point) {
		return new Point(point.x + this.incrementColumn, point.y + this.incrementRow);
	}
	
}
