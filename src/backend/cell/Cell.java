package backend.cell;

import backend.movable.*;

import java.awt.Point;

public abstract class Cell {

	protected Point position;
	
	public Cell(Point position) {
		this.position = position;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public abstract Movable getMovable();
	
	public abstract boolean isOccupiable();
	

	
	/**
	 * Testing method. Delete later.	
	 * @return
	 */
	public abstract String idCharacter();
	
	public abstract void receiveMovable(Movable movable);
	
	public abstract Movable releaseMovable();
	

}
