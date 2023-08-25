package de.emir.epd.model;

import de.emir.model.domain.maritime.vessel.CommandedValue;
import de.emir.model.domain.maritime.vessel.StandingCommands;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.impl.HeadingCommandImpl;
import de.emir.model.domain.maritime.vessel.impl.StandingCommandsImpl;
import de.emir.model.domain.maritime.vessel.impl.VesselImpl;
import de.emir.model.universal.core.impl.IdentifiedReferenceImpl;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.ObjectLayer;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.physics.impl.ObjectLayerImpl;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.TimeImpl;

public final class EPDModelUtils {

	private EPDModelUtils() {
	}
	
	public static ObjectLayer getObjectLayer(Environment environment) {
		if (environment == null || environment.getLayer() == null || environment.getLayer().stream() == null) {
			return null;
		}
		return (ObjectLayer) environment
				.getLayer()
				.stream()
				.filter(p -> p instanceof ObjectLayer)
				.findFirst()
				.orElse(null);
	}
	
	public static ObjectLayer retrieveObjectLayer(Environment environment) {
		ObjectLayer objectLayer = getObjectLayer(environment);
		if (objectLayer == null) {
			objectLayer = new ObjectLayerImpl();
			environment.getLayer().add(objectLayer);
		}
		return objectLayer;
	}
	
	public static Vessel getVesselById(Environment environment, String id) {
		try {
			// this method searches for a long, thus parsing the string
			// is better than calling to string on a trillion vessels.
			long mmsi = Long.parseLong(id);

			ObjectLayer objectLayer = retrieveObjectLayer(environment);

			return (Vessel) objectLayer
					.getObjects()
					.stream()
					.filter(p -> p instanceof Vessel)
					.filter(p -> ((Vessel) p).getMmsi() == mmsi)
					.findFirst().orElse(null);
		} catch (Exception ignored){
			// happens when string cannot be parsed as long
		}

		return null;
	}
	
	public static Vessel getOwnship(Environment environment) {
		Vessel result = null;
		ObjectLayer objectLayer = retrieveObjectLayer(environment);
		Time latestCmd = new TimeImpl(0L, TimeUnit.MILLISECOND);
		
		for (LocatableObject o : objectLayer.getObjects()) {
			if (o instanceof Vessel) {
				Vessel v = (Vessel) o;
				StandingCommands cmds = v.getFirstCharacteristic(StandingCommands.class, true);
				if (cmds == null) {
					continue;
				}
				for (CommandedValue c : cmds.getCommands()) {
					if (c.getSource() != null && c.getSource() instanceof IdentifiedReferenceImpl) {
						IdentifiedReferenceImpl ref = (IdentifiedReferenceImpl) c.getSource();
						if (ref.getReferencedIdentifier().equalsIgnoreCase("Ownship") && c.getCreationTime().greater(latestCmd)) {
							result = v;
							latestCmd = c.getCreationTime();
						}
					}
				}
			}
		}
//		return (Vessel) objectLayer.getObjects().stream().filter(p -> p instanceof Vessel)
//				.filter(p -> ((Vessel) p).getFirstCharacteristic(StandingCommands.class, true) != null)..findFirst().orElse(null);
		return result;
	}
	
	public static Vessel retrieveOwnship(Environment environment, String id) {
		Vessel result = getOwnship(environment);
		if (result == null || !id.equals(Long.toString(result.getMmsi()))) {
			setOwnship(environment, retrieveById(environment, id));
			result = getOwnship(environment);
		}
		return result;
	}
	
	public static boolean setOwnship(Environment environment, Vessel vessel) {
		add(environment, vessel);
//		Vessel vFromModel = getVesselById(environment, Long.toString(vessel.getMmsi()));
		StandingCommands cmds = vessel.getFirstCharacteristic(StandingCommands.class, true);
		if (cmds == null) {
			cmds = new StandingCommandsImpl();
		}
		HeadingCommandImpl hcmd = new HeadingCommandImpl();
		hcmd.setCreationTime(new TimeImpl(System.currentTimeMillis(), TimeUnit.MILLISECOND));
		if (vessel.getPose() == null) {
			vessel.setPose(new PoseImpl());
		}
		hcmd.setHeading(PhysicalObjectUtils.getHeading(vessel));
		hcmd.setSource(new IdentifiedReferenceImpl("Ownship"));
		cmds.getCommands().add(hcmd);
		vessel.getCharacteristics().add(cmds);
		return true;
	}
	
	public static Vessel retrieveById(Environment environment, String id) {
		Vessel result = getVesselById(environment, id);
		
		if (result == null) {
			result = new VesselImpl();
			result.setMmsi(Long.parseLong(id));

			add(environment, result);
		}
		return result;
	}
	
	public static boolean add(Environment environment, Vessel vessel) {
		return retrieveObjectLayer(environment).getObjects().add(vessel);
	}
	
	public static boolean remove(Environment environment, Object o) {
		return retrieveObjectLayer(environment).getObjects().remove(o);
	}

	public static void clear(Environment environment) {
		retrieveObjectLayer(environment).getObjects().clear();
	}

	public static Vessel[] getAisTargets(Environment environment) {
		return retrieveObjectLayer(environment).getObjects().stream().filter(p -> p instanceof Vessel)
				.map(Vessel.class::cast).toArray(Vessel[]::new);
	}
}
