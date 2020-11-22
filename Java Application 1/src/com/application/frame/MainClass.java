package com.application.frame;

public class MainClass {

	public static void main(String args []) {
		Window myWindow = new Window();
		
		myWindow.init();
		myWindow.setTitle("Ravi's Application");
		myWindow.setVisible(true);
		myWindow.setLocationRelativeTo(null);
	}
}
