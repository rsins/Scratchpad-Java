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
	KNIGHT;

	public String toString() {
		switch (this) {
		   case KNIGHT:
			   return "KNIGHT";
		}
		return null;
	}
	
	@Override
	public int getFirstAxisNoOfPossibleSteps() {
		switch (this) {
		   case KNIGHT:
			   return 2;
		}
		return 0;
	}

	@Override
	public int getSecondAxisNoOfPossibleSteps() {
		switch (this) {
		   case KNIGHT:
			   return 1;
		}
		return 0;
	}

}
