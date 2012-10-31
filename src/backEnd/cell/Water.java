package backEnd.cell;

import backEnd.board.Direction;
import backEnd.movable.Movable;

public class Water extends Floor {

	@Override
	public String idCharacter() {
		return "#";
	}
	
	@Override
	public boolean receiveMovable(Movable movable, Direction direction) {
		movable.updatePosition(this.getPosition());
		movable.getWet();
		return true;
	}
	

}
