package com.myexample.window.calculator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class MyWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final int WINDOW_WIDTH = 405;
	private static final int WINDOW_HEIGHT = 405;
	private static final int DISPLAY_WIDTH = 400;
	//private static final int DISPLAY_HEIGHT = 400;
	
	private static final int BUTTON_WIDTH = 400;
	private static final int BUTTON_HEIGHT = 120;
	
	private static final int TOOLBAR_HEIGHT = 100;
	
	private static final int HORIZONTAL_GAP = 5;
	private static final int VERTICAL_GAP = 5;
	
	private static final int TEXT_HEIGHT = 100;
	
	private enum Operator {
		PLUS, MINUS, ADD, MULTIPLY, DIVIDE, NONE;
	}
	
	private static float FLOAT_ZERO = Float.valueOf(0f);
	private Float mOperand1 = FLOAT_ZERO;
	private Float mOperand2 =FLOAT_ZERO;
	private Float mResult = FLOAT_ZERO;
	private Operator mOperator = Operator.NONE;
	
	JToolBar mToolBar = new JToolBar("Menu ToolBar");
	JMenuBar mMenuBar = new JMenuBar();
	JMenu mMenu = new JMenu("File");
	JMenuItem mMenuFileExit = new JMenuItem("Exit");

	JTextField mCalculatorText = new JTextField();
	
	JButton mButtonBlank1 = new JButton("");
	
	JButton mButton0 = new JButton("0");
	JButton mButton1 = new JButton("1");
	JButton mButton2 = new JButton("2");
	JButton mButton3 = new JButton("3");
	JButton mButton4 = new JButton("4");
	JButton mButton5 = new JButton("5");
	JButton mButton6 = new JButton("6");
	JButton mButton7 = new JButton("7");
	JButton mButton8 = new JButton("8");
	JButton mButton9 = new JButton("9");
	
	JButton mButtonPlus = new JButton("+");
	JButton mButtonMinus = new JButton("-");
	JButton mButtonMultiply = new JButton("X");
	JButton mButtonDivide = new JButton("/");
	JButton mButtonEqual = new JButton("=");
	JButton mBackSpace = new JButton("<-");
	JButton mClear = new JButton("C");
	JButton mDecimal = new JButton(".");
	JButton mSign = new JButton("+/-");
	
	/*
	 * INCOMPLETE.
	 */
	public MyWindow(String aTitle) {
		setTitle(aTitle);
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		//setResizable(false);
		
		Panel p1 = new Panel();
		p1.setLayout(new BorderLayout(HORIZONTAL_GAP, VERTICAL_GAP));
		add(p1);
		
		p1.add(mToolBar, BorderLayout.NORTH);
		mToolBar.setSize(DISPLAY_WIDTH, TOOLBAR_HEIGHT);
		mToolBar.add(mMenuBar);
		mMenuBar.add(mMenu);
		mMenu.add(mMenuFileExit);
		
		p1.add(mCalculatorText, BorderLayout.CENTER);
		mCalculatorText.setEditable(false);
		mCalculatorText.setSize(DISPLAY_WIDTH, TEXT_HEIGHT);
		mCalculatorText.setText("0");
		mCalculatorText.setHorizontalAlignment(JTextField.RIGHT);

		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(5, 5, HORIZONTAL_GAP, VERTICAL_GAP));
		p1.add(p2, BorderLayout.SOUTH);

		p2.add(mButtonEqual);
		mButtonEqual.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		p2.add(mButtonBlank1);
		mButtonBlank1.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mButtonBlank1.setEnabled(false);
		
		p2.add(mBackSpace);
		mBackSpace.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mBackSpace.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((mCalculatorText.getText().length() == 0) || (mCalculatorText.getText().trim() == "0")) {
					mCalculatorText.setText("0");
				}
				else {
					mCalculatorText.setText(mCalculatorText.getText().substring(0, mCalculatorText.getText().length() - 1));
				}
			}
		});
		
		p2.add(mClear);
		mClear.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mCalculatorText.setText("0");
				
			}
		});

		
		p2.add(mButton7);
		mButton7.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mButton7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendNumber('7');
				
			}
		});
		
		p2.add(mButton8);
		mButton8.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mButton8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendNumber('8');
				
			}
		});
		
		p2.add(mButton9);
		mButton9.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mButton9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendNumber('9');
				
			}
		});
		
		p2.add(mButtonDivide);
		mButtonDivide.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		p2.add(mButton4);
		mButton4.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mButton4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendNumber('4');
				
			}
		});

		p2.add(mButton5);
		mButton5.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mButton5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendNumber('5');
				
			}
		});

		p2.add(mButton6);
		mButton6.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mButton6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendNumber('6');
				
			}
		});

		p2.add(mButtonMultiply);
		mButtonMultiply.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		p2.add(mButton1);
		mButton1.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendNumber('1');
				
			}
		});

		p2.add(mButton2);
		mButton2.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendNumber('2');
				
			}
		});

		p2.add(mButton3);
		mButton3.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mButton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendNumber('3');
				
			}
		});

		p2.add(mButtonMinus);
		mButtonMinus.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		p2.add(mButton0);
		mButton0.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mButton0.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendNumber('0');
				
			}
		});

		p2.add(mSign);
		mSign.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		p2.add(mDecimal);
		mDecimal.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mDecimal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appendNumber('.');
				
			}
		});
		
		p2.add(mButtonPlus);
		mButtonPlus.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		mButtonPlus.addActionListener(new ButtonPlusActionListener());
	}
	
	private void appendNumber(char aChar) {
		StringBuilder myStringBuilder = new StringBuilder();
		
		myStringBuilder.append(mCalculatorText.getText().trim());

		switch (myStringBuilder.toString()) {
		case "0":
			if (aChar != '.') myStringBuilder.setLength(0);
			myStringBuilder.append(aChar);
			break;

		default:
			if (aChar == '.') {
				for (int i = 0; i < myStringBuilder.length(); i++) {
					if (myStringBuilder.charAt(i) == aChar) {
						return;
					}
				}
				
				if (myStringBuilder.length() == 0) myStringBuilder.append('0');
				myStringBuilder.append(aChar);
			}
			else {
				myStringBuilder.append(aChar);
			}
			break;
		}
		
		mCalculatorText.setText(myStringBuilder.toString());
	}
	
	private void processOperation() {
		switch (mOperator) {
		case ADD:
			mOperand2 = Float.valueOf(mCalculatorText.getText().trim());
			mResult = mOperand1 + mOperand2;
			mOperator = Operator.NONE;
			break;
			
		case DIVIDE:
			break;
		
		case MINUS:
			break;
		
		case MULTIPLY:
			break;
			
		case PLUS:
			break;
			
		case NONE:
		default:
			break;
		}
	}
	
	private class ButtonEqualActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			process();
		}
		
		public void process() {
			switch (mOperator) {
			case ADD:
				new ButtonPlusActionListener().process();
				break;
				
			case DIVIDE:
				break;
			
			case MINUS:
				break;
			
			case MULTIPLY:
				break;
				
			case PLUS:
				break;
				
			case NONE:
			default:
				break;
			}
		}
		
	}
	
	private class ButtonPlusActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			process();
			
			mOperand1 = Float.valueOf(mCalculatorText.getText().trim());
			mOperand2 = FLOAT_ZERO;
			mOperator = Operator.ADD;
			mCalculatorText.setText("0");
		}
		
		public void process() {
			switch (mOperator) {
			case NONE:
				break;

			default:
				processOperation();
				break;
			}
		}
	}
}
