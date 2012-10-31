package backend.movable;

import backend.board.Board;
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
	}
	
	public boolean move(Direction direction) {
		
		Cell currentCell = null;
		Cell targetCell = null;
		
		try {
			currentCell = this.board.getCell(this.position);
			targetCell = this.board.getCell(this.position);
		}
		catch (InvalidPositionException e) {
			return false;
		}
		
		currentCell.releaseMovable(direction);
		
		try {
			if (targetCell.isOccupiable())
				targetCell.receiveMovable(this, direction);
		}
		catch 
		
		
	}
	
	public abstract void getWet();
	public abstract void updatePosition(Point position);
		
}
