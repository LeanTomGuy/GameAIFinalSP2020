package pieces;

import player.PlayerType;

import java.util.List;

import game.*;

public class Bishop extends Piece {

	public Bishop(PlayerType play, Position pos)
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
		return " "+ret+"B ";
		
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isValidMove(Position end, Squares[][] squares) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position[] getPath(Position end) {
		// TODO Auto-generated method stub
		return null;
	}
}
