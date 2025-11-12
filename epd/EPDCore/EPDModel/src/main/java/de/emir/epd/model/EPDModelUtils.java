package de.emir.epd.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import de.emir.epd.model.ids.OwnshipIds;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.impl.VesselImpl;
import de.emir.model.universal.detection.ITarget;
import de.emir.model.universal.detection.ITrackedTarget;
import de.emir.model.universal.detection.impl.TargetImpl;
import de.emir.model.universal.detection.impl.TrackedTargetImpl;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.EnvironmentLayer;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.ObjectLayer;
import de.emir.model.universal.physics.impl.ObjectLayerImpl;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyStore;

/**
 * Util class for accessing vessels stored in the EPD model.
 * @apiNote This class has listener endpoints used for detecting changes to the model. Listening is
 * possible by using the subscribeModelChange() method. Currently 3 types of events will be fired.
 * "ownship" -> Returns the current ownship as a Vessel object if the ownship was changed.
 * "aisTargets" -> Returns an Vessel array of the current AIS targets if the targets were changed.
 * "aisTargetSet" -> Returns the updated default environment if targets or the ownship was changed.
 * "targets" -> Returns a target array of the current targets ifthe targets were changed.
 */
public final class EPDModelUtils {

	private static PropertyChangeSupport support = new PropertyChangeSupport(EPDModelUtils.class);

	private EPDModelUtils() {
	}

