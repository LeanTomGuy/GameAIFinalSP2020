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

	public Move(Position initPosition, Position finalPosition, Piece piece,  Piece captured) {
		this.initPosition = initPosition;
		this.finalPosition = finalPosition;
		this.piece = piece;
		this.capturedPiece = captured;
		this.capture = !(captured.getPlayer() == PlayerType.NONE);
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

	public Position getCapturePosition() {

		if (capture) {

			return finalPosition;

		}

		return null;

	}

}