package backend.board;
import java.awt.Point;

import backend.cell.*;

public class Board {
	
	private Cell terrain[][];
	private int width;
	private int height;
	
	/**
	 * Crea un tablero nuevo de alto height y ancho width.
	 * @param height Alto del tablero
	 * @param width Ancho del tablero
	 */
	public Board(int height, int width) {
		this.terrain = new Cell[height][width];
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
			throw new InvalidPositionException();
		}
	}
	
	public void setCell(Point p, Cell cell) {
		this.setCell(p.x, p.y, cell);
	}
	
	public void setCell(int x, int y, Cell cell) {
		if (this.validPosition(x, y)) {
			this.terrain[x][y] = cell;
		} else {
			throw new InvalidPositionException();
		}
	}
	

	private boolean validPosition(int x, int y) {
		return (x >= 0 && y >= 0 && y < this.height && x < this.width);
	}

	
	public void print() {
		int x;
		int y;
		
		try {
			for (y = 0; y < this.width; y++) {
				for (x = 0; x < this.height; x++) {
					System.out.print(this.getCell(x, y).idCharacter() + " ");
				}
				System.out.println();
			}
		} catch (InvalidPositionException e) {
			System.err.println("Stupid Error " + e);
		}
	}
	

}
