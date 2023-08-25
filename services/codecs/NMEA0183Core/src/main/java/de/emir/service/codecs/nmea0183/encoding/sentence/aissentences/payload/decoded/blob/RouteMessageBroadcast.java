package de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.blob;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryptionUtil;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.BinaryBroadcastMessage;

/**
 * 
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RouteMessageBroadcast extends BinaryBroadcastMessage {
	public class IntermediateLeg {
		Double latitude;
		Double longitude;
		Boolean isOrthodrome;
		Float speed;
		Float radius;

		/**
		 * @return the latitude
		 */
		public Double getLatitude() {
			return latitude;
		}

		/**
		 * @param latitude
		 *            the latitude to set
		 */
		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}

		/**
		 * @return the longitude
		 */
		public Double getLongitude() {
			return longitude;
		}

		/**
		 * @param longitude
		 *            the longitude to set
		 */
		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}

		/**
		 * @return the isOrthodrome
		 */
		public Boolean getIsOrthodrome() {
			return isOrthodrome;
		}

		/**
		 * @param isOrthodrome
		 *            the isOrthodrome to set
		 */
		public void setIsOrthodrome(Boolean isOrthodrome) {
			this.isOrthodrome = isOrthodrome;
		}

		/**
		 * @return the speed
		 */
		public Float getSpeed() {
			return speed;
		}

		/**
		 * @param speed
		 *            the speed to set
		 */
		public void setSpeed(Float speed) {
			this.speed = speed;
		}

		/**
		 * @return the radius
		 */
		public Float getRadius() {
			return radius;
		}

		/**
		 * @param radius
		 *            the radius to set
		 */
		public void setRadius(Float radius) {
			this.radius = radius;
		}

		public IntermediateLeg(Boolean isOrthodrome, Float speed, Float radius, Double longitude, Double latitude) {
			super();
			this.isOrthodrome = isOrthodrome;
			this.speed = speed;
			this.radius = radius;
			this.longitude = longitude;
			this.latitude = latitude;
		}

		public IntermediateLeg() {

		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("IntermediateLeg [isOrthodrome=").append(isOrthodrome).append(", speed=").append(speed)
					.append(", radius=").append(radius).append(", longitude=").append(longitude).append(", latitude=")
					.append(latitude).append("]");
			return builder.toString();
		}

		public void fillMap(Map<String, Object> res) {
			if (isOrthodrome != null)
				res.put("isOrthodrome", isOrthodrome);
			if (speed != null)
				res.put("speed", speed);
			if (radius != null)
				res.put("radius", radius);
			if (longitude != null)
				res.put("longitude", longitude);
			if (latitude != null)
				res.put("latitude", latitude);
		}
	}

	public enum SteeringType {
		Manual(0), HeadingControl(1), TrackControl(2), ReservedForFutureUse(3);

		private final Integer type;

		SteeringType(Integer type) {
			this.type = type;
		}

		public Integer getType() {
			return type;
		}

		public String getValue() {
			return toString();
		}

		public static SteeringType fromInteger(Integer integer) {
			if (integer != null) {
				for (SteeringType b : SteeringType.values()) {
					if (integer.equals(b.type)) {
						return b;
					}
				}
			}
			return null;
		}
	}

	Boolean startOfRoute;
	Double latitude;
	Double longitude;
	SteeringType steeringType;
	IntermediateLeg[] intermediateLegs;

	public RouteMessageBroadcast(BinaryBroadcastMessage original, String binaryData) {
		super(original);
		if (binaryData == null || binaryData.length() <= 8)
			return;
		startOfRoute = AISPayloadEncryptionUtil.convertToBoolean(binaryData.substring(0, 1));
		String lonBits = binaryData.substring(1, 29);
		String latBits = binaryData.substring(29, 56);
		longitude = AISPayloadEncryptionUtil.convertToDouble(lonBits) / 600000f;
		latitude = AISPayloadEncryptionUtil.convertToDouble(latBits) / 600000f;
		int numWaypoints = (binaryData.length() - 66) / 64;
		int index = 56;
		double lastLat = latitude;
		double lastLon = longitude;
		intermediateLegs = new IntermediateLeg[numWaypoints + 1];
		for (int i = 0; i <= numWaypoints; i++) {
			IntermediateLeg leg = new IntermediateLeg();
			String legBits = binaryData.substring(index, index + (i == numWaypoints ? 66 : 64));
			leg.isOrthodrome = AISPayloadEncryptionUtil.convertToBoolean(legBits.substring(0, 1));
			leg.speed = AISPayloadEncryptionUtil.convertToFloat(legBits.substring(1, 11)) / 10f;
			leg.radius = null;

			if (i == numWaypoints) {
				lastLon = AISPayloadEncryptionUtil.convertToDouble(legBits.substring(11, 39)) / 600000f;
				lastLat = AISPayloadEncryptionUtil.convertToDouble(legBits.substring(39, 66)) / 600000f;

			} else {
				leg.radius = AISPayloadEncryptionUtil.convertToFloat(legBits.substring(11, 20)) / 100f;
				double diffLon = AISPayloadEncryptionUtil.convertToDouble(legBits.substring(20, 42)) / 600000f;
				double diffLat = AISPayloadEncryptionUtil.convertToDouble(legBits.substring(42, 64)) / 600000f;
				lastLat = lastLat + diffLat;
				lastLon = lastLon + diffLon;
			}
			leg.latitude = lastLat;
			leg.longitude = lastLon;
			intermediateLegs[i] = leg;
			index += (i == numWaypoints ? 66 : 64);
		}
		steeringType = SteeringType
				.fromInteger(AISPayloadEncryptionUtil.convertToUnsignedInteger(binaryData.substring(index, index + 2)));
	}

	public String toBinaryData() {
		String result = null;
		String bitString = "";

		if (getStartOfRoute() == null || getLongitude() == null || getLatitude() == null) {
			return "00000000";
		}

		bitString += getStartOfRoute() ? "1" : "0";
		double longitude = getLongitude().doubleValue() * 600000f;
		bitString += AISPayloadEncryptionUtil.fillBits(Integer.toBinaryString((int) Math.abs(longitude)), 28,
				longitude < 0);
		double latitude = getLatitude().doubleValue() * 600000f;
		bitString += AISPayloadEncryptionUtil.fillBits(Integer.toBinaryString((int) Math.abs(latitude)), 27,
				latitude < 0);

		double lastLonBase = getLongitude().doubleValue();
		double lastLatBase = getLatitude().doubleValue();

		if (intermediateLegs != null && intermediateLegs.length > 0) {
			int numWaypoints = intermediateLegs.length - 1;
			for (int i = 0; i < numWaypoints; i++) {
				IntermediateLeg leg = intermediateLegs[i];
				bitString += leg.isOrthodrome ? "1" : "0";
				bitString += AISPayloadEncryptionUtil
						.fillBits(Integer.toBinaryString((int) (leg.getSpeed().floatValue() * 10f)), 10);
				bitString += AISPayloadEncryptionUtil
						.fillBits(Integer.toBinaryString((int) (leg.getRadius().floatValue() * 100f)), 9);

				double lonDiff = (leg.getLongitude().doubleValue() - lastLonBase) * 600000f;
				bitString += AISPayloadEncryptionUtil.fillBits(Integer.toBinaryString((int) Math.abs(lonDiff)), 22,
						lonDiff < 0);
				double latDiff = (leg.getLatitude().doubleValue() - lastLatBase) * 600000f;
				bitString += AISPayloadEncryptionUtil.fillBits(Integer.toBinaryString((int) Math.abs(latDiff)), 22,
						latDiff < 0);
				lastLonBase = leg.getLongitude().doubleValue();
				lastLatBase = leg.getLatitude().doubleValue();
			}
			IntermediateLeg leg = intermediateLegs[numWaypoints];
			bitString += leg.isOrthodrome ? "1" : "0";
			bitString += AISPayloadEncryptionUtil
					.fillBits(Integer.toBinaryString((int) (leg.getSpeed().floatValue() * 10f)), 10);
			longitude = getLongitude().doubleValue() * 600000f;
			bitString += AISPayloadEncryptionUtil.fillBits(Integer.toBinaryString((int) Math.abs(longitude)), 28,
					longitude < 0);
			latitude = getLatitude().doubleValue() * 600000f;
			bitString += AISPayloadEncryptionUtil.fillBits(Integer.toBinaryString((int) Math.abs(latitude)), 27,
					latitude < 0);
			bitString += AISPayloadEncryptionUtil.fillBits(Integer.toBinaryString((int) getSteeringType().getType()),
					2);

			// int padding = 426 - bitString.length();
			int padding = 4;
			for (int i = 0; i < padding; i++) {
				bitString += "0";
			}

		}
		result = bitString;
		return result;
	}
	
	public RouteMessageBroadcast(String binaryData) {
		this(null, binaryData);
	}

	public RouteMessageBroadcast() {
		this(null, null);
	}

	/**
	 * @return the startOfRoute
	 */
	public Boolean getStartOfRoute() {
		return startOfRoute;
	}

	/**
	 * @param startOfRoute
	 *            the startOfRoute to set
	 */
	public void setStartOfRoute(Boolean startOfRoute) {
		this.startOfRoute = startOfRoute;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the steeringType
	 */
	public SteeringType getSteeringType() {
		return steeringType;
	}

	/**
	 * @param steeringType
	 *            the steeringType to set
	 */
	public void setSteeringType(SteeringType steeringType) {
		this.steeringType = steeringType;
	}

	/**
	 * @return the intermediateLegs
	 */
	public IntermediateLeg[] getIntermediateLegs() {
		return intermediateLegs;
	}

	/**
	 * @param intermediateLegs
	 *            the intermediateLegs to set
	 */
	public void setIntermediateLegs(IntermediateLeg[] intermediateLegs) {
		this.intermediateLegs = intermediateLegs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RouteMessageBroadcast [startOfRoute=").append(startOfRoute).append(", longitude=")
				.append(longitude).append(", latitude=").append(latitude);
		if (intermediateLegs != null && intermediateLegs.length > 0) {
			int numWaypoints = intermediateLegs.length;
			builder.append(", intermediateLegs=");
			for (int i = 0; i < numWaypoints; i++) {
				IntermediateLeg leg = intermediateLegs[i];
				builder.append(i > 0 ? ", " : "").append(leg.toString());
			}
		} else {
			builder.append(", intermediateLegs=null");
		}
		builder.append(", steeringType=").append(steeringType).append("]");
		return builder.toString();
	}

	public void fillMap(Map<String, Object> res) {
		if (startOfRoute != null)
			res.put("startOfRoute", startOfRoute);
		if (longitude != null)
			res.put("longitude", longitude);
		if (latitude != null)
			res.put("latitude", latitude);
		if (intermediateLegs != null && intermediateLegs.length > 0) {
			int numWaypoints = intermediateLegs.length;
			for (int i = 0; i < numWaypoints; i++) {
				IntermediateLeg leg = intermediateLegs[i];
				res.put("intermedieateLegs[" + i + "]", leg);
			}
		}
		if (steeringType != null)
			res.put("steeringType", steeringType);
	}
}
