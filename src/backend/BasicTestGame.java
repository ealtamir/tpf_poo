package backend;

import backend.board.BasicTestBoard;
import backend.movable.IceCube;
import backend.movable.Player;
import java.awt.Point;

public class BasicTestGame extends Game {
	public BasicTestGame() {
		super(new BasicTestBoard(10, 10), new Point(7, 8));		
		
		(new IceCube(this.getBoard(), new Point(4, 4))).insert();
		
	}
}
