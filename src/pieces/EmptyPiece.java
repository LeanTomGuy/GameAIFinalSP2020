package pieces;

import player.PlayerType;

import java.util.List;

import game.*;

public class EmptyPiece extends Piece {

	public static int value = 0;
	
	public EmptyPiece(PlayerType player, Position pos) {

		super(player, pos);

	}

	@Override

	public String toDisplay()

	{

		return "   _   ";

	}
	
	public String toString() {
		return "_";
	}


	@Override

	public Position[] getPath(Position fin) {

		return null;

	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares, Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isValidMove(Position end, Squares[][] squares, Game game) {
		// TODO Auto-generated method stub
		return false;
	}

}