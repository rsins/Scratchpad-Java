package com.coursera.week2;

import java.awt.Color;
import java.io.File;

import edu.princeton.cs.algs4.Picture;

public class MyMainClass {

	public static void main(String[] args) {

		// PrintSeams.main(new String[] {System.getProperty("user.dir") + "\\resources\\week2\\seamCarving\\10x10.png"});
		// ShowEnergy.main(new String[] {System.getProperty("user.dir") + "\\resources\\week2\\seamCarving\\HJocean.png"});
		// ShowSeams.main(new String[] {System.getProperty("user.dir") + "\\resources\\week2\\seamCarving\\HJocean.png"});
		// ResizeDemo.main(new String[] {System.getProperty("user.dir") + File.separator + "resources" + File.separator + "week2" + File.separator + "seamCarving" + File.separator + "6x5.png", "1", "0"});
		// ResizeDemo.main(new String[] {System.getProperty("user.dir") + File.separator + "resources" + File.separator + "week2" + File.separator + "seamCarving" + File.separator + "HJocean.png", "100", "100"});
		
		// removeSeamTest();
		
		resizeTest2(System.getProperty("user.dir") + File.separator + "resources"+ File.separator + "week2"+ File.separator + "seamCarving"+ File.separator + "HJocean.png", 200, 200);
	}
	
	private static void removeSeamTest() {
		// Picture pic = new Picture(System.getProperty("user.dir") + "\\resources\\week2\\seamCarving\\6x5.png");
		Picture pic = new Picture(System.getProperty("user.dir") + File.separator + "resources" + File.separator + "week2" + File.separator + "seamCarving" + File.separator + "7x10.png");
		SeamCarver sc = new SeamCarver(pic);
		printRGB(pic);
		int[] seam = sc.findVerticalSeam();
		System.out.print("Vertical Seam : { ");
		for (int i = 0; i < seam.length; i++) System.out.print(seam[i] + "\t");
		System.out.println(" }");
		sc.removeVerticalSeam(seam);
		pic = sc.picture();
		printRGB(pic);
	}
	
	private static void printRGB(Picture pic) {
		for (int i = 0; i < pic.width(); i++) {
			System.out.println();
			for (int j = 0; j < pic.height(); j++) {
				Color col = pic.get(i, j);
				System.out.printf("(%3d, %3d, %3d)\t", col.getRed(), col.getGreen(), col.getBlue());
			}
		}
		System.out.println();
		System.out.println();
	}

	
	private static void resizeTest1(String fileName, int verSeamCountToRemove, int horSeamCountToRemove) {
		Picture pic = new Picture(fileName);
		pic.show();
		
		SeamCarver sc = new SeamCarver(pic);
		MyPicture picture = new MyPicture(pic);
		picture.show();
		
		for (int n = 0; n < verSeamCountToRemove; n++) {
			int[] seam = sc.findVerticalSeam();
			System.out.print("Vertical Seam : { ");
			for (int i = 0; i < seam.length; i++) System.out.print(seam[i] + "\t");
			System.out.println(" }");
			sc.removeVerticalSeam(seam);
			picture.reset(sc.picture());
			picture.show();
		}
		
		for (int n = 0; n < horSeamCountToRemove; n++) {
			int[] seam = sc.findHorizontalSeam();
			System.out.print("Vertical Seam : { ");
			for (int i = 0; i < seam.length; i++) System.out.print(seam[i] + "\t");
			System.out.println(" }");
			sc.removeHorizontalSeam(seam);
			picture.reset(sc.picture());
			picture.show();
		}

	}
	
	private static void resizeTest2(String fileName, int verSeamCountToRemove, int horSeamCountToRemove) {
		Picture pic = new Picture(fileName);
		pic.show();
		
		SeamCarver sc = new SeamCarver(pic);
		MyPicture picture = new MyPicture(pic);
		picture.show();
		
		int count = 0;
		while (true) {
			if (count < verSeamCountToRemove) {
				int[] seam = sc.findVerticalSeam();
				System.out.print("Vertical Seam : { ");
				for (int i = 0; i < seam.length; i++) System.out.print(seam[i] + "\t");
				System.out.println(" }");
				sc.removeVerticalSeam(seam);
				picture.reset(sc.picture());
				picture.show();
			}
			
			if (count < horSeamCountToRemove) {
				int[] seam = sc.findHorizontalSeam();
				System.out.print("Vertical Seam : { ");
				for (int i = 0; i < seam.length; i++) System.out.print(seam[i] + "\t");
				System.out.println(" }");
				sc.removeHorizontalSeam(seam);
				picture.reset(sc.picture());
				picture.show();
			}
			
			if (count >= verSeamCountToRemove && count >= horSeamCountToRemove) break;
			count++;
		}

	}
	
}
