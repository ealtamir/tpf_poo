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
		
		while (super.move(direction)) {
			moved = true;
			if (melted)
				break;
		}
		
		return moved;
	}

	@Override
	public void getWet() {
		this.melted = true;
	}
	
	@Override
	public String idCharacter() {
		return "C";
	}
	
	@Override
	public void accept(MovableVisitor visitor) {
		visitor.visit(this);
	}

}
