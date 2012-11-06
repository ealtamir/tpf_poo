package frontend;

import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoadGame extends StartGame {
	
	/** 
	 * Carga el juego a partir de un juego salvado anteriormente.
	 * @param gameScreen
	 */
	
	public LoadGame(JFrame gameScreen) {
		this.gameScreen = gameScreen;
	}
	
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
