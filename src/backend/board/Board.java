package backend.board;
import java.awt.Point;
import java.io.Serializable;

import backend.cell.*;

public class Board implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cell terrain[][];
	private int columns;
	private int rows;
	
	/**
	 * Crea un tablero nuevo de alto height y ancho width.
	 * @param width Ancho del tablero
	 * @param rows Alto del tabler
	 */
	public Board(int rows, int columns) {
		this.terrain = new Cell[rows][columns];
		this.columns = columns;
		this.rows = rows;
	}
	
	/**
	 * Devuelve una referencia a la celda en la posicion Point(x, y) 
	 * @param p Point(x, y)
	 * @return Referencia a la celda en la posicion Point(x, y)
	 */
	public Cell getCell(Point p) {
		return this.getCell(p.y, p.x);
	}
	
	public Cell getCell(int row, int column) {
		if (this.validPosition(row, column)) {
			return this.terrain[row][column];
		} else {
			throw new InvalidPositionException(this, new Point(row, column));
		}
	}
	
	public void setCell(Point p, Cell cell) {
		this.setCell(p.y, p.x, cell);
	}
	
	public void setCell(int row, int column, Cell cell) {
		if (this.validPosition(row, column)) {
			this.terrain[row][column] = cell;
		} else {
			throw new InvalidPositionException(this, new Point(column, row));
		}
	}
	
	private boolean validPosition(int row, int column) {
		return (column >= 0 && row >= 0 && row < this.rows && column < this.columns);
	}

	
	public void cellsAccept(CellVisitor visitor) {
		for (int y = 0; y < this.rows; y++)
			for (int x = 0; x < this.columns; x++)
				this.getCell(x, y).accept(visitor);
	}
	
	public int getWidth() {
		return columns;
	}

	public int getHeight() {
		return rows;
	}

}
