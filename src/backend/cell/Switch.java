package backend.cell;

import backend.movable.IceCube;

import backend.movable.Movable;

import java.awt.Point;

/**
 * Switch es un tipo de piso que tiene una referencia a una celda de tipo
 * Destination. Al ser ocupada por un Movable de tipo IceCube, Switch indica
 * a la celda Destination que debe hacerse visible.
 *
 */

public class Switch extends Floor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Destination destination;
	
	public Switch(Point position) {
		super(position);
	}
	
	public Switch(Cell cell) {
		super(cell);
	}
	
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	/**
	 * Al ingresar un Movable a una celda de tipo Switch, 
	 * debe mostrarse el destino referenciado solo si es de tipo IceCube.
	 * @see Floor#receiveMovable(Movable)
	 * @param movable Movable a ingresar en la celda.
	 */
	@Override
	public void receiveMovable(Movable movable) {
		/*
		 * Tradeoff entre una linea de codigo imperativa y ensuciar
		 * la jerarquia de claes de movable.
		 * @see Informe
		 */
		if (movable instanceof IceCube && this.destination != null)
			this.destination.show();
		
		super.receiveMovable(movable);
	}
	
	/**
	 * Al salir un Movable de una celda de tipo Switch, la accion
	 * a llevar a cabo es la de ocultar el destino referenciado.
	 * @see Floor#releaseMovable()
	 * @return Movable contenido en la celda.
	 */
	@Override
	public Movable releaseMovable() {
		
		if (this.destination != null)
			this.destination.hide();
		
		return super.releaseMovable();
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
