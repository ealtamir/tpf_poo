package backend.movable;

import backend.board.Board;

import backend.board.InvalidPositionException;
import backend.cell.ShallowWater;

import backend.board.InconsistentBoardStateException;

import java.awt.Point;

public class Box extends Movable{
	
	public Box(Board board, Point position) {
		super(board, position);
	}

	@Override
	public void getWet() {
		try {
			board.setCell(this.position, new ShallowWater(this.position));
		}
		catch (InvalidPositionException e) {
			throw new InconsistentBoardStateException("Box got wet at invalid position.");
		}
	}
	
	@Override
	public String idCharacter() {
		return "B";
	}

	
	
}
