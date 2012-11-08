package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

import backend.GameMapDeserializer;
import backend.GameMapParser;
import frontend.LoadPanel;


/**
 * En esta clase empieza el programa. Ella se encarga de instanciar
 * el JFrame inicial que es la primera ventana que ve el usuario. 
 * 
 * @author enzo
 *
 */
public class Main {
	
	private JFrame gameScreen;
	
	public static void main(String[] args) {
		new Main("TP POO"); 
	}
	
	public JFrame getGameScreen() {
		return gameScreen;
	}
	
	public Main(String gameName) {
		gameScreen = new JFrame(gameName);
		LoadPanel lpanel = new LoadPanel();
		gameScreen.add(lpanel);
		gameScreen.setSize(lpanel.getWidth() , lpanel.getHeight());
		gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameScreen.setVisible(true);
		gameScreen.setResizable(false);
		centerScreen(gameScreen);
		
		/**
		 * Sirve para empezar un juego nuevo. 
		 * Le agrega al botón "Start" un listener que le pide al
		 * usurio un archivo mapa de dónde crear el juego nuevo.
		 */
		lpanel.getStartButton().addActionListener(
			new GameStarter(gameScreen, new GameMapParser())
		);
		
		/**
		 * Sirve para cargar una partida guardada con anterioridad.
		 * Le agrega al botón "Load" un listener que le pide al
		 * usurio un archivo que contiene una partida serializada. 
		 */
		lpanel.getLoadButton().addActionListener(
			new GameStarter(gameScreen, new GameMapDeserializer())
		);
		
	}
	
	private void centerScreen(JFrame screen) {
		Toolkit toolkit = screen.getToolkit();
	    Dimension size = toolkit.getScreenSize();
	    screen.setLocation(size.width/2 - screen.getWidth()/2,
	                  size.height/2 - screen.getHeight()/2);
	}
}
