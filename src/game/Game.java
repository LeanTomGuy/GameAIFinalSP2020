package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pieces.EmptyPiece;
import pieces.Piece;
import player.PlayerType;

public class Game {

	public int turn;
	public PlayerType whoseTurn;
	public Board board;
	public List<Move> moveLst;
	public Boolean isOver = false;
	
	
	public Game()
	{
		turn = 1;
		whoseTurn = PlayerType.W;
		board = new Board();
		moveLst = new ArrayList<Move>();
	}
	
	void display() 
	{
		for(int i = 7; i >= 0; i--) 
		{
			for (int j = 0; j < 8; j++) 
			{
				Squares tmp = board.retSquare(i, j);
				System.out.print(tmp.pie.toDisplay());
			}
			System.out.println();
		}
	}

	public void changeTurns() 
	{
		if(whoseTurn == PlayerType.B)
			whoseTurn = PlayerType.W;
		else
			whoseTurn = PlayerType.B;
	}
	
	public Move parseMove(String input) 
	{	
		String patternString = "([BKPQR]\\s[a-h][1-8]\\s[a-h][1-8])|(Kn\\s[a-h][1-8]\\s[a-h][1-8])";
		
		Pattern pattern = Pattern.compile(patternString);
		
		Matcher matcher = pattern.matcher(input);
		Position mIPos, mFPos;
		Piece mPce, mCap;
		
		if(matcher.matches()) {
		
			switch (input.substring(0, 1)) {
			
			case "K":
				if(input.substring(0, 2).equals("Kn")) {
					mIPos = stringToPosition(input.substring(3, 5));
					mFPos = stringToPosition(input.substring(6, 8));
					mPce = board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
					mCap = board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
					if(!(mPce.toString().equals("Kn") && mPce.getPlayer() == whoseTurn)) {
						System.out.println(mPce.toDisplay());
						
						mPce = new EmptyPiece(PlayerType.NONE, mIPos);
					}
				} else {
					mIPos = stringToPosition(input.substring(2, 4));
					mFPos = stringToPosition(input.substring(5, 7));
					mPce = board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
					mCap = board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
					if(!(mPce.toString().equals("K") && mPce.getPlayer() == whoseTurn)) {
						mPce = new EmptyPiece(PlayerType.NONE, mIPos);
					}
				}
				break;
			case "B":
				mIPos = stringToPosition(input.substring(2, 4));
				mFPos = stringToPosition(input.substring(5, 7));
				mPce = board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
				mCap = board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
				if(!(mPce.toString().equals("B") && mPce.getPlayer() == whoseTurn)) {
					mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				}
				break;
			case "R":
				mIPos = stringToPosition(input.substring(2, 4));
				mFPos = stringToPosition(input.substring(5, 7));
				mPce = board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
				mCap = board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
				if(!(mPce.toString() == "R" && mPce.getPlayer() == whoseTurn)) {
					mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				}
				break;
			case "P":
				mIPos = stringToPosition(input.substring(2, 4));
				mFPos = stringToPosition(input.substring(5, 7));
				mPce = board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
				mCap = board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
				if(!(mPce.toString().equals("P") && mPce.getPlayer() == whoseTurn)) {
					mPce = new EmptyPiece(PlayerType.NONE, mIPos);
				}
				break;
			case "Q":
				mIPos = stringToPosition(input.substring(2, 4));
				mFPos = stringToPosition(input.substring(5, 7));
				mPce = board.squares[mIPos.getRank()][mIPos.getFile()].getPiece();
				mCap = board.squares[mFPos.getRank()][mFPos.getFile()].getPiece();
				if(!(mPce.toString().equals("Q") && mPce.getPlayer() == whoseTurn)) {
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
		if(str.length()!=2) {
			return new Position(-1,-1);
		}
		int file = str.charAt(0)-97; //converts from ASCII value to our range
		int rank = Integer.parseInt(str.substring(1))-1;
		return new Position(rank,file);
	}
	
	public void update() 
	{
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNext()) {
			
		}
			String input = sc.nextLine().trim();
			Move move = parseMove(input);
			if (move.getPiece().toString().equals("_")) {
				System.out.println("Not valid, try again.\n");
				sc.close();
				update();
			}
			if (!(move.getPiece().isValidMove(move.getFinalPosition(), board.squares))
					|| !(move.getInitPosition().equals(move.getPiece().getPosition()))) {
				System.out.println(move.getPiece().toString());
				System.out.println(move.getPiece().getPosition().toString());
				System.out.println("Not valid, try again.");
				sc.close();
				update();
			} else {
				if (move.getCapturedPiece().getPlayer() != whoseTurn) {
					moveLst.add(move);
					makeMove(move);
					if(whoseTurn == PlayerType.B) turn += 1;
					changeTurns();
				}
			}
		sc.close();
	}

	public void makeMove(Move move) {
		Piece movedPiece = move.getPiece();
		movedPiece.setPosition(move.getFinalPosition());
		board.squares[move.getFinalPosition().getRank()][move.getFinalPosition().getFile()].releasePiece();
		board.squares[move.getInitPosition().getRank()][move.getInitPosition().getFile()].releasePiece();
		board.squares[move.getFinalPosition().getRank()][move.getFinalPosition().getFile()].setPiece(movedPiece);
	}
}