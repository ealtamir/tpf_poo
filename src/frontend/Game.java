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

import general.observer.*;

import javax.swing.*;

import backend.board.Direction;
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
import backend.movable.Player;

import gui.BoardPanel;
import gui.ImageUtils;


public class Game extends JFrame 
	implements 
		KeyListener, 
		backend.cell.CellVisitor,
		backend.movable.MovableVisitor,
		Observer {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int CELL_SIZE = 30;
	private Map<Integer, Direction> movesMap;
	private int KEY_PRESSED = 0;
	private backend.Game game;
	
	private int width;
	private int height;
	
	private BoardPanel boardPanel;
	
	private Image player;
	private Image water;
	private Image iceCube;
	private Image swtch;
	private Image destination;
	private Image tree; 
	private Image shallowWater;
	private Image floor;
	private Image box;
	
	
	public Game(String windowTitle, int rows, int columns, backend.Game game, JMenuBar menuBar) {
		this.game = game;
		// Cantidad que sumo para que quede alineado el panel con el frame.
		width = CELL_SIZE * columns; // 22 es la altura del menubar.
		height = CELL_SIZE * rows + 43; 
		movesMap = new HashMap<Integer, Direction>();
		movesMap.put(KeyEvent.VK_UP, Direction.NORTH);
		movesMap.put(KeyEvent.VK_DOWN, Direction.SOUTH);
		movesMap.put(KeyEvent.VK_LEFT, Direction.WEST);
		movesMap.put(KeyEvent.VK_RIGHT, Direction.EAST);
		
		//setLayout(null);
		loadGraphics();
		setResizable(false);
		setSize(width, height);
		setLocationRelativeTo(null);
		setJMenuBar(menuBar);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		menuBar.setVisible(true);
		addKeyListener(this);
		
	}
	
	private void loadGraphics() {
		try {
			player 		= ImageUtils.loadImage("resources" + File.separator + "player.png");
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
	
	private void drawBoard() {		
		for (Cell cell: game.getBoard())
		{
			cell.addObserver(this);
			cell.accept(this);
		}
		repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Este m��todo no hace nada.
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
	public void visit(IceCube iceCube) {
		drawMovable(iceCube, this.iceCube);
	}

	@Override
	public void visit(Box box) {
		drawMovable(box, this.box);
	}

	@Override
	public void visit(Player player) {
		drawMovable(player, this.player);
	}	

	@Override
	public void visit(Floor f) {
		drawCell(f, this.floor);
	}

	@Override
	public void visit(ShallowWater sw) {
		drawCell(sw, this.shallowWater);
	}

	@Override
	public void visit(Switch s) {
		drawCell(s, this.swtch);
	}

	@Override
	public void visit(Tree t) {
		Point p = t.getPosition();
		drawCell(t, this.floor);
		boardPanel.appendImage(p.y, p.x, tree);
	}

	@Override
	public void visit(Water w) {
		drawCell(w, this.water);
	}

	@Override
	public void visit(Destination d) {
		if (d.isVisible())
			drawCell(d, this.destination);
		else
			drawCell(d, this.floor);
	}
	
	@Override
	public void observe(Observable o, Object arg) {
		if (o instanceof Cell) {
			((Cell) o).accept(this);
			repaint();
		}
	}

}
