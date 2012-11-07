package backend;

import backend.board.Board;
import backend.movable.*;
import java.awt.Point;
import java.io.Serializable;

public class Game implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Board board;
	private Player player;
	
	private boolean won = false;
	private boolean lost = false;
	
	private GameListener listener = null;
	
	public Game(Board board, Point startPosition) {
		this.board = board;
		this.player = new Player(this, board, startPosition);
		this.player.insert();
	}
	
	public Game(){
		
	}
	
	public void setListener(GameListener listener) {
		this.listener = listener;
	}
	
	public void setBoard(Board board){
		this.board = board;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public Game(Board board, Player player) {
		this.board = board;
		this.player = player;
	}
	
	public Board getBoard(){
		return this.board;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public void win() {
		won = true;
		
		if (listener != null)
			listener.gameWon(this);
	}
	
	public void lose() {
		lost = true;
		
		if (listener != null)
			listener.gameLost(this);
	}
	
	
	public boolean isWon() {
		return won;
	}
	
	public boolean isLost() {
		return lost;
	}
}
