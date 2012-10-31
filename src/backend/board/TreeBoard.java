package backend.board;

import backend.cell.*;

public class TreeBoard extends Board {
	
	public TreeBoard(int height, int width) {
		super(height, width);
		
		try {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					this.setCell(x, y, new Tree());
				}
			}
		} catch (InvalidPositionException e) {
			System.err.println("Error while creating board: " + e);
		}
	}
}
