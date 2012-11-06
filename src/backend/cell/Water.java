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
	
	
	/**
	 * Al recibir un Movable, una celda de tipo Water envia
	 * el mensaje getWet para indicarle dicho suceso.
	 * No almacena al Movable.
	 * @param movable Movable que ingresa a la celda.
	 */
	@Override
	public void receiveMovable(Movable movable) {
		movable.getWet();
	}
	
	/**
	 * Una celda de tipo Water nunca puede contener un movable.
	 * @return {@code null}
	 */
	@Override
	public Movable releaseMovable() {
		return null;
	}
	
	/**
	 * Una celda de tipo Water siempre es ocupable.
	 * @return {@code true}
	 */
	@Override
	public boolean isOccupiable() {
		return true;
	}
	
	/**
	 * Una celda de tipo Water nunca puede contener un Movable.
	 * @return {@code null}
	 */
	@Override
	public Movable getMovable() {
		return null;
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
