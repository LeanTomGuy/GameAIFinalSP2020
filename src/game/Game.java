package game;

import java.util.ArrayList;
import java.util.HashMap;

import agents.*;
import java.util.List;
import java.util.Map;
//import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pieces.EmptyPiece;
import pieces.Piece;
import player.PlayerType;

public class Game implements Cloneable {

	public int turn;
	public PlayerType whoseTurn;
	public Agent agentTurn;
	public Board board;
	public List<Move> moveLst;
	public Boolean wInCheck = false, bInCheck = false, gameOver = false, cantCastle = false;
	Position wKingPos = new Position(0, 4);
	Position bKingPos = new Position(7, 4);
	public int turnLimit = 200;
	Agent p1, p2;
	List<Node> nodesLst = new ArrayList<Node>();
	Map<String, Integer> map;

	public Game(Agent p1, Agent p2 ) {
		turn = 1;
		agentTurn = p1;
		whoseTurn = PlayerType.W;
		board = new Board();
		moveLst = new ArrayList<Move>();
		this.p1 = p1;
		this.p2 = p2;
		map = new HashMap<>();
	//	map.put("K", )
	}
	
	public Game clone() throws CloneNotSupportedException
	{
		Game cloned = (Game) super.clone();
	

		cloned.turn =this.turn;
		cloned.whoseTurn = this.whoseTurn;
		cloned.board = this.board;
		cloned.moveLst = this.moveLst;
		cloned.wInCheck = this.wInCheck;
		cloned.bInCheck = this.bInCheck;
		cloned.gameOver = this.gameOver;
		//cloned.sc = this.sc;
		cloned.wKingPos = this.wKingPos;
		cloned.bKingPos = this.bKingPos;
		cloned.turnLimit = this.turnLimit;
		return cloned;
	}
	
