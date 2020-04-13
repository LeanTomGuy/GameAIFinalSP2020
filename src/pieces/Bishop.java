package pieces;

import player.PlayerType;

public class Bishop extends Piece {

	public Bishop(PlayerType play)
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
		return " "+ret+"B ";
		
	}
}
