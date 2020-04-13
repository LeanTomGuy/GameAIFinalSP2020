package pieces;

import player.PlayerType;

public class King extends Piece {

	public King(PlayerType play)
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
		
		return " "+ret+"K ";
	}
}
