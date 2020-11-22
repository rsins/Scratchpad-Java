/**
 * 
 */
package com.ravi.chess.factories;

import com.ravi.chess.base.ChessRuleHandler;
import com.ravi.game.base.IGameRuleHandler;
import com.ravi.game.factories.IGameRuleFactory;

/**
 * @author Ravi Kant Singh
 *
 */
public class ChessRuleFactory implements IGameRuleFactory {

	@Override
	public IGameRuleHandler getGameRuleHandler() {
		return new ChessRuleHandler();
	}

}
