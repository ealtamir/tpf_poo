package backEnd.cell;

import backEnd.board.Direction;
import backEnd.movable.*;

import java.awt.Point;

public abstract class Cell {

	protected Point position;
	
	public abstract Movable getMovable();
	
	public abstract boolean isOccupiable();
	
	public abstract String idCharacter();
	
	public abstract boolean receiveMovable(Movable movable, Direction direction);
	
	public abstract Movable releaseMovable(Direction direction);
	

}
