package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class StartGame implements ActionListener {

	private JFrame gameScreen;
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

		int ret = fileopen.showDialog(gameScreen.getContentPane(), "Open file");
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fileopen.getSelectedFile();
			try {
				backend.Game game = (new Parser()).parse(file);
				gameScreen.setVisible(false);
				Game gameGraphics = new Game(
						"Silversphere", 
						game.getBoard().getWidth(),
						game.getBoard().getHeight(),
						game
				);
			} catch (Exception exception) {
				if (exception.getMessage() == null) {
					JOptionPane.showMessageDialog(gameScreen, "Error al intentar cargar el mapa.");
				} else {
					JOptionPane.showMessageDialog(gameScreen, exception.getMessage());
				}
				exception.printStackTrace();
			}
		}
	}
}
