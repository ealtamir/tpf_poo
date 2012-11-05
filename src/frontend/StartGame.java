package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
						game,
						createMenuBar()
				);
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
	}
	
	public JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu file = new JMenu("File");
		
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem reset = new JMenuItem("Reset");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem close = new JMenuItem("Close");
		
		file.add(newGame);
		file.add(reset);
		file.add(save);
		file.add(close);
		
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Esto no es estrictamente necesario que lo hagamos.
				// Acá tengo que darle la oportunidad al jugador de cargar un nuevo mapa.
				
			}
		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Hay que hacer esto para la entrega.
				// Aca tengo que invocar el método action performed de la clase.
				
			}
		});
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Hay que hacer esto para la entrega.
				// Acá tengo que serializar el mapa a un archivo que elija el usuario.
				
			}
		});
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		menuBar.add(file);
		
		return menuBar;
	}
}
