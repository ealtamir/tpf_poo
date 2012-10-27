package backend.movable;
import backend.board.Board;
import backend.board.Direction;

import java.awt.Point;

public class Box extends Movable{
	private Board board;
	private Point position;
	
	public Box(Board board, Point position){
		this.board = board;
		this.position = position;
	}

	@Override
	public void move(Direction direction) {
		// TODO Auto-generated method stub
		
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
