package backEnd.cell;

import backEnd.board.Direction;
import backEnd.movable.Movable;

public class Tree extends Cell {
	
	@Override
	public Movable getMovable() {
		throw new UnsupportedOperationException("Can't get movable from Tree.");
	}
	
	@Override
	public boolean isOccupiable() {
		return false;
	}
	
	@Override
	public String idCharacter() {
		return "T";
	}
	
	@Override
	public Movable releaseMovable(Direction direction) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Can't release movable from Tree.");
	}
	
	
	@Override
	public boolean receiveMovable(Movable movable, Direction direction) {
		throw new UnsupportedOperationException("Can't receive movable at Tree.");
		
	}
	
	
	
		
}
