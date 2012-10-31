package backend.cell;

import java.awt.Point;

import backend.movable.Movable;

public class Floor extends Cell {

	protected Movable movable;
	
	public Floor(Movable movable, Point position) {
		super(position);
		this.movable = movable;		
	}
	
	public Floor(Point position) {
		super(position);
		this.movable = null;
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
		if (this.movable == null)
			return this.idCharacterBase();
		else
			return this.movable.idCharacter();
	}
	
	public String idCharacterBase() {
		return " ";
	}
	
	@Override
	public Movable releaseMovable() {
		Movable movable = this.movable;
		this.movable = null;
		return movable;
	}
	
	@Override
	public void receiveMovable(Movable movable) {
		
		if (!this.isOccupiable())
			throw new UnoccupiableException("Cell already occupied.");
		
		this.movable = movable;
		
	}
	
	
	

}
