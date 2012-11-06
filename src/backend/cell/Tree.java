package backend.cell;

import java.awt.Point;

import backend.movable.Movable;

public class Tree extends Cell {
	
	public Tree(Point position) {
		super(position);
	}
	
	public Tree(Cell cell) {
		super(cell);
	}
	
	@Override
	public Movable getMovable() {
		return null;
	}
	
	@Override
	public boolean isOccupiable() {
		return false;
	}
	
	@Override
	public Movable releaseMovable() {
		return null;
	}
	
	@Override
	public void receiveMovable(Movable movable) {
		throw new UnoccupiableException("Instance of class Tree cannot be occupied.");
	}
	
	@Override
	public void accept(CellVisitor visitor) {
		visitor.visit(this);		
	}
	
	
	
		
}
