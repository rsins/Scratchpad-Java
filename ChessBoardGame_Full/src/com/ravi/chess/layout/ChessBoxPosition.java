/**
 * 
 */
package com.ravi.chess.layout;

import com.ravi.game.base.IPiece;
import com.ravi.game.layout.IPosition;

/**
 * @author Ravi Kant Singh
 *
 */
public class ChessBoxPosition implements IPosition {

	private final int mX;
	private final int mY;
	private final String mName;
	
	private IPiece mPiece = null;
	
	public ChessBoxPosition(String aName, int aX, int aY) {
		mName = aName;
		mX = aX;
		mY = aY;
	}
	
	@Override
	public int getX() {
		return mX;
	}

	@Override
	public int getY() {
		return mY;
	}

	@Override
	public String getName() {
		return mName;
	}

	@Override
	public void setPiece(IPiece aPiece) {
		mPiece = aPiece;
	}
	
	@Override
	public IPiece getPiece() {
		return mPiece;
	}

}
