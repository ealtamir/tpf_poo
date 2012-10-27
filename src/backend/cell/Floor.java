package backend.cell;

import backend.board.Direction;
import backend.movable.Movable;

public class Floor extends Cell {

	private Movable movable;
	
	public Floor(){
		
	}
	
	public Floor(Movable movable){
		this.movable = movable;		
	}
	
	@Override
	public Movable getMovable() {
		return this.movable;
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
	public void receiveMovable(Movable movable, Direction direction) {
		this.movable = movable;
		movable.updatePosition(this.position);
	}
	
	
	

}
