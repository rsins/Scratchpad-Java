/**
 * 
 */
package com.ravi.game.base;

/**
 * PieceType for aboard piece will decide how it moves around the board.
 * 
 * @author Ravi Kant Singh
 *
 */
public interface IPieceType {
	public int getFirstAxisNoOfPossibleSteps();
	public int getSecondAxisNoOfPossibleSteps();
}
