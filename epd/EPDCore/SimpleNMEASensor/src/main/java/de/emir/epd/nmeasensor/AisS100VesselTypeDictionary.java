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
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types.ShipType;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
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

	protected static Map<ShipType, VesselType> map() {
		return Collections.unmodifiableMap(Stream.of(
				entry(ShipType.NotAvailable, VesselType.Other),
				entry(ShipType.WingInGround, VesselType.WinginGround),
				entry(ShipType.WingInGroundHazardousA, VesselType.WinginGround),
				entry(ShipType.WingInGroundHazardousB, VesselType.WinginGround),
				entry(ShipType.WingInGroundHazardousC, VesselType.WinginGround),
				entry(ShipType.WingInGroundHazardousD, VesselType.WinginGround),
				entry(ShipType.Fishing, VesselType.Fishing),
				entry(ShipType.Towing, VesselType.Tug),
				entry(ShipType.LargeTowing, VesselType.RescueSalvageShip),
				entry(ShipType.DredgingOrUnderwaterOps, VesselType.Dredger),
				entry(ShipType.DivingOps, VesselType.DivingShip),
				entry(ShipType.MilitaryOps, VesselType.NavalMilitaryShip),
				entry(ShipType.Sailing, VesselType.SailingVessel),
				entry(ShipType.PleasureCraft, VesselType.YachtPleasureCraft),
				entry(ShipType.HighSpeedCraft, VesselType.HighSpeedCraft),
				entry(ShipType.HighSpeedCraftHarzardousA, VesselType.HighSpeedCraft),
				entry(ShipType.HighSpeedCraftHarzardousB, VesselType.HighSpeedCraft),
				entry(ShipType.HighSpeedCraftHarzardousC, VesselType.HighSpeedCraft),
				entry(ShipType.HighSpeedCraftHarzardousD, VesselType.HighSpeedCraft),
				entry(ShipType.PilotVessel, VesselType.PilotShip),
				entry(ShipType.SearchAndRescueVessel, VesselType.RescueSalvageShip),
				entry(ShipType.Tug, VesselType.Tug),
				entry(ShipType.PortTender, VesselType.SupplyShip),
				entry(ShipType.AntiPollutionEquipment, VesselType.AntiPollution),
				entry(ShipType.LawEnforcement, VesselType.PortPoliceLawEnforce),
				entry(ShipType.SpareLocalVessel1, VesselType.Other),
				entry(ShipType.SpareLocalVessel2, VesselType.Other),
				entry(ShipType.MedicalTransport, VesselType.GeneralCargo),
				entry(ShipType.ShipAccordingToRRResolutionNo18, VesselType.Other),
				entry(ShipType.Passenger, VesselType.Passenger),
				entry(ShipType.PassengerHazardousA, VesselType.Passenger),
				entry(ShipType.PassengerHazardousB, VesselType.Passenger),
				entry(ShipType.PassengerHazardousC, VesselType.Passenger),
				entry(ShipType.PassengerHazardousD, VesselType.Passenger),
				entry(ShipType.PassengerFuture1, VesselType.Passenger),
				entry(ShipType.PassengerFuture2, VesselType.Passenger),
				entry(ShipType.PassengerFuture3, VesselType.Passenger),
				entry(ShipType.PassengerFuture4, VesselType.Passenger),
				entry(ShipType.PassengerNoAdditionalInfo, VesselType.Passenger),
				entry(ShipType.Cargo, VesselType.GeneralCargo),
				entry(ShipType.CargoHazardousA, VesselType.GeneralCargo),
				entry(ShipType.CargoHazardousB, VesselType.GeneralCargo),
				entry(ShipType.CargoHazardousC, VesselType.GeneralCargo),
				entry(ShipType.CargoHazardousD, VesselType.GeneralCargo),
				entry(ShipType.CargoFuture1, VesselType.GeneralCargo),
				entry(ShipType.CargoFuture2, VesselType.GeneralCargo),
				entry(ShipType.CargoFuture3, VesselType.GeneralCargo),
				entry(ShipType.CargoFuture4, VesselType.GeneralCargo),
				entry(ShipType.CargoNoAdditionalInfo, VesselType.GeneralCargo),
				entry(ShipType.Tanker, VesselType.Tanker),
				entry(ShipType.TankerHazardousA, VesselType.Tanker),
				entry(ShipType.TankerHazardousB, VesselType.Tanker),
				entry(ShipType.TankerHazardousC, VesselType.Tanker),
				entry(ShipType.TankerHazardousD, VesselType.Tanker),
				entry(ShipType.TankerFuture1, VesselType.Tanker),
				entry(ShipType.TankerFuture2, VesselType.Tanker),
				entry(ShipType.TankerFuture3, VesselType.Tanker),
				entry(ShipType.TankerFuture4, VesselType.Tanker),
				entry(ShipType.TankerNoAdditionalInfo, VesselType.Tanker),
				entry(ShipType.Other, VesselType.Other),
				entry(ShipType.OtherHazardousA, VesselType.Other),
				entry(ShipType.OtherHazardousB, VesselType.Other),
				entry(ShipType.OtherHazardousC, VesselType.Other),
				entry(ShipType.OtherHazardousD, VesselType.Other),
				entry(ShipType.OtherFuture1, VesselType.Other),
				entry(ShipType.OtherFuture2, VesselType.Other),
				entry(ShipType.OtherFuture3, VesselType.Other),
				entry(ShipType.OtherFuture4, VesselType.Other),
				entry(ShipType.OtherNoAdditionalInfo, VesselType.Other))
				.collect(entriesToMap()));
	}
}