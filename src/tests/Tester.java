package tests;


import java.awt.Point;

import backend.board.*;
import backend.movable.*;
import backend.*;
//import backend.cell.*;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Game testGame = new BasicTestGame();
		
		
		testGame.getBoard().cellsAccept(new TestPrintCellVisitor());
		//testGame.getBoard().print();
		
		
//		Board testBoard = new BasicTestBoard(10, 10);
//		
//		Player player = new Player(testBoard, new Point(4, 3));
//		player.insert();
//		
//		IceCube cube = new IceCube(testBoard, new Point(5, 4));
//		cube.insert();
//		
//		Box box = new Box(testBoard, new Point(3, 4));
//		box.insert();
//		
//		// Push cube
//		player.move(Direction.EAST);
//		player.move(Direction.EAST);
//		player.move(Direction.SOUTH);
//		player.move(Direction.WEST);
//		
//		//Push Box
//		player.move(Direction.NORTH);
//		player.move(Direction.WEST);
//		player.move(Direction.WEST);
//		player.move(Direction.SOUTH);
//		player.move(Direction.SOUTH);
//		player.move(Direction.SOUTH);
//		player.move(Direction.SOUTH);
//		player.move(Direction.SOUTH);
//		
//		//Win
//		player.move(Direction.EAST);
//		player.move(Direction.EAST);
//		player.move(Direction.EAST);
//		player.move(Direction.EAST);
//		player.move(Direction.EAST);
//		//player.move(Direction.EAST);
//		//player.move(Direction.EAST);

		
		
//		testBoard.print();
			

	}

}
