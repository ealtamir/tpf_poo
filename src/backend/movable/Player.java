package backend.movable;

import java.awt.Point;
import backend.board.InvalidPositionException;

import backend.board.*;

public class Player extends Movable {
	
	public Player(Board board, Point position){
		super(board, position);
	}
	
	public boolean move(Direction dir) {
		
		Point nextPosition = dir.increment(position);
		
		Movable targetMovable = null;
		
		try {
			targetMovable = board.getCell(nextPosition).getMovable();
		}
		catch (InvalidPositionException e) {
			return false; // No podemos movernos si la siguiente posicion es invalida.
		}
		
		if (targetMovable != null)
			targetMovable.move(dir);
		
		return super.move(dir);
		
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
