package agents;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import game.*;
import pieces.EmptyPiece;
import pieces.Piece;
import player.PlayerType;

public class Human extends Agent {

	Scanner sc =  new Scanner(System.in);
;

	public Human(PlayerType player) {
		super(player);
	}

	public Position stringToPosition(String str) {
		if (str.length() != 2) {
			return new Position(-1, -1);
		}
		int file = java.lang.Character.toLowerCase(str.charAt(0)) - 97; // converts from ASCII value to our range
		int rank = Integer.parseInt(str.substring(1)) - 1;
		return new Position(rank, file);
	}

	@Override
	public Move getMove(Game game, long timeDue) {
		while (!sc.hasNext()) {

		}
		String input = sc.nextLine().trim();
		Move move = parseMove(input, game);
		if (move.getPiece().toString().equals("_")) {
			System.out.println("Not valid, try again.\n");
			return getMove(game, timeDue);
		}
		try {
			if (!(move.getPiece().isValidMove(move.getFinalPosition(), game.board.copySquares(), game.clone()))
					|| !(move.getInitPosition().equals(move.getPiece().getPosition()))) {
				// System.out.println(move.getPiece().toString());
				// System.out.println(move.getPiece().getPosition().toString());
				// System.out.println(move.getFinalPosition().toString());
				// System.out.println("Not valid, try again.");
				try {
					if (game.moveMakesCheck(move, game.clone()))
						System.out.println("can't move into check. try again.");
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				return getMove(game, timeDue);

			} else {
				if (move.getCapturedPiece().getPlayer() != game.whoseTurn) {
					System.out.println("move made\n");
					System.out.println("new:" + this.player);
					return move;
				} else
					return getMove(game, timeDue);
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return move;
		}
	}

	public Move parseMove(String input, Game game) {
		String patternString = "([BKPQR]\\s[a-h][1-8]\\s[a-h][1-8])|(Kn\\s[a-h][1-8]\\s[a-h][1-8])";
		String castlePat = "castle";

		Pattern pattern = Pattern.compile(patternString);

		Matcher matcher = pattern.matcher(input);

		Position mIPos, mFPos;
		Piece mPce, mCap;

		if (matcher.matches()) {

			switch (input.substring(0, 1).toUpperCase()) {

			case "K":
				if (input.substring(0, 2).equals("Kn")) {
					mIPos = stringToPosition(input.substring(3, 5));
					mFPos = stringToPosition(input.substring(6, 8));
					mPce = game.board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
					mCap = game.board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
					if (!(mPce.toString().equals("Kn") && mPce.getPlayer() == this.player)) {
						System.out.println(mPce.toDisplay());

						mPce = new EmptyPiece(PlayerType.NONE, mIPos);
					}
				} else {
					mIPos = stringToPosition(input.substring(2, 4));
					mFPos = stringToPosition(input.substring(5, 7));
					mPce = game.board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
					mCap = game.board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
					if (!(mPce.toString().equals("K") && mPce.getPlayer() == this.player)) {
						mPce = new EmptyPiece(PlayerType.NONE, mIPos);
					}
				}
				break;
			case "B":
				mIPos = stringToPosition(input.substring(2, 4));
				mFPos = stringToPosition(input.substring(5, 7));
				mPce = game.board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
				mCap = game.board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
				if (!(mPce.toString().equals("B") && mPce.getPlayer() == this.player)) {
					mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				}
				break;
			case "R":
				mIPos = stringToPosition(input.substring(2, 4));
				mFPos = stringToPosition(input.substring(5, 7));
				mPce = game.board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
				mCap = game.board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
				if (!(mPce.toString() == "R" && mPce.getPlayer() == this.player)) {
					mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				}
				break;
			case "P":
				mIPos = stringToPosition(input.substring(2, 4));
				mFPos = stringToPosition(input.substring(5, 7));
				mPce = game.board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
				mCap = game.board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
				System.out.println(mPce.toString());
				System.out.println(this.player);
				if (!(mPce.toString().equals("P") && mPce.getPlayer() == this.player)) {
					mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				}
				break;
			case "Q":
				mIPos = stringToPosition(input.substring(2, 4));
				mFPos = stringToPosition(input.substring(5, 7));
				mPce = game.board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
				mCap = game.board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
				if (!(mPce.toString().equals("Q") && mPce.getPlayer() == this.player)) {
					mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				}
				break;
			default:
				mIPos = new Position(-1, -1);
				mFPos = new Position(-1, -1);
				mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				mCap = new EmptyPiece(PlayerType.NONE, mIPos);

			}
		} else if (input.contains(castlePat)) {
			String whichSide = input.substring(6).toLowerCase();
			switch (whichSide) {
			case "k":
				mPce = game.getKingSquare(this.player).getPiece();
				mIPos = game.getKingSquare(this.player).getPosition();
				mFPos = new Position(mIPos.getRank(), mIPos.getFile() + 2);
				mCap = game.board.retSquare(mFPos).getPiece();

			case "q":
				mPce = game.getKingSquare(this.player).getPiece();
				mIPos = game.getKingSquare(this.player).getPosition();
				mFPos = new Position(mIPos.getRank(), mIPos.getFile() - 2);
				mCap = game.board.retSquare(mFPos).getPiece();

			default:
				mIPos = new Position(-1, -1);
				mFPos = new Position(-1, -1);
				mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				mCap = new EmptyPiece(PlayerType.NONE, mIPos);
			}
		}

		else {
			mIPos = new Position(-1, -1);
			mFPos = new Position(-1, -1);
			mPce = new EmptyPiece(PlayerType.NONE, mIPos);
			mCap = new EmptyPiece(PlayerType.NONE, mIPos);
		}
		return new Move(mIPos, mFPos, mPce, mCap);
	}

}
