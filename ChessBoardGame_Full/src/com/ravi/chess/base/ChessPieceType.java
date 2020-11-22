/**
 * 
 */
package com.ravi.chess.base;

import com.ravi.game.base.IPieceType;

/**
 * @author Ravi Kant Singh
 *
 */
public enum ChessPieceType implements IPieceType {
	KING,
	QUEEN,
	ROOK,
	BISHOP,
	KNIGHT,
	PAWN_TOP,
	PAWN_BOTTOM;

	public static int INFINITY_POS = 8;
	public static int INFINITY_NEG = -8;
	
	@Override
	public String toString() {
		switch (this) {
		   case KING:
			   return "KING";
		   case QUEEN:
			   return "QUEEN";
		   case ROOK:
			   return "ROOK";
		   case BISHOP:
			   return "BISHOP";
		   case KNIGHT:
			   return "KNIGHT";
		   case PAWN_TOP:
		   case PAWN_BOTTOM:
			   return "PAWN";
		}
		return null;
	}
	
	/*
	@Override
	public int[][] getPossibleSteps() {
		switch (this) {
		   case KING:
			   return new int[][] {{0,1}, {1,0}, {1,1}, {1,-1}, {0,-1}, {-1,0}, {-1,-1}, {-1,1}};
		   case QUEEN:
			   return new int[][] {{0,INFINITY_NEG}, {INFINITY_POS,0}, {INFINITY_POS,INFINITY_POS}, {INFINITY_POS,INFINITY_NEG}, 
					               {0,INFINITY_NEG}, {INFINITY_NEG,0}, {INFINITY_NEG,INFINITY_NEG}, {INFINITY_NEG,INFINITY_POS}};
		   case ROOK:
			   return new int[][] {{0,INFINITY_POS}, {INFINITY_POS,0}, {0,INFINITY_NEG}, {INFINITY_NEG, 0}};
		   case BISHOP:
			   return new int[][] {{INFINITY_POS, INFINITY_POS}, {INFINITY_POS, INFINITY_NEG}, {INFINITY_NEG, INFINITY_POS}, 
					               {INFINITY_NEG, INFINITY_NEG}};
		   case KNIGHT:
			   return new int[][] {{1,2}, {-1,2}, {1,-2}, {-1,-2}};
		   case PAWN_TOP:
			   return new int[][] {{0,-1}};
		   case PAWN_BOTTOM:
			   return new int[][] {{0,1}};
		}
		return null;
	}
*/
}
