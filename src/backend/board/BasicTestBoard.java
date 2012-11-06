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
				Point currentPosition = new Point(x, y);
				if (x == 0 || y == 0 || x == (width-1) || y == (height-1)) {
					if (y % 2 == 0)
						this.setCell(currentPosition, new Tree(currentPosition));
					else
						this.setCell(currentPosition, new Floor(currentPosition));

				}
				else if (y == 7) {
					this.setCell(currentPosition, new Water(currentPosition));
				}
				else {
					this.setCell(currentPosition, new Floor(currentPosition));
				}
			}
		}
		
		
		this.setCell(dest.getPosition(), dest);
		this.setCell(sw.getPosition(), sw);
	}

}