	/**
	 * Subscribes to changes made by the EPDModelUtils to the EPDModel.
	 * @apiNote Only fires when changes were made by EPDModelUtils. Direct changes in EPDModel are only registered by the ModelReadAdapters.
	 * @param listener Listener to register to changes.
	 */
	public static void subscribeModelChange(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	/**
	 * Unsubscribes from changes made by the EPDModelUtils to the EPDModel.
	 * @param listener Listener to unregister from changes.
	 */
	public static void unsubscribeModelChange(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}
	
	/**
	 * Subscribes to changes made by the EPDModelUtils to the EPDModel.
	 * @apiNote Only fires when changes were made by EPDModelUtils. Direct changes in EPDModel are only registered by the ModelReadAdapters.
	 * @param propertyName Name of the event to subscribe to.
	 * @param listener Listener to register to changes.
	 */
	public static void subscribeModelChange(String propertyName, PropertyChangeListener listener) {
		support.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Unsubscribes from changes made by the EPDModelUtils to the EPDModel.
	 * @param propertyName Name of the event to unsubscribe from.
	 * @param listener Listener to unregister from changes.
	 */
	public static void unsubscribeModelChange(String propertyName, PropertyChangeListener listener) {
		support.removePropertyChangeListener(propertyName, listener);
	}

	/**
	 * Returns the object layer of a environment if found, else null.
	 * @param environment Environment to extract object layer from.
	 * @return First object layer of the environment if found, else null.
	 */
	public static ObjectLayer getObjectLayer(Environment environment) {
		if (environment == null || environment.getLayer() == null || environment.getLayer().stream() == null) {
			return null;
		}
		List<EnvironmentLayer> copy = new ArrayList<>(environment.getLayer());
		return (ObjectLayer) copy
				.stream()
				.filter(p -> p instanceof ObjectLayer)
				.findFirst()
				.orElse(null);
	}

	/**
	 * Gets the default environment of the EPDModel. This is the environment used by all methods of EPDModelUtils which do not require a Environment 
	 * as a method parameter.
	 * @return Default environment of the Platform if found, else null.
	 */
	public static Environment getDefaultEnvironment() {
		Object o = PlatformUtil.getModelManager().getModelProvider().getModel();
		if(o instanceof EPDModel) {
			EPDModel model = (EPDModel) o;
			return model.getEnvironment();
		} else {
			return null;
		} 
	}
	
	/**
	 * Returns the object layer of a environment if found, else creates a new object layer for the environment.
	 * @param environment Environment to extract object layer from.
	 * @return First object layer of the environment or newly created object layer.
	 */
	public static ObjectLayer retrieveObjectLayer(Environment environment) {
		ObjectLayer objectLayer = getObjectLayer(environment);
		if (objectLayer == null) {
			objectLayer = new ObjectLayerImpl();
			environment.getLayer().add(objectLayer);
			forcePropertyChange("objectLayer", null, objectLayer);
		}
		return objectLayer;
	}
	
	/**
	 * Gets a vessel object by its id (MMSI).
	 * @implNote This method searches for vessels by using long comparisons for MMSI, therefore is faster than methods which comparses MMSIs as strings.
	 * @param environment Environment to extract vessel from.
	 * @param id MMSI of the vessel.
	 * @return Vessel object with matching MMSI if found, else null.
	 */
	public static Vessel getVesselById(Environment environment, String id) {
		try {
			// this method searches for a long, thus parsing the string
			// is better than calling to string on a trillion vessels.
			long mmsi = Long.parseLong(id);

			List<LocatableObject> copy = new ArrayList<>(retrieveObjectLayer(environment).getObjects());
			return (Vessel) copy
					.stream()
					.filter(p -> p instanceof Vessel)
					.filter(p -> Long.compare(((Vessel) p).getMmsi(), mmsi) == 0)
					.findFirst().orElse(null);
		} catch (Exception ignored){
			// happens when string cannot be parsed as long
		}

		return null;
	}

	/**
	 * Gets a Target by its id.
	 * @param environment Environment to retrieve Target from.
	 * @param id ID of the Target to get.
	 * @return Target if found, else null.
	 */
	public static ITarget getTargetById(Environment environment, String id) {
		List<LocatableObject> copy = new ArrayList<>(retrieveObjectLayer(environment).getObjects());
		return (ITarget) copy
				.stream()
				.filter(p -> p instanceof ITarget)
				.filter(p -> ((ITarget) p).getId().equals(id))
				.findFirst().orElse(null);
	}

	/**
	 * Gets a TrackedTarget by its id.
	 * @param environment Environment to retrieve TrackedTarget from.
	 * @param id ID of the TrackedTarget to get.
	 * @return TrackedTarget if found, else null.
	 */
	public static ITrackedTarget getTrackedTargetById(Environment environment, String id) {
		List<LocatableObject> copy = new ArrayList<>(retrieveObjectLayer(environment).getObjects());
		return (ITrackedTarget) copy
				.stream()
				.filter(p -> p instanceof ITrackedTarget)
				.filter(p -> ((ITrackedTarget) p).getId().equals(id))
				.findFirst().orElse(null);
	}
	
	/**
	 * Gets the ownship from a environment if found, else returns null.
	 * @implNote Looks for the alias tags of the vessels. If the vessel contains Ownship as an alias, it is considered the ownship and returned.
	 * This is to enable identification of the ownship independent of the MMSI (for example when handling radar targets).
	 * @param environment Environment to search for the ownship.
	 * @return Ownship if environment contains an ownship, else null.
	 */
	public static Vessel getOwnship(Environment environment) {
		List<LocatableObject> copy = new ArrayList<>(retrieveObjectLayer(environment).getObjects());
		return (Vessel) copy
			.stream()
			.filter(object -> object instanceof Vessel)
			.filter(vessel -> ((Vessel) vessel).getAllias().contains("Ownship"))
			.findFirst()
			.orElse(null);
	}
	
	/**
	 * Gets the ownship of the environment. If no ownship was found or the ownship MMSI does not match the MMSI specified in the "id" parameter, 
	 * the ship with the MMSI according to the parameter "id" is selected as the new ownship.
	 * @param environment Environment to search for ownship.
	 * @param id MMSI of the ownship.
	 * @return The current ownship or the newly selected ownship if no ownship is set or the ownship MMSI did not match the MMSI of the "id" parameter.
	 */
	public static Vessel retrieveOwnship(Environment environment, String id) {
		Vessel result = getOwnship(environment);
		if (result == null || !id.equals(Long.toString(result.getMmsi()))) {
			Vessel oldResult = result;
			Vessel [] oldTargets = getAisTargets(environment);
			Environment oldTargetSet = environment;
			setOwnship(environment, retrieveById(environment, id));
			result = getOwnship(environment);
			forcePropertyChange("ownship", oldResult, result);
			forcePropertyChange("aisTargets", oldTargets, getAisTargets(environment));
			forcePropertyChange("aisTargetSet", oldTargetSet, environment);
		}
		return result;
	}

	/**
	 * Gets the ownship of the default environment. If no ownship was found or the ownship MMSI does not match the MMSI specified in the "id" parameter, 
	 * the ship with the MMSI according to the parameter "id" is selected as the new ownship.
	 * @param id MMSI of the ownship.
	 * @return The current ownship or the newly selected ownship if no ownship is set or the ownship MMSI did not match the MMSI of the "id" parameter.
	 */
	public static Vessel retrieveOwnship(String id) {
		return retrieveOwnship(getDefaultEnvironment(), id);
	}

	/**
	 * Gets the ownship of the default environment with the ownship ID set by the ownship plugin. If no ownship was found or the ownship id was not set by the ownship plugin, 211876480 is used.
	 * @return The current ownship or the newly selected ownship with the ID 211876480 if the ownship MMSI is not set.
	 */
	public static Vessel retrieveOwnship() {
		String id = PropertyStore.getContext(OwnshipIds.OWNSHIP_VIEWER_PROP_CONTEXT).getProperty(OwnshipIds.OWNSHIP_VIEWER_PROP_AIS_TARGET, "211876480").getValue();
		return retrieveOwnship(id);
	}
	
	/**
	 * Sets a new ownship.
	 * @implNote Adds the tag "Ownship" to the alias of a vessel. If an ownship does currently exist in the environment, its "Ownship" tag is removed.
	 * @param environment Environment where the ownship is located.
	 * @param vessel New ownship which should be used.
	 * @return True.
	 */
	public static boolean setOwnship(Environment environment, Vessel vessel) {
		Vessel currentOwnship = getOwnship(environment);
		ObjectLayer objectLayer = retrieveObjectLayer(environment);
		if(currentOwnship != null) {
			LocatableObject lastOwnship = objectLayer.getObjects().get(objectLayer.getObjects().indexOf(currentOwnship));
			if(lastOwnship.getAllias() != null && lastOwnship.hasAlias("Ownship")) {
				lastOwnship.getAllias().remove("Ownship");
			}
		}
		vessel.getAllias().add("Ownship");
		if(!objectLayer.getObjects().contains(vessel)) {
			add(environment, vessel);
		}
		return true;
	}

	/**
	 * Sets a new ownship on the default environment.
	 * @implNote Adds the tag "Ownship" to the alias of a vessel. If an ownship does currently exist in the default environment, its "Ownship" tag is removed.
	 * @param vessel New ownship which should be used.
	 * @return True.
	 */
	public static boolean setOwnship(Vessel vessel) {
		return setOwnship(getDefaultEnvironment(), vessel);
	}
	
	/**
	 * Gets a vessel by its id (MMSI). If no vessel with the id exists, a new vessel is created.
	 * @param environment Environment to search for vessel in.
	 * @param id ID (MMSI) of the vessel.
	 * @return A vessel matching the id or a newly created vessel with the id.
	 */
	public static Vessel retrieveById(Environment environment, String id) {
		Vessel result = getVesselById(environment, id);

		if (result == null && id != null) {
			Vessel [] oldTargets = getAisTargets(environment);
			Environment oldTargetSet = environment;
			result = new VesselImpl();
			result.setMmsi(Long.parseLong(id));
			add(environment, result);
			support.firePropertyChange("aisTarget", null, result);
			forcePropertyChange("aisTargets", oldTargets, getAisTargets(environment));
			forcePropertyChange("aisTargetSet", oldTargetSet, environment);
		}
		return result;
	}

	/**
	 * Gets or creates a Target by a given ID.
	 * @param environment Environment to query or create Target on.
	 * @param id ID of the Target to get.
	 * @return Existing Target matching by ID or newly created Target.
	 */
	public static ITarget retrieveByTargetId(Environment environment, String id) {
		ITarget result = getTargetById(environment, id);

		if (result == null && id != null) {
			ITarget [] oldTargets = getTargets(environment);
			result = new TargetImpl();
			result.setId(id);
			add(environment, result);
			forcePropertyChange("targets", oldTargets, getTargets(environment));
		}
		return result;
	}

	/**
	 * Gets or creates a TrackedTarget by a given ID.
	 * @param environment Environment to query or create TrackedTarget on.
	 * @param id ID of the TrackedTarget to get.
	 * @return Existing TrackedTarget matching by ID or newly created TrackedTarget.
	 */
	public static ITrackedTarget retrieveByTrackedTargetId(Environment environment, String id) {
		ITrackedTarget result = getTrackedTargetById(environment, id);

		if (result == null && id != null) {
			ITrackedTarget [] oldTargets = getTrackedTargets(environment);
			result = new TrackedTargetImpl();
			result.setId(id);
			add(environment, result);
			forcePropertyChange("trackedTargets", oldTargets, getTrackedTargets(environment));
		}
		return result;
	}

	/**
	 * Gets a vessel by its id (MMSI) from the default environment. If no vessel with the id exists, a new vessel is created.
	 * @param id ID (MMSI) of the vessel.
	 * @return A vessel matching the id or a newly created vessel with the id.
	 */
	public static Vessel retrieveById(String id) {
		return retrieveById(getDefaultEnvironment(), id);
	}

	/**
	 * Adds a vessel to the object layer of an environment. If the vessel is already present in the object layer, it is not added again.
	 * @param environment Environment to add the vessel to.
	 * @param vessel Vessel to add.
	 * @return True if adding was successful, else false (for example when the vessel already exists).
	 */
	public static boolean add(Environment environment, Vessel vessel) {
		if(!retrieveObjectLayer(environment).getObjects().contains(vessel)) {
			return retrieveObjectLayer(environment).getObjects().add(vessel);
		} else {
			return false;
		}
	}

	/**
	 * Adds a Target to the object layer of an environment. If the Target is already present in the object layer, it is not added again.
	 * @param environment Environment to add the Target to.
	 * @param target Target to add.
	 * @return True if adding was successful, else false (for example when the Target already exists).
	 */
	public static boolean add(Environment environment, ITrackedTarget target) {
		if(!retrieveObjectLayer(environment).getObjects().contains(target)) {
			return retrieveObjectLayer(environment).getObjects().add(target);
		} else {
			return false;
		}
	}

	/**
	 * Adds a TrackedTarget to the object layer of an environment. If the TrackedTarget is already present in the object layer, it is not added again.
	 * @param environment Environment to add the TrackedTarget to.
	 * @param target TrackedTarget to add.
	 * @return True if adding was successful, else false (for example when the TrackedTarget already exists).
	 */
	public static boolean add(Environment environment, ITarget target) {
		if(!retrieveObjectLayer(environment).getObjects().contains(target)) {
			return retrieveObjectLayer(environment).getObjects().add(target);
		} else {
			return false;
		}
	}
	
	/**
	 * Removes an object from the object layer of an environment.
	 * @param environment Environment to remove object from.
	 * @param o Object to remove.
	 * @return True if removal was successul.
	 */
	public static boolean remove(Environment environment, Object o) {
		Vessel [] oldAISTargets = getAisTargets(environment);
		ITrackedTarget [] oldTrackedTargets = getTrackedTargets(environment);
		ITarget [] oldTargets = getTargets(environment);
		Environment oldEnvironment = environment;
		Vessel oldOwnship = getOwnship(environment);
		boolean removeStatus = retrieveObjectLayer(environment).getObjects().remove(o);
		forcePropertyChange("aisTargets", oldAISTargets, getAisTargets(environment));
		forcePropertyChange("aisTargetSet", oldEnvironment, environment);
		forcePropertyChange("ownship", oldOwnship, getOwnship(environment));
		forcePropertyChange("targets", oldTargets, getTargets(environment));
		forcePropertyChange("trackedTargets", oldTrackedTargets, getTrackedTargets(environment));
		return removeStatus;
	}

	/**
	 * Removes all objects from an object layer of an environment.
	 * @param environment Environment top remove objects from.
	 */
	public static void clear(Environment environment) {
		Vessel [] oldAISTargets = getAisTargets(environment);
		ITrackedTarget [] oldTrackedTargets = getTrackedTargets(environment);
		ITarget [] oldTargets = getTargets(environment);
		Vessel oldOwnship = getOwnship(environment);
		Environment oldEnvironment = environment;
		retrieveObjectLayer(environment).getObjects().clear();
		forcePropertyChange("aisTargets", oldAISTargets, getAisTargets(environment));
		forcePropertyChange("aisTargetSet", oldEnvironment, environment);
		forcePropertyChange("ownship", oldOwnship, getOwnship(environment));
		forcePropertyChange("targets", oldTargets, getTargets(environment));
		forcePropertyChange("trackedTargets", oldTrackedTargets, getTrackedTargets(environment));
	}

	/**
	 * Gets all ais targets of an environment as an array.
	 * @param environment Environment to get ais targets from.
	 * @return Array of current ais targets.
	 */
	public static Vessel[] getAisTargets(Environment environment) {
		List<LocatableObject> copy = new ArrayList<>(retrieveObjectLayer(environment).getObjects());
        return copy.stream().filter(p -> p instanceof Vessel)
				.map(Vessel.class::cast).toArray(Vessel[]::new);
	}

	/**
	 * Gets all Target objects of an environment as an array.
	 * @param environment Environment to get Targets from.
	 * @return Array of current Targets.
	 */
	public static ITarget[] getTargets(Environment environment) {
		List<LocatableObject> copy = new ArrayList<>(retrieveObjectLayer(environment).getObjects());
		return copy.stream().filter(p -> p instanceof ITarget)
				.map(ITarget.class::cast).toArray(ITarget[]::new);
	}

	/**
	 * Gets all TrackedTarget objects of an environment as an array.
	 * @param environment Environment to get TrackedTargets from.
	 * @return Array of current TrackedTargets.
	 */
	public static ITrackedTarget[] getTrackedTargets(Environment environment) {
		List<LocatableObject> copy = new ArrayList<>(retrieveObjectLayer(environment).getObjects());
		return copy.stream().filter(p -> p instanceof ITrackedTarget)
				.map(ITrackedTarget.class::cast).toArray(ITrackedTarget[]::new);
	}

	/**
	 * Fires the property change to all registered listeners regardless of the old and new value. This is to prevent
	 * events that are not sent when the old and new value are the same.
	 * @param topic Topic on which to publish message.
	 * @param oldValue Old value of message.
	 * @param newValue New value of message.
	 */
	private static void forcePropertyChange(String topic, Object oldValue, Object newValue) {
		PropertyChangeEvent event = new PropertyChangeEvent(EPDModelUtils.class, topic, oldValue, newValue);

		for(PropertyChangeListener listener : support.getPropertyChangeListeners(topic)) {
			listener.propertyChange(event);
		}
	}
}
