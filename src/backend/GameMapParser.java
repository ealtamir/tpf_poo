package backend;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

import java.awt.Point;

import java.util.Stack;


import backend.board.*;
import backend.cell.*;
import backend.movable.*;

/**
 * Parser que crea una instancia de backend.Game a partir
 * de un archivo que contiene un mapa. Para señalar cada tipo
 * de objeto dentro del mapa se utiliza la siguiente convención:
 * 
 * 	@: Jugador
 *	G: Destino
 *	#: Agua
 *	B: Caja
 *	C: Cubo de hielo
 *	K: Interruptor
 *	T: Árbol
 *
 */
public class GameMapParser extends GameLoader {
	
	final private int MINIMUM_ROWS = 5;
	final private int MINIMUM_COLS = 5;
	
	@Override
	public Game loadGame(File source) throws Exception {
		setLoadedMap(source);
		return parse(source);
	}	
	
	@SuppressWarnings("resource")
	public Game parse(File f) throws Exception {
		Game parsedGame = new Game();
		Board parsedBoard;
		Player parsedPlayer = null;
		Destination parsedDestination = null;
		Switch parsedSwitch = null;
		BufferedReader inStream = null;
		Stack<Switch> switchStack = new Stack<Switch>();
		
	
		try{
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
				throw new InvalidFileException("Rows or columns go out of bounds. Both must be 5 or greater.");					
			}
			
			parsedBoard = new Board(rows, columns);
		
			inStream = new BufferedReader(new FileReader(f));
			line = inStream.readLine();
			for(int row = 0; line != null; row++){
				if(line.length() != columns){
					throw new InvalidFileException("Some lines contain more columns than others." +
							"Please make sure that there are no blank spaces changing the lenght of the rows");
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
					case 'K': 	if( parsedSwitch != null){
								throw new InvalidFileException("Level contains more than one switch.");
								}
								parsedSwitch = new Switch(position);
								switchStack.push(parsedSwitch);
								parsedBoard.setCell(position, parsedSwitch);
						break;
					case '@': 	if(parsedPlayer != null){
									throw new InvalidFileException("Level contains more than one player.");
								}
								parsedBoard.setCell(position, new Floor(parsedPlayer = new Player(parsedGame, parsedBoard, position),position));
						break;
					case 'G': 	if (parsedDestination == null) {
								parsedDestination = new Destination(position);
								parsedBoard.setCell(position, parsedDestination);
								}
								else {
									throw new InvalidFileException("Level contains more than one destination.");
								}
								
						break;
					case '#': 	parsedBoard.setCell(position, new Water(position));
						break;
					case ' ': 	parsedBoard.setCell(position, new Floor(position));
						break;
					default: 	throw new InvalidFileException("Some line contains an invalid character.");
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
				throw new InvalidFileException("Level contains no destination.");
			}
			
			if(parsedPlayer == null){
				throw new InvalidFileException("No player starting position was specified on the level file.");
			}
			
			if(parsedSwitch == null){
				throw new InvalidFileException("No switch was specified on the level file.");
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

	/** 
	 * Regresa el nombre del directorio donde se encuentras los archivos
	 * que representan los mapas a parsear.
	 */
	@Override
	public String getCurrentDirectory() {
		return "levels";
	}


}
