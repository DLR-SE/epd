package de.emir.model.universal.spatial;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.math.utils.Vector2DUtils;
import de.emir.model.universal.spatial.impl.CoordinateImpl;

public class SpatialUtils {

	public static class LineSegment {
		Coordinate		start;
		Coordinate		end;
		
		public LineSegment(final Coordinate start, final Coordinate end){
			this.start = start;
			this.end = end;
		}

		public void setCRS(CoordinateReferenceSystem crs) {
			start = start.get(crs);
			end = end.get(crs);
		}
	}
	
	
	
	public static Coordinate getNearestPointOnRay(final LineSegment segment, final Coordinate coordinate) {
		//use one of the existing coordinates, if possible
		Engineering2D crs = (coordinate.getCrs() != null && coordinate.getCrs() instanceof Engineering2D) ? (Engineering2D)coordinate.getCrs() : null;
		if (crs == null)
			crs = (segment.start.getCrs() != null && segment.start.getCrs() instanceof Engineering2D) ? (Engineering2D)segment.start.getCrs() : null;
		if (crs == null)
			crs = (segment.end.getCrs() != null && segment.end.getCrs() instanceof Engineering2D) ? (Engineering2D)segment.end.getCrs() : null;
		if (crs == null) { //create one, at the position of the coordinate
			crs = new Engineering2DImpl(coordinate.get(CRSUtils.WGS84_2D).toVector());
		}
		return getNearestPointOnRay(segment, coordinate, crs);
	}
	
	private static Coordinate getNearestPointOnRay(LineSegment segment, Coordinate coordinate, Engineering2D crs) {
		Vector2D v = (Vector2D) segment.start.get(crs).toVector();
		Vector2D w = (Vector2D) segment.end.get(crs).toVector();
		Vector2D p = (Vector2D) coordinate.get(crs).toVector();
		return new CoordinateImpl(getNearestPointOnRay(v, w, p), crs);
	}

	public static Vector2D getNearestPointOnRay(Vector2D v, Vector2D w, Vector2D p) {
		double l2 = Vector2DUtils.squaredLength(v, w);
		if (l2 <= 0.00000001)
			return p;
		// Consider the line extending the segment, parameterized as v + t (w - v).
		// We find projection of point p onto the line. 
		// It falls where t = [(p-v) . (w-v)] / |w-v|^2
		Vector2D pv = p.sub(v);
		Vector2D wv = w.sub(v);
		double dot = pv.dot(wv);
		double t = dot / Math.sqrt(l2); 
		Vector2D projection = new Vector2DImpl(v);
		wv.normalizeLocal();
		wv.multLocal(t);
		projection.addLocal(wv);
		return projection;
	}
	

	
	public static Coordinate getNearestPointOnLine(final LineSegment segment, final Coordinate coordinate) {
		//use one of the existing coordinates, if possible
		Engineering2D crs = (coordinate.getCrs() != null && coordinate.getCrs() instanceof Engineering2D) ? (Engineering2D)coordinate.getCrs() : null;
		if (crs == null)
			crs = (segment.start.getCrs() != null && segment.start.getCrs() instanceof Engineering2D) ? (Engineering2D)segment.start.getCrs() : null;
		if (crs == null)
			crs = (segment.end.getCrs() != null && segment.end.getCrs() instanceof Engineering2D) ? (Engineering2D)segment.end.getCrs() : null;
		if (crs == null) { //create one, at the position of the coordinate
			crs = new Engineering2DImpl(coordinate.get(CRSUtils.WGS84_2D).toVector());
		}
		return getNearestPointOnLine(segment, coordinate, crs);
	}

	


	
	private static Coordinate getNearestPointOnLine(LineSegment segment, Coordinate coordinate, Engineering2D crs) {
		Vector2D v = (Vector2D) segment.start.get(crs).toVector();
		Vector2D w = (Vector2D) segment.end.get(crs).toVector();
		Vector2D p = (Vector2D) coordinate.get(crs).toVector();
		return new CoordinateImpl(getNearestPointOnLine(v, w, p), crs);
	}

	public static Vector2D getNearestPointOnLine(Vector2D v, Vector2D w, Vector2D p) {
		double l2 = Vector2DUtils.squaredLength(v, w);
		if (l2 <= 0.00000001)
			return p;
		// Consider the line extending the segment, parameterized as v + t (w - v).
		// We find projection of point p onto the line. 
		// It falls where t = [(p-v) . (w-v)] / |w-v|^2
		// We clamp t from [0,1] to handle points outside the segment vw.
		Vector2D pv = p.sub(v);
		Vector2D wv = w.sub(v);
		double dot = pv.dot(wv);
		double t = Math.max(0, Math.min(1, dot/l2));
		Vector2D projection = new Vector2DImpl(v);
		projection.multLocal(t);
		projection.mult(w.sub(v));
		return projection;
	}

	/**
	 * Checks the intersection of two line(segment)s and returns the intersection point, if they do intersect
	 * 
	 * @param l11 start point of the first line
	 * @param l12 end point of the first line
	 * @param l21 start point of the second line
	 * @param l22 end point of the second line
	 * @warn the intersection test is done in cartesian coordinates, e.g. Engineering2D
	 *  	if l11 or l21 has an Engineering2D CRS this one will be taken (in order: l11, l21)
	 *  	if both have other CRS, a new Engineering2D will be created, with l11.get(WGS84) as center
	 * @return the intersection point or null, if both lines do not intersect
	 */
	public static Coordinate intersectLineSegments(final Coordinate l11, final Coordinate l12, final Coordinate l21, final Coordinate l22){
		CoordinateReferenceSystem crs = l11.getCrs();
		if (crs == null || crs instanceof Engineering2D == false)
			crs = l21.getCrs();
		if (crs == null || crs instanceof Engineering2D == false)
			crs = null;
		if (crs == null)
			crs = new Engineering2DImpl(l11.get(CRSUtils.WGS84_2D).toVector());
			
		return intersectLineSegments(new LineSegment(l11, l12), new LineSegment(l21, l22), crs);
	}

