package frontend;

import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Esta clase que hereda de StartGame realiza las mismas tareas que
 * su sucesor: 
 * 
 * "Se encarga de instanciar los objetos necesarios para empezar
 * un juego. ..."
 * 
 * La diferencia es que el juego es cargado a partir de un archivo
 * que contiene una partida salvada con anterioridad. Del archivo 
 * deserializa una instancia de backend.Game (representa la lógica del juego) 
 * y un archivo File (representa el mapa que se estaba usando en la partida).
 * 
 * @author enzo
 *
 */
public class LoadGame extends StartGame {
	
	/** 
	 * Carga el juego a partir de un juego salvado anteriormente.
	 * @param gameScreen
	 */
	public LoadGame(JFrame gameScreen) {
		this.gameScreen = gameScreen;
	}
	
	/**
	 * Listener para el botón de "Load" del menú inicial. La acción es darle
	 * al usuario la opción de elegir un archivo que representa una partida
	 * salvada con anterioridad. La misma se debe encontrar en estado serializado.
	 * Para cargar la partida el método deserializa el archivo y carga las variables
	 * currentGameLogic y currentMap que son variables protegidas de su superclase.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileopen = new JFileChooser();

		int ret = fileopen.showDialog(gameScreen.getContentPane(), "Open file");
		if (ret == JFileChooser.APPROVE_OPTION) {
			File selectedLevel = fileopen.getSelectedFile();
			String fileName = selectedLevel.getAbsolutePath();
			
			try {
				ObjectInputStream file = new ObjectInputStream(
				           				new BufferedInputStream(
				           				new FileInputStream(fileName)));
				
				currentGameLogic = (backend.Game) file.readObject();
				currentMap = (File) file.readObject();
				
				file.close();
				startNewGame();
			} catch (Exception e1) {				
				JOptionPane.showMessageDialog(gameScreen, "Elegiste un archivo inválido para cargar.");
				e1.printStackTrace();
				gameScreen.setVisible(true);
			}
		}
	}
}
