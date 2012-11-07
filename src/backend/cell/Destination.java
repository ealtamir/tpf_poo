package backend.cell;

import backend.movable.Movable;
import backend.movable.Player;

import java.awt.Point;


/**
 * 
 * Destination es una clase de Floor que además tiene la habilidad
 * de finalizar el juego en caso de que estuviera visible, y un Movable
 * de tipo Player pasara a ocuparla.
 * 
 */

public class Destination extends Floor {
	
	private static final long serialVersionUID = 1L;
	private boolean visible = false;
		
	public Destination(Point position) {
		super(position);
	}
	
	public Destination(Cell cell) {
		super(cell);
	}
	
	/**
	 * De haber un jugador ocupando la celda, y si esta visible, 
	 * finaliza el juego.
	 */
	private void warpMovable() {
		Movable movable = this.getMovable();
		if (movable != null && movable instanceof Player && this.visible)
			((Player)movable).endGame();
	}
	
	
	/**
	 * Recibe un Movable @see Floor#receiveMovable(Movable)
	 * Intenta finalizar el juego de ser un jugador @see Destination#warpMovable()
	 */
	public void receiveMovable(Movable movable) {
		super.receiveMovable(movable);
		this.warpMovable();
	}
	
	/**
	 * Muestra el Destination, avisa a los respectivos Observers.
	 * Intenta finalizar el juego de haber un jugador @see Destination#warpMovable()
	 */
	public void show() {
		this.visible = true;
		this.warpMovable();
		this.notifyObservers();
	}
	
	/**
	 * Oculta el Destination, avisa a los respectivos Observers.
	 */
	public void hide() {
		this.visible = false;
		this.notifyObservers();
	}
	
	/**
	 * @return Estado de visibilidad del Destination.
	 */
	public boolean isVisible() {
		return this.visible;
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
