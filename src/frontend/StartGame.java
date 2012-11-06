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


import java.util.Date;

public class StartGame implements ActionListener {

	private Game currentGameGraphics;
	
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

		int ret = fileopen.showDialog(gameScreen.getContentPane(), "Open file");
		if (ret == JFileChooser.APPROVE_OPTION) {
			currentMap = fileopen.getSelectedFile();
			startGameFromLevel();
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
				backToMenu();
			}
		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGameFromLevel();
			}
		});
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("ddMMyyyHHmmss");
				Date date = new Date();
				String date_str = dateFormat.format(date);
				
				String file_name = "saved_games" + File.separator + "game_" + date_str + ".game";
				try {
					ObjectOutputStream file = new ObjectOutputStream(
							new BufferedOutputStream(
									new FileOutputStream(file_name)));
					file.writeObject(currentGameLogic);
					file.writeObject(currentMap);
					file.close();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(gameScreen, e1.getMessage());
					e1.printStackTrace();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(gameScreen, e1.getMessage());
					e1.printStackTrace();
				} finally {
					backToMenu();
				}
				
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
	private void startGameFromLevel() {
		try {
			currentGameLogic = (new Parser()).parse(currentMap);
		} catch (InvalidFileException exception) {
			JOptionPane.showMessageDialog(gameScreen, exception.getMessage());
		} catch (Exception exception) {
			if (exception.getMessage() == null) {
				JOptionPane.showMessageDialog(gameScreen, "Error al intentar cargar el mapa.");
			} else {
				JOptionPane.showMessageDialog(gameScreen, exception.getMessage());
			}
			exception.printStackTrace();
			gameScreen.setVisible(true);
		}
		startNewGame();
	}
	
	protected void startNewGame() {
		gameScreen.setVisible(false);
		currentGameGraphics = new Game(
				"Silversphere", 
				currentGameLogic.getBoard().getHeight(),
				currentGameLogic.getBoard().getWidth(),
				currentGameLogic,
				createMenuBar()
		);
	}
	
	private void backToMenu() {
		currentGameGraphics.setVisible(false);
		currentGameGraphics = null;
		gameScreen.setVisible(true);
	}
}
