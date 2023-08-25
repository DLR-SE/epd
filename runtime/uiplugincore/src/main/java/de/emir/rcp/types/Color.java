package de.emir.rcp.types;

import java.io.Serializable;

/**
 * This class simply exists to avoid serializing java.awt.Color.
 *
 */
public class Color implements Serializable {
	private static final long serialVersionUID = -674586248207935710L;
	int red;
	int green;
	int blue;
	int alpha = 255;
	
	public Color() {
		
	}
	
	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = 255;
	}

	public Color(int red, int green, int blue, int alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

	public Color(java.awt.Color source) {
		this.red = source.getRed();
		this.green = source.getGreen();
		this.blue = source.getBlue();
		this.alpha = source.getAlpha();
	}
	
	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public java.awt.Color getAWTColor() {
		return new java.awt.Color(this.red, this.green, this.blue, this.alpha);
	}
	
	public void setAWTColor(java.awt.Color source) {
		this.red = source.getRed();
		this.green = source.getGreen();
		this.blue = source.getBlue();
		this.alpha = source.getAlpha();
	}
}
