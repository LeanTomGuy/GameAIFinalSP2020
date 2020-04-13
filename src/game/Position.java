package game;

public class Position{

	int file;
	int rank;
	
	public Position(int f, int r)
	{
		// some array = upper
		// some array = lower -> check if in lower and in range
		this.file = f;
		this.rank = r;
	}
	
	public int getFile() 
	{
		return this.file;
	}
	
	public int getRank() 
	{
		return this.rank;
	}
}
