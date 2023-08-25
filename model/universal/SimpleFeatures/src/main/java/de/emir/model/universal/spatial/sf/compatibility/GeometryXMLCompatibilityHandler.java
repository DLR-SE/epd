package de.emir.model.universal.spatial.sf.compatibility;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Node;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.sf.LineString;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLCompatibilityManager.IXMLPostProcessor;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer.IXMLReadListener;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer.XMLReaderImpl;
import de.emir.tuml.ucore.runtime.utils.XMLReader;

public class GeometryXMLCompatibilityHandler implements IXMLPostProcessor {

	
	@Override
	public boolean requiresPostProcessing(UObject obj, Node xmlNode, XMLReader reader) {
		if (obj instanceof Geometry) {
			if (((Geometry)obj).numCoordinates() == 0)
				return true;
		}
		return false;
	}
	
	@Override
	public void onObjectRead(UObject object, Node xmlNode, XMLReader reader, XMLReaderImpl ser) {
		Geometry geom = (Geometry) object; //we can do this, since we are only registered for instances of Geometry
		if (geom.numCoordinates() > 0) return ; //we obviously have some coordiantes, so we did load something correct
		
		Collection<Node> coordNodes = reader.getAllNodes(xmlNode, "coordinats"); //only if we find coordinates in the node, we continue, otherwise it's already the new version
		if (coordNodes == null || coordNodes.isEmpty()) return ;
		
		
		ArrayList<Coordinate> coords = new ArrayList<>();
		try {
			for (Node cn : coordNodes) {
				Coordinate c = (Coordinate)ser.read(cn, SpatialPackage.Literals.Coordinate);
				if (c != null) coords.add(c);
			}
			if (coords.isEmpty() == false) {
				CoordinateSequence cs = null;
				if (geom instanceof LineString)
					cs = geom.getCoordinates();
				if (cs != null)
					for (Coordinate c : coords)
						cs.addCoordinate(c);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public UClassifier onCouldNotFindClassifier(String type, UObject instance, UStructuralFeature feature, Node node,
			XMLReader mReader, XMLReaderImpl xmlReaderImpl) {
		return null;
	}

	

}
