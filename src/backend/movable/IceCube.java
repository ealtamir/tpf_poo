package backend.movable;

import backend.board.Board;
import backend.board.Direction;

import java.awt.Point;

public class IceCube extends Movable {
	
	private boolean melted = false;
	
	public IceCube(Board board, Point position) {
		super(board, position);
	}
	
	
	@Override
	public boolean move(Direction direction) {
		
		boolean moved = false;
		boolean couldMove = false;
		
		while (moved = super.move(direction)) {
			if (moved)
				couldMove = true;
			if (melted)
				break;
		}
		
		return couldMove;
	}

	@Override
	public void getWet() {
		this.melted = true;
	}
	
	@Override
	public String idCharacter() {
		return "C";
	}

}
