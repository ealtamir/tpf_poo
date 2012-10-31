package backend.cell;

import java.awt.Point;

public class ShallowWater extends Floor {
	
	public ShallowWater(Point position) {
		super(position);
	}
	
	@Override
	public String idCharacterBase() {
		return "S";
	}
	
}
