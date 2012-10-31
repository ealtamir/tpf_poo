package backEnd.movable;
import java.awt.Point;

import backEnd.board.*;

public class Player extends Movable{
	private Board board;
	private Point position;
	
	public Player(Board board, Point position){
		this.board = board;
		this.position = position;
	}
	
	public boolean move(Direction dir){
		Point nextPosition = dir.increment(position);
		if(board.getCell(nextPosition).receiveMovable(this, dir)){
			this.updatePosition(nextPosition);
			return true;
		}
		return false;
		
		
	}

	@Override
	public void getWet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePosition(Point position) {
		this.position = position;
	}
	
}
