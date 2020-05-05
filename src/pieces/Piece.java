package pieces;

import player.*;
import game.*;
import java.util.*;

public abstract class Piece {

	/* Notes: 
	 *    K -> Check second char for K vs Kn 
	 *    Otherwise translate first letter into which piece it is 
	 *    
	 * 	  Check next char,  convert to value (A-H, 1-8)  check next as 1-8, decrement by 1 
	 * 
	 *    Space inbetween , then number-letter value pair 
	 *    
	 *    Otherwise, request new input 
	 *    
	 *    "P B2 B4"  -> Creates Move(Position B2 -> (1,1)
	 *    , Position B4 -> (3,1), Pawn, EmptyPiece) 
	 */
	
	public abstract List<Move> getValidMoves(Squares[][] squares);

	public abstract Boolean isValidMove(Position end, Squares[][] squares);

	public abstract Position[] getPath(Position end);

	private PlayerType player;
	
	private Position pos;

	Piece(PlayerType player, Position _pos) {
		this.player = player;
		this.pos = _pos;
	}
	public Boolean isEmptyPath(Position end, Squares[][] squares) {
		return false;
	}
	public Position getPosition() {
		return this.pos;
	}
	
	public void setPosition(Position newPos) 
	{
		this.pos = newPos;
	}
	
	public abstract String toString();
	
	public abstract String toDisplay();

//getPlayerType instead
	public PlayerType getPlayer() {
		return this.player;
	}



}
