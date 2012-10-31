package backend.cell;

import backend.board.Direction;
import backend.movable.*;

import java.awt.Point;

public abstract class Cell {

	protected Point position;
	
	public abstract Movable getMovable();
	
	public abstract boolean isOccupiable();
	
	/**
	 * Testing method. Delete later.	
	 * @return
	 */
	public abstract String idCharacter();
	
	public abstract void receiveMovable(Movable movable, Direction direction) throws UnoccupiableException;
	
	public abstract Movable releaseMovable(Direction direction);
	

}
