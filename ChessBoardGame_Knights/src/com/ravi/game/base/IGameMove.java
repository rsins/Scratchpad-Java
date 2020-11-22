package com.ravi.game.base;

/**
 * Each move by a player will be represented by an instance of this class.
 * 
 * @author Ravi Kant Singh
 *
 */
public interface IGameMove {
	public String getFromPosition();
	public String getToPosition();
	
	/**
	 * Player for which this move is stored/tried.
	 */
	public IPlayer getPlayer();
}
