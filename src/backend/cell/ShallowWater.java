package backend.cell;

import java.awt.Point;

public class ShallowWater extends Floor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShallowWater(Point position) {
		super(position);
	}
	
	@Override
	public void accept(CellVisitor visitor) {
		visitor.visit(this);		
	}
	
}
