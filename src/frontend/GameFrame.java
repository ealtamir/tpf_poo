package frontend;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import backend.cell.CellListener;

import backend.Game;
import backend.GameListener;

import javax.swing.*;

import backend.board.Direction;
import backend.cell.CellVisitor;
import backend.cell.Destination;
import backend.cell.Floor;
import backend.cell.ShallowWater;
import backend.cell.Switch;
import backend.cell.Tree;
import backend.cell.Water;
import backend.cell.Cell;
import backend.movable.Box;
import backend.movable.IceCube;
import backend.movable.Movable;
import backend.movable.MovableVisitor;
import backend.movable.Player;

import gui.BoardPanel;
import gui.ImageUtils;

/**
 * Clase que extiende de JFrame y que se ocupa de crear los gráficos
 * del juego. La clase implementa varias interfaces que se encargan
 * de los siguientes comportamientos:
 * 
 * -KeyListener: Listeners para los eventos de teclado. 
 * 
 * -backend.cell.CellVisitor: Interfaz que se utiliza para conectar el
 * frontend con el backend. Esta interfaz le provee a backend.cell.Cell de un 
 * método que el frontend usa para dibujar cada objeto Cell en el tablero. 
 * 
 * -backend.movable.MovableVisitor: Provee el mismo funcionamiento que la clase
 * CellVisitor sólo que para los objetos backend.movable.Movable.
 * 
 * -Observer: Clase que le provee una interfaz al backend para que el mismo le
 * avise al frontend cuándo actualizar los gráficos, para que reflejen los últimos
 * cambios realizados.
 * 
 * @author enzo
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame implements KeyListener {
	
	
	private static final int CELL_SIZE = 30;
	private Map<Integer, Direction> movesMap;
	
	/* Estado de las teclas presionadas */
	private int KEY_PRESSED = 0;
	
	/* Tamaño en píxeles de la pantalla del juego */
	private int width;
	private int height;
	
	/* Referencia a la lógica del juego que se usa para dibujar el mapa*/
	private Game game;
	
	/* Panel que contiene los JPanels que representan cada celda del juego. */
	private BoardPanel boardPanel;
	
	/* Referencia a la clase que inicia el juego. */
	private GameStarter starterObject;
	
	/* Imagenes que se utilizarán en el juego */
	private Image player;
	private Image water;
	private Image iceCube;
	private Image swtch;
	private Image destination;
	private Image tree; 
	private Image shallowWater;
	private Image floor;
	private Image box;
	
	private ElementRenderer renderer = new ElementRenderer();
	
	private class ElementRenderer implements CellVisitor, MovableVisitor {

		private void drawCell(Cell cell, Image image) {
			Point position = cell.getPosition();
			Movable movable = cell.getMovable();
			
			boardPanel.setImage(position.y, position.x, image);
			
			if (movable != null)
				movable.accept(this);
			
		}
		
		private void drawMovable(Movable movable, Image image) {
			Point position = movable.getPosition();		
			boardPanel.appendImage(position.y, position.x, image);
		}
		
		@Override 
		public void visit(IceCube ic) {
			drawMovable(ic, iceCube);
		}

		@Override
		public void visit(Box b) {
			drawMovable(b, box);
		}

		@Override
		public void visit(Player p) {
			drawMovable(p, player);
		}	

		@Override
		public void visit(Floor f) {
			drawCell(f, floor);
		}

		@Override
		public void visit(ShallowWater sw) {
			drawCell(sw, shallowWater);
		}

		@Override
		public void visit(Switch s) {
			drawCell(s, swtch);
		}

		@Override
		public void visit(Tree t) {
			Point p = t.getPosition();
			drawCell(t, floor);
			boardPanel.appendImage(p.y, p.x, tree);
		}

		@Override
		public void visit(Water w) {
			drawCell(w, water);
		}

		@Override
		public void visit(Destination d) {
			if (d.isVisible())
				drawCell(d, destination);
			else
				drawCell(d, floor);
		}
		
	}
	private GameFrameGameListener gameListener = new GameFrameGameListener();
	
	public class GameFrameGameListener implements GameListener {
		
		@Override
		public void gameLost(Game game) {
			JOptionPane.showMessageDialog(starterObject.getGameScreen(), "Perdiste el juego...");
			starterObject.backToMenu();
			
		}
		@Override
		public void gameWon(Game game) {
			JOptionPane.showMessageDialog(starterObject.getGameScreen(), "Ganaste el juego!");
			starterObject.backToMenu();
		}
		
	}
	
	
	private GameFrameCellListener cellListener =  new GameFrameCellListener();
	
	private class GameFrameCellListener implements CellListener {
		
		@Override
		public void cellChanged(Cell cell) {
			cell.accept(renderer);
			repaint();
		}
	}
	
	/**
	 * Constructor de la clase frontend.Game. Recibe los siguientes parámetros:
	 * 
	 * @param windowTitle, El título que tendrá el borde de la ventana de juego.
	 * @param rows, La cantidad de filas que tiene el tablero de juego.
	 * @param columns, La cantidad de columnas que tiene el tablero de juego.
	 * @param game, El objeto de backend.game que representa la lógica del juego.
	 * @param menuBar, El objeto JMenuBar que representa la barra de menú que se mostrará
	 * en la ventana de juego.
	 */
	public GameFrame(String windowTitle, final GameStarter starterObject) {
		int columns = starterObject.getCurrentGameLogic().getBoard().getWidth();
		int rows = starterObject.getCurrentGameLogic().getBoard().getHeight();
		
		JMenuBar menuBar = starterObject.createMenuBar();
		
		this.game = starterObject.getCurrentGameLogic();
		this.starterObject = starterObject;
		
		width = CELL_SIZE * columns; 
		// Cantidad que sumo para que quede alineado el panel con el frame.
		height = CELL_SIZE * rows + 43; 
		
		// Mapeo entre los eventos de teclados y las direcciones en las que
		// se tiene que mover el jugador.
		movesMap = new HashMap<Integer, Direction>();
		movesMap.put(KeyEvent.VK_UP, Direction.NORTH);
		movesMap.put(KeyEvent.VK_DOWN, Direction.SOUTH);
		movesMap.put(KeyEvent.VK_LEFT, Direction.WEST);
		movesMap.put(KeyEvent.VK_RIGHT, Direction.EAST);
		
		loadGraphics();
		
		setResizable(false);
		setSize(width, height);
		setLocationRelativeTo(null);
		setJMenuBar(menuBar);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		menuBar.setVisible(true);
		addKeyListener(this);
		
		game.setListener(gameListener);
		
	}
	
	/**
	 * Carga las imágenes a las variables respectivas y realiza ciertas configuraciones
	 * al JFrame 
	 */
	private void loadGraphics() {
		try {
			player 		= ImageUtils.loadImage("resources" + File.separator + "trollface.png");
			water 		= ImageUtils.loadImage("resources" + File.separator + "water.png");
			iceCube 	= ImageUtils.loadImage("resources" + File.separator + "ice-box.png");
			swtch 		= ImageUtils.loadImage("resources" + File.separator + "ice-box-target.png");
			destination = ImageUtils.loadImage("resources" + File.separator + "target.png");
			tree 		= ImageUtils.loadImage("resources" + File.separator + "tree.png");
			ImageUtils.loadImage("resources" + File.separator + "wall.png");
			floor 		= ImageUtils.loadImage("resources" + File.separator + "cell.png");
			box 		= ImageUtils.loadImage("resources" + File.separator + "box.png");
		} catch(IOException e) {
			System.out.println("No se puedo cargar un archivo.");
			e.printStackTrace();
		}
		
		boardPanel = new BoardPanel(height, width, CELL_SIZE);
		boardPanel.setBackground(Color.BLACK);
		add(boardPanel); 	// Agrego mi panel al frame.
		drawBoard();
		
	}
/*****************************************************
 * 
 * 				Helpers de gráficos
 * 
*****************************************************/
	private void drawBoard() {
		for (Cell cell: game.getBoard())
		{
			cell.setListener(cellListener);
			cell.accept(renderer);
		}
		repaint();
	}
	
	
/*****************************************************
 * 
 * 				Listeners del Teclado
 * 
*****************************************************/
	@Override
	public void keyTyped(KeyEvent e) {
		// Este método no hace nada.
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if ( KEY_PRESSED == 0 
				&& (keyCode == KeyEvent.VK_UP 
				|| keyCode == KeyEvent.VK_DOWN 
				|| keyCode == KeyEvent.VK_LEFT 
				|| keyCode == KeyEvent.VK_RIGHT )) {
			game.getPlayer().move(movesMap.get(keyCode));
			repaint();
			KEY_PRESSED = keyCode;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if( keyCode == KEY_PRESSED ) {
			KEY_PRESSED = 0;
		}
	}
}
