/**
 * 
 */
package com.ravi.chess.base;

import java.util.List;

import com.ravi.game.base.IGameMove;
import com.ravi.game.base.IPiece;
import com.ravi.game.base.IPieceSet;
import com.ravi.game.base.IPieceType;
import com.ravi.game.layout.IBoard;
import com.ravi.game.layout.IPosition;

/**
 * @author Ravi Kant Singh
 *
 */
public class ChessPiece implements IPiece {

	private final IPieceSet mPieceSet;
	private final IPieceType mPieceType;
	private final String mName;
	
	private boolean mIsActive = false;
	private IPosition mPosition;
	
	ChessPiece(IPieceSet aPieceSet, IPieceType aPieceType, String aName) {
		mPieceSet = aPieceSet;
		mPieceType = aPieceType;
		mName = aName;
	}
	
	@Override
	public void setActive(boolean aActiveFlag) {
		mIsActive = aActiveFlag;
	}

	@Override
	public boolean isActive() {
		return mIsActive;
	}

	@Override
	public IPieceType getPieceType() {
		return mPieceType;
	}

	@Override
	public IPieceSet getPieceSet() {
		return mPieceSet;
	}

	@Override
	public String getName() {
		return mName;
	}

	@Override
	public void setPosition(IPosition aPosition) {
		mPosition = aPosition;
		mIsActive = (aPosition == null) ? false : true;
	}

	@Override
	public IPosition getPosition() {
		return mPosition;
	}

	@Override
	public boolean isValidMove(IGameMove aGameMove, IBoard aBoard) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getAllValidMovePositionsFromGivenPosition(
			String aPositionLabel, IBoard aBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllCurrentValidMovePositions(String aPositionLabel,
			IBoard aBoard) {
		// TODO Auto-generated method stub
		return null;
	}

}
