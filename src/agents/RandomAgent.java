package agents;

import player.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import game.Move;
import pieces.*;
import game.*;

public class RandomAgent extends Agent {

	public RandomAgent(PlayerType player){
		super(player);
	}
	
	public Move getMove(Game game, long timeDue) {
		List<Piece> pceLst = game.board.copyPieces(this.player);
		List<Move> mveLst = new ArrayList<Move>();
		for(Piece pce : pceLst ) 
		{
			try {
//				System.out.println("piece: "+ pce.toString());
//				List<Move> lst = pce.getValidMoves(game.board.squares, game);
//				System.out.println("size: "+ lst.size());
				mveLst.addAll(pce.getValidMoves(game.board.copySquares(), game.clone()));
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		
		Random rand = new Random();
		for(int i = 0; i < 10; i++) 
		{
			rand.nextInt(mveLst.size());
		}
		
		/*for(Move move : mveLst) 
		{
			System.out.println("move: "+ move.toString());
		}
		*/
		int chosen = rand.nextInt(mveLst.size());
		
		Move move = mveLst.get(chosen);
//		System.out.println("pceLst size " + pceLst.size());
		System.out.println("selected: "+ move);
		return move;
	}
}
