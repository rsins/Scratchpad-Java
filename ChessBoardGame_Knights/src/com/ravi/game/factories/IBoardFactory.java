/**
 * 
 */
package com.ravi.game.factories;

import com.ravi.game.layout.IBoard;

/**
 * @author Ravi Kant Singh
 *
 */
public interface IBoardFactory {
	/**
	 * Based on game and board type return the Board instance.
	 * 
	 * @return
	 */
	public IBoard getNewBoard();
}
