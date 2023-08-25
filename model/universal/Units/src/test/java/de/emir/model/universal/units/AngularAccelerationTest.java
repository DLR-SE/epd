package de.emir.model.universal.units;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.emir.model.universal.UnitsModel;
import de.emir.model.universal.units.impl.AngularAccelerationImpl;
import de.emir.model.universal.units.impl.TimeImpl;

public class AngularAccelerationTest {

	static {
		UnitsModel.init();
	}
	public static void main(String[] args){
		new AngularAccelerationTest().testIntegrateOverTime();
	}
	@Test
	public void testIntegrateOverTime() {
		AngularAcceleration acc = new AngularAccelerationImpl(200, AngularAccelerationUnit.ROUNDS_PER_SQUARE_SECOND);
		
		AngularSpeed angSpeed = acc.getDeltaSpeedOverTime(new TimeImpl(1, TimeUnit.SECOND));
		assertEquals(angSpeed.getValue(), 200.0, 0.00001);
		assertEquals(acc.getDeltaSpeedOverTime(new TimeImpl(0.1, TimeUnit.SECOND)).getValue(), 20.0, 0.00001);
		assertEquals(acc.getDeltaSpeedOverTime(new TimeImpl(10, TimeUnit.SECOND)).getValue(), 2000.0, 0.00001);
	}
}
