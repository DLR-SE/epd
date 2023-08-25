package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types;

public enum AidType {
	Unspecified (0),
	ReferencePoint	(1),
	RACON(2),
	FixedStructure(3),
	FutureUse1(4),
	LightWithoutSectors(5),
	LightWithSectors(6),
	LeadingLightFront(7),
	LeadingLightRear(8),
	BeaconCardinalNorth(9),
	BeaconCardinalEast(10),
	BeaconCardinalSouth(11),
	BeaconCardinalWest(12),
	BeaconPortHand(13),
	BeaconStarboardHand(14),
	BeaconPreferredChannelPortHand(15),
	BeaconPreferredChannelStarboardHand(16),
	BeaconIsolatedDanger(17),
	BeaconSafeWater(18),
	BeaconSpecialMark(19),
	CardinalMarkNorth(20),
	CardinalMarkEast(21),
	CardinalMarkSouth(22),
	CardinalMarkWest(23),
	PortHandMark(24),
	StarboardHandMark(25),
	PreferredChannelPortHand(26),
	PreferredChannelStarboardHand(27),
	IsolatedDanger(28),
	SafeWater(29),
	SpecialMark(30),
	LightVessel(31);
	
	private final Integer aidtype;

	AidType(Integer type) {
		this.aidtype = type;
	}
	
	public Integer getAidType() {
		return aidtype;
	}

	public String getValue() {
	    return toString();
	}

	public static AidType fromInteger(Integer integer) {
		if (integer != null) {
			for (AidType type : AidType.values()) {
				if (integer.equals(type.aidtype)) {
					return type;
				}
			}
		}
		return null;
	}
}
