package backend.cell;

import java.awt.Point;

/**
 * Una celda de tipo ShallowWater tiene exactamente el mismo comportamiento
 * que una celda de tipo Floor.
 * Aunque actualmente sirve únicamente para distinguir la apariencia de la celda,
 * con este diseño, en un futuro sería posible construir un nuevo comportamiento
 * para una celda de este tipo, como por ejemplo que tenga la capacidad de hundirse.
 * 
 */

public class ShallowWater extends Floor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShallowWater(Point position) {
		super(position);
	}
	
	public ShallowWater(Cell cell) {
		super(cell);
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
