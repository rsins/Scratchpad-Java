package com.ravi.chess.base;

import java.util.ArrayList;
import java.util.List;

import com.ravi.game.base.IPieceSet;
import com.ravi.game.layout.IBoard;

public class ChessPieceKing extends AbstractChessPiece {

	public ChessPieceKing(IPieceSet aPieceSet) {
		super (aPieceSet, ChessPieceType.KING, aPieceSet.getPlayer().getName() + " - " + "King");
	}
	
	public ChessPieceKing(IPieceSet aPieceSet, String aName) {
		super(aPieceSet, ChessPieceType.KING, aName);

	}

	@Override
	protected List<String> internalGetAllValidMoves(int aStartX, int aStartY, IBoard aBoard) {
		List<String> myValidMoves = new ArrayList<String> ();
		
		// Return null if not a valid position.
		if (aStartX < 0 || aStartX >= WIDTH || aStartY < 0 || aStartY >= HEIGHT) 
			return null;
		
		int toX, toY;
		
		//..............
		
		return myValidMoves;
	}
}
