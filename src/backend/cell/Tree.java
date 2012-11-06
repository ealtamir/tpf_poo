package backend.cell;

import java.awt.Point;

import backend.movable.Movable;

public class Tree extends Cell {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tree(Point position) {
		super(position);
	}
	
	public Tree(Cell cell) {
		super(cell);
	}
	
	/**
	 * Una celda de tipo Tree nunca puede contener un Movable.
	 * @return {@code null}
	 */
	@Override
	public Movable getMovable() {
		return null;
	}
	
	/**
	 * Una celda de tipo Tree nunca puede ser ocupada.
	 * @return {@code false}
	 */
	@Override
	public boolean isOccupiable() {
		return false;
	}
	
	/**
	 * Una celda tipo Tree nunca puede contener un Movable.
	 * @return {@code null}
	 */
	@Override
	public Movable releaseMovable() {
		return null;
	}
	
	/**
	 * Una celda de tipo Tree nunca es ocupable.
	 * @throws UnoccupiableException incondicionalmente
	 * @param movable Movable a ocupar la celda.
	 */
	@Override
	public void receiveMovable(Movable movable) {
		throw new UnoccupiableException("Instance of class Tree cannot be occupied.");
	}
	
	/**
	 * Patron visitor para visitar las celdas.
	 * @param visitor CellVisitor
	 */
	@Override
	public void accept(CellVisitor visitor) {
		visitor.visit(this);		
	}
	
	
	
		
}
