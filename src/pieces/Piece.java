package pieces;

import player.*;
import game.*;
import java.util.*;

public abstract class Piece {

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
	
	public abstract String toString();

//getPlayerType instead
	public PlayerType getPlayer() {
		return this.player;
	}



}
