package backend.board;

import java.awt.Point;

import backend.cell.*;

public class BasicTestBoard extends Board {
	
	public BasicTestBoard(int width, int height) {
		super(width, height);
		
		Destination dest = new Destination(new Point(8,8));
		Switch sw = new Switch(new Point(4, 4));
		sw.setDestination(dest);
		
		
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x == 0 || y == 0 || x == (width-1) || y == (height-1)) {
					if (y % 2 == 0)
						this.setCell(x, y, new Tree(new Point(x, y)));
					else
						this.setCell(x, y, new Floor(new Point(x,y)));

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
	}

}
