package pieces;

import player.PlayerType;

import java.util.List;

import game.*;

public class EmptyPiece extends Piece {

	public EmptyPiece(PlayerType player, Position pos) {

		super(player, pos);

	}

	@Override

	public String toString()

	{

		return " _ ";

	}


	@Override

	public Position[] getPath(Position fin) {

		return null;

	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isValidMove(Position end, Squares[][] squares) {
		// TODO Auto-generated method stub
		return false;
	}

}