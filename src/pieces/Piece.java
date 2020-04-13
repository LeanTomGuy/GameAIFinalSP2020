package pieces;

import player.*;
import game.Position;
import java.util.*;

public abstract class Piece {

	List<Position> getValidMoves(Position startingPosition)
	{
		return null; //fix later
	};
	
	
	private PlayerType player;
	
	Piece(PlayerType player)
	{
		this.player = player;
	}

	public abstract String toString();

	//getPlayerType instead
	public PlayerType getPlayer() 
	{
		return this.player;
	}
	
}
