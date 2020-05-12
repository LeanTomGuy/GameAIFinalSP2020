package game;

import agents.*;
import player.*;
import pieces.*;


public class Executor 
{
	public static void main(String args[]) throws CloneNotSupportedException 
	{
		//run 
		Executor ex = new Executor();
		Agent one = new Human (PlayerType.W);
		Agent two = new RandomAgent(PlayerType.B);
		Agent three = new Human(PlayerType.B);
		//human "agent" 
		ex.runGame(one,two);
	}

	void runGame(Agent agent1, Agent agent2) throws CloneNotSupportedException
	{
		Game game = new Game(agent1, agent2);
		game.display();
		
		while(!game.gameOver) 
		{
			System.out.println("Input a valid move:\n");
			game.update();
			game.display();
		    game.isGameOver();
		}
		
		game.endGame();
	}
}
