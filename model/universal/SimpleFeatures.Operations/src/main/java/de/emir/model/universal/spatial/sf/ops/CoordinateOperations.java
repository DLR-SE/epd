package de.emir.model.universal.spatial.sf.ops;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.delegate.ICoordinateDelegationInterface;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.spatial.sf.Point;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Distance;

/**
 *	@generated 
 */
public class CoordinateOperations extends GeometryOperations implements ICoordinateDelegationInterface{


	@Override
	public org.locationtech.jts.geom.Geometry createNativeGeometry(Geometry self) {
		de.emir.model.universal.spatial.sf.Point p = (de.emir.model.universal.spatial.sf.Point) self;
		Coordinate c = p.getCoordinate();
		
		return sGeometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(c.getX(), c.getY(), c.getZ()));
	}

	@Override
	public int numCoordinates(Geometry self) {
		return 1;
	}

	@Override
	public Coordinate getCoordinate(Geometry self, int index) {
		return ((Point)self).getCoordinate();
	}

	@Override
	public int getNumGeometries(Geometry self) {
		return 1;
	}

	@Override
	public Geometry getGeometry(Geometry self, int idx) {
		return self;
	}

    @Override
    public Angle getAzimuth(Coordinate self, Coordinate _other) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Distance getDistance(Coordinate self, Coordinate _other) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Coordinate getTarget(Coordinate self, Distance _distance, Angle _azimuth) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int dimension(Coordinate self) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getLatitude(Coordinate self) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getLongitude(Coordinate self) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Coordinate get(Coordinate self, CoordinateReferenceSystem _crs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setLatLon(Coordinate self, double _lat, double _lon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setLatLonAlt(Coordinate self, double _lat, double _lon, double _alt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setXY(Coordinate self, double _x, double _y) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setXYZ(Coordinate self, double _x, double _y, double _z) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Coordinate copy(Coordinate self) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Vector toVector(Coordinate self) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Vector2D toVector2D(Coordinate self) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Vector3D toVector3D(Coordinate self) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void fromVector(Coordinate self, Vector _value, CoordinateReferenceSystem _crs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void fromVector(Coordinate self, Vector _value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void set(Coordinate self, Coordinate _value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void set(Coordinate self, double _x, double _y, double _z, CoordinateReferenceSystem _crs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String readableString(Coordinate self) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
