package frontend;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import backend.board.Direction;
import backend.cell.Destination;
import backend.cell.Floor;
import backend.cell.ShallowWater;
import backend.cell.Switch;
import backend.cell.Tree;
import backend.cell.Water;
import backend.movable.Box;
import backend.movable.IceCube;
import backend.movable.MovableVisitor;
import backend.movable.Player;

import gui.BoardPanel;
import gui.ImageUtils;


public class Game extends JFrame 
	implements 
		KeyListener, 
		backend.cell.CellVisitor,
		backend.movable.MovableVisitor {
	
	
	private static final int CELL_SIZE = 30;
	private Map<Integer, Direction> movesMap;
	private int KEY_PRESSED = 0;
	private backend.Game game;
	
	private int rows;
	private int cols;
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
	private Image wall;
	private Image box;
	
	
	public Game(String windowTitle, int rows, int columns, backend.Game game) {
		this.game = game;
		width = CELL_SIZE * (columns + 1);
		height = CELL_SIZE * (rows + 1);
		this.cols = columns;
		this.rows = rows;
		
		movesMap = new HashMap<Integer, Direction>();
		movesMap.put(KeyEvent.VK_UP, Direction.NORTH);
		movesMap.put(KeyEvent.VK_DOWN, Direction.SOUTH);
		movesMap.put(KeyEvent.VK_LEFT, Direction.WEST);
		movesMap.put(KeyEvent.VK_RIGHT, Direction.EAST);
		
		setLayout(null);
		loadGraphics();
		setResizable(false);
		setSize(height, width);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addKeyListener(this);
		
	}
	
	private void loadGraphics() {
		try {
			player 		= ImageUtils.loadImage("resources/player.png");
			water 		= ImageUtils.loadImage("resources/water.png");
			iceCube 	= ImageUtils.loadImage("resources/ice-box.png");
			swtch 		= ImageUtils.loadImage("resources/ice-box-target.png");
			destination = ImageUtils.loadImage("resources/target.png");
			tree 		= ImageUtils.loadImage("resources/tree.png");
			wall 		= ImageUtils.loadImage("resources/wall.png");
			floor 		= ImageUtils.loadImage("resources/cell.png");
			box 		= ImageUtils.loadImage("resources/cell.png");
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
		backend.board.Board board = game.getBoard();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				backend.cell.Cell cell = board.getCell(new Point(col, row));
				cell.accept(this);
				if( cell.getMovable() != null ) {
					cell.getMovable().accept(this);
				}
			}
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
			drawBoard();
			System.out.println("pressed key.");
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
	@Override
	public void visit(IceCube iceCube) {
		Point p = iceCube.getPosition();
		boardPanel.appendImage(p.y, p.x, this.iceCube);
	}

	@Override
	public void visit(Box box) {
		Point p = box.getPosition();
		boardPanel.appendImage(p.y, p.x, this.wall);
	}

	@Override
	public void visit(Player player) {
		Point p = player.getPosition();
		boardPanel.appendImage(p.y, p.x, this.player);
	}	

	@Override
	public void visit(Floor f) {
		Point p = f.getPosition();
		boardPanel.setImage(p.y, p.x, floor);
	}

	@Override
	public void visit(ShallowWater sw) {
		Point p = sw.getPosition();
		boardPanel.setImage(p.y, p.x, shallowWater);
	}

	@Override
	public void visit(Switch s) {
		Point p = s.getPosition();
		boardPanel.setImage(p.y, p.x, swtch);
	}

	@Override
	public void visit(Tree t) {
		Point p = t.getPosition();
		boardPanel.setImage(p.y, p.x, floor);
		boardPanel.appendImage(p.y, p.x, tree);
	}

	@Override
	public void visit(Water w) {
		Point p = w.getPosition();
		boardPanel.setImage(p.y, p.x, water);
	}

	@Override
	public void visit(Destination d) {
		Point p = d.getPosition();
		boardPanel.setImage(p.y, p.x, destination);
	}
}
