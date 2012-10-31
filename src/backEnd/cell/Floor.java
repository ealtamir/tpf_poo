package backEnd.cell;

import java.awt.Point;

import backEnd.board.Board;
import backEnd.board.Direction;
import backEnd.movable.Movable;

public class Floor extends Cell {

	private Movable movable;
	private Point position;
	private Board board;
	
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
	/**
	 * Testing method. Delete later.
	 */
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
	public boolean receiveMovable(Movable movable, Direction direction) {
		Cell nextCell = board.getCell(direction.increment(this.getPosition()));
		if(this.isOccupiable()){
			return true;
		}
		if(!nextCell.isOccupiable()){
			return false;
		}
		
		((Floor)nextCell).setMovable(this.getMovable());
		this.getMovable().updatePosition(((Floor)nextCell).getPosition());
		setMovable(movable);
		return true;
	}
	
	
	

}
