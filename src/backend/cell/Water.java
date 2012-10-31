package backend.cell;

import backend.movable.Movable;

import java.awt.Point;

public class Water extends Cell {
	
	public Water(Point position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String idCharacter() {
		return "#";
	}
	
	@Override
	public void receiveMovable(Movable movable) {
		movable.getWet();
	}
	
	@Override
	public Movable releaseMovable() {
		return null;
	}
	
	@Override
	public boolean isOccupiable() {
		return true;
	}
	
	@Override
	public Movable getMovable() {
		return null;
	}
	

}
