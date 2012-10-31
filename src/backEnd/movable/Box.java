package backEnd.movable;
import backEnd.board.Board;
import backEnd.board.Direction;

import java.awt.Point;

public class Box extends Movable{
	private Board board;
	private Point position;
	
	public Box(Board board, Point position){
		this.board = board;
		this.position = position;
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
