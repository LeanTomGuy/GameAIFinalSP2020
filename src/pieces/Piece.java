package pieces;

import player.*;
import game.*;
import java.util.*;

public abstract class Piece {

	public Boolean hasMoved = false;
	/*
	 * Notes: K -> Check second char for K vs Kn Otherwise translate first letter
	 * into which piece it is
	 * 
	 * Check next char, convert to value (A-H, 1-8) check next as 1-8, decrement by
	 * 1
	 * 
	 * Space inbetween , then number-letter value pair
	 * 
	 * Otherwise, request new input
	 * 
	 * "P B2 B4" -> Creates Move(Position B2 -> (1,1) , Position B4 -> (3,1), Pawn,
	 * EmptyPiece)
	 */

	private static int value;
	
	public abstract List<Move> getValidMoves(Squares[][] squares, Game game);

	public abstract Boolean isValidMove(Position end, Squares[][] squares, Game game);

	public abstract Position[] getPath(Position end);

	private PlayerType player;

	private Position pos;

	public static Piece copyPiece(Piece pce) {
		switch (pce.toString()) {
		case "K":
			return new King(pce.getPlayer(), pce.getPosition().copy());
		case "Q":
			return new Queen(pce.getPlayer(),  pce.getPosition().copy());
		case "R":
			return new Rook(pce.getPlayer(),  pce.getPosition().copy());
		case "Kn":
			return new Knight(pce.getPlayer(),  pce.getPosition().copy());
		case "B":
			return new Bishop(pce.getPlayer(),  pce.getPosition().copy());
		case "P":
			return new Pawn(pce.getPlayer(),  pce.getPosition().copy());
		default:
			return new EmptyPiece(PlayerType.NONE,  pce.getPosition().copy());
		}
	}

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

	public int getScore() 
	{
		return value;
	}
	public void setPosition(Position newPos) {
		//System.out.println("setPosition() called at " + newPos.toString());
		this.pos = newPos;
	}

	public abstract String toString();

	public abstract String toDisplay();

//getPlayerType instead
	public PlayerType getPlayer() {
		return this.player;
	}

}
