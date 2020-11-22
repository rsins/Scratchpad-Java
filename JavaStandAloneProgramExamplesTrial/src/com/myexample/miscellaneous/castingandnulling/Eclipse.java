package com.myexample.miscellaneous.castingandnulling;

public class Eclipse implements Drawing {
	private int mRadiusX;
	private int mRadiusY;
	
	@Override
	public void draw() {
		System.out.println("** Eclipse draw method.");
	}

	public int getRadiusX() {
		return mRadiusX;
	}

	public void setRadiusX(int radiusX) {
		this.mRadiusX = radiusX;
	}

	public int getRadiusY() {
		return mRadiusY;
	}

	public void setRadiusY(int radiusY) {
		this.mRadiusY = radiusY;
	}

}
