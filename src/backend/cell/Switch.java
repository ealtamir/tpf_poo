package backend.cell;

import backend.movable.IceCube;
import backend.movable.Movable;

import java.awt.Point;

public class Switch extends Floor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Destination destination;
	
	public Switch(Point position) {
		super(position);
	}
	
	public Switch(Cell cell) {
		super(cell);
	}
	
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	@Override
	public void receiveMovable(Movable movable) {
		/*
		 * Tradeoff entre una linea de codigo imperativa y ensuciar
		 * la jerarquia de claes de movable.
		 * @see Informe
		 */
		if (movable instanceof IceCube && this.destination != null)
			this.destination.show();
		
		super.receiveMovable(movable);
	}
	
	@Override
	public Movable releaseMovable() {
		
		if (this.destination != null)
			this.destination.hide();
		
		return super.releaseMovable();
	}
	
	@Override
	public void accept(CellVisitor visitor) {
		visitor.visit(this);		
	}
	
}
