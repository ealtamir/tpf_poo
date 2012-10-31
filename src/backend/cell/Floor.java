package backend.cell;

import java.awt.Point;

import backend.board.Board;
import backend.board.Direction;
import backend.movable.Movable;

public class Floor extends Cell {

	protected Movable movable;
	
	public Floor(Movable movable, Point position) {
		super(position);
		this.movable = movable;		
	}
	
	public Floor(Point position) {
		super(position);
	}
	
	@Override
	public Movable getMovable() {
		return this.movable;
	}
	
	@Override
	public boolean isOccupiable() {
		return (this.movable == null);
	}

	@Override
	public String idCharacter() {
		return " ";
	}
	
	
	@Override
	public Movable releaseMovable(Direction direction) {
		Movable movable = this.movable;
		this.movable = null;
		return movable;
	}
	
	@Override
	public boolean receiveMovable(Movable movable, Direction direction) {
		
		boolean canOccupy = this.isOccupiable();
		
		if (canOccupy) {
			this.movable = movable;
			movable.updatePosition(position);			
		}
		
		return canOccupy;
	}
	
	
	

}
