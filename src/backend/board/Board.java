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
	 * @throws InvalidPositionException
	 */
	public Cell getCell(Point p) throws InvalidPositionException {
		return this.getCell(p.x, p.y);
	}
	

	private boolean validPosition(int x, int y) {
		return (x >= 0 && y >= 0 && y < this.height && x < this.width);
	}
	
	public Cell getCell(int x, int y) throws InvalidPositionException {
		if (this.validPosition(x, y)) {
			return this.terrain[x][y];
		} else {
			throw new InvalidPositionException();
		}
	}
	
	
	public void setCell(int x, int y, Cell cell) throws InvalidPositionException {
		if (this.validPosition(x, y)) {
			this.terrain[x][y] = cell;
		} else {
			throw new InvalidPositionException();
		}
	}
	
	public void print() {
		int x;
		int y;
		
		try {
			for (x = 0; x < this.width; x++) {
				for (y = 0; y < this.height; y++) {
					System.out.print(this.getCell(x, y).idCharacter() + " ");
				}
				System.out.println();
			}
		} catch (InvalidPositionException e) {
			System.err.println("Stupid Error " + e);
		}
	}
	

}
