package pieces;

import java.util.ArrayList;
import java.util.List;

import game.Move;
import game.Position;
import game.Squares;
import player.PlayerType;

public class Bishop extends Piece {

	public Bishop(PlayerType play, Position pos)
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
		return " "+ret+"B ";
		
	}

	@Override
	public List<Move> getValidMoves(Squares[][] squares) {
		List<Move> moveLst = new ArrayList<Move>();
		
		return moveLst;
	}

	@Override
	public Boolean isValidMove(Position end, Squares[][] squares) {
		if(!this.getPosition().equals(end) && end.isValidPos()) {
			int thisRank = this.getPosition().getRank();
			int thisFile = this.getPosition().getFile();
			int endRank = end.getRank();
			int endFile = end.getFile();
			int rankDiff = Math.abs(thisRank-endRank);
			int fileDiff = Math.abs(thisFile-endFile);
			if(rankDiff == fileDiff) {
				if(thisRank > endRank) {
					if(thisFile > endFile) {
						for(int i = 1; i < rankDiff; i++) {
							if(!(squares[thisRank-i][thisFile-i].isEmpty())) {
								return false;
								}
						}
					} else {
						for(int i = 1; i < rankDiff; i++) {
							if(!(squares[thisRank-i][thisFile+i].isEmpty())) {
								return false;
							}
						}
					}
				} else {
					if(thisFile > endFile) {
						for(int i = 1; i < rankDiff; i++) {
							if(!(squares[thisRank+i][thisFile-i].isEmpty())) {
								return false;
								}
						}
					} else {
						for(int i = 1; i < rankDiff; i++) {
							if(!(squares[thisRank+i][thisFile+i].isEmpty())) {
								return false;
							}
						}
					}
				}
				return true;
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
