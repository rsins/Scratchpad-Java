/**
 * 
 */
package com.ravi.chess.other;

import com.ravi.game.base.IGameMove;
import com.ravi.game.base.IPiece;
import com.ravi.game.base.IPlayer;

/**
 * @author Ravi Kant Singh
 *
 */
public class ChessGameMove implements IGameMove {
	private final String mFromPosition;
	private final String mToPosition;
	private final IPlayer mPlayer;
	private final IPiece mPiece;
	
	public ChessGameMove(IPlayer aPlayer, IPiece aPiece, String aFromPosition, String aToPosition) {
		mPlayer = aPlayer;
		mPiece = aPiece;
		mFromPosition = aFromPosition;
		mToPosition = aToPosition;
	}
	
	@Override
	public String getFromPosition() {
		return mFromPosition;
	}

	@Override
	public String getToPosition() {
		return mToPosition;
	}

	@Override
	public IPlayer getPlayer() {
		return mPlayer;
	}

	@Override
	public String toString() {
		return mPlayer.getName() + " -> '" + mPiece.getName() + "' : " + mFromPosition + " to " + mToPosition;
	}
}
