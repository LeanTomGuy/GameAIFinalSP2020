package pieces;

import java.util.ArrayList;
import java.util.List;

import game.Game;
import game.Move;
import game.Position;
import game.Squares;
import player.PlayerType;

public class Bishop extends Piece {

	public static int value = 3;

	public Bishop(PlayerType play, Position pos) {

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
		return "  " + ret + "B   ";

	}

	public String toString() {
		return "B";
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares, Game game) {
		List<Move> moveLst = new ArrayList<Move>();
		int thisRank = this.getPosition().getRank();
		int thisFile = this.getPosition().getFile();

		for (int i = 1; i < 8; i++) {
			Position tempPos = new Position(thisRank + i, thisFile + i);
			Position tempPos2 = new Position(thisRank - i, thisFile + i);
			Position tempPos3 = new Position(thisRank - i, thisFile - i);
			Position tempPos4 = new Position(thisRank + i, thisFile + i);

			Position[] arr = { tempPos, tempPos2, tempPos3, tempPos4 };
			for (Position elem : arr) {
				if (elem.isValidPos()) {
					if (isValidMove(elem, squares, game)) {
						Move mve = new Move(this.getPosition(), elem, this,
								squares[elem.getRank()][elem.getFile()].getPiece());
						moveLst.add(mve);
					}
				}
			}
		}
		return moveLst;
	}

	@Override
	public Boolean isValidMove(Position end, Squares[][] squares, Game game) {
		if (!this.getPosition().equals(end) && end.isValidPos()) {
			int thisRank = this.getPosition().getRank();
			int thisFile = this.getPosition().getFile();
			int endRank = end.getRank();
			int endFile = end.getFile();
			int rankDiff = Math.abs(thisRank - endRank);
			int fileDiff = Math.abs(thisFile - endFile);
			if (rankDiff == fileDiff) {
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
				Move tmpMove = new Move(end, this.getPosition(), this, squares[endRank][endFile].getPiece());
				try {
					return squares[endRank][endFile].getPiece().getPlayer() != this.getPlayer()
							&& !game.moveMakesCheck(tmpMove, game);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}

			}
		}
		return false;
	}

	@Override
	public Position[] getPath(Position end) {
		int pathLength = Math.abs(this.getPosition().getRank() - end.getRank()) + 1;
		Position[] arr = new Position[pathLength];

		int i_X = Integer.signum(end.getRank() - this.getPosition().getRank());
		int i_Y = Integer.signum(end.getFile() - this.getPosition().getFile());

		for (int cnt = 0; cnt < pathLength; cnt++) {
			arr[cnt] = new Position(this.getPosition().getRank() + i_X * cnt, this.getPosition().getFile() + i_Y * cnt);
		}

		return arr;

	}
}
