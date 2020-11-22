/**
 * 
 */
package com.ravi.chess.factories;

import com.ravi.chess.base.ChessPieceSet;
import com.ravi.game.base.IPieceSet;
import com.ravi.game.base.IPlayer;
import com.ravi.game.factories.IPieceSetFactory;

/**
 * @author Ravi Kant Singh
 *
 */
public class ChessPieceSetFactory implements IPieceSetFactory {

	@Override
	public IPieceSet getNewPieceSet(IPlayer aPlayer, String aName) {
		return new ChessPieceSet(aPlayer, aName);
	}

}
