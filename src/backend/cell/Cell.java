package backend.cell;

import backend.movable.*;

import general.observer.Observable;

import java.awt.Point;

public abstract class Cell extends Observable {

	private Point position;
	
	public Cell(Point position) {
		this.position = position;
	}
	
	public Cell(Cell cell) {
		super(cell);
		this.position = cell.position;
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
