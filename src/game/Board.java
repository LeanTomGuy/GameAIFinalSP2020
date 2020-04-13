package game;

import pieces.*;
import player.PlayerType;

public class Board {

	private Squares[][] squares = new Squares[8][8];
	
	Board() 
	{
		settingSquares();
		settingWhitePieces();
		settingBlackPieces();
	}
	
	public void resetGame() 
	{
		//nah 
	};
	
	void settingSquares() 
	{
		for (int j = 0; j < 8; j++) 
		{
			for( int i = 0; i < 8; i++) 
			{
				squares[j][i] = new Squares(new Position(j, i), new EmptyPiece(PlayerType.NONE));
			}
		}
		
	}
	
	Squares retSquare(int ind1, int ind2) 
	{
		return squares[ind1][ind2];
	}
	
	void settingWhitePieces() 
	{
		//pawns
		for (int i = 0; i < 8; i++) 
		{
			squares[1][i].setPiece(new Pawn(PlayerType.W));
		}
		//rooks
		squares[0][0].setPiece(new Rook(PlayerType.W));
		squares[0][7].setPiece(new Rook(PlayerType.W));
		//knights
		squares[0][1].setPiece(new Knight(PlayerType.W));
		squares[0][6].setPiece(new Knight(PlayerType.W));
		//bishops
		squares[0][2].setPiece(new Bishop(PlayerType.W));
		squares[0][5].setPiece(new Bishop(PlayerType.W));
		//queen
		squares[0][3].setPiece(new Queen(PlayerType.W));
		//king
		squares[0][4].setPiece(new King(PlayerType.W));
	}
	
	void settingBlackPieces() 
	{
		//pawns
		for (int i = 0; i < 8; i++) 
		{
			squares[6][i].setPiece(new Pawn(PlayerType.B));
		}
		//rooks
		squares[7][0].setPiece(new Rook(PlayerType.B));
		squares[7][7].setPiece(new Rook(PlayerType.B));
		
		//knights
		squares[7][1].setPiece(new Knight(PlayerType.B));
		squares[7][6].setPiece(new Knight(PlayerType.B));
	
		//bishops
		squares[7][2].setPiece(new Bishop(PlayerType.B));
		squares[7][5].setPiece(new Bishop(PlayerType.B));
		//king 
		squares[7][4].setPiece(new King(PlayerType.B));
		//queen
		squares[7][3].setPiece(new Queen(PlayerType.B));
	}
	
}
