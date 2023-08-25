package de.emir.model.universal.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.emir.model.universal.math.impl.StringFunction1Impl;

public class StringFunction1Test {
	
	public static void main(String[] args) {
		new StringFunction1Test().test();
	}

	@Test
	public void test() {
		StringFunction1Impl strLin = new StringFunction1Impl();
		strLin.setDefinition("35 * x + 42");
		assertTrue(strLin.isValid());
		for (int i = 0; i < 10; i++) {
			double x = 13.9383 * i;
			double yExp = 35 * x + 42;
			double y = strLin.getValue(x);
			assertEquals(yExp, y, 0.00000001);
		}
		
		strLin.setDefinition("35 * y + 42"); //invalid, requires variable x or X
		assertFalse(strLin.isValid());
		double y = strLin.getValue(10); //shall return NaN
		assertFalse(y == y);
	}

}
