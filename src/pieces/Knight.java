package pieces;

import player.PlayerType;

public class Knight extends Piece {

	public Knight(PlayerType play)
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
		
		return " "+ret+"Kn ";
	}
}

