package pieces;

import java.util.ArrayList;
import java.util.List;

import game.Move;
import game.Position;
import game.Squares;
import player.PlayerType;

public class Rook extends Piece {

	public static int value = 5;
	
	public Boolean hasMoved = false;
	
	public Rook(PlayerType play, Position pos)
	{
		
			super(play, pos);
	}
	
	public String toDisplay() 
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
	
	public String toString() {
		return "R";
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares) {
		
		List<Move> moveLst = new ArrayList<Move>();
		int thisRank = this.getPosition().getRank();
		int thisFile = this.getPosition().getFile();
		for(int i = thisRank+1; i < 8; i++) {
			Position tempPos = new Position(i,thisFile);
			if(isValidMove(tempPos, squares)) {
				Move move = new Move(this.getPosition(), tempPos, 
						this, squares[tempPos.getRank()][thisFile].getPiece());
				moveLst.add(move);
			}	
		}
		for(int i = thisRank-1; i >= 0; i--) {
			Position tempPos = new Position(i,thisFile);
			if(isValidMove(tempPos, squares)) {
				Move move = new Move(this.getPosition(), tempPos, 
						this, squares[tempPos.getRank()][thisFile].getPiece());
				moveLst.add(move);
			}
		}
		
		for(int i = thisFile+1; i < 8; i++) {
			Position tempPos = new Position(thisRank,i);
			if(isValidMove(tempPos, squares)) {
				Move move = new Move(this.getPosition(), tempPos, 
						this, squares[thisRank][tempPos.getFile()].getPiece());
				moveLst.add(move);
			}
		}
		
		for(int i = thisFile-1; i >= 0; i--) {
			Position tempPos = new Position(thisRank,i);
			if(isValidMove(tempPos, squares)) {
				Move move = new Move(this.getPosition(), tempPos, 
						this, squares[thisRank][tempPos.getFile()].getPiece());
				moveLst.add(move);
			}
		}
		
		return moveLst;
	}


	@Override
	public Boolean isValidMove(Position destination, Squares[][] squares) {
		if(destination.isValidPos() && !this.getPosition().equals(destination)) {
			int thisRank = this.getPosition().getRank();
			int thisFile = this.getPosition().getFile();
			int destRank = destination.getRank();
			int destFile = destination.getFile();
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
