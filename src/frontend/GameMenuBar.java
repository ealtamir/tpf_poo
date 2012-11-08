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
import java.util.Date;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import backend.Game;

@SuppressWarnings("serial")
public class GameMenuBar extends JMenuBar {

	public GameMenuBar(final GameStarter starterObject) {
		
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
			public void actionPerformed(ActionEvent e) {
				starterObject.backToMenu();
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
			public void actionPerformed(ActionEvent e) {
				starterObject.getCurrentGameGraphics().setVisible(false);
				starterObject.setCurrentGameGraphics(null);
				starterObject.startGameFromCurrentMap();
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
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy.HH_mm_ss");
				Date date = new Date();
				String date_str = dateFormat.format(date);
				String map_name = (starterObject.getCurrentMap().getName().split("\\."))[0] + ".";
				
				String file_name = "saved_games" + File.separator + map_name + date_str;
				try {
					ObjectOutputStream file = new ObjectOutputStream(
							new BufferedOutputStream(
									new FileOutputStream(file_name)));
					
					Game game = starterObject.getCurrentGameLogic();
					
					file.writeObject(game.getBoard());
					file.writeObject(game.getPlayer());
					file.writeObject(starterObject.getCurrentMap());
					file.close();
					
					JOptionPane.showMessageDialog(starterObject.getGameScreen(), "Tu partida ha sido salvada.");
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(starterObject.getGameScreen(), e1.getMessage());
					e1.printStackTrace();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(starterObject.getGameScreen(), e1.getMessage());
					e1.printStackTrace();
				} finally {
					starterObject.backToMenu();
				}
			}
		});
		
		/**
		 * ActionListener que escucha el evento disparado cuando el usuario
		 * hace click sobre la acción "Close" en la barra de menú dentro del juego. 
		 * cierra el programa.
		 */
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		this.add(file);
	}
	
}
