package backend.cell;

import backend.movable.*;

import java.awt.Point;
import java.io.Serializable;

public abstract class Cell implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point position;
	
	public Cell(Point position) {
		this.position = position;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public abstract void accept(CellVisitor visitor);
	
	public abstract Movable getMovable();
	
	public abstract boolean isOccupiable();
	
	public abstract void receiveMovable(Movable movable);
	
	public abstract Movable releaseMovable();
	

}
