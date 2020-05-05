package pieces;

import java.util.ArrayList;
import java.util.List;

import game.Move;
import game.Position;
import game.Squares;
import player.PlayerType;

public class Queen extends Piece {

	public static int value = 9;
	
	public Queen(PlayerType play, Position pos) {

		super(play, pos);
	}

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

		return " " + ret + "Q ";
	}
	
	public String toString() {
		return "Q";
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares) {
		// TODO Auto-generated method stub
		List<Move> moveLst = new ArrayList<Move>();
		int thisRank = this.getPosition().getRank();
		int thisFile = this.getPosition().getFile();
		for (int i = 1; i < 8; i++) {
			// bishop logic
			Position tempPos = new Position(thisRank + i, thisFile + i);
			Position tempPos2 = new Position(thisRank - i, thisFile + i);
			Position tempPos3 = new Position(thisRank - i, thisFile - i);
			Position tempPos4 = new Position(thisRank + i, thisFile + i);

			Position[] arr = { tempPos, tempPos2, tempPos3, tempPos4 };
			for (Position elem : arr) {
				if (elem.isValidPos()) {
					if (isValidMove(elem, squares)) {
						Move mve = new Move(this.getPosition(), elem, this,
								squares[elem.getRank()][elem.getFile()].getPiece());
						moveLst.add(mve);
					}
				}
			}
		}

		// rook logic
		for (int i = thisRank + 1; i < 8; i++) {
			Position tempPos = new Position(i, thisFile);
			if (isValidMove(tempPos, squares)) {
				Move move = new Move(this.getPosition(), tempPos, this,
						squares[tempPos.getRank()][thisFile].getPiece());
				moveLst.add(move);
			}
		}
		for (int i = thisRank - 1; i >= 0; i--) {
			Position tempPos = new Position(i, thisFile);
			if (isValidMove(tempPos, squares)) {
				Move move = new Move(this.getPosition(), tempPos, this,
						squares[tempPos.getRank()][thisFile].getPiece());
				moveLst.add(move);
			}
		}

		for (int i = thisFile + 1; i < 8; i++) {
			Position tempPos = new Position(thisRank, i);
			if (isValidMove(tempPos, squares)) {
				Move move = new Move(this.getPosition(), tempPos, this,
						squares[thisRank][tempPos.getFile()].getPiece());
				moveLst.add(move);
			}
		}

		for (int i = thisFile - 1; i >= 0; i--) {
			Position tempPos = new Position(thisRank, i);
			if (isValidMove(tempPos, squares)) {
				Move move = new Move(this.getPosition(), tempPos, this,
						squares[thisRank][tempPos.getFile()].getPiece());
				moveLst.add(move);
			}
		}

		return moveLst;
	}

	@Override
	public Boolean isValidMove(Position end, Squares[][] squares) {
		// TODO Auto-generated method stub
		if (end.isValidPos() && !this.getPosition().equals(end)) {
			int thisRank = this.getPosition().getRank();
			int thisFile = this.getPosition().getFile();
			int endRank = end.getRank();
			int endFile = end.getFile();
			int rankDiff = Math.abs(thisRank - endRank);
			int fileDiff = Math.abs(thisFile - endFile);
			if (thisRank - endRank == 0 || thisFile - endFile == 0) {
				if (thisRank - endRank != 0) {
					if (thisRank > endRank) {
						for (int i = thisRank - 1; i > endRank; i--) {
							if (!squares[i][thisFile].isEmpty()) {
								return false;
							}
						}
					} else {
						for (int i = thisRank + 1; i < endRank; i++) {
							if (!squares[i][thisFile].isEmpty()) {
								return false;
							}
						}
					}
					return squares[endRank][endFile].getPiece().getPlayer() != this.getPlayer();
				} else {
					if (thisFile > endFile) {
						for (int i = thisFile - 1; i > endFile; i--) {
							if (!squares[thisRank][i].isEmpty()) {
								return false;
							}
						}
					} else {
						for (int i = thisFile + 1; i < endFile; i++) {
							if (!squares[thisRank][i].isEmpty()) {
								return false;
							}
						}
					}
					return squares[endRank][endFile].getPiece().getPlayer() != this.getPlayer();
				}
			} else if (rankDiff == fileDiff) {
				if (thisRank > endRank) {
					if (thisFile > endFile) {
						for (int i = 1; i < rankDiff; i++) {
							if (!(squares[thisRank - i][thisFile - i].isEmpty())) {
								return false;
							}
						}
					} else {
						for (int i = 1; i < rankDiff; i++) {
							if (!(squares[thisRank - i][thisFile + i].isEmpty())) {
								return false;
							}
						}
					}
				} else {
					if (thisFile > endFile) {
						for (int i = 1; i < rankDiff; i++) {
							if (!(squares[thisRank + i][thisFile - i].isEmpty())) {
								return false;
							}
						}
					} else {
						for (int i = 1; i < rankDiff; i++) {
							if (!(squares[thisRank + i][thisFile + i].isEmpty())) {
								return false;
							}
						}
					}
				}
				return squares[endRank][endFile].getPiece().getPlayer() != this.getPlayer();
			}
		}

		return false;
	}

	@Override
	public Position[] getPath(Position end) {
		// TODO Auto-generated method stub
		return null;
	}
}
