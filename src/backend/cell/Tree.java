package backend.cell;

import backend.board.Direction;
import backend.movable.Movable;

public class Tree extends Cell {
	
	@Override
	public Movable getMovable() {
		return null;
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
		return null;
	}
	
	@Override
	public void receiveMovable(Movable movable, Direction direction) throws UnoccupiableException {	
		throw new UnoccupiableException("Cell of type Tree cannot receive a movable.");
	}
	
	
	
		
}
