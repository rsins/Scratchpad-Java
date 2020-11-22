/**
 * 
 */
package com.ravi.game.layout;

import com.ravi.game.base.IPiece;

/**
 * A fixed position on the board which can be empty or can store a single piece from game.
 * 
 * @author Ravi Kant Singh
 *
 */
public interface IPosition {
	
	/**
	 * Horizontal position on the board.
	 */
	public int getX();
	
	/**
	 * Vertical position on the board.
	 */
	public int getY();
	
	/**
	 * Position name viz. A1, A2 etc.
	 */
	public String getName();
	
	/**
	 * Allows to place a piece to be placed at this position on the board.
	 * If piece is null means the position is empty. 
	 */
	public void setPiece(IPiece aPiece);
	
	/**
	 * Get the piece at a particular position on the board.
	 * Returns null if the position is empty. 
	 */
	public IPiece getPiece();
}
