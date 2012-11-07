package backend.movable;

import backend.board.Board;
import backend.board.Direction;

import java.awt.Point;

/**
 * IceCube es una clase de Movable que, en caso de ser desplazada (recibir
 * el mensaje move), se mueve hasta no poder moverse mas, o derretirse.
 * @see IceCube#move(Direction)
 *
 */

public class IceCube extends Movable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean melted = false;
	
	public IceCube(Board board, Point position) {
		super(board, position);
	}
	
	
	/**
	 * Realiza un movimiento continuo hasta que encuentra que no puede
	 * seguir moviendose.
	 * En caso de mojarse al entrar en una celda de tipo Water, tambien
	 * finaliza el movimiento.
	 * @param direction Direccion del movimiento.
	 */
	@Override
	public boolean move(Direction direction) {
		
		boolean moved = false;
		
		while (super.move(direction)) {
			moved = true;
			if (melted)
				break;
		}
		
		return moved;
	}

	
	/**
	 * Establece un flag que indica que el cubo de hielo
	 * esta derretido y no debe seguir moviendose.
	 */
	@Override
	public void getWet() {
		this.melted = true;
	}
	
	/**
	 *  Patron Visitor para visitar Movables.
	 *  @param visitor Visitor de Movable
	 */
	@Override
	public void accept(MovableVisitor visitor) {
		visitor.visit(this);
	}

}
