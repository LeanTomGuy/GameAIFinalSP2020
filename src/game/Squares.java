package game;

import pieces.Piece;

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
}
