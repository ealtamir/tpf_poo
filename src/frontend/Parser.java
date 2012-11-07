package frontend;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

import java.awt.Point;

import java.util.Stack;

import backend.board.*;
import backend.cell.*;
import backend.Game;
import backend.movable.*;

public class Parser {
	
	final private int MINIMUM_ROWS = 5;
	final private int MINIMUM_COLS = 5;
	
	@SuppressWarnings("resource")
	public Game parse(File f) throws Exception {
		Game parsedGame = new Game();
		Board parsedBoard;
		Player parsedPlayer = null;
		Destination parsedDestination = null;
		BufferedReader inStream = null;
		Stack<Switch> switchStack = new Stack<Switch>();
		
		
	
		try{
			//	char[] lineParts = null;
			inStream = new BufferedReader(new FileReader(f));
			String line = inStream.readLine();
			int columns = line.length(); // Counts the columns in the first line
			
			inStream = new BufferedReader(new FileReader(f));
			int rows = 1;
			
			/* Count rows */
			for(int i = inStream.read(); i != -1; ){
				if(i == '\n'){
					if((i = inStream.read()) != -1){ /* Avoids a \n at the last place breaking the parser */
						rows++;	
					}
				}
				else{
					i = inStream.read();
				}
			}
			/* Makes sure the level has a reasonable minimal space */
			if ( rows < MINIMUM_ROWS || columns < MINIMUM_COLS){
				throw new InvalidFileException("El mapa debe tener más de " 
						+ MINIMUM_COLS + " columnas y más de " 
						+ MINIMUM_ROWS + " columnas.");
			}
			
			parsedBoard = new Board(rows, columns);
		
			inStream = new BufferedReader(new FileReader(f));
			line = inStream.readLine();
			for(int row = 0; line != null; row++){
				if(line.length() != columns){
					throw new InvalidFileException("Algunas líneas contienen más " 		+
							"	columnas que otras. Asegúrate que no hayan espacios " 	+
							"	en blanco cambiando la longitud de las filas.");
				}
				
				char[] dividedLine = line.toCharArray();
				for(int col = 0; col < columns ; col++){
					Point position = new Point(col,row);
					switch(dividedLine[col]){
					case 'T': 	parsedBoard.setCell(position, new Tree(position));
						break;
					case 'B': 	parsedBoard.setCell(position, new Floor(new Box(parsedBoard, position),position ));
						break;
					case 'C': 	parsedBoard.setCell(position, new Floor(new IceCube(parsedBoard, position),position ));
						break;
					case 'K': 	
						Switch newSwitch = new Switch(position);
						switchStack.push(newSwitch);
						parsedBoard.setCell(position, newSwitch);
						break;
					case '@': 	if(parsedPlayer != null){
									throw new InvalidFileException("El nivel contiene más de un jugador.");
								}
								parsedBoard.setCell(position, new Floor(parsedPlayer = new Player(parsedGame, parsedBoard, position),position));
						break;
					case 'G': 	
						
						if (parsedDestination == null) {
							parsedDestination = new Destination(position);
							parsedBoard.setCell(position, parsedDestination);
						}
						else {
							throw new InvalidFileException("El nivel contiene más de un destino.");
						}
						
						break;
					case '#': 	parsedBoard.setCell(position, new Water(position));
						break;
					case ' ': 	parsedBoard.setCell(position, new Floor(position));
						break;
					default: 	throw new InvalidFileException("Algunas líneas " +
							"contienen caracteres inválidos.");
					}	
				}
				line = inStream.readLine();
			}
			
			/*
			 * Connect loaded switches to destination.
			 */
			if (parsedDestination != null) {
				while (!switchStack.empty()) {
					switchStack.pop().setDestination(parsedDestination);
				}
			}
			else {
				throw new InvalidFileException("El nivel no contiene ningún destino.");
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
}
