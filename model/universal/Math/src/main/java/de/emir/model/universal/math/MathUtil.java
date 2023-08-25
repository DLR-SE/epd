package de.emir.model.universal.math;

public class MathUtil {

	 /**
	   * Clamps a <tt>double</tt> value to a given range.
	   * @param x the value to clamp
	   * @param min the minimum value of the range
	   * @param max the maximum value of the range
	   * @return the clamped value
	   */
	  public static double clamp(double x, double min, double max)
	  {
	    if (x < min) return min;
	    if (x > max) return max;
	    return x;
	  }
	  
	  /**
	   * Clamps an <tt>int</tt> value to a given range.
	   * @param x the value to clamp
	   * @param min the minimum value of the range
	   * @param max the maximum value of the range
	   * @return the clamped value
	   */
	  public static int clamp(int x, int min, int max)
	  {
	    if (x < min) return min;
	    if (x > max) return max;
	    return x;
	  }

}
