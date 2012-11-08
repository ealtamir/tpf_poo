package backend;

import java.io.File;

/**
 * Clase abstracta que declara un método para cargar
 * un juego a partir de un archivo. Adicionalmente crea 
 * la variable de instancia "loadedMap" que guarda el 
 * archivo que representa el mapa del juego. Es necesario
 * contar con esta variable para poder obtener el mapa 
 * en caso de que la partida haya sido cargada a partir de
 * un juego serializado.
 * 
 * @author enzo
 *
 */
public abstract class GameLoader {
	
	private File loadedMap;
	
	public File getLoadedMap() {
		return this.loadedMap;
	}
	protected void setLoadedMap(File loadedMap) {
		this.loadedMap = loadedMap;
	}
	
	public abstract Game loadGame(File source) throws Exception;
	public abstract String getCurrentDirectory();
}
