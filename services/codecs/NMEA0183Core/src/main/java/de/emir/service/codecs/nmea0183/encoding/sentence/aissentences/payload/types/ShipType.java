package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.types;

public enum ShipType {
	NotAvailable(0),
    ReservedForFutureUse1(1),
    ReservedForFutureUse2(2),
    ReservedForFutureUse3(3),
    ReservedForFutureUse4(4),
    ReservedForFutureUse5(5),
    ReservedForFutureUse6(6),
    ReservedForFutureUse7(7),
    ReservedForFutureUse8(8),
    ReservedForFutureUse9(9),
    ReservedForFutureUse10(10),
    ReservedForFutureUse11(11),
    ReservedForFutureUse12(12),
    ReservedForFutureUse13(13),
    ReservedForFutureUse14(14),
    ReservedForFutureUse15(15),
    ReservedForFutureUse16(16),
    ReservedForFutureUse17(17),
    ReservedForFutureUse18(18),
    ReservedForFutureUse19(19),
	WingInGround(20),
	WingInGroundHazardousA(21),
	WingInGroundHazardousB(22),
	WingInGroundHazardousC(23),
	WingInGroundHazardousD(24),
    WingInGroundFuture1(25),
	WingInGroundFuture2(26),
	WingInGroundFuture3(27),
	WingInGroundFuture4(28),
    WingInGroundFuture5(29),
	Fishing(30),
	Towing(31),
	LargeTowing(32),
	DredgingOrUnderwaterOps(33),
	DivingOps(34),
	MilitaryOps(35),
	Sailing(36),
	PleasureCraft(37),
    Reserved1(38),
    Reserved2(39),
	HighSpeedCraft(40),
	HighSpeedCraftHarzardousA(41),
	HighSpeedCraftHarzardousB(42),
	HighSpeedCraftHarzardousC(43),
	HighSpeedCraftHarzardousD(44),
    HighSpeedCraftFuture1(45),
    HighSpeedCraftFuture2(46),
    HighSpeedCraftFuture3(47),
    HighSpeedCraftFuture4(48),
    HighSpeedCraftNoAdditionalInfo(49),
	PilotVessel(50),
	SearchAndRescueVessel(51),
	Tug(52),
	PortTender(53),
	AntiPollutionEquipment(54),
	LawEnforcement(55),
	SpareLocalVessel1(56),
	SpareLocalVessel2(57),
	MedicalTransport(58),
	ShipAccordingToRRResolutionNo18(59),
	Passenger(60),
	PassengerHazardousA(61),
	PassengerHazardousB(62),
	PassengerHazardousC(63),
	PassengerHazardousD(64),
	PassengerFuture1(65),
	PassengerFuture2(66),
	PassengerFuture3(67),
	PassengerFuture4(68),
	PassengerNoAdditionalInfo(69),
	Cargo(70),
	CargoHazardousA(71),
	CargoHazardousB(72),
	CargoHazardousC(73),
	CargoHazardousD(74),
	CargoFuture1(75),
	CargoFuture2(76),
	CargoFuture3(77),
	CargoFuture4(78),
	CargoNoAdditionalInfo(79),
	Tanker(80),
	TankerHazardousA(81),
	TankerHazardousB(82),
	TankerHazardousC(83),
	TankerHazardousD(84),
	TankerFuture1(85),
	TankerFuture2(86),
	TankerFuture3(87),
	TankerFuture4(88),
	TankerNoAdditionalInfo(89),
	Other(90),
	OtherHazardousA(91),
	OtherHazardousB(92),
	OtherHazardousC(93),
	OtherHazardousD(94),
	OtherFuture1(95),
	OtherFuture2(96),
	OtherFuture3(97),
	OtherFuture4(98),
	OtherNoAdditionalInfo(99);
	
	private final Integer type;

	ShipType(Integer type) {
		this.type = type;
	}
	
	public Integer getType() {
		return type;
	}

	public String getValue() {
	    return toString();
	}

	public static ShipType fromInteger(Integer integer) {
		if (integer != null) {
            if (integer > 99 || integer < 0) return ShipType.NotAvailable;
			for (ShipType b : ShipType.values()) {
				if (integer.equals(b.type)) {
					return b;
				}
			}
		}
		return null;
	}
}
