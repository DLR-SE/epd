/**
 * 
 */
package de.emir.rcp.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

/**
 * This class simply exists to avoid serializing java.awt.Rectangle.
 *
 */
public class Rectangle implements Serializable {
	private static final long serialVersionUID = -7291002360781016449L;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Rectangle() {
		
	}
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(java.awt.Rectangle source) {
		this.x = source.x;
		this.y = source.y;
		this.width = source.width;
		this.height = source.height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
    @JsonIgnore                 
	public java.awt.Rectangle getAWTRectangle() {
		return new java.awt.Rectangle(this.x, this.y, this.width, this.height);
	}

    @JsonIgnore   
	public void setAWTRectangle(java.awt.Rectangle source) {
		this.x = source.x;
		this.y = source.y;
		this.width = source.width;
		this.height = source.height;
	}
}
