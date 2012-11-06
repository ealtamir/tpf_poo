package backend.cell;

import backend.movable.Movable;
import backend.movable.Player;

import java.awt.Point;

public class Destination extends Floor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean visible = false;
		
	public Destination(Point position) {
		super(position);
	}
	
	public Destination(Cell cell) {
		super(cell);
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
		this.notifyObservers();
	}
	
	public void hide() {
		this.visible = false;
		this.notifyObservers();
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	
	@Override
	public void accept(CellVisitor visitor) {
		visitor.visit(this);		
	}
	
}
