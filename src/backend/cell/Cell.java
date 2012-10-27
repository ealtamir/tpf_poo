package backend.cell;

import backend.movable.*;
import backend.board.Direction;
import java.awt.Point;

public abstract class Cell {

	protected Point position;
	
	public abstract Movable getMovable();
	
	public abstract boolean isOccupiable();
	
	public abstract String idCharacter();
	
	public abstract void receiveMovable(Movable movable, Direction direction);
	
	public abstract Movable releaseMovable(Direction direction);
	

}
