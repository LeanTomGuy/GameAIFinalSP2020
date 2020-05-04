package game;

import pieces.Piece;
import player.PlayerType;

public class Squares {
	
	Position pos;
	Piece pie;
	
	public Squares(Position p, Piece p2)
	{
		this.pos = p;
		this.pie = p2;
	}
	
	public void setPiece(Piece pce) 
	{
		this.pie = pce;
	}
	
	public Piece getPiece() {
		return pie;
	}
	
	public Boolean isEmpty() {
		return (pie.getPlayer() == PlayerType.NONE);
	}
}
