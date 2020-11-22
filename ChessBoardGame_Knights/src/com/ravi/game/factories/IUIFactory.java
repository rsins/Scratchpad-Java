/**
 * 
 */
package com.ravi.game.factories;

import com.ravi.game.ui.IGameUI;

/**
 * @author Ravi Kant Singh
 *
 */
public interface IUIFactory {
	/**
	 * Runtime the UIObject can be dynamically changed for the Board Game.
	 * 
	 */
	public IGameUI getUIObject();
}
