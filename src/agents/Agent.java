package agents;

import game.*;
import player.*;


public abstract class Agent {
	public boolean responded = true;
	public final PlayerType player;
	public abstract Move getMove(Game game, long timeDue);
	
		
	
	
	Agent(PlayerType player){
		this.player = player;
	}
}