	private static Coordinate intersectLineSegments(LineSegment ls1, LineSegment ls2, CoordinateReferenceSystem crs) {
		// Uses the method given at: http://local.wasp.uwa.edu.au/~pbourke/geometry/lineline2d/
		
		ls1.setCRS(crs);
		ls2.setCRS(crs);
		
		double ls2ey = ls2.end.getY();
		double ls2sy = ls2.start.getY();
		double l1ex = ls1.end.getX();
		double l1sx = ls1.start.getX();
		double l2ex = ls2.end.getX();
		double l2sx = ls2.start.getX();
		double l1ey = ls1.end.getY();
		double l1sy = ls1.start.getY();
		
		final double commonDenominator = (ls2ey - ls2sy)*(l1ex - l1sx) - (l2ex - l2sx)*(l1ey - l1sy);

		final double numeratorA = (l2ex - l2sx)*(l1sy - ls2sy) - (ls2ey - ls2sy)*(l1sx -l2sx);

		final double numeratorB = (l1ex - l1sx)*(l1sy - ls2sy) - (l1ey - l1sy)*(l1sx -l2sx);

		Coordinate out = null;
		if(equals(commonDenominator, 0.0))
		{
			// The lines are either coincident or parallel
			// if both numerators are 0, the lines are coincident
			if(equals(numeratorA, 0.f) && equals(numeratorB, 0.f))
			{
				// Try and find a common endpoint
				if(ls2.start == ls1.start ||ls2.end == ls1.start)
					out = ls1.start;
				else if(ls2.end == ls1.end || ls2.start == ls1.end)
					out = ls1.end;
				// now check if the two segments are disjunct
				else if (l2sx>l1sx && l2ex>l1sx && l2sx>l1ex && l2ex>l1ex)
					return null;
				else if (ls2sy>l1sy && ls2ey>l1sy && ls2sy>l1ey && ls2ey>l1ey)
					return null;
				else if (l2sx<l1sx && l2ex<l1sx && l2sx<l1ex && l2ex<l1ex)
					return null;
				else if (ls2sy<l1sy && ls2ey<l1sy && ls2sy<l1ey && ls2ey<l1ey)
					return null;
				// else the lines are overlapping to some extent
				else
				{
					// find the points which are not contributing to the
					// common part
					Coordinate maxp;
					Coordinate minp;
					if ((l1sx>l2sx && l1sx>l2ex && l1sx>l1ex) || (l1sy>ls2sy && l1sy>ls2ey && l1sy>l1ey))
						maxp=ls1.start;
					else if ((l1ex>l2sx && l1ex>l2ex && l1ex>l1sx) || (l1ey>ls2sy && l1ey>ls2ey && l1ey>l1sy))
						maxp=ls1.end;
					else if ((l2sx>l1sx && l2sx>l2ex && l2sx>l1ex) || (ls2sy>l1sy && ls2sy>ls2ey && ls2sy>l1ey))
						maxp=ls2.start;
					else
						maxp=ls2.end;
					if (maxp != ls1.start && ((l1sx<l2sx && l1sx<l2ex && l1sx<l1ex) || (l1sy<ls2sy && l1sy<ls2ey && l1sy<l1ey)))
						minp=ls1.start;
					else if (maxp != ls1.end && ((l1ex<l2sx && l1ex<l2ex && l1ex<l1sx) || (l1ey<ls2sy && l1ey<ls2ey && l1ey<l1sy)))
						minp=ls1.end;
					else if (maxp != ls2.start && ((l2sx<l1sx && l2sx<l2ex && l2sx<l1ex) || (ls2sy<l1sy && ls2sy<ls2ey && ls2sy<l1ey)))
						minp=ls2.start;
					else
						minp=ls2.end;

					// one line is contained in the other. Pick the center
					// of the remaining points, which overlap for sure
					out = new CoordinateImpl(0,0,crs);
					if (ls1.start != maxp && ls1.start != minp)
						add(out, ls1.start);
					if (ls1.end != maxp && ls1.end != minp)
						add(out, ls1.end);
					if (ls2.start != maxp && ls2.start != minp)
						add(out, ls2.start);
					if (ls2.end != maxp &&ls2.end != minp)
						add(out, ls2.end);
					mult(out, 0.5);
				}

				return out; // coincident
			}

			return null; // parallel
		}

		// Get the point of intersection on this line, checking that
		// it is within the line segment.
		double uA = numeratorA / commonDenominator;
		if((uA < 0.f || uA > 1.f) )
			return null; // Outside the line segment

		double uB = numeratorB / commonDenominator;
		if((uB < 0.f || uB > 1.f))
			return null; // Outside the line segment

		// Calculate the intersection point.
		if (out == null){
			return new CoordinateImpl((l1sx + uA * (l1ex - l1sx)), (l1sy + uA * (l1ey - l1sy)), crs);
		}
		out.setX(l1sx + uA * (l1ex - l1sx));
		out.setY(l1sy + uA * (l1ey - l1sy));
		return out;
	}

	private static void mult(Coordinate out, double d) {
		out.setX(out.getX() * d);
		out.setY(out.getY() * d);
	}

	private static void add(Coordinate out, Coordinate start) {
		out.setX(out.getX() + start.getX());
		out.setY(out.getY() + start.getY());
	}

	private static boolean equals(double a, double b) {
		return Math.abs(a-b) < 0.000000001;
	}
}
