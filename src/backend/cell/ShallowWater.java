package backend.cell;

import java.awt.Point;

/**
 * Una celda de tipo ShallowWater tiene exactamente el mismo comportamiento
 * que una celda de tipo Floor.
 * La unica diferencia apreciable es su apariencia, razon por la que
 * se distingue su tipo.
 * En el frontend esta diferencia deberia manifestarse en el grafico.
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
