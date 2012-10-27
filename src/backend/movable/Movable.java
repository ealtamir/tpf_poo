package backend.movable;

import java.awt.Point;
import backend.board.Direction;

public abstract class Movable {
	
	private Point position;
	
	public abstract void move(Direction direction);
	
	public abstract void getWet();
	public abstract void updatePosition(Point position);
		
}
