package backend;

import backend.board.Board;
import backend.movable.Player;

public class Game {
	private Board board;
	private Player player;
	
	public Game(Board board, Player player){
		this.board = board;
		this.player = player;
	}
	
	public Board getBoard(){
		return this.board;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
}
