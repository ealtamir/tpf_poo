package backend.board;

import java.awt.Point;

import backend.cell.*;
import backend.movable.Player;

public class BasicTestBoard extends Board {
	
	public BasicTestBoard(int height, int width) {
		super(height, width);
		
		try {
			
			Destination dest = new Destination(new Point(9,9));
			Switch sw = new Switch(new Point(4, 4));
			sw.setDestination(dest);
			
			
			
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					if (x == 0 || y == 0 || x == (width-1) || y == (height-1)) {
						this.setCell(x, y, new Tree(new Point(x, y)));						
					}
					else if (y == 7) {
						this.setCell(x, y, new Water(new Point(x, y)));
					}
					else {
						this.setCell(x, y, new Floor(new Point(x,y)));
					}
				}
			}
			
			
			this.setCell(8, 8, dest);
			this.setCell(4, 4, sw);
			
			
		} catch (InvalidPositionException e) {
			System.err.println("Error while creating board: " + e);
		}
	}

}
