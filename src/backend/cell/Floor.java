package backend.cell;

import java.awt.Point;

import backend.board.Board;
import backend.board.Direction;
import backend.movable.Movable;

public class Floor extends Cell {

	private Movable movable;
	private Point position;
	
	public Floor(){
		
	}
	
	public Floor(Movable movable, Point position){
		this.movable = movable;		
		this.position = position;
	}
	
	public Point getPosition(){
		return this.position; 
	}
	
	@Override
	public Movable getMovable() {
		return this.movable;
	}

	public void setMovable(Movable movable){
		this.movable = movable;
	}
	
	@Override
	public boolean isOccupiable() {
		return true;
	}

	@Override
	public String idCharacter() {
		return " ";
	}
	
	
	@Override
	public Movable releaseMovable(Direction direction) {
		Movable movable = this.movable;
		this.movable = null;
		return movable;
	}
	
	@Override
	public void receiveMovable(Movable movable, Direction direction) throws UnoccupiableException {
		movable.updatePosition(position);
	}
	
	
	

}
