/**
 * 
 */
package com.ravi.chess.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ravi.chess.other.ChessGameMove;
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

	private final static String FINAL_POSITION_LABEL = "H8";
	
	private Map<IPlayer, List<String>> mAlreadyCoveredPath = new HashMap<IPlayer, List<String>> ();
	
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
		for (String myPositionName : aStartingPositions.keySet()) {
			if (myPositionName.equals(FINAL_POSITION_LABEL)) return false;
		}
		return true;
	}

	/*
	 * Only extra validations like if To Position is not empty can it be replaced with piece at From position
	 * and remove the piece out of board.
	 * 
	 * (non-Javadoc)
	 * @see com.ravi.game.base.GameRuleHandler#isValidMove(com.ravi.game.base.Board, com.ravi.game.base.GameMove)
	 */
	@Override
	public boolean isValidMove(IBoard aBoard, IGameMove aGameMove) {
		String myToPositionLabel = aGameMove.getToPosition();
		if (aBoard.getPosition(myToPositionLabel).getPiece() != null) return false;
		return true;
	}
	
	@Override
	public boolean hasPlayerWonTheGame(IBoard aBoard, IPlayer aPlayer) {
		return aPlayer.getPieceSet().getListOfAllActivePieces().get(0).getPosition().getName().equals(FINAL_POSITION_LABEL);
	}

	@Override
	public IGameMove computeNextMoveForPlayer(IBoard aBoard, IPlayer aPlayer) {		
		IPiece myPiece = aPlayer.getPieceSet().getListOfAllActivePieces().get(0);
		return computeNextMoveForPlayerPieceImpl(aBoard, aPlayer, myPiece);
	}

	
	private IGameMove computeNextMoveForPlayerPieceImpl(IBoard aBoard, IPlayer aPlayer, IPiece aPiece) {
		IGameMove myGameMove = null;
		
		List<String> myValidMoves = aBoard.getAllValidMovePositions(aPiece.getPosition().getName(), aPiece);
		Map<Integer, String> myTreeMap = new TreeMap<Integer, String> ();
		
		IPosition myFinalPosition = aBoard.getPosition(FINAL_POSITION_LABEL);
		for (String myToPositionLabel : myValidMoves) {
			IPosition myToPosition = aBoard.getPosition(myToPositionLabel);
			int myDistance = (myFinalPosition.getX() - myToPosition.getX()) 
					         * (myFinalPosition.getY() - myToPosition.getY());
			myDistance = Math.abs(myDistance);
			myTreeMap.put(new Integer(myDistance), myToPositionLabel);
		}

		for(String myComputedPositionLabel : myTreeMap.values()) {
			if (aBoard.isEmptyPosition(myComputedPositionLabel) 
					&& !isPositionAlreadyCoveredByPlayer(aPlayer, myComputedPositionLabel)) {
				myGameMove = new ChessGameMove(aPlayer, aPiece, aPiece.getPosition().getName(), myComputedPositionLabel);
				setPositionCoveredByPlayer(aPlayer, myComputedPositionLabel);
				break;
			}
		}
		
		return myGameMove;
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
