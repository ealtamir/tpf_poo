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
		fileopen.setCurrentDirectory(new File("levels"));

		int ret = fileopen.showDialog(gameScreen.getContentPane(), "Open file");
		if (ret == JFileChooser.APPROVE_OPTION) {
			currentMap = fileopen.getSelectedFile();
			startGameFromLevel();
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
		
		/**
		 * ActionListener que escucha el evento disparado cuando el usuario
		 * hace click en la opción "New Game" en la barra de menú dentro del juego. 
		 * La acción es cerrar el juego y volver a la ventana de inicio.   
		 * 
		 */
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backToMenu();
			}
		});
		/**
		 * ActionListener que escucha el evento disparado cuando el usuario
		 * hace click sobre la acción "Reset" en la barra de menú dentro del juego. 
		 * La acción reiniciar el juego. El mismo queda en el estado inicial que 
		 * carga el parser del archivo mapa.
		 * 
		 */
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentGameGraphics.setVisible(false);
				currentGameGraphics = null;
				startGameFromLevel();
			}
		});
		/** 
		 * ActionListener que escucha el evento disparado cuando el usuario
		 * hace click sobre la acción "Save" en la barra de menú dentro del juego. 
		 * La acción es guardar el estado de la partida actual en un archivo dentro
		 * del directorio "saved_games". Para hacer esto serializa el mapa del juego,
		 * (el cual es almacenado en la variable "currentMap") y el estado del backend
		 *  (contenido en la variable "currentGameLogic") .
		 * 
		 */
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy.HH_mm_ss");
				Date date = new Date();
				String date_str = dateFormat.format(date);
				String map_name = (currentMap.getName().split("\\."))[0] + ".";
				
				String file_name = "saved_games" + File.separator + map_name + date_str + ".game";
				try {
					ObjectOutputStream file = new ObjectOutputStream(
							new BufferedOutputStream(
									new FileOutputStream(file_name)));
					file.writeObject(currentGameLogic);
					file.writeObject(currentMap);
					file.close();
					
					JOptionPane.showMessageDialog(gameScreen, "Tu partida ha sido salvada.");
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
		/**
		 * ActionListener que escucha el evento disparado cuando el usuario
		 * hace click sobre la acción "Close" en la barra de menú dentro del juego. 
		 * cierra el programa.
		 */
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menuBar.add(file);
		
		return menuBar;
	}
	/**
	 * Carga un nuevo juego a partir de un archivo que representa un mapa.
	 * De ocurrir algún problema durante el parseo del mapa, el método 
	 * muestra una ventana de error, imprime el stack trace y regresa al menú
	 * principal.
	 * 
	 */
	private void startGameFromLevel() {
		try {
			currentGameLogic = (new Parser()).parse(currentMap);
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
	 * Carga un nuevo juego. 
	 * 
	 * Notar que el método espera que la lógica de juego a utilizar 
	 * esté cargada en la variable de instancia"currentGameLogic" y 
	 * que el mapa siendo utilizado se encuentre en la variable de instancia
	 * "currentMap".
	 * 
	 */
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
	
	/**
	 * Se encarga de mostrarle al usuario la ventana de inicio. Al mismo
	 * tiempo termina el juego actual. 
	 * 
	 */
	private void backToMenu() {
		currentGameGraphics.setVisible(false);
		currentGameGraphics = null;
		gameScreen.setVisible(true);
	}
}
