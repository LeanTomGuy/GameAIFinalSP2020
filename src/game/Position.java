package game;

public class Position{

	int rank;
	int file;
	
	public Position(int r, int f)
	{
		this.file = f;
		this.rank = r;
	}
	
	public Position copy() 
	{
		return new Position(this.getRank(),this.getFile());
	}
	public int getFile() 
	{
		return this.file;
	}
	
	public int getRank() 
	{
		return this.rank;
	}
	
	public boolean equals(Position position) {
		return ((file == position.getFile()) && (rank == position.getRank()));
	}
	public boolean isValidPos() {
		return((file >= 0 && file <8) && (rank >=0 && rank <8));
	}
	
	public boolean isValidPos(Position pos) {
		return ((pos.getFile() >= 0 && pos.getFile() < 8) && (pos.getRank() >= 0 && pos.getRank() < 8));
	}
	
	public String toString() {
		return "(" + rank + "," + file + ")"; 
	}

}
