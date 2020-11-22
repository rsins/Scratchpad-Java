/**
 * 
 */
package com.ravi.game.base;

import com.ravi.game.layout.IPosition;

/**
 * @author Ravi Kant Singh
 *
 */
public interface IPiece {
	
	/**
	 * Setting this flag would indicate if piece is still on the board
	 * or not.
	 */
	public void setActive(boolean aActiveFlag);
	
	/**
	 * To return if the piece is still on the board.
	 */
	public boolean isActive();
	
	/**
	 * Returns the Piece Type.
	 */
	public IPieceType getPieceType();
	
	/**
	 * Returns the PieceSet this piece is part of.
	 */
	public IPieceSet getPieceSet();
	
	public String getName();
	
	/**
	 * Set the current position on the board. If position is null then
	 * mark the piece inactive.
	 */
	public void setPosition(IPosition aPosition);
	
	/**
	 * Gets the current position on the board. 
	 * If not active then it returns null.
	 */
	public IPosition getPosition();
}
