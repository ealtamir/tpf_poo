package backend.cell;

import backend.movable.Movable;
import backend.movable.Player;

import java.awt.Point;

public class Destination extends Floor {
	
	private boolean visible = false;
		
	public Destination(Point position) {
		super(position);
	}
	
	private void warpMovable() {
		if (this.movable != null && this.movable instanceof Player && this.visible)
			((Player)this.movable).endGame();
	}
	
	public void receiveMovable(Movable movable) {
		super.receiveMovable(movable);
		this.warpMovable();
	}
	
	public void show() {
		this.visible = true;
		this.warpMovable();
	}
	
	public void hide() {
		this.visible = false;
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	
	@Override
	public void accept(CellVisitor visitor) {
		visitor.visit(this);		
	}
	
}
