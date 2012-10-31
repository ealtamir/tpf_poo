package tests;


import java.awt.Point;

import backend.board.*;
import backend.movable.*;
//import backend.cell.*;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Board testBoard = new BasicTestBoard(10, 10);
		
		Player player = new Player(testBoard, new Point(4, 3));
		
		IceCube cube = new IceCube(testBoard, new Point(5, 4));
		
		Box box = new Box(testBoard, new Point(4, 4));
		
		player.move(Direction.SOUTH);
		player.move(Direction.SOUTH);
		//player.move(Direction.SOUTH);
		//player.move(Direction.SOUTH);
		//player.move(Direction.SOUTH);
		

		
		
		testBoard.print();
			

	}

}
