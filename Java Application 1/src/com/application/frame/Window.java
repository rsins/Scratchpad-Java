package com.application.frame;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Window extends JFrame implements ActionListener{

	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = -5088290544211739819L;
	
	private Container myWindowContainer;
	/* 
	 * Menu items.
	 */
	private JMenuBar mJMenuBar;
	private JMenu mJMenuFile;
	private JMenu mJMenuHelp;
	private JMenuItem mJMenuItemFileExit;
	private JMenuItem mJMenuItemHelpAbout;
	/* 
	 * Other Items.
	 */
	private JLabel mFirstJLabel;
	private JTextArea mFirstJTextArea;
	private Canvas mCanvas;
	/* 
	 * Help Window.
	 */
	JDialog myHelpDialogWindow = null;
	
	public Window() throws HeadlessException {
		super();
	}

	public Window(GraphicsConfiguration aGraphicsConfiguration) {
		super(aGraphicsConfiguration);
	}

	public Window(String aTitle, GraphicsConfiguration aGraphicsConfiguration) {
		super(aTitle, aGraphicsConfiguration);
	}

	public Window(String aTitle) throws HeadlessException {
		super(aTitle);
	}
	
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		myWindowContainer = getContentPane();
		// Adding menu bar and menu items.
		mJMenuBar = new JMenuBar();
		myWindowContainer.add(mJMenuBar,BorderLayout.NORTH);
		
		/* File Menu. */
		mJMenuFile = new JMenu("File");
		mJMenuBar.add(mJMenuFile);
		mJMenuItemFileExit = new JMenuItem("Exit");
		mJMenuItemFileExit.addActionListener(this);
		mJMenuFile.add(mJMenuItemFileExit);
		
		/* Help Menu. */
		mJMenuHelp = new JMenu("Help");
		mJMenuBar.add(mJMenuHelp);
		mJMenuItemHelpAbout = new JMenuItem("About");
		mJMenuItemHelpAbout.addActionListener(this);
		mJMenuHelp.add(mJMenuItemHelpAbout);
		
		/* Other Fields. */
		mFirstJLabel = new JLabel();
		mFirstJLabel.setText("Sample Label:");
		mFirstJLabel.setSize(100, 50);
		myWindowContainer.add(mFirstJLabel,BorderLayout.WEST);
		mFirstJTextArea = new JTextArea();
		mFirstJTextArea.setSize(100, 50);
		mFirstJTextArea.setText("Timepass.");
		mFirstJTextArea.setToolTipText("Sample text field for my sample java application.");
		myWindowContainer.add(mFirstJTextArea,BorderLayout.EAST);
		
		/* Canvas. */
		mCanvas = new Canvas();
		mCanvas.setSize(200, 200);
		mCanvas.setBackground(new Color(250, 200, 150));
		mCanvas.setForeground(new Color(100, 150, 200));
		myWindowContainer.add(mCanvas,BorderLayout.SOUTH);
		
		/* Resize the window. */
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent aActionEvent) {
		Object myActionEventObject = aActionEvent.getSource();
		
		if (myActionEventObject == mJMenuItemFileExit) {
			actionMenuFileExitPressed();
		}
		else if (myActionEventObject == mJMenuItemHelpAbout) {
			actionMenuHelpAboutPressed();
		}
	}
	
	private void actionMenuFileExitPressed() {
		System.exit(0);
	}
	
	private void actionMenuHelpAboutPressed() {
		if (myHelpDialogWindow == null) {
			myHelpDialogWindow = new JDialog(this,true);
			myHelpDialogWindow.setTitle("About " + getTitle());
			myHelpDialogWindow.add(new JLabel("Sample Java Application for test."),BorderLayout.CENTER);
			myHelpDialogWindow.pack();
			myHelpDialogWindow.setSize(getWidth(), 100);
			myHelpDialogWindow.setDefaultCloseOperation(HIDE_ON_CLOSE);
			myHelpDialogWindow.setLocationRelativeTo(null);
			myHelpDialogWindow.setResizable(false);
		}
	
		myHelpDialogWindow.setVisible(true);
	}
}
