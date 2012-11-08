package backend;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

public class GameMapDeserializer extends GameLoader {

	@Override
	public Game loadGame(File source) throws Exception {
		String fileName = source.getAbsolutePath();
		
		ObjectInputStream file = new ObjectInputStream(
		           				new BufferedInputStream(
		           				new FileInputStream(fileName)));
		
		Game currentGameLogic = (backend.Game) file.readObject();
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