	void display() {
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				Squares tmp = board.retSquare(i, j);
				System.out.print(tmp.pie.toDisplay());
			}
			System.out.println();
		}
	}

	public void changeTurns() {
		//System.out.println("changing turns\n");
		if (whoseTurn == PlayerType.W)
			whoseTurn = PlayerType.B;
		else
			whoseTurn = PlayerType.W;
	}


	public void changeAgentTurn() 
	{
		if(agentTurn == p1)
			agentTurn = p2;
		else
			agentTurn = p1;
	}
	
	public Move parseMove(String input) {
		String patternString = "([BKPQR]\\s[a-h][1-8]\\s[a-h][1-8])|(Kn\\s[a-h][1-8]\\s[a-h][1-8])";
		String castlePat = "castle ";

		Pattern pattern = Pattern.compile(patternString);

		Matcher matcher = pattern.matcher(input);

		Position mIPos, mFPos;
		Piece mPce, mCap;

		if (input.contains(castlePat)) {
	//		System.out.println("reached castling");
			String whichSide = input.substring(7).toLowerCase();
			switch (whichSide) {
			case "k":
				mPce = getKingSquare(whoseTurn).getPiece();
				mIPos = getKingSquare(whoseTurn).getPosition();
				mFPos = new Position(mIPos.getRank(), mIPos.getFile() + 2);
				mCap = board.retSquare(mFPos).getPiece();

			case "q":
				mPce = getKingSquare(whoseTurn).getPiece();
				mIPos = getKingSquare(whoseTurn).getPosition();
				mFPos = new Position(mIPos.getRank(), mIPos.getFile() - 2);
				mCap = board.retSquare(mFPos).getPiece();

			default:
				mIPos = new Position(-1, -1);
				mFPos = new Position(-1, -1);
				mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				mCap = new EmptyPiece(PlayerType.NONE, mIPos);
			}
		} else if (matcher.matches()) {

			switch (input.substring(0, 1).toUpperCase()) {

			case "K":
				if (input.substring(0, 2).equals("Kn")) {
					mIPos = stringToPosition(input.substring(3, 5));
					mFPos = stringToPosition(input.substring(6, 8));
					mPce = board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
					mCap = board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
					if (!(mPce.toString().equals("Kn") && mPce.getPlayer() == whoseTurn)) {
	//					System.out.println(mPce.toDisplay());

						mPce = new EmptyPiece(PlayerType.NONE, mIPos);
					}
					break;
				} else {
					mIPos = stringToPosition(input.substring(2, 4));
					mFPos = stringToPosition(input.substring(5, 7));
					mPce = board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
					mCap = board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
					if (!(mPce.toString().equals("K") && mPce.getPlayer() == whoseTurn)) {
						mPce = new EmptyPiece(PlayerType.NONE, mIPos);
					}
					break;
				}
				
			case "B":
				mIPos = stringToPosition(input.substring(2, 4));
				mFPos = stringToPosition(input.substring(5, 7));
				mPce = board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
				mCap = board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
				if (!(mPce.toString().equals("B") && mPce.getPlayer() == whoseTurn)) {
					mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				}
				break;
			case "R":
				mIPos = stringToPosition(input.substring(2, 4));
				mFPos = stringToPosition(input.substring(5, 7));
				mPce = board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
				mCap = board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
				if (!(mPce.toString() == "R" && mPce.getPlayer() == whoseTurn)) {
					mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				}
				break;
			case "P":
				mIPos = stringToPosition(input.substring(2, 4));
				mFPos = stringToPosition(input.substring(5, 7));
				mPce = board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
				mCap = board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
//				System.out.println(mPce.toString());
//				System.out.println(whoseTurn);
				if (!(mPce.toString().equals("P") && mPce.getPlayer() == whoseTurn)) {
					mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				}
				break;
			case "Q":
				mIPos = stringToPosition(input.substring(2, 4));
				mFPos = stringToPosition(input.substring(5, 7));
				mPce = board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
				mCap = board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
				if (!(mPce.toString().equals("Q") && mPce.getPlayer() == whoseTurn)) {
					mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				}
				break;
			default:
				mIPos = new Position(-1, -1);
				mFPos = new Position(-1, -1);
				mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				mCap = new EmptyPiece(PlayerType.NONE, mIPos);

			}
		} else {
			mIPos = new Position(-1, -1);
			mFPos = new Position(-1, -1);
			mPce = new EmptyPiece(PlayerType.NONE, mIPos);
			mCap = new EmptyPiece(PlayerType.NONE, mIPos);
		}
		return new Move(mIPos, mFPos, mPce, mCap);
	}

	public Position stringToPosition(String str) {
		if (str.length() != 2) {
			return new Position(-1, -1);
		}
		int file = java.lang.Character.toLowerCase(str.charAt(0)) - 97; // converts from ASCII value to our range
		int rank = Integer.parseInt(str.substring(1)) - 1;
		return new Position(rank, file);
	}

	public void update() throws CloneNotSupportedException {
		System.out.println("Current turn is : "+whoseTurn);

		Move move = agentTurn.getMove(this.clone(), 10000);
//		System.out.println(move.toString());
		
		if (move.getPiece().toString().equals("_")) {
			System.out.println("Not valid, try again.\n");
			update();
		}
		if (!(move.getPiece().isValidMove(move.getFinalPosition(), board.copySquares(), this.clone()))
				|| !(move.getInitPosition().equals(move.getPiece().getPosition()))) {
			// System.out.println(move.getPiece().toString());
			// System.out.println(move.getPiece().getPosition().toString());
			// System.out.println(move.getFinalPosition().toString());
			// System.out.println("Not valid, try again.");
			if (moveMakesCheck(move, this.clone())) 
				System.out.println("can't move into check. try again.");
			update();
			
		} else {
			if (move.getCapturedPiece().getPlayer() != whoseTurn) {
				advanceGame(move);
				moveLst.add(move);
//				System.out.println("move made\n");
//				System.out.println("new:" + whoseTurn);
				if (whoseTurn == PlayerType.B)
					turn += 1;
			}
			else
				update();
		}
			System.out.println("Turn " + turn);
	}

	// castling

	// en passsant

	// pawn promotion

	public Boolean isCheck(PlayerType player) throws CloneNotSupportedException {
		if (getAttackingPieces(player).size() > 0) {
			setCheck(player, true);
			return true;
		} else
			setCheck(player, false);
		return false;
	}

	public void setCheck(PlayerType player, Boolean assign) {
		switch (player) {
		case W:
			wInCheck = assign;
		case B:
			bInCheck = assign;
		default:
		}
	}

	public Boolean isCheckmate(PlayerType player) throws CloneNotSupportedException {
		List<Piece> atk = getAttackingPieces(player);
		if (atk.isEmpty()) {
			return false;
		} else {
			List<Move> validMoves = new ArrayList<Move>();
			switch (player) {
			case W:
				for (Piece each : board.whitePieces) {

					validMoves.addAll(each.getValidMoves(board.squares, this.clone()));
				}
			case B:
				for (Piece each : board.blackPieces) {
					validMoves.addAll(each.getValidMoves(board.squares, this.clone()));
				}
			default:

			}
			for (Move mv : validMoves) {
//				System.out.println(mv.toString());
			}
			return validMoves.isEmpty(); // we back
		}
	}

	public Boolean isGameOver() throws CloneNotSupportedException {
		return (isCheckmate(PlayerType.W) || isCheckmate(PlayerType.B) || isDraw());
	}

	public Boolean isDraw() throws CloneNotSupportedException {
		if (isCheck(whoseTurn))
			return false;
		else {
			List<Move> validMoves = new ArrayList<Move>();
			switch (whoseTurn) {
			case W:
				for (Piece each : board.whitePieces) {

					validMoves.addAll(each.getValidMoves(board.copySquares(), this.clone()));
				}
			case B:
				for (Piece each : board.whitePieces) {
					validMoves.addAll(each.getValidMoves(board.copySquares(), this.clone()));
				}
			default:

			}
			return ((board.blackPieces.size() == 1 && board.whitePieces.size() == 1)) || validMoves.isEmpty()
					|| turn >= turnLimit;
		}
	}

	public List<Piece> getAttackingPieces(PlayerType player) throws CloneNotSupportedException {
		List<Piece> pceLst = new ArrayList<Piece>();
		Squares kingSquare = getKingSquare(player);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Squares tmpSquare = board.retSquare(i, j);
				if (!tmpSquare.isEmpty() && tmpSquare.getPiece().getPlayer() != player) {
					if (tmpSquare.getPiece().isValidMove(kingSquare.getPosition(), board.copySquares(), this.clone())) {
						pceLst.add(tmpSquare.getPiece());
					}
				}
			}
		}

		return pceLst;
	}

	public void updateKing(PlayerType player, Position newPos) {
		// assuming none type should never get passed in
		if (player == PlayerType.W) {
			wKingPos = newPos;
		} else
			bKingPos = newPos;
	}

	public Squares getKingSquare(PlayerType player) {
		if (player == PlayerType.W)
			return board.retSquare(wKingPos);
		else
			return board.retSquare(bKingPos);
	}

	public void endGame() {
		System.out.println("Game over; if this is a surprise to you, you probably got mated lol.");
	}

	public Boolean moveMakesCheck(Move move, Game copy) throws CloneNotSupportedException {
		simulateMove(move);
		Boolean check = copy.isCheck(move.getPiece().getPlayer());
		//undoMove(move);
		return check;
	}

	public Board simulateMove(Move move) {
		Piece movedPiece = Piece.copyPiece(move.getPiece());
		Board newBoard = board.copyBoard();
		if (move.isCastle()) {
			movedPiece.setPosition(move.getFinalPosition());
			newBoard.retSquare(move.getInitPosition()).releasePiece();
			newBoard.retSquare(move.getFinalPosition()).releasePiece();
			newBoard.retSquare(move.getFinalPosition()).setPiece(movedPiece);

			if (move.getFinalPosition().getFile() > move.getInitPosition().getFile()) {
				Piece movedRook = Piece.copyPiece(board.retSquare(move.getInitPosition().getRank(), 7).getPiece());
				newBoard.retSquare(movedRook.getPosition()).releasePiece();
				movedRook.setPosition(new Position(movedRook.getPosition().getRank(), 5));
				newBoard.retSquare(movedRook.getPosition()).releasePiece();
				newBoard.retSquare(movedRook.getPosition()).setPiece(movedRook);
				changeTurns();
				changeAgentTurn();
				
			} else {
				Piece movedRook = Piece.copyPiece(board.retSquare(move.getInitPosition().getRank(), 0).getPiece());
				newBoard.retSquare(movedRook.getPosition()).releasePiece();
				movedRook.setPosition(new Position(movedRook.getPosition().getRank(), 3));
				newBoard.retSquare(movedRook.getPosition()).releasePiece();
				newBoard.retSquare(movedRook.getPosition()).setPiece(movedRook);
				changeTurns();
				changeAgentTurn();
			}
		} else {
			if (move.isCapture()) {
				if (move.getCapturedPiece().getPlayer() == PlayerType.W)
					newBoard.whitePieces.remove(move.getCapturedPiece());
				else
					newBoard.blackPieces.remove(move.getCapturedPiece());
			}
			movedPiece.setPosition(move.getFinalPosition());
			/*
			System.out.println(move.toString());
			System.out.println("(sim) initial at " + move.getInitPosition().toString() + "|" 
			+ move.getInitPosition().getRank() + move.getInitPosition().getFile());
			System.out.println("final at " + move.getFinalPosition().toString() + "|" 
					+ move.getFinalPosition().getRank() + move.getFinalPosition().getFile());*/
			newBoard.squares[move.getFinalPosition().getRank()][move.getFinalPosition().getFile()].releasePiece();
			newBoard.squares[move.getInitPosition().getRank()][move.getInitPosition().getFile()].releasePiece();
			newBoard.squares[move.getFinalPosition().getRank()][move.getFinalPosition().getFile()].setPiece(movedPiece);
			changeTurns();
			changeAgentTurn();
		}
		
		return newBoard;
	}

	public void advanceGame(Move move) {
	//	System.out.println(move.toString());
		Piece movedPiece = move.getPiece();
		if (move.isCastle()) {
			System.out.println("isCastle successful");
			movedPiece.setPosition(move.getFinalPosition());
			board.retSquare(move.getInitPosition()).releasePiece();
			board.retSquare(move.getFinalPosition()).releasePiece();
			board.retSquare(move.getFinalPosition()).setPiece(movedPiece);

			if (move.getFinalPosition().getFile() > move.getInitPosition().getFile()) {
				Piece movedRook = board.retSquare(move.getInitPosition().getRank(), 7).getPiece();
				board.retSquare(movedRook.getPosition()).releasePiece();
				movedRook.setPosition(new Position(movedRook.getPosition().getRank(), 5));
				board.retSquare(movedRook.getPosition()).releasePiece();
				board.retSquare(movedRook.getPosition()).setPiece(movedRook);
				changeTurns();
				changeAgentTurn();
			} else {
				Piece movedRook = board.retSquare(move.getInitPosition().getRank(), 0).getPiece();
				board.retSquare(movedRook.getPosition()).releasePiece();
				movedRook.setPosition(new Position(movedRook.getPosition().getRank(), 3));
				board.retSquare(movedRook.getPosition()).releasePiece();
				board.retSquare(movedRook.getPosition()).setPiece(movedRook);
				changeTurns();
				changeAgentTurn();
			}
		} else {
//			System.out.println("not castle");
			if (movedPiece.toString().equals("R") || movedPiece.toString().equals("K"))
			{	//movedPiece.hasMoved = true;
				cantCastle = true;
				System.out.println("This shit is happening.");
			}
			if (move.isCapture()) {
//				System.out.println("isCapture");
				if (move.getCapturedPiece().getPlayer() == PlayerType.W)
					board.whitePieces.remove(move.getCapturedPiece());
				else
					board.blackPieces.remove(move.getCapturedPiece());
			}
			movedPiece.setPosition(move.getFinalPosition());
//			System.out.println(move.toString());
//			System.out.println("initial at " + move.getInitPosition().toString() + "|" 
//			+ move.getInitPosition().getRank() + move.getInitPosition().getFile());
//			System.out.println("final at " + move.getFinalPosition().toString() + "|" 
//					+ move.getFinalPosition().getRank() + move.getFinalPosition().getFile());
			board.squares[move.getFinalPosition().getRank()][move.getFinalPosition().getFile()].releasePiece();
			board.squares[move.getInitPosition().getRank()][move.getInitPosition().getFile()].releasePiece();
			board.squares[move.getFinalPosition().getRank()][move.getFinalPosition().getFile()].setPiece(movedPiece);
			changeTurns();
			changeAgentTurn();
		}
	}
	
}