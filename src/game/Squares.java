package game;

import pieces.*;
import player.PlayerType;

public class Squares {
	
	Position pos;
	Piece pie;
	
	public Squares(Position p, Piece p2)
	{
		this.pos = p;
		this.pie = p2;
	}
	
	public void releasePiece() 
	{
		this.pie = new EmptyPiece(PlayerType.NONE, this.pos);
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
