/**
 * 
 */
package com.ravi.game.factories;

import com.ravi.game.base.IGameRuleHandler;

/**
 * @author Ravi Kant Singh
 *
 */
public interface IGameRuleFactory {
	/**
	 * Based on game type this method needs to return the GameRuleHandler instance.
	 * 
	 * @return IGameRuleHandler
	 */
	public IGameRuleHandler getGameRuleHandler();
}
