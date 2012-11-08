package backend;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import backend.board.Board;
import backend.movable.Player;

/**
 * Implementa una clase que crea una instancia de
 * backend.Game a partir de un archivo que contiene
 * una partida que fue guardada con anterioridad
 * utilizando serialización.
 * 
 * @author enzo
 *
 */
public class GameMapDeserializer extends GameLoader {

	@Override
	public Game loadGame(File source) throws Exception {
		String fileName = source.getAbsolutePath();
		
		ObjectInputStream file = new ObjectInputStream(
		           				new BufferedInputStream(
		           				new FileInputStream(fileName)));
		
		/* Obtengo el board, player y el mapa de la partida guardada */
		Board loadedBoard = (Board) file.readObject();
		Player loadedPlayer = (Player) file.readObject();
		
		Game currentGameLogic = new Game(loadedBoard, loadedPlayer);
		loadedPlayer.setGame(currentGameLogic);
		
		setLoadedMap((File) file.readObject());
		
		file.close();
		
		return currentGameLogic;
	}

	/**
	 * Devuelve el nombre de la carpeta de donde se sacaran las partidas
	 * serializadas.
	 */
	@Override
	public String getCurrentDirectory() {
		return "saved_games";
	}

}
