/**
 * 
 */
package com.ravi.chess.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ravi.chess.factories.ChessBoardFactory;
import com.ravi.chess.factories.ChessPieceSetFactory;
import com.ravi.chess.factories.ChessRuleFactory;
import com.ravi.chess.factories.ChessUIFactory;
import com.ravi.game.base.IBoardGame;
import com.ravi.game.base.IGameMove;
import com.ravi.game.base.IGameRuleHandler;
import com.ravi.game.base.IPiece;
import com.ravi.game.base.IPieceSet;
import com.ravi.game.base.IPlayer;
import com.ravi.game.base.IPlayer.Type;
import com.ravi.game.layout.IBoard;
import com.ravi.game.ui.IGameUI;

/**
 * @author Ravi Kant Singh
 *
 */
public class ChessGame implements IBoardGame {

	private static String NAME = "Chess";
	
	private IGameUI mChessUI = null;
	private IBoard mChessBoard = null;
	private List<IPlayer> mPlayers = null;
	private IGameRuleHandler mChessRuleHandler = null;
	
	public ChessGame() {
		mChessUI = new ChessUIFactory().getUIObject();
		mChessBoard = new ChessBoardFactory().getNewBoard();
		mChessRuleHandler = new ChessRuleFactory().getGameRuleHandler();
		mPlayers = new ArrayList<IPlayer>();
	}
	
	@Override
	public void addPlayer(String aPlayerName, Type aPlayerType, String aPieceSetName) {
		IPlayer myPlayer = new ChessPlayer(aPlayerName, aPlayerType);
		IPieceSet myPieceSet = new ChessPieceSetFactory().getNewPieceSet(myPlayer, aPieceSetName);
		myPlayer.setPieceSet(myPieceSet);
		mPlayers.add(myPlayer);
	}
	
	@Override
	public void init() {
		// Get starting position of pieces for each player.
		for (IPlayer myPlayer : mPlayers) {
			Map<String, IPiece> myStartingPosition = null;
			do {
				myStartingPosition = mChessUI.getStartingPositionForPlayer(mChessBoard, myPlayer);
				
				if (mChessRuleHandler.isValidStartingPosition(mChessBoard, myPlayer, myStartingPosition)) {
					break;
				}
				else {
					mChessUI.showMessage(" Invalid starting positions provided for player : " + myPlayer.getName());
				}
			} while (true);
			
			for (Entry<String, IPiece> piecePosition : myStartingPosition.entrySet()) {
				mChessBoard.setPiecePostion(piecePosition.getKey(), piecePosition.getValue());
			}
		}

	}	

	@Override
	public void startGame() {
		boolean myContinueGameFlag = (mPlayers.size() > 0);
		IGameMove myGameMove = null;
		
		mChessUI.repaint(mChessBoard, null);
		mChessUI.showMessage("");
		mChessUI.showMessage(" **** And the Game '" + getName() + "' starts ****");
		
		while (myContinueGameFlag) {
			OutsidePlayers:
			for (IPlayer myPlayer : mPlayers) {
				boolean myMoveSucecssFlag = false;
				
				while (!myMoveSucecssFlag) {
					switch (myPlayer.getType()) {
					case MACHINE:
						//mChessUI.repaint(mChessBoard, null);
						myGameMove = mChessRuleHandler.computeNextMoveForPlayer(mChessBoard, myPlayer);
						break;
					case HUMAN:
						myGameMove = mChessUI.getUserInput(mChessBoard, myPlayer);
						break;
					default:
						myGameMove = null;
						break;
					}
				
					// If user wants to exit the game move returned will be null.
					if (myGameMove == null) { 
						myContinueGameFlag = false;
						break OutsidePlayers;
					}
				
					myMoveSucecssFlag = mChessBoard.tryMove(myGameMove, mChessRuleHandler);
					
					if (myMoveSucecssFlag) {
						mChessUI.showMessage("");
						mChessUI.showMessage(myGameMove.toString());
					}
					else {
						mChessUI.showMessage("Invalid Move - " + myGameMove.toString());
					}
				}
				
				if (mChessRuleHandler.hasPlayerWonTheGame(mChessBoard, myPlayer)) {
					mChessUI.showMessage("");
					mChessUI.showMessage(" #### PLAYER '" + myPlayer.getName() + "' HAS WON ####");
					mChessUI.repaint(mChessBoard, null);
					myContinueGameFlag = false;
					break OutsidePlayers;
				}
			}
			
		}
		
		mChessUI.showMessage("");
		mChessUI.showMessage(" **** And the Game '" + getName() + "' ends ****");
		
	}	
	
	@Override
	public String getName() {
		return NAME;
	}

}
