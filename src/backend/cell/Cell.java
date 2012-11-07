package backend.cell;

import backend.movable.*;

import java.awt.Point;
import java.io.Serializable;

/**
 * Define e implementa metodos básicos para el comportamiento de una celda
 * en el juego.
 * Una celda abstracta consiste de un objeto observable con posibilidad
 * de ubicarlo en alguna posicion de Board.
 * 
 */

public abstract class Cell implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point position;
	
	private CellListener listener = null;
	
	public Cell(Point position) {
		this.position = position;
	}
	
	public Cell(Cell cell) {
		this.position = cell.position;
		this.listener = cell.listener;
	}
	
	public void setListener(CellListener listener) {
		this.listener = listener;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void wasChanged() {
		if (listener != null)
			listener.cellChanged(this);
	}
	
	/**
	 * Patron visitor para visitar las celdas.
	 * @param visitor CellVisitor
	 */
	public abstract void accept(CellVisitor visitor);
	
	public abstract Movable getMovable();
	
	public abstract boolean isOccupiable();
	
	public abstract void receiveMovable(Movable movable);
	
	public abstract Movable releaseMovable();
	

}
