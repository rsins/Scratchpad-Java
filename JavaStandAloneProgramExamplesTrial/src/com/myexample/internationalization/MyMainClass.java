package com.myexample.internationalization;

import java.util.Locale;

public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Locale myLocale = new Locale("en", "US");
		//Locale myLocale = new Locale("fr", "FR");
		
		Locale.setDefault(myLocale);
		Window myWindow = new Window(myLocale);
		
		myWindow.init();
		myWindow.setTitle("Application.Name");
		myWindow.setVisible(true);
		myWindow.setLocationRelativeTo(null);
	}
}
