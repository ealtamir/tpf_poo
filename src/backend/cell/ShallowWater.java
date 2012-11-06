package backend.cell;

import java.awt.Point;

public class ShallowWater extends Floor {
	
	public ShallowWater(Point position) {
		super(position);
	}
	
	public ShallowWater(Cell cell) {
		super(cell);
	}
	
	@Override
	public void accept(CellVisitor visitor) {
		visitor.visit(this);		
	}
	
}
