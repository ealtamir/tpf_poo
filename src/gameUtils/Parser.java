package gameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.awt.Point;

import backend.board.*;
import backend.cell.*;
import backend.Game;
import backend.movable.*;

public class Parser {
	public Game parse(File f) throws Exception{
		Game parsedGame = new Game();
		Board parsedBoard;
		Player parsedPlayer = null;
		BufferedReader inStream = null;
	
		try{
			//	char[] lineParts = null;
			inStream = new BufferedReader(new FileReader(f));
			String line = inStream.readLine();
			int columns = line.length();
			
			inStream = new BufferedReader(new FileReader(f));
			int rows = 1;
			
			/* Count rows */
			for(int i; (i = inStream.read()) != -1; ){
				if(i == '\n'){
					rows++;
				}
			}
			/* Makes sure the level has a reasonable minimal space*/
			if ( rows < 4 || columns < 4){
				throw new InvalidFileException("Rows or columns go out of bounds. Both must be 5 or greater.");					
			}
			
			parsedBoard = new Board(rows+2, columns+2);
			initializeBoard(rows+2, columns+2, parsedBoard);
			
			inStream = new BufferedReader(new FileReader(f));
			line = inStream.readLine();
			for(int row = 1; line != null && line.length() == columns; row++){
				if(line.length() != columns){
					throw new InvalidFileException("Some line contains more columns than the others.");
				}
				
				char[] dividedLine = line.toCharArray();
				for(int col = 1; col < columns+1; col++){
					Point position = new Point(row,col);
					switch(dividedLine[col-1]){
					case 'T': 	parsedBoard.setCell(position, new Tree(position));
						break;
					case 'B': 	parsedBoard.setCell(position, new Floor(new Box(parsedBoard, position),position ));
						break;
					case 'C': 	parsedBoard.setCell(position, new Floor(new IceCube(parsedBoard, position),position ));
						break;
					case 'K': 	parsedBoard.setCell(position, new Switch(position));
						break;
					case '@': 	if(parsedPlayer != null){
									throw new InvalidFileException("Level contains more than one player.");
								}
								parsedBoard.setCell(position, new Floor(parsedPlayer = new Player(parsedGame, parsedBoard, position),position));
						break;
					case 'G': 	parsedBoard.setCell(new Point(row,col), new Destination(new Point(row,col)));
						break;
					case '#': 	parsedBoard.setCell(new Point(row,col), new Water(new Point(row,col)));
						break;
					case ' ': 	parsedBoard.setCell(position, new Floor(position));
						break;
					default: 	throw new InvalidFileException("Some line contains an invalid character.");
					}	
				}
				line = inStream.readLine();
			}
			
			parsedGame.setBoard(parsedBoard);
			parsedGame.setPlayer(parsedPlayer);
			
			return parsedGame;
			
		}
		catch(Exception e){
			throw new InvalidFileException(e.getMessage());			
		}
		finally{
			if(inStream != null){
				inStream.close();	
			}
		}

	}
	/** Initializes a board filling the extreme rows and cols with Trees
	 * and the rest of the cells with floors.
	 */
	private void initializeBoard(int rows, int cols, Board parsedBoard){
		for(int i = 1; i < rows-1; i++){  
			for(int j = 1; j < cols-1; j++){
				parsedBoard.setCell(new Point(i,j), new Floor(null, new Point(i,j)));
			}
		}
		
		for(int i = 0; i < rows; i++){
			parsedBoard.setCell(new Point(i,0), new Tree(new Point(i,0)));
			parsedBoard.setCell(new Point(i,cols-1), new Tree(new Point(i,cols-1)));
		}
		
		for(int j = 1; j < cols-1; j++){
			parsedBoard.setCell(new Point(0,j), new Tree(new Point(0,j)));
			parsedBoard.setCell(new Point(rows-1 ,j), new Tree(new Point(rows-1 ,j)));
		}
	}
	
	private static class InvalidFileException extends Exception	{
		private static final long serialVersionUID = 1L;
		InvalidFileException(){
		}
		
		InvalidFileException(String msg){
			super(msg);
		}
		
	}
}
