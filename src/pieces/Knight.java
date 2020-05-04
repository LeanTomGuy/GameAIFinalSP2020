package pieces;

import java.util.ArrayList;
import java.util.List;

import game.Move;
import game.Position;
import game.Squares;
import player.PlayerType;

public class Knight extends Piece {

	public Knight(PlayerType play, Position pos)
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
		
		return " "+ret+"Kn ";
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares) {
		List<Move> moveLst = new ArrayList<Move>();
		int thisRank = this.getPosition().getRank();
		int thisFile = this.getPosition().getFile();
		
		for(int i = 1; i < 3; i++) {
			int j = 3-i;
			Position tempPos1 = new Position(thisRank+i,thisFile+j);
			Position tempPos2 = new Position(thisRank+i,thisFile-j);
			Position tempPos3 = new Position(thisRank-i,thisFile+j);
			Position tempPos4 = new Position(thisRank-i,thisFile-j);
			Position[] tempPosArr = {tempPos1,tempPos2,tempPos3,tempPos4};
			
			for(Position pos : tempPosArr) {
				if(isValidMove(pos, squares)) {
					Move move = new Move(this.getPosition(),pos,this,
							squares[pos.getRank()][pos.getFile()].getPiece());
					moveLst.add(move);
				}
			}
			
		}
		
		return moveLst;
	}

	@Override
	public Boolean isValidMove(Position end, Squares[][] squares) {
		if(!this.getPosition().equals(end) && end.isValidPos()) {
			int thisRank = this.getPosition().getRank();
			int thisFile = this.getPosition().getFile();
			int endRank = end.getRank();
			int endFile = end.getFile();
			
			int diffRank = Math.abs(thisRank-endRank);
			int diffFile = Math.abs(thisFile-endFile);
			
			return(squares[endRank][endFile].getPiece().getPlayer() != this.getPlayer() &&
					(diffRank + diffFile) == 3 && diffRank != 0 && diffFile != 0);
			
		}
		return false;
	}

	@Override
	public Position[] getPath(Position end) {
		Position[] arr = {this.getPosition(), end};
		return arr;
	}
}

