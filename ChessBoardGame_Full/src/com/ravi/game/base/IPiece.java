/**
 * 
 */
package com.ravi.game.base;

import java.util.List;

import com.ravi.game.layout.IBoard;
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
	
	
	/**
	 * Try to move a piece from position given by GameMove. Return true if successful. 
	 * Return false if not successful.
	 */
	public boolean isValidMove(IGameMove aGameMove, IBoard aBoard);
	
	/**
	 * Based on piece at GIVEN position return the possible destination positions. 
	 * Will return empty list if no moves possible.
	 * Return null if not a valid position.
	 */
	public List<String> getAllValidMovePositionsFromGivenPosition(String aPositionLabel, IBoard aBoard);
	
	/**
	 * Based on piece at CURRENT position return the possible destination positions. 
	 * Will return empty list if no moves possible.
	 * Return null if not a valid position.
	 */
	public List<String> getAllCurrentValidMovePositions(String aPositionLabel, IBoard aBoard);
}
