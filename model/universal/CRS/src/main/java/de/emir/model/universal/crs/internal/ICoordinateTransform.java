package de.emir.model.universal.crs.internal;

import de.emir.model.universal.crs.CoordinateReferenceSystem;

public interface ICoordinateTransform {
	/**
	 * returns true if this transform is able to transform a coordinate from src - reference system to dst - reference system
	 * @param src
	 * @param dst
	 * @return
	 */
	boolean canTransform(CoordinateReferenceSystem src, CoordinateReferenceSystem dst);
	
	/**
	 * Does the transformation between _src and _dst coordinate reference system 
	 * @note the call / check to canTransform should have been done before.
	 * @param in coordinates to be transformed, expressed in the CRS = _src
	 * @param _src coordinate reference system, that is used to describe the in values
	 * @param _dst target coordinate reference system
	 * @return null if the transformation failed otherwise the position of in expressed in the CRS _dst. if not null, the result always has the same dimension as the input coordinate (in)
	 */
	double[] transform(double[] in, CoordinateReferenceSystem _src, CoordinateReferenceSystem _dst);

	/**
	 * Does the transformation between _src and _dst coordinate reference system but only takes the direction into account 
	 * @note the call / check to canTransform should have been done before.
	 * @param ds direction to be transformed, expressed in the CRS = _src
	 * @param _src coordinate reference system, that is used to describe the in values
	 * @param _dst target coordinate reference system
	 * @return null if the transformation failed otherwise the direction of in expressed in the CRS _dst. if not null, the result always has the same dimension as the input coordinate (ds)
	 */
	double[] transformDirection(double[] ds, CoordinateReferenceSystem src, CoordinateReferenceSystem dst);
}
