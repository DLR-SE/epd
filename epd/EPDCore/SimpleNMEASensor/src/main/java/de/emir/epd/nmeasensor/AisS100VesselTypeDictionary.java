/**
 * 
 */
package de.emir.epd.nmeasensor;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.emir.model.domain.maritime.vessel.VesselType;

/**
 * This dictionary maps the AIS shiptype IDs to the eMIR model VesselType enum.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 */
public class AisS100VesselTypeDictionary {
	public static <K, V> Map.Entry<K, V> entry(K key, V value) {
		return new AbstractMap.SimpleEntry<>(key, value);
	}

	public static <K, U> Collector<Map.Entry<K, U>, ?, Map<K, U>> entriesToMap() {
		return Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue());
	}

	public static <K, U> Collector<Map.Entry<K, U>, ?, ConcurrentMap<K, U>> entriesToConcurrentMap() {
		return Collectors.toConcurrentMap((e) -> e.getKey(), (e) -> e.getValue());
	}
	
	/**
	 * Map the AIS shiptype IDs to the eMIR model VesselType enum elements.
	 * 
	 * @return the map containing all AIS shiptype IDs and their eMIR model representation
	 */
	protected static Map<Integer, VesselType> map() {
		return Collections.unmodifiableMap(Stream.of(
				entry(0, VesselType.Other),
				entry(20, VesselType.WinginGround),
				entry(21, VesselType.WinginGround),
				entry(22, VesselType.WinginGround),
				entry(23, VesselType.WinginGround),
				entry(24, VesselType.WinginGround),
				entry(30, VesselType.Fishing),
				entry(31, VesselType.Tug),
				entry(32, VesselType.RescueSalvageShip),
				entry(33, VesselType.Dredger),
				entry(34, VesselType.DivingShip),
				entry(35, VesselType.NavalMilitaryShip),
				entry(36, VesselType.SailingVessel),
				entry(37, VesselType.YachtPleasureCraft),
				entry(40, VesselType.HighSpeedCraft),
				entry(41, VesselType.HighSpeedCraft),
				entry(42, VesselType.HighSpeedCraft),
				entry(43, VesselType.HighSpeedCraft),
				entry(44, VesselType.HighSpeedCraft),
				entry(50, VesselType.PilotShip),
				entry(51, VesselType.RescueSalvageShip),
				entry(52, VesselType.Tug),
				entry(53, VesselType.SupplyShip),
				entry(54, VesselType.AntiPollution),
				entry(55, VesselType.PortPoliceLawEnforce),
				entry(56, VesselType.Other),
				entry(57, VesselType.Other),
				entry(58, VesselType.GeneralCargo),
				entry(59, VesselType.Other),
				entry(60, VesselType.Passenger),
				entry(61, VesselType.Passenger),
				entry(62, VesselType.Passenger),
				entry(63, VesselType.Passenger),
				entry(64, VesselType.Passenger),
				entry(65, VesselType.Passenger),
				entry(66, VesselType.Passenger),
				entry(67, VesselType.Passenger),
				entry(68, VesselType.Passenger),
				entry(69, VesselType.Passenger),
				entry(70, VesselType.GeneralCargo),
				entry(71, VesselType.GeneralCargo),
				entry(72, VesselType.GeneralCargo),
				entry(73, VesselType.GeneralCargo),
				entry(74, VesselType.GeneralCargo),
				entry(75, VesselType.GeneralCargo),
				entry(76, VesselType.GeneralCargo),
				entry(77, VesselType.GeneralCargo),
				entry(78, VesselType.GeneralCargo),
				entry(79, VesselType.GeneralCargo),
				entry(80, VesselType.Tanker),
				entry(81, VesselType.Tanker),
				entry(82, VesselType.Tanker),
				entry(83, VesselType.Tanker),
				entry(84, VesselType.Tanker),
				entry(85, VesselType.Tanker),
				entry(86, VesselType.Tanker),
				entry(87, VesselType.Tanker),
				entry(88, VesselType.Tanker),
				entry(89, VesselType.Tanker),
				entry(90, VesselType.Other),
				entry(91, VesselType.Other),
				entry(92, VesselType.Other),
				entry(93, VesselType.Other),
				entry(94, VesselType.Other),
				entry(95, VesselType.Other),
				entry(96, VesselType.Other),
				entry(97, VesselType.Other),
				entry(98, VesselType.Other),
				entry(99, VesselType.Other))
				.collect(entriesToMap()));
	}
}