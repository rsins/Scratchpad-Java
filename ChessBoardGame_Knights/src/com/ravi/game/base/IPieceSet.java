/**
 * 
 */
package com.ravi.game.base;

import java.util.List;

/**
 * @author Ravi Kant Singh
 *
 */
public interface IPieceSet {
	/**
	 * All Pieces in the PieceSet for the player.
	 */
	public List<IPiece> getListOfAllPieces();
	
	/**
	 * Return only active pieces. There can be inactive pieces due to various game rules
	 * putting pieces off board.
	 */
	public List<IPiece> getListOfAllActivePieces();
	
	/**
	 * Return player instance to which this PieceSet is attached to.
	 */
	public IPlayer getPlayer();
	
	/**
	 * PieceSet Name.
	 */
	public String getName();
}
