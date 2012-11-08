package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import backend.GameMapParser;


import java.util.Date;

/**
 * Se encarga de instanciar los objetos necesarios para empezar
 * un juego. Además implementa un objeto JMenuBar y varios
 * listeners, que le pasa a la clase frontend.Game para que le añada
 * una barra de menú a la ventana de juego.
 * 
 * 
 * @param JFrame gameScreen, esta es la instancia de JFrame instanciada
 * 	por la ventana de inicio.
 * 
 * @author enzo
 *
 */
public class StartGame implements ActionListener {

	private GameFrame currentGameGraphics;
	
	protected backend.Game currentGameLogic;
	protected JFrame gameScreen;
	protected File currentMap;
	
	/**
	 * Carga el juego a partir de un mapa.
	 * @param gameScreen
	 */
	public StartGame(JFrame gameScreen) {
		this.gameScreen = gameScreen;
	}
	
	/**
	 * Constructor default para la subclase LoadGame.
	 */
	public StartGame() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileopen = new JFileChooser();
		fileopen.setCurrentDirectory(new File("levels"));

		int ret = fileopen.showDialog(gameScreen.getContentPane(), "Open file");
		if (ret == JFileChooser.APPROVE_OPTION) {
			currentMap = fileopen.getSelectedFile();
			startGameFromLevel();
		}
	}
	
	
}
