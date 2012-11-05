package tests;

import static org.junit.Assert.*;

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
		game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "levels"  + File.separator + "emptyBoard.lvl"));
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
				
		game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "levels"  + File.separator + "surroundedByBoxes.lvl"));
			player = game.getPlayer();
			
			if(playerIsSurroundedByBoxes(game)){
				throw new Exception("Invalid testing board.");
			}
 			
 			player.move(Direction.NORTH);
 			assertTrue(game.getBoard().getCell(player.getPosition().y+1, player.getPosition().x).isOccupiable());
 			assertTrue(!game.getBoard().getCell(player.getPosition().y-1, player.getPosition().x).isOccupiable());
 			player.move(Direction.SOUTH);
 			
 			if(game.getBoard().getCell(player.getPosition().y+1, player.getPosition().x).isOccupiable() ||
 					!game.getBoard().getCell(player.getPosition().y+2, player.getPosition().x).isOccupiable()){
 						throw new Exception("Invalid testing board. There is no box on the downward cell from where the player is standing.");
 					}
 			
 			player.move(Direction.SOUTH);
 			assertTrue(game.getBoard().getCell(player.getPosition().y-1, player.getPosition().x).isOccupiable());
 			assertTrue(!game.getBoard().getCell(player.getPosition().y+1, player.getPosition().x).isOccupiable());
 			player.move(Direction.NORTH);
 			
 			if(game.getBoard().getCell(player.getPosition().y, player.getPosition().x+1).isOccupiable() ||
 					!game.getBoard().getCell(player.getPosition().y, player.getPosition().x+2).isOccupiable()){
 						throw new Exception("Invalid testing board. There is no box on the downward cell from where the player is standing.");
 			}
 			
 			player.move(Direction.EAST);
 			assertTrue(game.getBoard().getCell(player.getPosition().y, player.getPosition().x-1).isOccupiable());
 			assertTrue(!game.getBoard().getCell(player.getPosition().y, player.getPosition().x+1).isOccupiable());	
 			player.move(Direction.WEST);
 			
 			if(game.getBoard().getCell(player.getPosition().y, player.getPosition().x-1).isOccupiable() ||
 					!game.getBoard().getCell(player.getPosition().y, player.getPosition().x-2).isOccupiable()){
 						throw new Exception("Invalid testing board. There is no box on the downward cell from where the player is standing.");
 			}
 			
 			player.move(Direction.WEST);
 			assertTrue(game.getBoard().getCell(player.getPosition().y, player.getPosition().x+1).isOccupiable());
 			assertTrue(!game.getBoard().getCell(player.getPosition().y, player.getPosition().x-1).isOccupiable());	
 			
 }
	/**
	 * Checks if the player is surrounded by boxes, and if next to the boxes there is available space to move
	 *
	 * @param game
	 * @return true if the game is the correct one for the test, false otherwise.
	 */
	 private boolean playerIsSurroundedByBoxes(Game game){
		 return !((game.getBoard().getCell(player.getPosition().x, player.getPosition().y+1).getMovable() instanceof Box) &&
				 game.getBoard().getCell(player.getPosition().x, player.getPosition().y+2).isOccupiable() &&
			(game.getBoard().getCell(player.getPosition().x, player.getPosition().y-1).getMovable() instanceof Box) &&
			game.getBoard().getCell(player.getPosition().x, player.getPosition().y-2).isOccupiable() &&
				(game.getBoard().getCell(player.getPosition().x+1, player.getPosition().y).getMovable() instanceof Box) &&
				game.getBoard().getCell(player.getPosition().x+2, player.getPosition().y).isOccupiable() &&
						(game.getBoard().getCell(player.getPosition().x-1, player.getPosition().y).getMovable() instanceof Box) &&
						game.getBoard().getCell(player.getPosition().x-2, player.getPosition().y).isOccupiable());
	 }
 
 @Test
 public void playerCantMoveBoxIntoTree() throws Exception{
	 game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "levels"  + File.separator + "BoxesAndTrees.lvl"));
	 player = game.getPlayer();
		
 		if(game.getBoard().getCell(player.getPosition().x, player.getPosition().y+1).isOccupiable() ||
 			game.getBoard().getCell(player.getPosition().x, player.getPosition().y-1).isOccupiable() ||
 			game.getBoard().getCell(player.getPosition().x+1, player.getPosition().y).isOccupiable() ||
 			game.getBoard().getCell(player.getPosition().x-1, player.getPosition().y).isOccupiable()){
 				throw new Exception("Invalid testing board. There is no box on the upward cell from where the player is standing.");
 		}
 		player.move(Direction.NORTH);
 		assertTrue(!game.getBoard().getCell(player.getPosition().x, player.getPosition().y).isOccupiable());
 		player.move(Direction.SOUTH);
 		assertTrue(!game.getBoard().getCell(player.getPosition().x, player.getPosition().y).isOccupiable());
 		player.move(Direction.EAST);
 		assertTrue(!game.getBoard().getCell(player.getPosition().x, player.getPosition().y).isOccupiable());
 		player.move(Direction.WEST);
 		assertTrue(!game.getBoard().getCell(player.getPosition().x, player.getPosition().y).isOccupiable());
 }
 
 @Test
 public void playerCantMoveTwoBoxesSimultaneously() throws Exception{
	game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "levels"  + File.separator + "simultaneousBoxes.lvl"));
	player = game.getPlayer();

	if(!playerIsSurroundedByDoubleBoxes(game)){
		throw new Exception("Invalid testing board.");
	}
	player.move(Direction.NORTH);

	assertTrue(game.getBoard().getCell(player.getPosition().y-3, player.getPosition().x).isOccupiable());
	assertTrue(!game.getBoard().getCell(player.getPosition().y, player.getPosition().x).isOccupiable());
	
	player.move(Direction.SOUTH);
	assertTrue(game.getBoard().getCell(player.getPosition().y+3, player.getPosition().x).isOccupiable());
	assertTrue(!game.getBoard().getCell(player.getPosition().y, player.getPosition().x).isOccupiable());
				
	player.move(Direction.EAST);
	assertTrue(game.getBoard().getCell(player.getPosition().y, player.getPosition().x+3).isOccupiable());
	assertTrue(!game.getBoard().getCell(player.getPosition().y, player.getPosition().x).isOccupiable());
				
	player.move(Direction.WEST);
	assertTrue(game.getBoard().getCell(player.getPosition().y, player.getPosition().x-3).isOccupiable());
	assertTrue(!game.getBoard().getCell(player.getPosition().y, player.getPosition().x).isOccupiable());	
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
	 return !(game.getBoard().getCell(player.getPosition().x, player.getPosition().y+1).isOccupiable() &&
		game.getBoard().getCell(player.getPosition().x, player.getPosition().y+2).isOccupiable() &&
		game.getBoard().getCell(player.getPosition().x, player.getPosition().y-1).isOccupiable() &&
			game.getBoard().getCell(player.getPosition().x, player.getPosition().y-2).isOccupiable() &&
			game.getBoard().getCell(player.getPosition().x+1, player.getPosition().y).isOccupiable() &&
					game.getBoard().getCell(player.getPosition().x+2, player.getPosition().y).isOccupiable() &&
					game.getBoard().getCell(player.getPosition().x-1, player.getPosition().y).isOccupiable() &&
 					game.getBoard().getCell(player.getPosition().x-2, player.getPosition().y).isOccupiable());
 }
 
 @Test
 public void playerCantMoveIntoTree() throws Exception{
	game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "levels"  + File.separator + "surroundedByTrees.lvl"));
	player = game.getPlayer();
	
	
		if(!playerIsSurroundedByTrees(game)) {
				throw new Exception("Invalid testing board. Player isn't surrounded by trees.");
		}
		
		player.move(Direction.NORTH);
		assertTrue(!game.getBoard().getCell(player.getPosition().x, player.getPosition().y).isOccupiable());
		player.move(Direction.SOUTH);
		assertTrue(!game.getBoard().getCell(player.getPosition().x, player.getPosition().y).isOccupiable());
		player.move(Direction.EAST);
		assertTrue(!game.getBoard().getCell(player.getPosition().x, player.getPosition().y).isOccupiable());
		player.move(Direction.WEST);
		assertTrue(!game.getBoard().getCell(player.getPosition().x, player.getPosition().y).isOccupiable());
}

 private boolean playerIsSurroundedByTrees(Game game){
	 return (game.getBoard().getCell(player.getPosition().x, player.getPosition().y+1) instanceof Tree) ||
				(game.getBoard().getCell(player.getPosition().x, player.getPosition().y-1) instanceof Tree) ||
				game.getBoard().getCell(player.getPosition().x+1, player.getPosition().y) instanceof Tree ||
				game.getBoard().getCell(player.getPosition().x-1, player.getPosition().y) instanceof Tree;
 }
 
 /* Test highly depends on the level to be loaded since cell coordinates are hardcoded. */
 @Test
 public void iceCubeMovesToLastPossiblePlace() throws Exception{
	 game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "levels"  + File.separator + "iceCubeMovementTest.lvl"));
	 player = game.getPlayer();
	 
	 // TODO chequeo de que el lvl cargado es el correcto.
	 assertTrue(game.getBoard().getCell(1,3).isOccupiable());
	 player.move(Direction.NORTH);
	 assertTrue(!game.getBoard().getCell(1,3).isOccupiable());
	 player.move(Direction.SOUTH);
	 
	 assertTrue(game.getBoard().getCell(8,3).isOccupiable());
	 player.move(Direction.SOUTH);
	 assertTrue(!game.getBoard().getCell(8,3).isOccupiable());
	 player.move(Direction.NORTH);
	 
	 assertTrue(game.getBoard().getCell(5,7).isOccupiable());
	 player.move(Direction.EAST);
	 assertTrue(!game.getBoard().getCell(5,7).isOccupiable());
	 player.move(Direction.WEST);
	 
	 assertTrue(game.getBoard().getCell(5,1).isOccupiable());
	 player.move(Direction.WEST);
	 assertTrue(!game.getBoard().getCell(5,1).isOccupiable());
 }
 
 
 @Test
 public void gameEndsWhenPlayerFallsInTheWater() throws Exception{
	 game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "levels"  + File.separator + "surroundedByWater.lvl"));
	 player = game.getPlayer();
	 
	 player.move(Direction.NORTH);
	// assertTrue(game.loseConditions()); 
	// TODO modificar el assert de arriba con la linea correcta que indica si se perdio el juego
	 }
@Test
 public void iceCubeDisappearsWhenFallingIntoWater() throws Exception{
	 game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "levels"  + File.separator + "waterInCubesPath.lvl"));
	 player = game.getPlayer();
	 
	 player.move(Direction.EAST);
	 /*Checks that the ice cube is not on the cell before the water, nor after.*/
	 assertTrue(game.getBoard().getCell(2, 5).isOccupiable());
	 assertTrue(game.getBoard().getCell(2, 7).isOccupiable());
 }
 
 @Test(expected=Exception.class)
 public void parserThrowsExceptionIfTwoPlayersAreFound() throws Exception{
	 game = parser.parse(new File(new File(".").getCanonicalPath() + File.separator + "levels"  + File.separator + "twoPlayers.lvl"));
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
//	

 
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

