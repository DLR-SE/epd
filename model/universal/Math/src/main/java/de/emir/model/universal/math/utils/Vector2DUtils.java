package de.emir.model.universal.math.utils;

import de.emir.model.universal.math.Vector2D;

public class Vector2DUtils {

	public static double squaredLength(final Vector2D v, final Vector2D w) {
		double x = v.getX() - w.getX();
		double y = v.getY() - w.getY();
		return x*x + y*y;
	}

}
