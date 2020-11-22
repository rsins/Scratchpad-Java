/**
 * 
 */
package com.ravi.chess;

import com.ravi.chess.base.ChessGame;
import com.ravi.game.base.IBoardGame;
import com.ravi.game.base.IPlayer;

/**
 * @author Ravi Kant Singh
 *
 */
public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IBoardGame myGame = new ChessGame();
		
		myGame.addPlayer("Auto_Player_1", IPlayer.Type.MACHINE, "WHITE");
		myGame.addPlayer("Auto_Player_2", IPlayer.Type.MACHINE, "BLACK");
		
		myGame.init();
		myGame.startGame();
	}

}
