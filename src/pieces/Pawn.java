package pieces;

import player.*;
import game.*;
import java.util.*;
public class Pawn extends Piece {

	// constructor
	public Pawn(PlayerType play)
	{
		super(play);
	};
	
	Boolean isValidMove(Position initial, Position fin) 
	{
		if(initial.getFile() == fin.getFile())
		{
			if(initial.getRank() == fin.getRank())
			{
				return false;
			}
		}
		
		if ((Math.abs(initial.getRank() - fin.getRank()) == 1) && (initial.getFile() == fin.getFile()))
			// normal moves 
		{
			if (this.getPlayer() == PlayerType.W)
			{
				return (initial.getRank() < fin.getRank());
			}
			else 
			{
				return (initial.getRank() > fin.getRank());
			}
		}
		
		if(Math.abs(initial.getRank() - fin.getRank()) == 2 && (initial.getFile() == fin.getFile()) && (initial.getRank() == 1 || (initial.getRank() == 6))) 
		{
			if (this.getPlayer() == PlayerType.W)
			{
				return (initial.getRank() < fin.getRank());
			}
			else 
			{
				return (initial.getRank() > fin.getRank());
			}
		}
		
		// need to address pawn capturing and en passant 
		return true;
	}

	@Override
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
		
		return " "+ret+"P ";
	}
	
}
