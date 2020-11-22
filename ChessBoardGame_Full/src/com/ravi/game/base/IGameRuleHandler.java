/**
 * 
 */
package com.ravi.game.base;

import java.util.Map;

import com.ravi.game.layout.IBoard;

/**
 * All the customization regarding a board game rule to be placed here.
 * 
 * @author Ravi Kant Singh
 *
 */
public interface IGameRuleHandler {
	/**
	 * Game specific starting position validity check.
	 * 
	 * @param aBoard
	 * @param aPlayer
	 * @param aStartingPositions List of starting position and the piece to be placed at that position
	 * @return
	 */
	public boolean isValidStartingPosition(IBoard aBoard, IPlayer aPlayer, Map<String, IPiece> aStartingPositions);
	
	/**
	 * Extra checks for valid moves.
	 * Example: whether the Piece at source position can forcefully remove the 
	 * Piece at destination position.
	 */
	public boolean isValidMove(IBoard aBoard, IGameMove aGameMove);
	
	/**
	 * Check if a particular player has won the game.
	 */
	public boolean hasPlayerWonTheGame(IBoard aBoard, IPlayer aPlayer);
	
	/**
	 * To be called for MACHINE player to compute the next move.
	 */
	public IGameMove computeNextMoveForPlayer(IBoard aBoard, IPlayer aPlayer);
}
