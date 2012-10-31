package backend.cell;

import backend.board.Direction;
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
	public Movable releaseMovable(Direction direction) {
		return null;
	}
	
	@Override
	public boolean receiveMovable(Movable movable, Direction direction) {
		return false;
	}
	
	
	
		
}
