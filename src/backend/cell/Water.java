package backend.cell;

import backend.board.Direction;
import backend.movable.Movable;

import java.awt.Point;

public class Water extends Floor {
	
	public Water(Point position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String idCharacter() {
		return "#";
	}
	
	@Override
	public boolean receiveMovable(Movable movable, Direction direction) {
		movable.updatePosition(this.position);
		movable.getWet();
		return false;
	}
	

}
