package pieces;

import java.util.ArrayList;
import java.util.List;

import game.Move;
import game.Position;
import game.Squares;
import player.PlayerType;

public class King extends Piece {

	public Boolean hasMoved = false;
	
	public King(PlayerType play, Position pos)
	{
		
			super(play, pos);
	}
	
	public String toString() 
	{
		String ret;
		
		switch(this.getPlayer()) 
		{
		case W:
			ret = "W";
			break;
		case B:
			ret = "B";
			break;
		default:
			ret = "";
		}
		
		return " "+ret+"K ";
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares) {
		List<Move> moveLst = new ArrayList<Move>();
		
		return moveLst;
	}

	@Override
	public Boolean isValidMove(Position end, Squares[][] squares) {
		return false;
	}

	@Override
	public Position[] getPath(Position end) {
		return null;
	}
}
