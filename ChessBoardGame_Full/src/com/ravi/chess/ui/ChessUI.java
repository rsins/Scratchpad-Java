/**
 * 
 */
package com.ravi.chess.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ravi.chess.base.ChessPieceType;
import com.ravi.chess.other.IChessGameConstants;
import com.ravi.chess.other.ChessGameMove;
import com.ravi.game.base.IGameMove;
import com.ravi.game.base.IPiece;
import com.ravi.game.base.IPlayer;
import com.ravi.game.layout.IBoard;
import com.ravi.game.layout.IPosition;
import com.ravi.game.ui.IGameUI;

/**
 * @author Ravi Kant Singh
 *
 */
public class ChessUI implements IGameUI, IChessGameConstants {

	private static PrintStream sOut = System.out;
	private static InputStream sIn = System.in;
	
	private Scanner myScanner = new Scanner(sIn);
	
	public ChessUI() {

	}
	
	@Override
	public Map<String, IPiece> getStartingPositionForPlayer(IBoard aBoard, IPlayer aPlayer) {
		Map<String, IPiece> myStartingPosition = new HashMap<String, IPiece> ();
		
		for (IPiece myPiece : aPlayer.getPieceSet().getListOfAllPieces()) {
			repaint(aBoard, null);
	
			String myPosition = null;
			do {
				sOut.print(aPlayer.getName() + " # Provide your starting position on board for piece : " + myPiece.getName() + " = ");
				myPosition = myScanner.nextLine().toUpperCase(); 
				myStartingPosition.put(myPosition, myPiece);
			} while (!aBoard.isEmptyPosition(myPosition));
		}
		
		return myStartingPosition;
	}
	
	/*
	 * If return is null then needs to exit game.
	 * 
	 * (non-Javadoc)
	 * @see com.ravi.game.base.GameUI#getUserInput(com.ravi.game.base.Player)
	 */
	@Override
	public IGameMove getUserInput(IBoard aBoard, IPlayer aPlayer) {
		IPiece myPiece = aPlayer.getPieceSet().getListOfAllPieces().get(0);
		IGameMove myGameMove = null;
		
		switch (aPlayer.getType()) {
		case HUMAN:
			repaint(aBoard, null);
			
			sOut.println(" Current turn for player : " + aPlayer.getName());
			sOut.println("      Move piece ( " + myPiece.getName() + " ) from " + myPiece.getPosition().getName() + " to : ");
			
			String myNewPosition = myScanner.nextLine().toUpperCase();
			myGameMove = new ChessGameMove(aPlayer, myPiece, myPiece.getPosition().getName(), myNewPosition);
			break;
		default:
			break;
		}
		
		return myGameMove;
	}

	@Override
	public void repaint(IBoard aBoard, List<String> aErrorMessages) {
		String[][] mBoardPrintArray = new String[HEIGHT + 1][WIDTH + 1];
		
		if (aBoard == null) {
			sOut.println("**** Nothing to print. ****");
			return;
		}
		
		// Fill up the first row (Header row)
		mBoardPrintArray[0][0] = BLANK;
		for (int j = 1; j <= WIDTH; j++) {
			mBoardPrintArray[0][j] = H_LABEL_ARRAY[j-1];
		}
				
		// Fill up the rest of the rows
		for (int i = 1; i <= HEIGHT; i++) {
			Arrays.fill(mBoardPrintArray[i], BLANK);
			// Header column
			mBoardPrintArray[i][0] = V_LABEL_ARRAY[i-1];
		}
		
		for (IPosition myPos : aBoard.getAllPositions()) {
			IPiece myPiece = myPos.getPiece();
			if (myPiece != null) {
				mBoardPrintArray[1 + myPiece.getPosition().getY()][1 + myPiece.getPosition().getX()] 
						= getPieceNotation(myPiece);
			}
		}

		printBoardPrintArray(mBoardPrintArray);
		
		if (aErrorMessages != null) {
			for (String errMessage : aErrorMessages) {
				sOut.println("* " + errMessage);
			}
		}
		
	}

	/**
	* Here customization is done based on the player which this piece belongs to.
	* 
	*/
	private String getPieceNotation(IPiece aPiece) {
		return new String(new char[] {' ', getPieceTypeChar(aPiece), ' '});
	}
	
	private char getPieceTypeChar(IPiece aPiece) {
		if (aPiece == null) return ' ';
		
		switch ((ChessPieceType) aPiece.getPieceType()) {
		   case KING:
			   return 'K';
		   case QUEEN:
			   return 'Q';
		   case ROOK:
			   return 'R';
		   case BISHOP:
			   return 'B';
		   case KNIGHT:
			   return 'k';
		   case PAWN_TOP:
		   case PAWN_BOTTOM:
			   return 'P';
		default:
			return ' ';
		}
	}
	
	/**
	 * Simply prints the array which has stored the board information.
	 * 
	 * @param aBoardPrintArray
	 */
	private void printBoardPrintArray(String[][] aBoardPrintArray) {
		StringBuilder myStringBuilder = new StringBuilder();
		
		sOut.println();
		for (int j = 0; j <= HEIGHT; j++) {
			myStringBuilder.setLength(0);
			
			for (int i =0; i <= WIDTH; i++) {
				myStringBuilder.append(aBoardPrintArray[j][i]);
			}
			
			sOut.println(myStringBuilder.toString());
		}
	}

	@Override
	public void showMessage(String aMessage) {
		sOut.println(aMessage);
	}
}
