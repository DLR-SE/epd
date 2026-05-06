package de.emir.epd.routemanager.test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.GraphicsEnvironment;

import org.apache.logging.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import de.emir.epd.routemanager.cmd.CopyXMLRouteCommand;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class CopyXMLRouteCommandNoSelectionTest{
	private static final Logger LOG = ULog.getLogger(CopyXMLRouteCommandNoSelectionTest.class);

	Route mDummyRoute;
	
	@Before
	public void setup() {
		if (GraphicsEnvironment.isHeadless()) {
			LOG.debug("Running in Headless-Mode, no clipboard available!");
			return;
		}
		try {
			try {
				PlatformUtil.initBasicManagers();				
			}
			catch (UnsupportedOperationException e) {
				LOG.debug("BasicManagers already initialized");
			}	
			CopyXMLRouteCommandTestHelper.clearSelections();
			CopyXMLRouteCommandTestHelper.clearClipboard();
			assertTrue(CopyXMLRouteCommandTestHelper.isClipboardEmpty());
			assertTrue(CopyXMLRouteCommandTestHelper.isSelectionEmpty());
			assertTrue(CopyXMLRouteCommandTestHelper.isClipboardEmpty());
			assertFalse(CopyXMLRouteCommandTestHelper.didSelectRoute());			
		}
		catch (java.awt.HeadlessException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testCopyRouteNoSelection() {
		Assume.assumeFalse(GraphicsEnvironment.isHeadless());
		try {
			//  Verify no copy to clipboard is done, since no route is selected
			CopyXMLRouteCommand cpRouteCmd = new CopyXMLRouteCommand();
			cpRouteCmd.execute();
			assertTrue(CopyXMLRouteCommandTestHelper.isClipboardEmpty());			
		}
		catch (java.awt.HeadlessException e) {
			e.printStackTrace();
		}
	}
}
