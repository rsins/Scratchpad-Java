/**
 * 
 */
package com.ravi.chess.factories;

import com.ravi.chess.layout.ChessBoard;
import com.ravi.game.factories.IBoardFactory;
import com.ravi.game.layout.IBoard;

/**
 * @author Ravi Kant Singh
 *
 */
public class ChessBoardFactory implements IBoardFactory {

	@Override
	public IBoard getNewBoard() {
		return new ChessBoard();
	}

}
