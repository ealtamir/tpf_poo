package backend;

import backend.board.BasicTestBoard;
import backend.movable.*;
import java.awt.Point;

public class BasicTestGame extends Game {
	public BasicTestGame() {
		super(new BasicTestBoard(15, 11), new Point(1, 1));		
		
		(new IceCube(this.getBoard(), new Point(4, 3))).insert();
		(new Box(this.getBoard(), new Point(4, 5))).insert();
		
	}
}
