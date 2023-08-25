package de.emir.epd.model;

import de.emir.epd.model.ownship.Ownship;
import de.emir.epd.model.route.RouteSet;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.impl.VesselImpl;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.ObjectLayer;
import de.emir.model.universal.physics.impl.EnvironmentImpl;
import de.emir.model.universal.physics.impl.ObjectLayerImpl;

public class EPDModel implements Cloneable {
	private Environment environment;
	private ObjectLayer objectLayer;
	private RouteSet routeSet = new RouteSet();

	public RouteSet getRouteSet() {
		return routeSet;
	}
	
	public Environment getAisTargetSet() {
		return environment;
	}
	
	/**
	 * 
	 */
	public EPDModel() {
		environment = new EnvironmentImpl();
		objectLayer = new ObjectLayerImpl(); 
		environment.getLayer().add(objectLayer);
	}
	
	/**
	 * 
	 */
	public EPDModel(EPDModel original) {
		environment = new EnvironmentImpl(original.environment);
		objectLayer = new ObjectLayerImpl(original.objectLayer); 
		environment.getLayer().add(objectLayer); 
	}

	@Override
	public EPDModel clone() {
		return new EPDModel(this);
	}

	public int size() {
		return objectLayer.getObjects().size();
	}

	public Vessel[] getAisTargets() {
		return objectLayer.getObjects().stream().filter(p -> p instanceof Vessel).map(Vessel.class::cast)
				.toArray(Vessel[]::new);
	}

	public Environment getEnvironment() {
		return environment;
	}
}
