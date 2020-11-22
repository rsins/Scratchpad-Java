/**
 * 
 */
package com.ravi.chess.factories;

import com.ravi.chess.ui.ChessUI;
import com.ravi.game.factories.IUIFactory;
import com.ravi.game.ui.IGameUI;

/**
 * @author Ravi Kant Singh
 *
 */
public class ChessUIFactory implements IUIFactory {

	@Override
	public IGameUI getUIObject() {
		return new ChessUI();
	}

}
