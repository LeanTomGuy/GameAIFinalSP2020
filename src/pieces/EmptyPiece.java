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
}
