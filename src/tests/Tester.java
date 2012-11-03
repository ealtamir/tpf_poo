package tests;


import gameUtils.Parser;

import java.awt.Point;
import java.io.File;

import backend.board.*;
import backend.movable.*;
import backend.*;
//import backend.cell.*;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Game testGame = new BasicTestGame();
		// Player player = testGame.getPlayer();
		Game parsedGame;

		try{
			File f = new File(new File(".").getCanonicalPath() + File.separator + "src" + File.separator
					+ "tests" + File.separator + "testmap");
		parsedGame = (new Parser()).parse(f);
		}
		catch(Exception e){
			e.printStackTrace();
			return;
			
		}
		
		Player player = parsedGame.getPlayer();

		player.move(Direction.EAST);
		player.move(Direction.EAST);
		player.move(Direction.EAST);
		player.move(Direction.SOUTH);
		player.move(Direction.SOUTH);
		
		player.move(Direction.EAST);
		player.move(Direction.SOUTH);
		player.move(Direction.SOUTH);
		
		player.move(Direction.WEST);
		player.move(Direction.WEST);
		
		player.move(Direction.NORTH);
		player.move(Direction.WEST);
		
		player.move(Direction.SOUTH);
		player.move(Direction.SOUTH);
		player.move(Direction.SOUTH);
		player.move(Direction.SOUTH);
		
		player.move(Direction.EAST);
		player.move(Direction.EAST);
		player.move(Direction.EAST);
		player.move(Direction.EAST);
		player.move(Direction.EAST);
		player.move(Direction.EAST);
		
//		player.move(Direction.SOUTH);
//		
//		for (int i = 0; i < 8; i++) {
//			player.move(Direction.EAST);
//		}
//		
//		player.move(Direction.WEST);
//		
//		player.move(Direction.NORTH);
//		player.move(Direction.NORTH);
		
//		testGame.getBoard().cellsAccept(new TestPrintCellVisitor());
		
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
