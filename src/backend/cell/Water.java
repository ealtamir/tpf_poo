package backend.cell;

import backend.movable.Movable;

import java.awt.Point;


/**
 * La celda de tipo Water no puede contener un movable, pero sin embargo
 * es ocupable. Cuando un movable se posiciona sobre esta celda es como si
 * desapareciera del registro de movables en el tablero del juego.
 */

public class Water extends Cell {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Water(Point position) {
		super(position);
		// TODO Auto-generated constructor stub
	}
	
	public Water(Cell cell) {
		super(cell);
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
	
	@Override
	public void accept(CellVisitor visitor) {
		visitor.visit(this);		
	}

}
