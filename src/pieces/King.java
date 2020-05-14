package pieces;

import java.util.ArrayList;
import java.util.List;

import game.*;
import player.PlayerType;

public class King extends Piece {

	public Boolean hasMoved = false;
	public Boolean inCheck = false;

	public static int value = 0;
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

		return "  " + ret + "K   ";
	}

	public String toString() {
		return "K";
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares, Game game) {
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
		Position tempPos9 = new Position(thisRank, thisFile+2);
		Position tempPos10 = new Position(thisRank, thisFile-2);
		
		Position[] arr = { tempPos, tempPos2, tempPos3, tempPos4, tempPos5, tempPos6, tempPos7, tempPos8, tempPos9, tempPos10};
		for (Position elem : arr) {
			if (elem.isValidPos()) {
				if (isValidMove(elem, squares, game)) {
					Move mve = new Move(this.getPosition(), elem, this,
							squares[elem.getRank()][elem.getFile()].getPiece());
					moveLst.add(mve);
				}
			}

		}

		return moveLst;
	}

	@Override
	public Boolean isValidMove(Position end, Squares[][] squares, Game game) {
		if (end.isValidPos() && !end.equals(this.getPosition())) {
			int thisRank = this.getPosition().getRank();
			int thisFile = this.getPosition().getFile();
			int endRank = end.getRank();
			int endFile = end.getFile();

			if ((Math.abs(thisRank - endRank) == 1 && thisFile == endFile)
					|| (Math.abs(thisFile - endFile) == 1 && thisRank == endRank)
					|| (Math.abs(thisFile - endFile) == 1 && Math.abs(thisRank - endRank) == 1)) {
				Move tmpMove = new Move(end, this.getPosition(), this, squares[endRank][endFile].getPiece());
				try {
					return squares[endRank][endFile].getPiece().getPlayer() != this.getPlayer()
							&& !game.moveMakesCheck(tmpMove, game);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}

			if (Math.abs(thisFile - endFile) == 2 && (thisRank == endRank) && !hasMoved) {
//				System.out.println("Mama we made it");
				if (thisFile > endFile) {
					Piece rook = game.board.retSquare(thisRank, 0).getPiece();
					//System.out.println("boolean check is " + game.cantCastle);
					if (rook.toString().equals("R") && !game.cantCastle) {
						try {
							if (!game.isCheck(this.getPlayer())) {
								for (int i = thisFile - 1; i >= 1; i--) {
									if (!game.board.retSquare(thisRank, i).isEmpty()) {
										return false;
									}
								}
								Move tmpMove2 = new Move(this.getPosition(), new Position(thisRank, thisFile-1), this, game.board.retSquare(thisRank, thisFile-1).getPiece());
								return !game.moveMakesCheck(tmpMove2, game);
								
							} else {
								return false;
							}
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
					}
				} else {
//					System.out.println("We make it to here maybe?");
					Piece rook = game.board.retSquare(thisRank, 7).getPiece();
//					System.out.println(rook.toString());
//					System.out.println(rook.hasMoved);
					if (rook.toString().equals("R")) { //&& !rook.hasMoved) {
						try {
							if (!game.isCheck(this.getPlayer())) {
//								System.out.println("Do we make it here?");
								for (int i = thisFile + 1; i < 7; i++) {
									if (!game.board.retSquare(thisRank, i).isEmpty()) {
										return false;
									}
								}
								Move tmpMove2 = new Move(this.getPosition(), new Position(thisRank, thisFile+1), this, game.board.retSquare(thisRank, thisFile+1).getPiece());
//								System.out.println("Made it to here in King ValidMove");
								return !game.moveMakesCheck(tmpMove2, game);
							}
							else {
								return false;
							}
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return false;
	}

	public void setCheck() {
		inCheck = true;
	}

	@Override
	public Position[] getPath(Position end) {
		Position[] arr = { this.getPosition(), end };
		return arr;
	}
}
