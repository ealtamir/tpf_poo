package backEnd;

import backEnd.board.Board;
import backEnd.movable.Player;

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
