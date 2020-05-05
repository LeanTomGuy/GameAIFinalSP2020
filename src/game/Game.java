package game;

import player.*;
import pieces.*;
import java.util.List;
import java.util.Scanner;

public class Game {

	int turn; 
	PlayerType whoseTurn;
	Board board;
	List<Move> moveLst;
	
	public Game()
	{
		turn = 1;
		whoseTurn = PlayerType.W;
		board = new Board();
	}
	
	void display() 
	{
		for(int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			{
				Squares tmp = board.retSquare(i, j);
				System.out.print(tmp.pie.toString());
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
	
	public Move parseInput(String input) 
	{	
		Position mIPos, mFPos;
		Piece mPce, mCap;
		String p = "";
		Boolean check = false;
		
		switch(input.substring(0,1)) 
		{
		
	
		
			//default: "_";
			
			
		}
		return new Move(mIPos, mFPos, mPce, mCap);
	}
	public void update() 
	{
		Scanner sc = new Scanner(System.in);
		if(sc.hasNext()) {
		String moveToInput = sc.nextLine().trim();
		
		/*parse moveToInput with Move Parser -> place in Move mve 
		//if (mve.isValidMove(board.copy(board.squares));
		//int move
		//Piece pieceToMove = board.squares[mve.initPosition.getRank()][mve.initPosition.getFile].getPiece();
		/ if (mve.capture) 
		{
			board.squares[][]
		}
		// setPiece 
		
		*/}

		sc.close();
	}
}
