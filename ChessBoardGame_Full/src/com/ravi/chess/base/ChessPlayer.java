/**
 * 
 */
package com.ravi.chess.base;

import com.ravi.game.base.IPieceSet;
import com.ravi.game.base.IPlayer;

/**
 * @author Ravi Kant Singh
 *
 */
public class ChessPlayer implements IPlayer {

	private final String mName;
	private final Type mType;
	
	private IPieceSet mPieceSet = null;
	
	ChessPlayer(String aName, Type aType) {
		mName = aName;
		mType = aType;
	}

	@Override
	public void setPieceSet(IPieceSet aPieceSet) {
		mPieceSet = aPieceSet;
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
	public Type getType() {
		return mType;
	}

	@Override
	public boolean equals(Object aPlayer) {
		if (this == aPlayer) return true;
		if ((aPlayer instanceof ChessPlayer) == false) return false;
		if (((ChessPlayer)aPlayer).getName().equals(this.getName())) return true;
		return false;
	}
}
