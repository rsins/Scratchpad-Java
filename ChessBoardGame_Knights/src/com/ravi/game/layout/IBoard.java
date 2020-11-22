/**
 * 
 */
package com.ravi.game.layout;

import java.util.Collection;
import java.util.List;

import com.ravi.game.base.IGameMove;
import com.ravi.game.base.IGameRuleHandler;
import com.ravi.game.base.IPiece;

/**
 * Board class for the game.
 * Position on the board is marked by position name.
 * 
 * @author Ravi Kant Singh
 *
 */
public interface IBoard {
	
	/**
	 * Required to initialize the board with a piece.
	 * Needs to clear the position if piece is null.
	 */
	public void setPiecePostion(String aPositionLabel, IPiece aPiece);
	
	/**
	 * Try to move a piece from position given by GameMove. Return true if successful. 
	 * Return false if not successful.
	 */
	public boolean tryMove(IGameMove aGameMove, IGameRuleHandler aGameRuleHandler);
	
	/**
	 * Based on piece at position return the possible destination positions. 
	 * Will return empty list if no moves possible.
	 * Return null if not a valid position.
	 */
	public List<String> getAllValidMovePositions(String aPositionLabel, IPiece aPiece);
	
	/**
	 * Get the piece at a particular position.
	 */
	public IPiece getPieceAtPosition(String aPositionLabel);
	
	/**
	 * Check if the position label provided is valid.
	 * This is to move all position related checks inside Board.
	 */
	public boolean isValidPosition(String aPositionLabel);
	
	/**
	 * Check if the position is empty and has no piece.
	 */
	public boolean isEmptyPosition(String aPositionLabel);
	
	/**
	 * Get the position object given by a label. 
	 */
	public IPosition getPosition(String aPositionLabel);
	
	/**
	 * Get all the position objects on the board. 
	 * Required by the UI.
	 */
	public Collection<IPosition> getAllPositions();

}
