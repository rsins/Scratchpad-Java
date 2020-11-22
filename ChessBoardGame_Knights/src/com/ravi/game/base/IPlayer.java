/**
 * 
 */
package com.ravi.game.base;

/**
 * @author Ravi Kant Singh
 *
 */
public interface IPlayer {
	enum Type {
		MACHINE,
		HUMAN;
	}
	
	/**
	 * Store the PieceSet information with the player.
	 */
	public void setPieceSet(IPieceSet aPieceSet);
	public IPieceSet getPieceSet();
	
	/**
	 * Name of the player.
	 */
	public String getName();
	
	/**
	 * Returns the type of player (HUMAN, MACHINE)
	 */
	public Type getType();
}
