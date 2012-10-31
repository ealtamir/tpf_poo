package backend.movable;
import backend.board.Board;
import backend.board.Direction;

import java.awt.Point;

public class Box extends Movable{
	
	public Box(Board board, Point position) {
		super(board, position);
	}

	@Override
	public boolean move(Direction direction) {
		return true; // TODO Realmente es necesario un move en Box? Si no, sacarlo de Movable e implementar solo en Player.
		
	}

	@Override
	public void getWet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePosition(Point position) {
		// TODO Auto-generated method stub
		
	}
	
	
}
