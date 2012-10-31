package backend.cell;

import java.awt.Point;

import backend.movable.Movable;

public class Tree extends Cell {
	
	public Tree(Point position) {
		super(position);
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
	public String idCharacter() {
		return "T";
	}
	
	@Override
	public Movable releaseMovable() {
		return null;
	}
	
	@Override
	public void receiveMovable(Movable movable) {
		throw new UnoccupiableException("Instance of class Tree cannot be occupied.");
	}
	
	
	
		
}
