package pieces;

import java.util.ArrayList;
import java.util.List;

import game.Move;
import game.Position;
import game.Squares;
import player.PlayerType;

public class King extends Piece {
	
	public Boolean hasMoved = false;

	public King(PlayerType play, Position pos) {

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

		return " " + ret + "K ";
	}
	
	public String toString() {
		return "K";
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares) {
		List<Move> moveLst = new ArrayList<Move>();
		int thisRank = this.getPosition().getRank();
		int thisFile = this.getPosition().getFile();

		Position tempPos = new Position(thisRank + 1, thisFile + 1);
		Position tempPos2 = new Position(thisRank - 1, thisFile + 1);
		Position tempPos3 = new Position(thisRank - 1, thisFile - 1);
		Position tempPos4 = new Position(thisRank + 1, thisFile + 1);
		Position tempPos5 = new Position(thisRank, thisFile + 1);
		Position tempPos6 = new Position(thisRank, thisFile - 1);
		Position tempPos7 = new Position(thisRank + 1, thisFile);
		Position tempPos8 = new Position(thisRank - 1, thisFile);

		Position[] arr = { tempPos, tempPos2, tempPos3, tempPos4, tempPos5, tempPos6, tempPos7, tempPos8 };
		for (Position elem : arr) {
			if (elem.isValidPos()) {
				if (isValidMove(elem, squares)) {
					Move mve = new Move(this.getPosition(), elem, this,
							squares[elem.getRank()][elem.getFile()].getPiece());
					moveLst.add(mve);
				}
			}

		}

		return moveLst;
	}

	@Override
	public Boolean isValidMove(Position end, Squares[][] squares) {
		if (end.isValidPos() && !end.equals(this.getPosition())) {
			int thisRank = this.getPosition().getRank();
			int thisFile = this.getPosition().getFile();
			int endRank = end.getRank();
			int endFile = end.getFile();
			if ((Math.abs(thisRank - endRank) == 1 && thisFile == endFile)
					|| (Math.abs(thisFile - endFile) == 1 && thisRank == endRank)
					|| (Math.abs(thisFile - endFile) == 1 && Math.abs(thisRank - endRank) == 1)) {
				return squares[endRank][endFile].getPiece().getPlayer() != this.getPlayer();
			}
		} // castling not included (for now)
		return false;
	}

	@Override
	public Position[] getPath(Position end) {
		return null;
	}
}
