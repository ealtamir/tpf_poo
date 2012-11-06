package backend.movable;

import java.awt.Point;
import backend.board.InvalidPositionException;
import backend.Game;

import backend.board.*;

public class Player extends Movable {
	
	private Game game; // TODO ver si realmente es necesario game.
	
	public Player(Game game, Board board, Point position){
		super(board, position);
		this.game = game;
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
	public void getWet() {
		this.game.lose();
	}
	
	public void endGame() {
		this.game.win();	
	}
	
	public void accept(MovableVisitor visitor) {
		visitor.visit(this);
	}
	
}
