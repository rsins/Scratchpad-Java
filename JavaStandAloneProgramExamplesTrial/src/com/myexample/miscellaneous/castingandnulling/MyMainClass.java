package com.myexample.miscellaneous.castingandnulling;

import java.util.ArrayList;

public class MyMainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Drawing> myDrawingArrayList = new ArrayList<Drawing> ();
		Eclipse myEclipse = new Eclipse();
		Drawing myDrawing = new Eclipse();
		Circle myCircle = new Circle();
		
		System.out.println("** Trying the implicit casting.");
		tryDrawing(myDrawing);
		tryDrawing(myEclipse);
		tryDrawing(myCircle);
		
		System.out.println();
		System.out.println("** Trying with arraylist.");
		
		/* Before any update. */
		myEclipse.setRadiusX(5);
		myEclipse.setRadiusY(15);
		myDrawingArrayList.add(myEclipse);
		
		System.out.println("** Before any update.");
		System.out.println(myDrawingArrayList);
		System.out.println(myDrawingArrayList.get(0));
		System.out.println(((Eclipse)myDrawingArrayList.get(0)).getRadiusX());
		System.out.println(((Eclipse)myDrawingArrayList.get(0)).getRadiusY());
		
		/* After an update. */
		myEclipse.setRadiusX(25);
		myEclipse.setRadiusY(35);
		
		System.out.println("** After an update.");
		System.out.println(myDrawingArrayList);
		System.out.println(myDrawingArrayList.get(0));
		System.out.println(((Eclipse)myDrawingArrayList.get(0)).getRadiusX());
		System.out.println(((Eclipse)myDrawingArrayList.get(0)).getRadiusY());

		/* After Setting null. */
		myEclipse = null;
		
		System.out.println("** After setting null.");
		System.out.println(myDrawingArrayList);
		System.out.println(myDrawingArrayList.get(0));
		System.out.println(((Eclipse)myDrawingArrayList.get(0)).getRadiusX());
		System.out.println(((Eclipse)myDrawingArrayList.get(0)).getRadiusY());

	}
	
	private static void tryDrawing(Drawing aDrawing) {
		aDrawing.draw();
	}

}
