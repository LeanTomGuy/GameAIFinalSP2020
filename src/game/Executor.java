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
		
		for(int i = 0; i <3; i++) {
			System.out.println("Input a valid move:\n");
			game.update();
			game.display();
		}
		
	}
}
