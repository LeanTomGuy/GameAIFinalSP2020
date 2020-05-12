package game;

import pieces.Piece;
import player.PlayerType;

/**
 * 
 * @author gnik
 * 
 * 
 * 
 */

public class Move {

	private Position initPosition;

	private Position finalPosition;

	private Piece piece;

	private Piece capturedPiece;

	private Boolean capture;

	private Boolean castle = false;
	
	public Move(Position initPosition, Position finalPosition, Piece piece,  Piece captured) {
		this.initPosition = initPosition;
		this.finalPosition = finalPosition;
		this.piece = piece;
		this.capturedPiece = captured;
		this.capture = !(captured.getPlayer() == PlayerType.NONE);
		this.castle = (piece.toString().equals("K") &&( Math.abs(initPosition.getFile() - finalPosition.getFile()) == 2));
	}

	/*public Boolean isValidMove(Squares[][] squares ) 
	{
		if(!(this.initPosition.isValidPos() && this.finalPosition.isValidPos()))
		{
			return false;
		}
		
		return (this.piece.isValidMove(this.finalPosition, squares));
	}
	*/
	
	public String toString() 
	{
		String out = this.getInitPosition().toString() + " | " + 
				this.getFinalPosition().toString()  + " | "
				+ this.getPiece().toString() + " | "
				+ this.getCapturedPiece().toString();
		return out; 
	}
	
	public Boolean isCastle() 
	{
		return castle;
	}
	public Position getInitPosition() {

		return initPosition;

	}

	public Position getFinalPosition() {

		return finalPosition;

	}

	public Piece getPiece() {

		return piece;

	}

	public boolean isCapture() {

		return capture;

	}

	public Piece getCapturedPiece() {

		return capturedPiece;

	}

	//note: set funcs can be volatile
	public void setInitPosition(Position newPos) {
		this.initPosition = newPos;
	}

	public void setFinalPosition(Position newPos) 
	{
		this.finalPosition = newPos;
	}
	
	public void setCapturePiece(Piece newPiece) 
	{
		this.capturedPiece = newPiece;
	}
	
	public Position getCapturePosition() {

		if (capture) {

			return finalPosition;

		}

		return null;

	}

}