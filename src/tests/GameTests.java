package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.File;

import frontend.Parser;

import org.junit.Test;

import backend.board.Direction;
import backend.Game;
import backend.cell.*;
import backend.movable.*;

public class GameTests {
	Game game;
	Parser parser = new Parser();
	Player player;
	
	
	@Test
	public void playerMovesCorrectlyToEmptyCell() throws Exception {
		game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "testlevels"  + File.separator + "emptyBoard.lvl"));
		player = game.getPlayer();
		
		int oldRow = game.getPlayer().getPosition().y;
		game.getPlayer().move(Direction.NORTH);
		int newRow = game.getPlayer().getPosition().y;
		assertEquals(oldRow-1, newRow);
		oldRow = game.getPlayer().getPosition().y;
		game.getPlayer().move(Direction.SOUTH);
		newRow = game.getPlayer().getPosition().y;
		assertEquals(oldRow+1, newRow);
		
		int oldCol = game.getPlayer().getPosition().x;
		game.getPlayer().move(Direction.EAST);
		int newCol = game.getPlayer().getPosition().x;
		assertEquals(oldCol+1, newCol);
		oldCol = game.getPlayer().getPosition().x;
		game.getPlayer().move(Direction.WEST);
		newCol = game.getPlayer().getPosition().x;
		assertEquals(oldCol-1, newCol);
	}
	
	@Test
	public void playerPushesCorrectlyBoxToEmptyCell() throws Exception{
				
		game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "testlevels"  + File.separator + "surroundedByBoxes.lvl"));
			player = game.getPlayer();
			
			if(playerIsSurroundedByBoxes(game)){
				throw new Exception("Invalid testing board.");
			}
 			
 			player.move(Direction.NORTH);
 			assertTrue(game.getBoard().getCell(Direction.SOUTH.increment(player.getPosition())).isOccupiable());
 			assertTrue(!game.getBoard().getCell(Direction.NORTH.increment(player.getPosition())).isOccupiable());
 			player.move(Direction.SOUTH);
 			
 			player.move(Direction.SOUTH);
 			assertTrue(game.getBoard().getCell(Direction.NORTH.increment(player.getPosition())).isOccupiable());
 			assertTrue(!game.getBoard().getCell(Direction.SOUTH.increment(player.getPosition())).isOccupiable());
 			player.move(Direction.NORTH);
 			
 			player.move(Direction.EAST);
 			assertTrue(game.getBoard().getCell(Direction.WEST.increment(player.getPosition())).isOccupiable());
 			assertTrue(!game.getBoard().getCell(Direction.EAST.increment(player.getPosition())).isOccupiable());	
 			player.move(Direction.WEST);
 			
 			player.move(Direction.WEST);
 			assertTrue(game.getBoard().getCell(Direction.EAST.increment(player.getPosition())).isOccupiable());
 			assertTrue(!game.getBoard().getCell(Direction.WEST.increment(player.getPosition())).isOccupiable());	
 			
 }
	/**
	 * Checks if the player is surrounded by boxes, and if next to the boxes there is available space to move
	 *
	 * @param game
	 * @return true if the game is the correct one for the test, false otherwise.
	 */
	 private boolean playerIsSurroundedByBoxes(Game game){
		 return !((game.getBoard().getCell(Direction.SOUTH.increment(player.getPosition())).getMovable() instanceof Box) &&
				 game.getBoard().getCell(Direction.SOUTH.increment(Direction.SOUTH.increment(player.getPosition()))).isOccupiable() &&
			(game.getBoard().getCell(Direction.NORTH.increment(player.getPosition())).getMovable() instanceof Box) &&
			game.getBoard().getCell(Direction.NORTH.increment(Direction.NORTH.increment(player.getPosition()))).isOccupiable() &&
				(game.getBoard().getCell(Direction.EAST.increment(player.getPosition())).getMovable() instanceof Box) &&
				game.getBoard().getCell(Direction.EAST.increment(Direction.EAST.increment(player.getPosition()))).isOccupiable() &&
						(game.getBoard().getCell(Direction.WEST.increment(player.getPosition())).getMovable() instanceof Box) &&
						game.getBoard().getCell(Direction.WEST.increment(Direction.WEST.increment(player.getPosition()))).isOccupiable());
	 }
 
 @Test
 public void playerCantMoveBoxIntoTree() throws Exception{
	 game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "testlevels"  + File.separator + "BoxesAndTrees.lvl"));
	 player = game.getPlayer();
		
 		if(game.getBoard().getCell(Direction.SOUTH.increment(player.getPosition())).isOccupiable() ||
 			game.getBoard().getCell(Direction.NORTH.increment(player.getPosition())).isOccupiable() ||
 			game.getBoard().getCell(Direction.EAST.increment(player.getPosition())).isOccupiable() ||
 			game.getBoard().getCell(Direction.WEST.increment(player.getPosition())).isOccupiable()){
 				throw new Exception("Invalid testing board. There is no box on the upward cell from where the player is standing.");
 		}
 		player.move(Direction.NORTH);
 		assertTrue(!game.getBoard().getCell(player.getPosition()).isOccupiable());
 		player.move(Direction.SOUTH);
 		assertTrue(!game.getBoard().getCell(player.getPosition()).isOccupiable());
 		player.move(Direction.EAST);
 		assertTrue(!game.getBoard().getCell(player.getPosition()).isOccupiable());
 		player.move(Direction.WEST);
 		assertTrue(!game.getBoard().getCell(player.getPosition()).isOccupiable());
 }
 
 @Test
 public void playerCantMoveTwoBoxesSimultaneously() throws Exception{
	game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "testlevels"  + File.separator + "simultaneousBoxes.lvl"));
	player = game.getPlayer();
	
	if(!playerIsSurroundedByDoubleBoxes(game)){
		throw new Exception("Invalid testing board.");
	}
	
	player.move(Direction.NORTH);
	assertTrue(game.getBoard().getCell(new Point(player.getPosition().x,player.getPosition().y-3)).isOccupiable());
	assertTrue(!game.getBoard().getCell(player.getPosition()).isOccupiable());
	
	player.move(Direction.SOUTH);
	assertTrue(game.getBoard().getCell(new Point(player.getPosition().x,player.getPosition().y+3)).isOccupiable());
	assertTrue(!game.getBoard().getCell(player.getPosition()).isOccupiable());
				
	player.move(Direction.EAST);
	assertTrue(game.getBoard().getCell(new Point(player.getPosition().x+3,player.getPosition().y)).isOccupiable());
	assertTrue(!game.getBoard().getCell(player.getPosition()).isOccupiable());
				
	player.move(Direction.WEST);
	assertTrue(game.getBoard().getCell(new Point(player.getPosition().x-3,player.getPosition().y)).isOccupiable());
	assertTrue(!game.getBoard().getCell(player.getPosition()).isOccupiable());	
 }
 /** Checks if player is surrounded by rows and cols of 2 boxes.
  *   
  *      B
  *      B
  *  B B P B B
  *      B
  *      B 
  */  
 private boolean playerIsSurroundedByDoubleBoxes(Game game){
	 return !(game.getBoard().getCell(Direction.SOUTH.increment(player.getPosition())).isOccupiable() &&
		game.getBoard().getCell(Direction.SOUTH.increment(Direction.SOUTH.increment(player.getPosition()))).isOccupiable() &&
		game.getBoard().getCell(Direction.NORTH.increment(player.getPosition())).isOccupiable() &&
			game.getBoard().getCell(Direction.NORTH.increment(Direction.NORTH.increment(player.getPosition()))).isOccupiable() &&
			game.getBoard().getCell(Direction.EAST.increment(player.getPosition())).isOccupiable() &&
					game.getBoard().getCell(Direction.EAST.increment(Direction.EAST.increment(player.getPosition()))).isOccupiable() &&
					game.getBoard().getCell(Direction.WEST.increment(player.getPosition())).isOccupiable() &&
 					game.getBoard().getCell(Direction.WEST.increment(Direction.WEST.increment(player.getPosition()))).isOccupiable());
 }
 
 @Test
 public void playerCantMoveIntoTree() throws Exception{
	game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "testlevels"  + File.separator + "surroundedByTrees.lvl"));
	player = game.getPlayer();
	
	
		if(!playerIsSurroundedByTrees(game)) {
				throw new Exception("Invalid testing board. Player isn't surrounded by trees.");
		}
		
		player.move(Direction.NORTH);
		assertTrue(!game.getBoard().getCell(player.getPosition()).isOccupiable());
		player.move(Direction.SOUTH);
		assertTrue(!game.getBoard().getCell(player.getPosition()).isOccupiable());
		player.move(Direction.EAST);
		assertTrue(!game.getBoard().getCell(player.getPosition()).isOccupiable());
		player.move(Direction.WEST);
		assertTrue(!game.getBoard().getCell(player.getPosition()).isOccupiable());
}

 private boolean playerIsSurroundedByTrees(Game game){
	 return (game.getBoard().getCell(Direction.SOUTH.increment(player.getPosition())) instanceof Tree) ||
				(game.getBoard().getCell(Direction.NORTH.increment(player.getPosition())) instanceof Tree) ||
				game.getBoard().getCell(Direction.EAST.increment(player.getPosition())) instanceof Tree ||
				game.getBoard().getCell(Direction.WEST.increment(player.getPosition())) instanceof Tree;
 }
 
 /* Test highly depends on the level to be loaded since cell coordinates are hardcoded. */
 @Test
 public void iceCubeMovesToLastPossiblePlace() throws Exception{
	 game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "testlevels"  + File.separator + "iceCubeMovementTest.lvl"));
	 player = game.getPlayer();
	 Point p = new Point(3,1);
	 // TODO chequeo de que el lvl cargado es el correcto.
	 
	 assertTrue(game.getBoard().getCell(p).isOccupiable());
	 player.move(Direction.NORTH);
	 assertTrue(!game.getBoard().getCell(p).isOccupiable());
	 player.move(Direction.SOUTH);
	 
	 p = new Point(3,8);
	 
	 assertTrue(game.getBoard().getCell(p).isOccupiable());
	 player.move(Direction.SOUTH);
	 assertTrue(!game.getBoard().getCell(p).isOccupiable());
	 player.move(Direction.NORTH);
	 
	 p = new Point(7,5);
	 
	 assertTrue(game.getBoard().getCell(p).isOccupiable());
	 player.move(Direction.EAST);
	 assertTrue(!game.getBoard().getCell(p).isOccupiable());
	 player.move(Direction.WEST);
	 
	 p = new Point(1,5);
	 
	 assertTrue(game.getBoard().getCell(p).isOccupiable());
	 player.move(Direction.WEST);
	 assertTrue(!game.getBoard().getCell(p).isOccupiable());
 }
 
 
 @Test
 public void gameEndsWhenPlayerFallsInTheWater() throws Exception{
	 game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "testlevels"  + File.separator + "surroundedByWater.lvl"));
	 player = game.getPlayer();
	 
	 player.move(Direction.NORTH);
	// assertTrue(game.loseConditions()); 
	// TODO modificar el assert de arriba con la linea correcta que indica si se perdio el juego
	 }
