package backend.cell;

import java.awt.Point;


/**
 * Floor es una celda (@see Cell) que además tiene la habilidad de poder estar
 * ocupada por un Movable.
 * 
 */

import backend.movable.Movable;

public class Floor extends Cell {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Movable movable;
	
	public Floor(Movable movable, Point position) {
		super(position);
		this.movable = movable;		
	}
		
	public Floor(Point position) {
		super(position);
		this.movable = null;
	}
	
	public Floor(Cell cell) {
		super(cell);
	}
	
	@Override
	public Movable getMovable() {
		return this.movable;
	}
	
	/**
	 * Una celda de tipo Floor es ocupable si no esta ocupada por un
	 * Movable.
	 * @return {@code true} Si la celda esta ocupada por un Movable, {@code false} de lo contrario.
	 */
	@Override
	public boolean isOccupiable() {
		return (this.movable == null);
	}
	
	/**
	 * Libera el Movable de la celda y lo devuelve.
	 * @return Movable que ocupa la celda.
	 */
	@Override
	public Movable releaseMovable() {
		Movable movable = this.movable;
		this.movable = null;
		this.wasChanged();
		return movable;
	}
	
	/**
	 * Almacena un Movable en la celda.
	 * La ocupabilidad debe ser chequeada. @see Floor#isOccupiable()
	 * @throws UnoccupiableException En caso de estar ocupada por otro Movable.
	 * @param movable Movable a almacenar en la celda.
	 */
	@Override
	public void receiveMovable(Movable movable) {
		
		if (!this.isOccupiable())
			throw new UnoccupiableException("Cell already occupied.");
		
		this.movable = movable;
		
		this.wasChanged();
		
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
