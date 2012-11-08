package backend.board;
import java.awt.Point;
import java.io.Serializable;
import java.util.NoSuchElementException;

import java.util.Iterator;

import backend.cell.*;

public class Board implements Iterable<Cell>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Cell terrain[][];
	private int columns;
	private int rows;
	
	private class BoardIterator implements Iterator<Cell> {
		
		private int cellIndex = 0;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cellIndex < columns*rows;
		}
		
		@Override
		public Cell next() {
			if (this.hasNext()) {
				Cell cell = getCell(cellIndex % rows, cellIndex/rows);
				cellIndex++;
				return cell;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("Cannot remove cell from board.");
		}
		
	}
	
	/**
	 * Crea un tablero nuevo de alto height y ancho width.
	 * @param columns Ancho del tablero
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
	
	private Cell getCell(int row, int column) {
		if (this.validPosition(row, column)) {
			return this.terrain[row][column];
		} else {
			throw new InvalidPositionException(this, new Point(row, column));
		}
	}
	
	public void setCell(Point p, Cell cell) {
		this.setCell(p.y, p.x, cell);
	}
	
	private void setCell(int row, int column, Cell cell) {
		if (this.validPosition(row, column)) {
			this.terrain[row][column] = cell;
			cell.wasChanged();
		} else {
			throw new InvalidPositionException(this, new Point(column, row));
		}
	}
	
	private boolean validPosition(int row, int column) {
		return (column >= 0 && row >= 0 && row < this.rows && column < this.columns);
	}
	
	
	@Override
	public Iterator<Cell> iterator() {
		// TODO Auto-generated method stub
		return new BoardIterator();
	}
	

	
	public int getWidth() {
		return columns;
	}

	public int getHeight() {
		return rows;
	}

}
