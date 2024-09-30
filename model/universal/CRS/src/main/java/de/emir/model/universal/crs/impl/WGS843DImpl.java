package de.emir.model.universal.crs.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.WGS843D;
import de.emir.model.universal.crs.impl.WGS84CRSImpl;
import de.emir.model.universal.crs.*;
import de.emir.model.universal.crs.internal.calc.VincentCalculator3D;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.math.impl.Vector3DImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import net.jafama.FastMath;


/**
 Defines the default global CRS to be used in eMIR, for 3 dimensions. 
 * The WGS843D differs fom its 2D counterpart (WGS82D) by adding the 3er dimension depth (in meters) as the z component.
 * @generated 
 */
@UMLImplementation(classifier = WGS843D.class)
public class WGS843DImpl extends WGS84CRSImpl implements WGS843D  
{
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public WGS843DImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public WGS843DImpl(final WGS843D _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public WGS843DImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.WGS843D;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "WGS843DImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}

	@Override
	public int dimension() {
		return 3;
	}

	private VincentCalculator3D mCalculator = null;
	private VincentCalculator3D getCalculator() {
		if (mCalculator == null)
			mCalculator = new VincentCalculator3D();
		return mCalculator;
	}
	
	@Override
	public Vector getDirectionToNorth() {
		return new Vector2DImpl(0, 1); // North has no defined position in z
	}

	@Override
	public List<Double> directionToBearing(double x, double y, double z) {
		double azimuth = getCalculator().getAzimuth(0, 0, y, x); //the WGS84 is always centered at 0,0 and we have no other options
		if (z != z) //z=NaN
			return Arrays.asList(azimuth, 0.0);
		//? does it matter on which plane we do the calculation? No.
		// Defining the center at 0,0,0 and the reference of the vertical angle as orthogonal to lon and lat
		// using Cartesian interpretation of the vertical angle direction
		double dist2D = getCalculator().getDistance(0,0, y, x);
		double altitudeAngle = Math.asin(x/dist2D) * Math.PI /180;
		return Arrays.asList(azimuth, altitudeAngle);
	}

	@Override
	public Vector bearingToDirection(double yaw, double pitch) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public CoordinateReferenceSystem copy() {
		return new WGS843DImpl(this);
	}


	@Override
	public double getDistance(Vector locA, Vector locB) {
		supportedVector(locA); supportedVector(locB); // Filter supported Vector Types
		ActualVectorType actualType_locA = getActualVectorType(locA);
		ActualVectorType actualType_locB = getActualVectorType(locB);
		// if both true 3d Vectors use 3d Distance. If either mimicking a 3d vector cast to 2d Vector
		// if 1 2d and 1 3d use distance at altitude. if both 2d use distance at sea level.
		// resulting in this ver complicated decision tree casting to proper type
		// and letting the behaviour of distance calculation being resolved by type and overloading

		switch(actualType_locA) {
			case TrueTwoDimensional:
				Vector2D _locA = (Vector2D) locA;
				switch (actualType_locB) {
					case TrueThreeDimensional:
						Vector3D _locB = (Vector3D) locB;
						return getDistanceFromCalculator(_locA, _locB); // 2d, 3d
					case TrueTwoDimensional:
						Vector2D __locB = (Vector2D) locB;
						return getDistanceFromCalculator(_locA, __locB); // 2d, 2d
				}
			case TrueThreeDimensional:
				Vector3D ___locA = (Vector3D) locA;
				switch (actualType_locB) {
					case TrueThreeDimensional:
						Vector3D _locB = (Vector3D) locB;
						return getDistanceFromCalculator(___locA, _locB); // 3d, 3d
					case TrueTwoDimensional:
						Vector2D __locB = (Vector2D) locB;
						return getDistanceFromCalculator(___locA, __locB); // 3d, 2d
				}
		}
		throw new UnsupportedOperationException("Vector is not supported");
	}



	private double getDistanceFromCalculator(Vector2D locA, Vector3D locB) {
		return getDistanceFromCalculator(locB, locA); // Assume that distance is symmetric
	}

	/**
	 * This would force usage of the 2D-distance between two 3D Vectors
	 * @param locA location A
	 * @param locB location B
	 * @return Distance between both locations in meters
	 */
	private double getDistanceFromCalculator(Vector3D locA, Vector3D locB) {
		return getCalculator().getDistance(locA.getX(), locA.getY(), locA.getZ(),
				locB.getX(), locB.getY(), locB.getZ());
	}

	@Override
	public Vector getDistanceAndAzimuth(Vector loc1, Vector loc2) {
		// inefficient way of calculating this because it is calculated twice
		return new Vector2DImpl(getDistance(loc1, loc2), getCalculator().getAzimuth(loc1.get(0), loc1.get(1), loc2.get(0), loc2.get(1)));
	}
	
	@Override
	public int getHash() {
		return 1; //no parameters, so always the same
	}

	/**
	 * Check for support of vector given
	 * @param vector vector to check
	 * @exception UnsupportedOperationException when vector is not supported
	 */
	private static void supportedVector(Vector vector) {
		if (vector == null) {
			throw new UnsupportedOperationException("null is not supported");
		}
		if ( !(vector.dimensions() == 2 || vector.dimensions() == 3)) {
			throw new UnsupportedOperationException("Vector Dimensions are not supported");
		}
	}

	private double getDistanceFromCalculator(Vector2D locA, Vector2D locB) {
		// Similar Calculation to WHS842DImpl
		return getCalculator().getDistance(locA.getX(), locA.getY(), locB.getX(), locB.getY());
	}

	private double getDistanceFromCalculator(Vector3D locA, Vector2D locB) {
		return getCalculator().getDistance(locA.getX(), locA.getY(), locB.getX(), locB.getY(), locA.getZ());
	}

	private ActualVectorType getActualVectorType(Vector vectorUnderInvestigation) {
		if(vectorUnderInvestigation.dimensions() == 2) {
			return  ActualVectorType.TrueTwoDimensional;
		} else if (vectorUnderInvestigation.dimensions() == 3) {
			return ActualVectorType.TrueThreeDimensional;
		} else {
			throw new UnsupportedOperationException("Vector not supported");
		}
	}

	private Vector2D reduce3DVectorDimension(Vector3D vector) {
		Vector2D new_vector = new Vector2DImpl();
		new_vector.setX(vector.getX());
		new_vector.setY(vector.getY());
		return new_vector;
	}

	private enum ActualVectorType {
		TrueThreeDimensional,
		TrueTwoDimensional
	}

}