@Test
 public void iceCubeDisappearsWhenFallingIntoWater() throws Exception{
	 game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "testlevels"  + File.separator + "waterInCubesPath.lvl"));
	 player = game.getPlayer();
	 
	 player.move(Direction.EAST);
	 /*Checks that the ice cube is not on the cell before the water, nor after.*/
	 assertTrue(game.getBoard().getCell(new Point(5,2)).isOccupiable());
	 assertTrue(game.getBoard().getCell(new Point(7,2)).isOccupiable());
 }
 
 @Test(expected=Exception.class)
 public void parserThrowsExceptionIfTwoPlayersAreFound() throws Exception{
	 game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "testlevels"  + File.separator + "twoPlayers.lvl"));
 }
 
 @Test(expected=Exception.class)
 public void parserThrowsExceptionIfNoDestinationIsFound() throws Exception{
	 game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "testlevels"  + File.separator + "noDestination.lvl"));
 }
 
 
// @Test
// public void gameEndsWhenPlayerIsInDestination() throws Exception{
//	 game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "src"  + File.separator +
//					"tests" + File.separator + "winConditionsTestBoard.lvl"));
//	 
//	 game.getPlayer().move(Direction.NORTH);
//	 game.getPlayer().move(Direction.EAST);
//	 game.getPlayer().move(Direction.SOUTH);
//	 assertTrue(game.winConditions());
// }
	

 
// @Test(expected=Exception.class)
// public void parserThrowsExceptionIfLowerBoundsArentRespected() throws Exception{
//	  	parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "src"  + File.separator +
//				"tests" + File.separator + "outOfLowerBounds.lvl"));
// }
// 
// @Test(expected=Exception.class)
// public void parserThrowsExceptionIfUpperBoundsArentRespected() throws Exception{
//	  	parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "src"  + File.separator +
//				"tests" + File.separator + "outOfUpperBounds.lvl"));
// }
		
}

