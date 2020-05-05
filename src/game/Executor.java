package game;

public class Executor 
{
	public static void main(String args[]) 
	{
		//run 
		Executor ex = new Executor();
		ex.runGame();
	}

	void runGame()
	{
		Game game = new Game();
		game.display();
		/*
		 *  while(!(game.gameOver()))
		 *  	game.update();
		 */
		
	}
}
