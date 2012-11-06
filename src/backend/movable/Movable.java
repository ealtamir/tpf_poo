package backend.movable;

import backend.board.Board;
import backend.board.InvalidPositionException;
import backend.cell.Cell;

import java.awt.Point;
import java.io.Serializable;

import backend.board.Direction;

public abstract class Movable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point position;
	private Board board;
	
	public Movable(Board board, Point position) {
		this.board = board;
		this.position = position;
	}
	
	public void insert() {
		this.board.getCell(this.position).receiveMovable(this);
	}
	
	public boolean move(Direction direction) {
		
		Cell currentCell = this.board.getCell(this.position);
		Cell targetCell = null;
		
		/*
		 * Podria ocurrir que intentaramos movernos hacia una posicion invalida
		 * y que no sea un error fatal.
		 */
		try {
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
	
	public Board getBoard() {
		return this.board;
	}
	
	public abstract String idCharacter();
	
	public abstract void accept(MovableVisitor visitor);
	
	public abstract void getWet();
		
}
