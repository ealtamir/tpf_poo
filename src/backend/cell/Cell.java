package backend.cell;

import backend.movable.*;

import java.awt.Point;

public abstract class Cell {

	private Point position;
	
	public Cell(Point position) {
		this.position = position;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public abstract Movable getMovable();
	
	public abstract boolean isOccupiable();
	

	
	/**
	 * El metodo devuelve un String que caracteriza a la celda.
	 * Sirve para propositos de debug, de modo de dibujar un mapa en
	 * consola.
	 * @deprecated Testing method. Delete later.
	 * @return String que caracteriza a la celda
	 */
	public abstract String idCharacter();
	
	public abstract void receiveMovable(Movable movable);
	
	public abstract Movable releaseMovable();
	

}
