package backend.movable;
import java.awt.Point;
import backend.board.*;

public class Player extends Movable{
	private Board board;
	private Point position;
	
	public Player(Board board, Point position){
		this.board = board;
		this.position = position;
	}
	
	public void move(Direction dir){
		try{
			board.getCell(dir.increment(position)).receiveMovable(this, dir); // TODO hay que ver bien este último método, realmente necesita un Direction?
		}
		catch(Exception e){
			e.printStackTrace(); // TODO Qué hago aca? nada? 
		}
		
		
	}

	@Override
	public void getWet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePosition(Point position) {
		// TODO Auto-generated method stub
		
	}
	
}
