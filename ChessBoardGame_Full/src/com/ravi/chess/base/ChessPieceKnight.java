package com.ravi.chess.base;

import java.util.ArrayList;
import java.util.List;

import com.ravi.game.base.IPieceSet;
import com.ravi.game.layout.IBoard;

public class ChessPieceKnight extends AbstractChessPiece {

	private static int AXIS_ONE_STEP = 1;
	private static int AXIS_TWO_STEP = 2;
	
	public ChessPieceKnight(IPieceSet aPieceSet) {
		super (aPieceSet, ChessPieceType.KNIGHT, aPieceSet.getPlayer().getName() + " - " + "Knight");
	}
	
	public ChessPieceKnight(IPieceSet aPieceSet, String aName) {
		super(aPieceSet, ChessPieceType.KNIGHT, aName);
	}

	@Override
	protected List<String> internalGetAllValidMoves(int aStartX, int aStartY, IBoard aBoard) {
		List<String> myValidMoves = new ArrayList<String> ();
		
		// Return null if not a valid position.
		if (aStartX < 0 || aStartX >= WIDTH || aStartY < 0 || aStartY >= HEIGHT) 
			return null;
		
		int toX, toY;
		
		// Permutaion 1
		toX = aStartX - AXIS_ONE_STEP;
		toY = aStartY - AXIS_TWO_STEP;
		if (toX >= 0 && toY >= 0) {
			myValidMoves.add(POSITION_LABELS[toY][toX]);
		}
		
		// Permutaion 2
		toX = aStartX - AXIS_ONE_STEP;
		toY = aStartY + AXIS_TWO_STEP;
		if (toX >= 0 && toY < HEIGHT) {
			myValidMoves.add(POSITION_LABELS[toY][toX]);
		}
		
		// Permutaion 3
		toX = aStartX + AXIS_ONE_STEP;
		toY = aStartY - AXIS_TWO_STEP;
		if (toX < WIDTH && toY >= 0) {
			myValidMoves.add(POSITION_LABELS[toY][toX]);
		}
		
		// Permutaion 4
		toX = aStartX + AXIS_ONE_STEP;
		toY = aStartY + AXIS_TWO_STEP;
		if (toX < WIDTH && toY < HEIGHT) {
			myValidMoves.add(POSITION_LABELS[toY][toX]);
		}
		
		// Permutaion 5
		toX = aStartX - AXIS_TWO_STEP;
		toY = aStartY - AXIS_ONE_STEP;
		if (toX >= 0 && toY >= 0) {
			myValidMoves.add(POSITION_LABELS[toY][toX]);
		}
		
		// Permutaion 6
		toX = aStartX - AXIS_TWO_STEP;
		toY = aStartY + AXIS_ONE_STEP;
		if (toX >= 0 && toY < HEIGHT) {
			myValidMoves.add(POSITION_LABELS[toY][toX]);
		}
		
		// Permutaion 7
		toX = aStartX + AXIS_TWO_STEP;
		toY = aStartY - AXIS_ONE_STEP;
		if (toX < WIDTH && toY >= 0) {
			myValidMoves.add(POSITION_LABELS[toY][toX]);
		}
		
		// Permutaion 8
		toX = aStartX + AXIS_TWO_STEP;
		toY = aStartY + AXIS_ONE_STEP;
		if (toX < WIDTH && toY < HEIGHT) {
			myValidMoves.add(POSITION_LABELS[toY][toX]);
		}
		
		return myValidMoves;
	}

}
