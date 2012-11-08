package backend;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import backend.board.Board;
import backend.movable.Player;

public class GameMapDeserializer extends GameLoader {

	@Override
	public Game loadGame(File source) throws Exception {
		String fileName = source.getAbsolutePath();
		
		ObjectInputStream file = new ObjectInputStream(
		           				new BufferedInputStream(
		           				new FileInputStream(fileName)));
		
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
