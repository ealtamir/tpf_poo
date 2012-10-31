package backend.movable;

import backend.board.Board;
import backend.board.InconsistentBoardStateException;
import backend.board.InvalidPositionException;
import backend.cell.Cell;

import java.awt.Point;

import backend.board.Direction;

public abstract class Movable {
	
	protected Point position;
	protected Board board;
	
	public Movable(Board board, Point position) {
		this.board = board;
		this.position = position;
		
		try {
			board.getCell(this.position).receiveMovable(this);
		} catch (InvalidPositionException e) {
			throw new InconsistentBoardStateException();
		}
	}
	
	public boolean move(Direction direction) {
		
		Cell currentCell = null;
		Cell targetCell = null;
		
		try {
			currentCell = this.board.getCell(this.position);
			targetCell = this.board.getCell(direction.increment(this.position));
		}
		catch (InvalidPositionException e) {
			return false;
		}
		
		if (targetCell.isOccupiable()) {
			this.position = targetCell.getPosition();
			targetCell.receiveMovable(this);
			currentCell.releaseMovable();
			return true;
		}
		
		return false;
		
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public abstract String idCharacter();
	
	public abstract void getWet();
		
}
