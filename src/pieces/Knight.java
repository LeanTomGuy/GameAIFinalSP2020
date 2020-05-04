package pieces;

import java.util.List;

import game.Move;
import game.Position;
import game.Squares;
import player.PlayerType;

public class Knight extends Piece {

	public Knight(PlayerType play, Position pos)
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
		
		return " "+ret+"Kn ";
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

