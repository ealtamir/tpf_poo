package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import backend.Game;
import backend.GameLoader;
import backend.GameMapParser;

/**
 * Esta clase se encarga de inicializar el juego. Obtiene
 * del usuario un archivo que luego le pasa a una subclase
 * de la clase abstracta backend.GameLoader la cual devuelve
 * una instancia de backend.Game (un objeto que representa la
 * lógica del juego). Adicionalmente implementa algunos métodos
 * auxiliares que se encargan de manejar las ventanas que se
 * le muestran al usuario.
 * 
 * @author enzo
 *
 */
public class GameStarter implements ActionListener {

	private GameLoader sourceType;
	
	private GameFrame currentGameGraphics;
	private Game currentGameLogic;
	private JFrame gameScreen;
	private File currentMap;
	
	public void setCurrentMap(File currentMap) {
		this.currentMap = currentMap;
	}
	
	public GameStarter(JFrame gameScreen, GameLoader sourceType) {
		this.gameScreen = gameScreen;
		this.sourceType = sourceType;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileopen = new JFileChooser();
		fileopen.setCurrentDirectory(new File(sourceType.getCurrentDirectory()));

		int ret = fileopen.showDialog(gameScreen.getContentPane(), "Open file");
		if (ret == JFileChooser.APPROVE_OPTION) {
			try {
				currentGameLogic = sourceType.loadGame(fileopen.getSelectedFile());
				currentMap = sourceType.getLoadedMap();
				startNewGame();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(gameScreen, e1.getMessage());
				e1.printStackTrace();
				backToMenu();
			}
		}
	}
	
	/**
	 * Carga un nuevo juego. 
	 * 
	 * Notar que el método espera que la lógica de juego a utilizar 
	 * esté cargada en la variable de instancia"currentGameLogic" y 
	 * que el mapa siendo utilizado se encuentre en la variable de instancia
	 * "currentMap".
	 * 
	 */
	public void startNewGame() {
		gameScreen.setVisible(false);
		currentGameGraphics = new GameFrame(
			"Silversphere", 
			this
		);
	}
	
	/**
	 * Se encarga de mostrarle al usuario la ventana de inicio. Al mismo
	 * tiempo termina el juego actual. 
	 * 
	 */
	public void backToMenu() {
		currentGameGraphics.setVisible(false);
		currentGameGraphics = null;
		gameScreen.setVisible(true);
	}
	
	/**
	 * Carga un nuevo juego a partir de un archivo que representa un mapa.
	 * De ocurrir algún problema durante el parseo del mapa, el método 
	 * muestra una ventana de error, imprime el stack trace y regresa al menú
	 * principal.
	 * 
	 */
	public void startGameFromCurrentMap() {
		try {
			currentGameLogic = (new GameMapParser()).parse(currentMap);
			startNewGame();
		} catch (Exception exception) {
			if (exception.getMessage() == null) {
				JOptionPane.showMessageDialog(gameScreen, "Error al intentar cargar el mapa.");
			} else {
				JOptionPane.showMessageDialog(gameScreen, exception.getMessage());
			}
			exception.printStackTrace();
			gameScreen.setVisible(true);
		}
	}

	/** 
	 * Este método crea el objeto JMenuBar que representa la barra
	 * de menú dentro de la ventana de juego. El objeto devuelto
	 * cuenta con un botón llamado "File", que le da al usuario la opción
	 * de elegir entre varias acciones. Los listener de todos estos botones
	 * se implementa dentro de esta clase.
	 * 
	 * @return Objeto JMenuBar para ser usado por el JFrame que contiene
	 * 	al juego.
	 */
	public JMenuBar createMenuBar() {
		return new GameMenuBar(this);
	}
	
	
	public GameFrame getCurrentGameGraphics() {
		return currentGameGraphics;
	}
	public void setCurrentGameGraphics(GameFrame currentGameGraphics) {
		this.currentGameGraphics = currentGameGraphics;
	}

	public Game getCurrentGameLogic() {
		return currentGameLogic;
	}

	public JFrame getGameScreen() {
		return gameScreen;
	}

	public File getCurrentMap() {
		return currentMap;
	}
}
	
