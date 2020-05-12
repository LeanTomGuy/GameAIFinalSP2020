package game;

import java.util.ArrayList;
import java.util.List;

import pieces.*;
import player.PlayerType;

public class Board {

	List<Piece> whitePieces = new ArrayList<Piece>();
	List<Piece> blackPieces = new ArrayList<Piece>();
	public Squares[][] squares = new Squares[8][8];
	
	Board() 
	{
		settingSquares();
		settingWhitePieces();
		settingBlackPieces();
	}
	public Board copyBoard() 
	{
		Board out = new Board();
		out.squares = copySquares();
		out.whitePieces = copyPieces(PlayerType.W);
		out.blackPieces = copyPieces(PlayerType.B);
		
		return out;
	}
	
	public List<Piece> copyPieces(PlayerType player) 
	{
		List<Piece> outLst = new ArrayList<Piece>();
		List<Piece> toCopy;
		if(player == PlayerType.W) 
			toCopy = whitePieces;
		else
			toCopy =  blackPieces;
		for(Piece pce : toCopy) 
		{
			outLst.add(pce.copyPiece(pce));
		}
		
		return outLst;
	}
	
	public Squares[][] copySquares() {
		Squares[][] ret = new Squares[8][8];
		for (int j = 0; j < 8; j++) 
		{
			for( int i = 0; i < 8; i++) 
			{
				ret[j][i] = copy(squares[j][i]);
			}
		}
		return ret;
	}
	
	public Squares[][] getSquares() {
		return squares;
	}
	Squares copy(Squares square) {
		Position tempPos = new Position(square.pos.getRank(), square.pos.getFile());
		return new Squares(tempPos, square.getPiece());
	}
	
	public void resetGame() 
	{
		settingSquares();
		settingWhitePieces();
		settingBlackPieces();
	};
	
	void settingSquares() 
	{
		for (int j = 0; j < 8; j++) 
		{
			for( int i = 0; i < 8; i++) 
			{
				Position tempPos = new Position(j, i);
				squares[j][i] = new Squares(tempPos, new EmptyPiece(PlayerType.NONE, tempPos));
			}
		}
		
	}
	
	
	public void releasePiece(Position pos) 
	{
		retSquare(pos).releasePiece();
	}
	
	public Squares retSquare(Position pos) 
	{
		return squares[pos.getRank()][pos.getFile()];
	}
	
	public Squares retSquare(int i, int j) 
	{
		return squares[i][j];
	}
	
	public void setPiece(Piece newPiece, Position newPos) 
	{
		//System.out.println("Setting piece " + newPiece.toString() + " at position " + newPos.toString());
		retSquare(newPos).setPiece(newPiece);
	}
	
	void settingWhitePieces() 
	{
		//pawns
		for (int i = 0; i < 8; i++) 
		{
			Position tempPos = new Position(1,i);
			Piece tempPawn = new Pawn(PlayerType.W, tempPos);
			squares[1][i].setPiece(tempPawn);
			whitePieces.add(tempPawn);
		}
		//rooks
		Position tempRookPos1 = new Position(0,0);
		Piece tempRook = new Rook(PlayerType.W, tempRookPos1);
		squares[0][0].setPiece(tempRook);
		whitePieces.add(tempRook);
		Position tempRookPos2 = new Position(0,7);
		Piece tempRook2 = new Rook(PlayerType.W, tempRookPos2);
		squares[0][7].setPiece(tempRook2);
		whitePieces.add(tempRook2);
		//knights
		Position tempKnightPos1 = new Position(0,1);
		Piece tempKnight = new Knight(PlayerType.W, tempKnightPos1);
		squares[0][1].setPiece(tempKnight);
		whitePieces.add(tempKnight);
		Position tempKnightPos2 = new Position(0,6);
		Piece tempKnight2 = new Knight(PlayerType.W, tempKnightPos2);
		squares[0][6].setPiece(tempKnight2);
		whitePieces.add(tempKnight2);
		//bishops
		Position tempBishopPos1 = new Position(0,2);
		Piece tempBishop = new Bishop(PlayerType.W, tempBishopPos1);
		squares[0][2].setPiece(tempBishop);
		whitePieces.add(tempBishop);
		Position tempBishopPos2 = new Position(0,5);
		Piece tempBishop2 = new Bishop(PlayerType.W, tempBishopPos2);
		squares[0][5].setPiece(tempBishop2);
		whitePieces.add(tempBishop2);
		//queen
		Position tempQueenPos = new Position(0,3);
		Piece tempQueen = new Queen(PlayerType.W, tempQueenPos);
		squares[0][3].setPiece(tempQueen);
		whitePieces.add(tempQueen);
		//king
		Position tempKingPos = new Position(0,4);
		Piece tempKing = new King(PlayerType.W, tempKingPos);
		whitePieces.add(tempKing);
		squares[0][4].setPiece(tempKing);
	}
	
	void settingBlackPieces() 
	{
		//pawns
		for (int i = 0; i < 8; i++) 
		{
			Position tempPos = new Position(6,i);
			Piece tempPawn = new Pawn(PlayerType.B, tempPos);
			squares[6][i].setPiece(tempPawn);
			blackPieces.add(tempPawn);
			
		}
		//rooks
		Position tempRookPos1 = new Position(7,0);
		Piece tempRook = new Rook(PlayerType.B, tempRookPos1);
		blackPieces.add(tempRook);
		squares[7][0].setPiece(tempRook);
		Position tempRookPos2 = new Position(7,7);
		Piece tempRook2 = new Rook(PlayerType.B, tempRookPos2);
		blackPieces.add(tempRook2);
		squares[7][7].setPiece(tempRook2);
		
		//knights
		Position tempKnightPos1 = new Position(7,1);
		Piece tempKnight = new Knight(PlayerType.B, tempKnightPos1);
		blackPieces.add(tempKnight);
		squares[7][1].setPiece(tempKnight);
		Position tempKnightPos2 = new Position(7,6);
		Piece tempKnight2 = new Knight(PlayerType.B, tempKnightPos2);
		blackPieces.add(tempKnight2);
		squares[7][6].setPiece(tempKnight2);
	
		//bishops
		Position tempBishopPos1 = new Position(7,2);
		Piece tempBishop = new Bishop(PlayerType.B, tempBishopPos1);
		squares[7][2].setPiece(tempBishop);
		blackPieces.add(tempBishop);
		Position tempBishopPos2 = new Position(7,5);
		Piece tempBishop2 = new Bishop(PlayerType.B, tempBishopPos2);
		blackPieces.add(tempBishop2);
		squares[7][5].setPiece(tempBishop2);
		//king 
		Position tempKingPos = new Position(7,4);
		Piece tempKing = new King(PlayerType.B, tempKingPos);
		blackPieces.add(tempKing);
		squares[7][4].setPiece(tempKing);
		//queen
		Position tempQueenPos = new Position(7,3);
		Piece tempQueen = new Queen(PlayerType.B, tempQueenPos);
		blackPieces.add(tempQueen);
		squares[7][3].setPiece(tempQueen);
	}
	
}
