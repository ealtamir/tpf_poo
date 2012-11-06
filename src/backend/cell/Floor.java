package backend.cell;

import java.awt.Point;

import backend.movable.Movable;

public class Floor extends Cell {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	@Override
	public void accept(CellVisitor visitor) {
		visitor.visit(this);		
	}
	
	

}
