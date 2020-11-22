/**
 * 
 */
package com.ravi.game.ui;

import java.util.List;
import java.util.Map;

import com.ravi.game.base.IGameMove;
import com.ravi.game.base.IPiece;
import com.ravi.game.base.IPlayer;
import com.ravi.game.layout.IBoard;

/**
 * @author Ravi Kant Singh
 *
 */
public interface IGameUI {
	/**
	 * Initialize starting position for a player.
	 * Game UI can either ask user to provide the starting position or automatically 
	 * decide the positions.
	 * 
	 */
	public Map<String, IPiece> getStartingPositionForPlayer(IBoard aBoard, IPlayer aPlayer);
	
	/**
	 * Get user's next move for the game.
	 * If the User type is MACHINE then it does nothing and returns null.
	 */
	public IGameMove getUserInput(IBoard aBoard, IPlayer aPlayer);
	
	/**
	 * Repaint the UI and show the error message if any.
	 */
	public void repaint(IBoard aBoard, List<String> aErrorMessages);
	
	/**
	 * Show the error message to user.
	 * No repaint of the board.
	 */
	public void showMessage(String aMessage);
}
