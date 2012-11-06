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

	@Override
	public void getWet() {
		Point currentPosition = this.getPosition();
		this.getBoard().setCell(currentPosition, new ShallowWater(currentPosition));
	}
	
	@Override
	public String idCharacter() {
		return "B";
	}

	public void accept(MovableVisitor visitor) {
		visitor.visit(this);
	}	
	
	
}
