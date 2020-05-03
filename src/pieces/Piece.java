package pieces;

import player.*;
import game.Position;
import java.util.*;

public abstract class Piece {

	public abstract List<Position> getValidMoves(Position startingPosition);

	public abstract Boolean isValidMove(Postion start, Postion end);

	public abstract Position[] getPath(Postion start, Postion end);

	private PlayerType player;

	Piece(PlayerType player) {
		this.player = player;
	}

	public abstract String toString();

//getPlayerType instead
	public PlayerType getPlayer() {
		return this.player;
	}

}
