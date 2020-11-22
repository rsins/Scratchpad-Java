/**
 * 
 */
package com.ravi.game.factories;

import com.ravi.game.base.IPieceSet;
import com.ravi.game.base.IPlayer;

/**
 * @author Ravi Kant singh
 *
 */
public interface IPieceSetFactory {
	/**
	 * Return new PieceSet instance for player referenced from input parameter.
	 * 
	 * @param aPlayer instance of player
	 * @param aName PieceSet instance name
	 * @return PieceSet instance
	 */
	public IPieceSet getNewPieceSet(IPlayer aPlayer, String aName);
}
