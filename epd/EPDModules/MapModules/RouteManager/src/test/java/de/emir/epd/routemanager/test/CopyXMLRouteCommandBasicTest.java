package de.emir.epd.routemanager.test;

import static org.junit.Assert.assertNotNull;
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
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;


/*
 * Test of normal behavior of copy xml route command.
 */
public class CopyXMLRouteCommandBasicTest{
	private static final Logger LOG = ULog.getLogger(CopyXMLRouteCommandBasicTest.class);

	Route mDummyRoute;

	@Before
	public void setup() {
		if (GraphicsEnvironment.isHeadless()) {
			LOG.debug("Running in Headless-Mode, no clipboard available!");
			return;
		}
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
		mDummyRoute = CopyXMLRouteCommandTestHelper.selectSingleRoute();
		assertTrue(CopyXMLRouteCommandTestHelper.didSelectRoute());
		assertNotNull(mDummyRoute);
	}
	
	@Test
	public void testCopyRouteBasic() {
		Assume.assumeFalse(GraphicsEnvironment.isHeadless());

		//  Copy dummy route to clipboard:
		CopyXMLRouteCommand copyRouteCommand = new CopyXMLRouteCommand();
		copyRouteCommand.execute();
		
		Object obj = CopyXMLRouteCommandTestHelper.readObjectFromClipboard();
		assertTrue(obj instanceof Route );
		
		Route clipboardRoute = (Route) obj;
		assertNotNull(clipboardRoute);
		assertTrue(UCoreUtils.equals(mDummyRoute, clipboardRoute));			
	}
}
