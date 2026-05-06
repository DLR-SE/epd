# Physics Util
Here are various Physics Utils.


## Content

This contains a TransformedPositionGenerator with two implementations:
- SimpleTransformedPositionGenerator
- LinearizedTransformedPositionGenerator

## TransformedPositionGenerator

This is a utility interface to provide an easy way to transform relative positions and poses into global positions.
Say we require the WGS84 Coordinate 50 meters north of a given coordinate. The implementations of this Interface may provide that.

Note that all transformation are only performed in 2D Space. (x,y / lat,lon and Around z-Axis)

### SimpleTransformedPositionGenerator
Uses a dummy LocatableObject to perform the transformation using the CRS transformation features.
Implicitly leverages a vincent calculator and thus is inherently limited in accuracy and performance. The accuracy is usually good enough and the vincent calculator is the standard approach for such tasks.

### LinearizedTransformedPositionGenerator
Linearizes the transformation from an EngineeringCRS to an WGS84 CRS at a given distance in lat and lon direction.
The farther away a generated coordinate is from the linearization distance the inaccurate results will be.
Performance is very good but only when generating multiple coordinates from a single linearization since during linearization the vincent calculator is leveraged.

### How To Use:
1) Create a TransformedPositionGenerator at a reference Point. 

*Note that in this example the default Orientation is facing north since no Orientation is explicitly provided*

Simple:
```java Simple
Coordinate referenceCoordinate = new CoordinateImpl(53.140557, 8.219967, CRSUtils.WGS84_2D);
TransformedPositionGenerator positionGenerator = new LinearizedTransformedPositionGenerator(referenceCoordinate, 1852, 1852);
```
Linearized:
```java Linearized
Coordinate referenceCoordinate = new CoordinateImpl(53.140557, 8.219967, CRSUtils.WGS84_2D);
TransformedPositionGenerator positionGenerator = new SimpleTransformedPositionGenerator(referenceCoordinate);
```
2) generate a new Coordinate using one of the provided methods

This will create a new coordinate 60 meters north and 50 meters east of the reference position.
This is because the reference is facing north. Otherwise, it would be 60 meters ahead and 50 meters to starboard.


```java new coordinate from
Coordinate newCoordinate = positionGenerator.generateTransformed(60, 50);
```

This will yield the same result.

```java new coordinate from 
Vessel ship = new VesselImpl();

ship.getPose.setCoordinate(referenceCoordinate);
ship.getPose.setOrientation(new EulerImpl(0, 0, 0, AngleUnit.DEGREE));

Coordinate relativeCoordinate = new CoordinateImpl(-50, 60, ship.getOwnedCoordinateSystem()); // note the change of parameter order.
Coordinate newCoordinate = positionGenerator.generateTransformed(relativeCoordinate);
```

### Testing 
Tests are implemented for each implementation, each constructor and each method in the interface is run and results are checks.

Rotation of the reference Pose and the relative Pose is not tested.
    
Tested:
- Translation
- Constructors
- Method Calls

Untested
- Rotation

