package backend;

import java.io.File;

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
