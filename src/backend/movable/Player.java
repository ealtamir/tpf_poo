package backend.movable;

import java.awt.Point;
import backend.board.InvalidPositionException;

import backend.board.*;

public class Player extends Movable {
	
	public Player(Board board, Point position){
		super(board, position);
	}
	
	public boolean move(Direction dir) {
		
		Point nextPosition = dir.increment(this.getPosition());
		
		Movable targetMovable = null;
		
		try {
			targetMovable = this.getBoard().getCell(nextPosition).getMovable();
		}
		catch (InvalidPositionException e) {
			/*
			 * Si la posicion a la que queremos movernos es invalida,
			 * abortamos todo el proceso, pues no puede haber ningun Movable
			 * tampoco.
			 */
			return false;
		}
		
		if (targetMovable != null)
			targetMovable.move(dir);
		
		return super.move(dir);
		
	}
	
	@Override
	public String idCharacter() {
		return "@";
	}

	@Override
	public void getWet() {
		System.out.println("You lose!");
	}
	
	public void endGame() {
		System.out.println("You win!");		
	}
	
}
