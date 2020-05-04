package game;

import pieces.Piece;

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

	/**
	 * 
	 * This is the piece that was captured.
	 * 
	 */

	private Piece capturedPiece;

	private Boolean capture;

	public Move(Position initPosition, Position finalPosition, Piece piece,  Piece captured) {

		this.initPosition = initPosition;

		this.finalPosition = finalPosition;

		this.piece = piece;

		this.capturedPiece = captured;

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

	/**
	 * 
	 * Returns the Position of the capture.
	 * 
	 * @return The Position where the capture occured.
	 * 
	 */

	public Position getCapturePosition() {

		if (capture) {

			return finalPosition;

		}

		return null;

	}

}