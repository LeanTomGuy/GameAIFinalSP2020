package pieces;

import player.PlayerType;

public class Queen extends Piece {

	public Queen(PlayerType play)
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
		
		return " "+ret+"Q ";
	}
}
