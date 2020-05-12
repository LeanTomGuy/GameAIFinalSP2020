package pieces;

import java.util.ArrayList;
import java.util.List;

import game.Game;
import game.Move;
import game.Position;
import game.Squares;
import player.PlayerType;

public class Rook extends Piece {

	public static int value = 5;

	public Boolean hasMoved = false;

	public Rook(PlayerType play, Position pos) {

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

		return "   " + ret + "R   ";
	}

	public String toString() {
		return "R";
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares, Game game) {

		List<Move> moveLst = new ArrayList<Move>();
		int thisRank = this.getPosition().getRank();
		int thisFile = this.getPosition().getFile();
		for (int i = thisRank + 1; i < 8; i++) {
			Position tempPos = new Position(i, thisFile);
			if (isValidMove(tempPos, squares, game)) {
				Move move = new Move(this.getPosition(), tempPos, this,
						squares[tempPos.getRank()][thisFile].getPiece());
				moveLst.add(move);
			}
		}
		for (int i = thisRank - 1; i >= 0; i--) {
			Position tempPos = new Position(i, thisFile);
			if (isValidMove(tempPos, squares, game)) {
				Move move = new Move(this.getPosition(), tempPos, this,
						squares[tempPos.getRank()][thisFile].getPiece());
				moveLst.add(move);
			}
		}

		for (int i = thisFile + 1; i < 8; i++) {
			Position tempPos = new Position(thisRank, i);
			if (isValidMove(tempPos, squares, game)) {
				Move move = new Move(this.getPosition(), tempPos, this,
						squares[thisRank][tempPos.getFile()].getPiece());
				moveLst.add(move);
			}
		}

		for (int i = thisFile - 1; i >= 0; i--) {
			Position tempPos = new Position(thisRank, i);
			if (isValidMove(tempPos, squares, game)) {
				Move move = new Move(this.getPosition(), tempPos, this,
						squares[thisRank][tempPos.getFile()].getPiece());
				moveLst.add(move);
			}
		}

		return moveLst;
	}

	@Override
	public Boolean isValidMove(Position end, Squares[][] squares, Game game) {
		if (end.isValidPos() && !this.getPosition().equals(end)) {
			int thisRank = this.getPosition().getRank();
			int thisFile = this.getPosition().getFile();
			int endRank = end.getRank();
			int endFile = end.getFile();
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
					Move tmpMove = new Move(end, this.getPosition(), this, squares[endRank][endFile].getPiece());
					try {
						return squares[endRank][endFile].getPiece().getPlayer() != this.getPlayer()
								&& !game.moveMakesCheck(tmpMove, game);
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
					Move tmpMove = new Move(end, this.getPosition(), this, squares[endRank][endFile].getPiece());

					try {
						return squares[endRank][endFile].getPiece().getPlayer() != this.getPlayer()
								&& !game.moveMakesCheck(tmpMove, game);
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	@Override
	public Position[] getPath(Position end) {
		int pathLength = Math.abs(this.getPosition().getRank() - end.getRank())
				+ Math.abs(this.getPosition().getFile() - end.getFile()) + 1;
		Position[] path = new Position[pathLength];
		for (int cnt = 0; cnt < pathLength; cnt++) {
			if ((this.getPosition().getRank() == end.getRank())) {
				path[cnt] = new Position(this.getPosition().getRank(),
						Math.min(this.getPosition().getFile(), end.getFile()) + cnt);
			} else {
				path[cnt] = new Position(Math.min(this.getPosition().getRank(), end.getRank()) + cnt,
						this.getPosition().getFile());
			}
		}
		return path;
	}
}
