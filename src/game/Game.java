package game;

import player.*;
import pieces.*;

public class Game {

	int turn; 
	PlayerType whoseTurn;
	Board board;
	
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

	
	
}
