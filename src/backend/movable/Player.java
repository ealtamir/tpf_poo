package backend.movable;

import java.awt.Point;

import backend.board.InvalidPositionException;
import backend.Game;

import backend.board.*;

/**
 * Player es una clase de Movable que ademas tiene la capacidad
 * de terminar el juego (Game) que referencia, en caso de recibir el
 * mensaje endGame, o el mensaje getWet, ganando o perdiendo segun corresponda.
 * 
 * @see Player#endGame()
 * 
 * Ademas, Player sobreescribe el metodo move, de manera que mueva al
 * Movable que se encuentre en la direccion de su movimiento antes de moverse
 * el mismo.
 * 
 * @see Player#move(Direction)
 * 
 * @author fede
 *
 */

public class Player extends Movable {
	
	private static final long serialVersionUID = 1L;
	
	private transient Game game; // TODO ver si realmente es necesario game.
	
	public Player(Game game, Board board, Point position){
		super(board, position);
		this.game = game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
		
	/**
	 * Intenta mover el objeto que se encuentra en la direccion del jugador,
	 * pidiendoselo a Board.
	 * Luego realiza el movimiento normal de un Movable @see Movable#move(Direction)
	 * @param dir Direccion de movimiento del jugador
	 */
	public boolean move(Direction dir) {
		
		Point nextPosition = dir.increment(this.getPosition());
		
		Movable targetMovable = null;
		
		try {
			targetMovable = this.getBoard().getCell(nextPosition).getMovable();
		}
		catch (InvalidPositionException e) {
			/*
			 * Si la posicion a la que queremos movernos es invalida,
			 * abortamos todo el proceso, pues no puede haber ningun Movable
			 * tampoco.
			 */
			return false;
		}
		
		if (targetMovable != null)
			targetMovable.move(dir);
		
		return super.move(dir);
		
	}
	
	/**
	 * Mojar al jugador. Ocasiona la perdida del juego.
	 */
	@Override
	public void getWet() {
		this.game.lose();
	}
	
	/**
	 * Termina el juego con exito.
	 */
	public void endGame() {
		this.game.win();
	}
	
	/**
	 *  Patron Visitor para visitar Movables.
	 *  @param visitor Visitor de Movable
	 */
	public void accept(MovableVisitor visitor) {
		visitor.visit(this);
	}
	
}
