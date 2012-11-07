package backend.movable;

import backend.board.Board;

import backend.cell.ShallowWater;

import java.awt.Point;

public class Box extends Movable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public Box(Board board, Point position) {
		super(board, position);
	}
	
	/**
	 * Coloca una celda ShallowWater en la posicion actual.
	 */
	@Override
	public void getWet() {
		Board board = this.getBoard();
		Point currentPosition = this.getPosition();
		ShallowWater newShallowWater = new ShallowWater(board.getCell(currentPosition));
		board.setCell(currentPosition, newShallowWater);
	}

	
	/**
	 *  Patron Visitor para visitar Movables.
	 *  @param visitor Visitor de Movable
	 */
	public void accept(MovableVisitor visitor) {
		visitor.visit(this);
	}	
	
	
}
