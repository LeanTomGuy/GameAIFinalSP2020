package pieces;

import player.PlayerType;

public class Rook extends Piece {

	public Rook(PlayerType play)
	{
		
			super(play);
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
		
		return " "+ret+"R ";
	}
}
