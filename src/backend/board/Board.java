package backend.board;
import java.awt.Point;

import backend.cell.*;

public class Board {
	
	private Cell terrain[][];
	private int width;
	private int height;
	
	/**
	 * Crea un tablero nuevo de alto height y ancho width.
	 * @param width Ancho del tablero
	 * @param height Alto del tabler
	 */
	public Board(int width, int height) {
		this.terrain = new Cell[width][height];
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Devuelve una referencia a la celda en la posicion Point(x, y) 
	 * @param p Point(x, y)
	 * @return Referencia a la celda en la posicion Point(x, y)
	 */
	public Cell getCell(Point p) {
		return this.getCell(p.x, p.y);
	}
	
	public Cell getCell(int x, int y) {
		if (this.validPosition(x, y)) {
			return this.terrain[x][y];
		} else {
			throw new InvalidPositionException(this, new Point(x, y));
		}
	}
	
	public void setCell(Point p, Cell cell) {
		this.setCell(p.x, p.y, cell);
	}
	
	public void setCell(int x, int y, Cell cell) {
		if (this.validPosition(x, y)) {
			this.terrain[x][y] = cell;
		} else {
			throw new InvalidPositionException(this, new Point(x, y));
		}
	}
	
	private boolean validPosition(int x, int y) {
		return (x >= 0 && y >= 0 && y < this.height && x < this.width);
	}

	
	public void cellsAccept(CellVisitor visitor) {
		for (int y = 0; y < this.height; y++)
			for (int x = 0; x < this.width; x++)
				this.getCell(x, y).accept(visitor);
	}
	

}
