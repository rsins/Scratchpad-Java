/**
 * 
 */
package com.ravi.chess.base;

import java.util.List;

import com.ravi.chess.other.IChessGameConstants;
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
public abstract class AbstractChessPiece implements IPiece, IChessGameConstants {

	private final IPieceSet mPieceSet;
	private final IPieceType mPieceType;
	private final String mName;
	
	private boolean mIsActive = false;
	private IPosition mPosition;
	
	AbstractChessPiece(IPieceSet aPieceSet, IPieceType aPieceType, String aName) {
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
		List<String> myAllValidMoves = getAllValidMovePositionsFromGivenPosition(aGameMove.getFromPosition(),aBoard);
		return myAllValidMoves.contains(aGameMove.getToPosition());
	}
	
	@Override
	public List<String> getAllValidMovePositionsFromGivenPosition(
			String aPositionLabel, IBoard aBoard) {
		return (aBoard.getPosition(aPositionLabel) == null) 
				? null
				: internalGetAllValidMoves(aBoard.getPosition(aPositionLabel).getX(), aBoard.getPosition(aPositionLabel).getY(), aBoard);
	}

	@Override
	public List<String> getAllCurrentValidMovePositions(String aPositionLabel,
			IBoard aBoard) {
		return (aBoard.getPosition(aPositionLabel) == null) 
				? null
				: internalGetAllValidMoves(this.getPosition().getX(), this.getPosition().getY(), aBoard);
	}
	
	abstract protected List<String> internalGetAllValidMoves(int aStartX, int aStartY, IBoard aBoard);
}
