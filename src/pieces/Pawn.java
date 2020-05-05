package pieces;

import player.*;
import game.*;
import java.util.*;

public class Pawn extends Piece {

	public static int value = 1;
	
//constructor

	public Pawn(PlayerType play, Position pos) {
		super(play, pos);
	};

	@Override
	public Boolean isValidMove(Position fin, Squares[][] squares) {

		if (this.getPosition().isValidPos() && fin.isValidPos() && !this.getPosition().equals(fin)) {
			int thisRank = this.getPosition().getRank();
			int thisFile = this.getPosition().getFile();
			if ((Math.abs(thisRank - fin.getRank()) == 1) && (thisFile == fin.getFile()))
// normal moves
			{
				if (this.getPlayer() == PlayerType.W) {
					return (thisRank < fin.getRank()
							&& squares[fin.getRank()][fin.getFile()].getPiece().getPlayer() == PlayerType.NONE);
				} else {
					return (thisRank > fin.getRank()
							&& squares[fin.getRank()][fin.getFile()].getPiece().getPlayer() == PlayerType.NONE);
				}
			}

			if (Math.abs(thisRank - fin.getRank()) == 2 && (thisFile == fin.getFile())
					&& (thisRank == 1 || (thisRank == 6))) {
				if (this.getPlayer() == PlayerType.W) {
					Position tempPos = new Position(thisRank + 1, thisFile);
					return (squares[tempPos.getRank()][tempPos.getFile()].isEmpty() && thisRank < fin.getRank()
							&& squares[fin.getRank()][fin.getFile()].getPiece().getPlayer() == PlayerType.NONE);
				} else {
					Position tempPos = new Position(thisRank - 1, thisFile);
					return (squares[tempPos.getRank()][tempPos.getFile()].isEmpty() && thisRank > fin.getRank()
							&& squares[fin.getRank()][fin.getFile()].getPiece().getPlayer() == PlayerType.NONE);
				}
			}

			if ((Math.abs(thisRank - fin.getRank()) == 1) && (Math.abs(thisFile - fin.getFile()) == 1)) {
				return ((squares[fin.getRank()][fin.getFile()].getPiece().getPlayer() != PlayerType.NONE)
						&& (squares[fin.getRank()][fin.getFile()].getPiece().getPlayer() != this.getPlayer()));
			}

// need to address en passant
		}
		return false;
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares) {
		List<Move> moveList = new ArrayList<Move>();
		for (int i = this.getPosition().getRank() + 1; i <= this.getPosition().getRank() + 2; i++) {
			for (int j = -1; j < 2; j++) {
				Position tempPos = new Position(i, j);
				if (tempPos.isValidPos()) {
					if (isValidMove(tempPos, squares)) {
						Piece capture = squares[tempPos.getRank()][tempPos.getFile()].getPiece();
						Move move = new Move(this.getPosition(), tempPos, this, capture);
						moveList.add(move);
					}
				}
			}
		}

		return moveList;
	}

	@Override
	public Position[] getPath(Position finalPos) {
//This is for pawn captures
		if (this.getPosition().getFile() != finalPos.getFile()) {
			return new Position[] { this.getPosition(), finalPos };
		}
//This is for normal pawn moves and first pawn moves.
		int pathLength = Math.abs(this.getPosition().getRank() - finalPos.getRank()) + 1;
		Position[] path = new Position[pathLength];

		for (int cnt = 0; cnt < pathLength; cnt++) {
			path[cnt] = new Position(this.getPosition().getFile(),
					Math.min(this.getPosition().getRank(), finalPos.getRank()) + cnt);
		}

		return path;
	}

	@Override
	public String toDisplay() {
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
	
	public String toString() {
		return "P";
	}

}