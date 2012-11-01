package backend;

import backend.board.Board;
import backend.movable.*;
import java.awt.Point;

public class Game {
	private Board board;
	private Player player;
	
	public Game(Board board, Point startPosition) {
		this.board = board;
		this.player = new Player(this, startPosition);
		this.player.insert();
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
		System.out.println("You win!");
	}
	
	public void lose() {
		System.out.println("You lose!");
	}
	
	
}
