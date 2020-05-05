package game;

import pieces.*;
import player.PlayerType;

public class Board {

	public Squares[][] squares = new Squares[8][8];
	
	Board() 
	{
		settingSquares();
		settingWhitePieces();
		settingBlackPieces();
	}
	public Squares[][] copyBoard() {
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
	
	public Squares retSquare(Position pos) 
	{
		return squares[pos.getRank()][pos.getFile()];
	}
	
	public Squares retSquare(int i, int j) 
	{
		return squares[i][j];
	}
	
	void settingWhitePieces() 
	{
		//pawns
		for (int i = 0; i < 8; i++) 
		{
			Position tempPos = new Position(1,i);
			squares[1][i].setPiece(new Pawn(PlayerType.W, tempPos));
		}
		//rooks
		Position tempRook1 = new Position(0,0);
		squares[0][0].setPiece(new Rook(PlayerType.W, tempRook1));
		Position tempRook2 = new Position(0,7);
		squares[0][7].setPiece(new Rook(PlayerType.W, tempRook2));
		//knights
		Position tempKnight1 = new Position(0,1);
		squares[0][1].setPiece(new Knight(PlayerType.W, tempKnight1));
		Position tempKnight2 = new Position(0,6);
		squares[0][6].setPiece(new Knight(PlayerType.W, tempKnight2));
		//bishops
		Position tempBishop1 = new Position(0,2);
		squares[0][2].setPiece(new Bishop(PlayerType.W, tempBishop1));
		Position tempBishop2 = new Position(0,2);
		squares[0][5].setPiece(new Bishop(PlayerType.W, tempBishop2));
		//queen
		Position tempQueen = new Position(0,4);
		squares[0][3].setPiece(new Queen(PlayerType.W, tempQueen));
		//king
		Position tempKing = new Position(0,3);
		squares[0][4].setPiece(new King(PlayerType.W, tempKing));
	}
	
	void settingBlackPieces() 
	{
		//pawns
		for (int i = 0; i < 8; i++) 
		{
			Position tempPos = new Position(6,i);
			squares[6][i].setPiece(new Pawn(PlayerType.B, tempPos));
		}
		//rooks
		Position tempRook1 = new Position(7,0);
		squares[7][0].setPiece(new Rook(PlayerType.B, tempRook1));
		Position tempRook2 = new Position(7,7);
		squares[7][7].setPiece(new Rook(PlayerType.B, tempRook2));
		
		//knights
		Position tempKnight1 = new Position(7,1);
		squares[7][1].setPiece(new Knight(PlayerType.B, tempKnight1));
		Position tempKnight2 = new Position(7,6);
		squares[7][6].setPiece(new Knight(PlayerType.B, tempKnight2));
	
		//bishops
		Position tempBishop1 = new Position(7,2);
		squares[7][2].setPiece(new Bishop(PlayerType.B, tempBishop1));
		Position tempBishop2 = new Position(7,5);
		squares[7][5].setPiece(new Bishop(PlayerType.B, tempBishop2));
		//king 
		Position tempKing = new Position(7,3);
		squares[7][4].setPiece(new King(PlayerType.B, tempKing));
		//queen
		Position tempQueen = new Position(7,4);
		squares[7][3].setPiece(new Queen(PlayerType.B, tempQueen));
	}
	
}
