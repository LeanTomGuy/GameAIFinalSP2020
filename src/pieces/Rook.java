package pieces;

import java.util.List;

import game.Move;
import game.Position;
import game.Squares;
import player.PlayerType;

public class Rook extends Piece {

	public Boolean hasMoved = false;
	
	public Rook(PlayerType play, Position pos)
	{
		
			super(play, pos);
	}
	
	public String toString() 
	{
		String ret;
		
		switch(this.getPlayer()) 
		{
		case W:
			ret = "W";
			break;
		case B:
			ret = "B";
			break;
		default:
			ret = "";
		}
		
		return " "+ret+"R ";
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean isValidMove(Position destination, Squares[][] squares) {
		int thisRank = this.getPosition().getRank();
		int thisFile = this.getPosition().getFile();
		int destRank = destination.getRank();
		int destFile = destination.getFile();
		if(destination.isValidPos() && !this.getPosition().equals(destination)) {
			if(thisRank - destRank == 0 || thisFile - destFile == 0) {
				if(thisRank - destRank != 0) {
					if(thisRank > destRank) {
						for(int i = thisRank-1; i > destRank; i--) {
							if(!squares[i][thisFile].isEmpty()) {
								return false;
							}
						}
					}
					else {
						for(int i = thisRank+1; i < destRank; i++) {
							if(!squares[i][thisFile].isEmpty()) {
								return false;
							}
						}
					}
					return squares[destRank][destFile].getPiece().getPlayer() != this.getPlayer();
				} else {
					if(thisFile > destFile) {
						for(int i = thisFile - 1; i > destFile; i--) {
							if(!squares[thisRank][i].isEmpty()) {
								return false;
							}
						}
					} else {
						for(int i = thisFile + 1; i < destFile; i++) {
							if(!squares[thisRank][i].isEmpty()) {
								return false;
							}
						}
					}
					return squares[destRank][destFile].getPiece().getPlayer() != this.getPlayer();
				}
			}
		}
		return false;
	}

	@Override
	public Position[] getPath(Position end) {
		// TODO Auto-generated method stub
		return null;
	}
}
