/**
 * 
 */
package com.ravi.game.base;

/**
 * @author Ravi Kant Singh
 *
 */
public interface IBoardGame {
	public void addPlayer(String aPlayerName, IPlayer.Type aPlayerType, String aPieceSetName);
	
	/**
	 * init() method must be called before startGame() method.
	 * 
	 * This method initializes the game with pieces at their starting positions.
	 */
	public void init();
	
	/**
	 * All initializations have been completed and not game needs to be started.
	 */
	public void startGame();
	
	public String getName();
}
