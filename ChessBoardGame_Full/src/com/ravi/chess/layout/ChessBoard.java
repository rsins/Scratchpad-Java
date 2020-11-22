/**
 * 
 */
package com.ravi.chess.layout;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ravi.chess.other.IChessGameConstants;
import com.ravi.game.base.IGameMove;
import com.ravi.game.base.IGameRuleHandler;
import com.ravi.game.base.IPiece;
import com.ravi.game.layout.IBoard;
import com.ravi.game.layout.IPosition;

/**
 * Board boxes to be placed in following order.
 * 
 *     A  B  C  D  E  F  G H
 *  1
 *  2
 *  3
 *  4
 *  5
 *  6
 *  7
 *  8
 * 
 * @author Ravi Kant Singh
 *
 */
public class ChessBoard implements IBoard, IChessGameConstants {

	private final Map<String, IPosition> mChessBoardBoxes;
	
	public ChessBoard() {
		mChessBoardBoxes = new HashMap<String, IPosition>(WIDTH * HEIGHT);
		
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				String myPositionName = POSITION_LABELS[j][i];
				mChessBoardBoxes.put(myPositionName, new ChessBoxPosition(myPositionName, i, j));
			}
		}
	}
	
	private IPosition getPositionImpl(String aPositionLabel) {
		return (mChessBoardBoxes.get(aPositionLabel) == null) ? null : mChessBoardBoxes.get(aPositionLabel);
	}
	
	@Override
	public void setPiecePostion(String aPositionLabel, IPiece aPiece) {
		IPosition myPosition = getPositionImpl(aPositionLabel);
		myPosition.setPiece(aPiece);
		aPiece.setPosition(myPosition);
	}

	@Override
	public boolean tryMove(IGameMove aGameMove, IGameRuleHandler aGameRuleHandler) {
		boolean myMoveCompleted = false;
		
		IPosition myFromPosition = getPositionImpl(aGameMove.getFromPosition());
		IPosition myToPosition = getPositionImpl(aGameMove.getToPosition());
		
		// If From or To positions are not valid then move is not possible.
		if ((myFromPosition == null) || (myToPosition == null)) return false;
		
		IPiece myPiece = myFromPosition.getPiece();
		
		// Check with Game Rule Handler if this move is okay - extra validations.
       if(!aGameRuleHandler.isValidMove(this, aGameMove)) return false;
		
		// If there is no piece at From location then nothing to move.
		if (myPiece == null) return false;
		
		if (myPiece.isValidMove(aGameMove, this)) {
			IPiece myToPiece = myToPosition.getPiece();
			if (myToPiece != null) {
				myToPiece.setPosition(null);
				myToPiece.setActive(false);
			}
			
			myFromPosition.setPiece(null);
			myToPosition.setPiece(myPiece);
			myPiece.setPosition(myToPosition);
			myMoveCompleted = true;
		}
		
		return myMoveCompleted;
	}
	
	@Override
	public List<String> getAllValidMovePositions(String aPositionLabel, IPiece aPiece) {
		return aPiece.getAllValidMovePositionsFromGivenPosition(aPositionLabel, this);
	}

	@Override
	public IPiece getPieceAtPosition(String aPositionLabel) {
		return getPositionImpl(aPositionLabel).getPiece();
	}

	@Override
	public boolean isValidPosition(String aPositionLabel) {
		return (getPositionImpl(aPositionLabel) != null);
	}

	@Override
	public boolean isEmptyPosition(String aPositionLabel) {
		return isValidPosition(aPositionLabel) 
				? (getPositionImpl(aPositionLabel).getPiece() == null ? true : false) 
				: false;
	}
	
	@Override
	public IPosition getPosition(String aPositionLabel) {
		return getPositionImpl(aPositionLabel);
	}
	
	@Override
	public Collection<IPosition> getAllPositions() {
		return mChessBoardBoxes.values();
	}

}
