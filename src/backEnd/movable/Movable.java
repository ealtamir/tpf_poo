package backEnd.movable;

import java.awt.Point;

import backEnd.board.Direction;

public abstract class Movable {
	
	private Point position;
	
	public abstract boolean move(Direction direction);
	
	public abstract void getWet();
	public abstract void updatePosition(Point position);
		
}
