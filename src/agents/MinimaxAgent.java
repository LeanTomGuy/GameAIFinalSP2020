package agents;


import game.*;
import javafx.util.Pair;
import pieces.*;
import player.PlayerType;

public class MinimaxAgent extends Agent {

	private static int maxDepth = 2;

	private PlayerType agent;
	public MinimaxAgent(PlayerType player) {
		super(player);
		agent = player;
	}

	public Move getMove(Game game, long timeDue) {
		System.out.println("AI calculating move...");
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		Move outMove = miniMax(game.whoseTurn, game, 0, alpha, beta).getValue();
		System.out.println("done, move was " +outMove.toString());
		return outMove;
	}


	public  Pair<Integer, Move> miniMax(PlayerType player, Game game, int currentDepth, int alpha, int beta) {
		// end algorithm if we've looked far enough
		//System.out.println("running with player " + player);
		Move tmpMove = new Move(new Position(-1, -1), new Position(-1, -1), 
				new EmptyPiece(PlayerType.NONE, new Position(-1, -1)), new EmptyPiece(PlayerType.NONE, new Position(-1, -1)));
		
		try {
			if (currentDepth > maxDepth) {
				if (game.isDraw())
					return new Pair<>(0, tmpMove);
				else if (game.isCheckmate(PlayerType.W)) {
					return  new Pair<>(-1000, tmpMove);
				} else if (game.isCheckmate(PlayerType.B))
					return  new Pair<>(1000, tmpMove);
				else
					return new Pair<>(game.getScore(PlayerType.W) - game.getScore(PlayerType.B), tmpMove);
			} else
			{
				if(player == agent) {
					return getMax(player, game, currentDepth, alpha, beta);
				}
				else
					return getMin(player, game, currentDepth, alpha, beta);
			}

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return new Pair<>(0, tmpMove); // should not reach

	}

	
	public Pair<Integer, Move> getMax(PlayerType player, Game game, int currentDepth, int alpha, int beta) {
		int bestScore = Integer.MIN_VALUE, newAlpha = alpha;
		Move bestMove = new Move(new Position(-1, -1), new Position(-1, -1), 
				new EmptyPiece(PlayerType.NONE, new Position(-1, -1)), new EmptyPiece(PlayerType.NONE, new Position(-1, -1)));
		
		for (Piece pce : game.retPceLst(player)) {
			for (Move mv : pce.getValidMoves(game.board.squares, game)) {
				Game modified;

				modified = new Game(game);

				modified.advanceGameSim(mv);

				int score =  miniMax(modified.whoseTurn, modified, currentDepth + 1, alpha, beta).getKey();
				newAlpha = Math.max(newAlpha,  score);
				if(newAlpha >= beta)
				{	bestScore = newAlpha;
					bestMove = mv;
					return new Pair<>(bestScore, bestMove);
				}
				else if(score >= bestScore) 
				{
					bestScore = score;
					bestMove = mv;
				}
			}
		}
		
		bestScore = newAlpha;
		return new Pair<>(bestScore, bestMove);

	}

	public Pair<Integer, Move> getMin(PlayerType player, Game game, int currentDepth, int alpha, int beta) {
		int bestScore = Integer.MAX_VALUE, newBeta = beta;
		Move bestMove = new Move(new Position(-1, -1), new Position(-1, -1), 
				new EmptyPiece(PlayerType.NONE, new Position(-1, -1)), new EmptyPiece(PlayerType.NONE, new Position(-1, -1)));
		
		for (Piece pce : game.retPceLst(player)) {
			for (Move mv : pce.getValidMoves(game.board.squares, game)) {
				Game modified;

				modified = new Game(game);

				modified.advanceGameSim(mv);

				int score = miniMax(modified.whoseTurn, modified, currentDepth + 1, alpha, beta).getKey();
				newBeta = Math.min(beta, score);
				if (alpha >= newBeta) {
					bestScore = newBeta;
					bestMove = mv;
					return new Pair<>(bestScore, bestMove);
				}
				else if (score <= bestScore) 
				{
					bestScore = score;
					bestMove = mv;
				}
			}
		}

		bestScore = newBeta;
		return new Pair<>(bestScore, bestMove);

	}


}
