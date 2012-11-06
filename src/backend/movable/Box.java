package backend.movable;

import backend.board.Board;

import backend.cell.ShallowWater;

import java.awt.Point;

public class Box extends Movable{
	
	public Box(Board board, Point position) {
		super(board, position);
	}

	@Override
	public void getWet() {
		Board board = this.getBoard();
		Point currentPosition = this.getPosition();
		ShallowWater newShallowWater = new ShallowWater(board.getCell(currentPosition));
		board.setCell(currentPosition, newShallowWater);
	}

	public void accept(MovableVisitor visitor) {
		visitor.visit(this);
	}	
	
	
}
