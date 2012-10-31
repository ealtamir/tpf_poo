package backend.cell;

import backend.board.Direction;
import backend.movable.Movable;

public class Water extends Floor {

	@Override
	public String idCharacter() {
		return "#";
	}
	
	@Override
	public void receiveMovable(Movable movable, Direction direction) throws UnoccupiableException {
		super.receiveMovable(movable, direction);
		movable.getWet();
	}
	

}
