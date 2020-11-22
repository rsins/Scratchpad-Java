/**
 * 
 */
package com.ravi.chess.base;

import java.util.ArrayList;
import java.util.List;

import com.ravi.game.base.IPiece;
import com.ravi.game.base.IPieceSet;
import com.ravi.game.base.IPlayer;

/**
 * @author Ravi Kant Singh
 *
 */
public class ChessPieceSet implements IPieceSet {

	private final static int NO_OF_PIECES = 1;
	private final IPlayer mPlayer; 
	private final String mName;
	
	List<IPiece> mPieceList;
	
	public ChessPieceSet(IPlayer aPlayer, String aName) {
		mPlayer = aPlayer;
		mName = aName;

		mPieceList = new ArrayList<IPiece> (NO_OF_PIECES);
		mPieceList.add(new ChessPiece(this, ChessPieceType.KNIGHT, aName + " - Knight"));
	}
	
	@Override
	public List<IPiece> getListOfAllPieces() {
		return mPieceList;
	}

	@Override
	public List<IPiece> getListOfAllActivePieces() {
		ArrayList<IPiece> activePieceList = new ArrayList<IPiece> ();
		for (IPiece myPiece : mPieceList) {
			if (myPiece.isActive()) {
				activePieceList.add(myPiece);
			}
		}
		
		return activePieceList;
	}

	@Override
	public IPlayer getPlayer() {
		return mPlayer;
	}

	@Override
	public String getName() {
		return mName;
	}

}
