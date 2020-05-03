package pieces;

import player.*;
import game.*;
import java.util.*;

public class Pawn extends Piece {

//constructor

	public Pawn(PlayerType play) {
		super(play);
	};

	Boolean isValidMove(Position initial, Position fin) {
		if (initial.getFile() == fin.getFile()) {
			if (initial.getRank() == fin.getRank()) {
				return false;
			}
		}

		if ((Math.abs(initial.getRank() - fin.getRank()) == 1) && (initial.getFile() == fin.getFile()))
// normal moves
		{
			if (this.getPlayer() == PlayerType.W) {
				return (initial.getRank() < fin.getRank());
			} else {
				return (initial.getRank() > fin.getRank());
			}
		}

		if (Math.abs(initial.getRank() - fin.getRank()) == 2 && (initial.getFile() == fin.getFile())
				&& (initial.getRank() == 1 || (initial.getRank() == 6))) {
			if (this.getPlayer() == PlayerType.W) {
				return (initial.getRank() < fin.getRank());
			} else {
				return (initial.getRank() > fin.getRank());
			}
		}

		if ((Math.abs(initial.getRank() - fin.getRank()) == 1) && (Math.abs(initial.getFile() - fin.getFile()) == 1)) {
			return ((fin.getSquare().getPiece().getPlayer() != PlayerType.NONE)
					&& (fin.getSquare().getPiece().getPlayer() != this.getPlayer()));
		}

// need to address en passant

		return false;
	}

	@Override
	public Position[] getPath(Position initPos, Position finalPos) {
//This is for pawn captures
		if (initPos.getX() != finalPos.getFile()) {
			return new Position[] { initPos, finalPos };
		}
//This is for normal pawn moves and first pawn moves.
		int pathLength = Math.abs(initPos.getRank() - finalPos.getRank()) + 1;
		Position[] path = new Position[pathLength];

		for (int cnt = 0; cnt < pathLength; cnt++) {
			path[cnt] = new Position(initPos.getFile(), Math.min(initPos.getRank(), finalPos.getRank()) + cnt);
		}

		return path;
	}

	@Override
	public String toString() {
		String ret;

		switch (this.getPlayer()) {
		case W:
			ret = "W";
			break;
		case B:
			ret = "B";
			break;
		default:
			ret = "";
		}

		return " " + ret + "P ";
	}

}