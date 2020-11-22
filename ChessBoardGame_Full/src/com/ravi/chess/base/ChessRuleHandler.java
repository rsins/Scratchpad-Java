/**
 * 
 */
package com.ravi.chess.base;

import java.util.Collection;
import java.util.Map;

import com.ravi.game.base.IGameMove;
import com.ravi.game.base.IGameRuleHandler;
import com.ravi.game.base.IPiece;
import com.ravi.game.base.IPlayer;
import com.ravi.game.layout.IBoard;
import com.ravi.game.layout.IPosition;

/**
 * @author Ravi Kant Singh
 *
 */
public class ChessRuleHandler implements IGameRuleHandler {

	/*
	 * To enforce any additional rules.
	 *  - Here it enforces that the starting position cannot be same as the destination.
	 * 
	 * (non-Javadoc)
	 * @see com.ravi.game.base.GameRuleHandler#isValidStartingPosition(com.ravi.game.layout.Board, com.ravi.game.base.Player, java.util.Map)
	 */
	@Override
	public boolean isValidStartingPosition(IBoard aBoard, IPlayer aPlayer,
			Map<String, IPiece> aStartingPositions) {
		return true;//throw new UnsupportedOperationException();
	}

	/*
	 * Only extra validations like if To Position is not empty can it be replaced with piece at From position
	 * and remove the piece out of board. Check for number of valid steps is out of scope for this method.
	 * That check will be in the box class.
	 * 
	 * (non-Javadoc)
	 * @see com.ravi.game.base.GameRuleHandler#isValidMove(com.ravi.game.base.Board, com.ravi.game.base.GameMove)
	 */
	@Override
	public boolean isValidMove(IBoard aBoard, IGameMove aGameMove) {
		String myToPositionLabel = aGameMove.getToPosition();
		if (aBoard.getPosition(myToPositionLabel).getPiece() == null) return true;
		if (aBoard.getPosition(myToPositionLabel).getPiece().getPieceSet().getPlayer().equals(aGameMove.getPlayer())) return false;
		return true;
	}
	
	@Override
	public boolean hasPlayerWonTheGame(IBoard aBoard, IPlayer aPlayer) {
		boolean playerHasWon = true;
		Collection<IPosition> myAllPositions = aBoard.getAllPositions();
		for (IPosition myPosition : myAllPositions) {
			if (myPosition.getPiece() != null) {
				if (! myPosition.getPiece().getPieceSet().getPlayer().equals(aPlayer)) {
					playerHasWon = false;
					break;
				}
			}
		}
		return playerHasWon;
	}

	@Override
	public IGameMove computeNextMoveForPlayer(IBoard aBoard, IPlayer aPlayer) {		
		// Not implemented yet.
		throw new UnsupportedOperationException();
	}

	/*
	private IGameMove computeNextMoveForPlayerPieceImpl(IBoard aBoard, IPlayer aPlayer, IPiece aPiece) {
		// Not implemented yet.
		throw new UnsupportedOperationException();
	}
	
	private boolean isPositionAlreadyCoveredByPlayer(IPlayer aPlayer, String aPositionLabel) {
		List<String> myCoveredPositions = mAlreadyCoveredPath.get(aPlayer);
		
		return (myCoveredPositions == null) ? false : myCoveredPositions.contains(aPositionLabel);
	}
	
	private void setPositionCoveredByPlayer(IPlayer aPlayer, String aPositionLabel) {
		List<String> myCoveredPositions = mAlreadyCoveredPath.get(aPlayer);
		
		if (myCoveredPositions == null) {
			myCoveredPositions = new ArrayList<String>();
			mAlreadyCoveredPath.put(aPlayer, myCoveredPositions);
		}
		
		if (!myCoveredPositions.contains(aPositionLabel)) myCoveredPositions.add(aPositionLabel);
	}
	*/
	
	/*
	private GameMove computeBestMoveForPlayerPieceImpl(Board aBoard, Player aPlayer, Piece aPiece) {
		GameMove myGameMove = null;
		
		List<Deque<String>> myAllMoves = new ArrayList<Deque<String>> ();
		Deque<String> myCurrentMoves = new LinkedList<String> ();
		
		recursiveComputeBestMoveForPlayerPieceImpl(aBoard, aPiece, myAllMoves, myCurrentMoves, aPiece.getPosition().getName());
		
		Deque<String> mySmallestPath = null;
		for (Deque<String> myPath : myAllMoves) {
			// Remove the current position
			myPath.removeFirst();
			
			// Check if the first move to place is empty
			if (!aBoard.isEmptyPosition(myPath.getFirst())) continue;
			
			if (mySmallestPath == null) {
				mySmallestPath = myPath;
			}
			else if (myPath.size() < mySmallestPath.size()) {
				mySmallestPath = myPath;
			}
		}
		if (mySmallestPath != null) {
			myGameMove = new ChessGameMove(aPlayer, aPiece, aPiece.getPosition().getName(), mySmallestPath.getFirst());
		}
		
		return myGameMove;
	}
	
	private void recursiveComputeBestMoveForPlayerPieceImpl(Board aBoard, Piece aPiece, List<Deque<String>> aAllMoves, 
															Deque<String> aCurrentMoves, String aPosition) {
		
		// If position is already covered then ignore
		if (aCurrentMoves.contains(aPosition)) return;
		aCurrentMoves.add(aPosition);
		
		List<String> myValidMoves = aBoard.getAllValidMovePositions(aPosition, aPiece);
		
		// Check if reached final destination
		if (myValidMoves.contains(FINAL_POSITION_LABEL)) {
			aCurrentMoves.add(FINAL_POSITION_LABEL);
			Deque<String> myCompletedMoves = new LinkedList<String>(aCurrentMoves);
			aAllMoves.add(myCompletedMoves);
			return;
		}
					
		for (String myToPositionLabel : myValidMoves) {
			recursiveComputeBestMoveForPlayerPieceImpl(aBoard, aPiece, aAllMoves, aCurrentMoves, myToPositionLabel);
		}
		
		aCurrentMoves.removeLast();
	}
	*/
}
