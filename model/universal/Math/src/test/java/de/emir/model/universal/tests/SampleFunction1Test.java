package de.emir.model.universal.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.emir.model.universal.math.impl.SampleFunction1Impl;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.math.BorderBehavior;
import de.emir.model.universal.math.SampleFunction1;

public class SampleFunction1Test {

	public static void main(String[] args) {
		new SampleFunction1Test().test();
	}
	@Test
	public void test() {
		SampleFunction1 func = new SampleFunction1Impl();
		func.getSamples().add(new Vector2DImpl(0, 0));
		func.getSamples().add(new Vector2DImpl(1, 10));
		func.getSamples().add(new Vector2DImpl(10, 100));
		func.getSamples().add(new Vector2DImpl(100, 1000));
		
		double epsilon = 0.000001;
		assertEquals(0, func.getValue(0), epsilon);
		assertEquals(10, func.getValue(1), epsilon);
		assertEquals(5, func.getValue(.5), epsilon);
		assertEquals(990, func.getValue(99), epsilon);		
		
		
		func.setBorderBehavior(BorderBehavior.EXCEPTION);
		try {
			func.getValue(-1);
			fail("Missing exception");
		}catch(Exception e) {}
		try {
			func.getValue(1000);
			fail("Missing Exception");
		}catch(Exception e) {}
		
		func.setBorderBehavior(BorderBehavior.MIN_MAX_VALUE);
		assertEquals(0, func.getValue(-1), epsilon);
		assertEquals(1000, func.getValue(122230), epsilon);
		
		func.setBorderBehavior(BorderBehavior.NaN);
		double r = func.getValue(-1);
		assertFalse(r == r);
		r = func.getValue(1000389);
		assertFalse(r == r);
		
		
	}

}
