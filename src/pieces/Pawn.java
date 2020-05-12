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
	public Boolean isValidMove(Position end, Squares[][] squares, Game game) {

		if (this.getPosition().isValidPos() && end.isValidPos() && !this.getPosition().equals(end)) {
			int thisRank = this.getPosition().getRank();
			int thisFile = this.getPosition().getFile();
			int endRank = end.getRank();
			int endFile = end.getFile();
			if ((Math.abs(thisRank - end.getRank()) == 1) && (thisFile == end.getFile()))
// normal moves
			{
				if (this.getPlayer() == PlayerType.W) {
					Move tmpMove = new Move(end, this.getPosition(), this,squares[endRank][endFile].getPiece());
					
					try {
						return (thisRank < end.getRank()
								&& squares[end.getRank()][end.getFile()].getPiece().getPlayer() == PlayerType.NONE) 
								&& !game.moveMakesCheck(tmpMove, game);
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				} else {
					Move tmpMove = new Move(end, this.getPosition(), this,squares[endRank][endFile].getPiece());
					
					try {
						return (thisRank > end.getRank()
								&& squares[end.getRank()][end.getFile()].getPiece().getPlayer() == PlayerType.NONE
								&& !game.moveMakesCheck(tmpMove, game));
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}

			if (Math.abs(thisRank - end.getRank()) == 2 && (thisFile == end.getFile())
					&& (thisRank == 1 || (thisRank == 6))) {
				if (this.getPlayer() == PlayerType.W) {
					Position tempPos = new Position(thisRank + 1, thisFile);
					Move tmpMove = new Move(end, this.getPosition(), this,squares[endRank][endFile].getPiece());
					
					try {
						return (squares[tempPos.getRank()][tempPos.getFile()].isEmpty() && thisRank < end.getRank()
								&& squares[end.getRank()][end.getFile()].getPiece().getPlayer() == PlayerType.NONE)
								&& !game.moveMakesCheck(tmpMove, game);
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				} else {
					Position tempPos = new Position(thisRank - 1, thisFile);
					Move tmpMove = new Move(end, this.getPosition(), this,squares[endRank][endFile].getPiece());
					
					try {
						return (squares[tempPos.getRank()][tempPos.getFile()].isEmpty() && thisRank > end.getRank()
								&& squares[end.getRank()][end.getFile()].getPiece().getPlayer() == PlayerType.NONE)
								&& !game.moveMakesCheck(tmpMove, game);
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}

			if ((Math.abs(thisRank - end.getRank()) == 1) && (Math.abs(thisFile - end.getFile()) == 1)) {
				Move tmpMove = new Move(end, this.getPosition(), this,squares[endRank][endFile].getPiece());
				
				try {
					return ((squares[end.getRank()][end.getFile()].getPiece().getPlayer() != PlayerType.NONE)
							&& (squares[end.getRank()][end.getFile()].getPiece().getPlayer() != this.getPlayer()))
							&& !game.moveMakesCheck(tmpMove, game);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}

// need to address en passant
		}
		return false;
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares, Game game) {
		List<Move> moveList = new ArrayList<Move>();
		for (int i = this.getPosition().getRank() -2 ; i <= this.getPosition().getRank() + 2; i++) {
			for (int j = this.getPosition().getFile(); j <= 7; j++) {
				Position tempPos = new Position(i, j);
				if (tempPos.isValidPos()) {
					if (isValidMove(tempPos, squares, game)) {
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
	public Position[] getPath(Position endalPos) {
//This is for pawn captures
		if (this.getPosition().getFile() != endalPos.getFile()) {
			return new Position[] { this.getPosition(), endalPos };
		}
//This is for normal pawn moves and first pawn moves.
		int pathLength = Math.abs(this.getPosition().getRank() - endalPos.getRank()) + 1;
		Position[] path = new Position[pathLength];

		for (int cnt = 0; cnt < pathLength; cnt++) {
			path[cnt] = new Position(this.getPosition().getFile(),
					Math.min(this.getPosition().getRank(), endalPos.getRank()) + cnt);
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

		return "   " + ret + "P  ";
	}
	
	public String toString() {
		return "P";
	}

}