package pieces;

import player.PlayerType;

public class EmptyPiece extends Piece {

	public EmptyPiece(PlayerType player) {

		super(player);

	}

	@Override

	public String toString()

	{

		return " _ ";

	}

	public boolean isValidMove (Position init, Position final) {

		return false;

	}

	@Override

	public Position[] getPath(Position init, Position fin) {

		return new Position[];

	}

}