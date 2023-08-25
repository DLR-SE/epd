package de.emir.epd.model.ownship;

import de.emir.model.application.track.impl.TrackCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.impl.VesselImpl;

public class Ownship extends VesselImpl {
	private TrackCharacteristicImpl track = new TrackCharacteristicImpl();

	public Ownship(Vessel v) {
		super(v);
	}

	public Ownship() {
		super();
		this.getCharacteristics().add(track);
	}
	
}
