package de.emir.epd.routemanager.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.GraphicsEnvironment;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import de.emir.epd.routemanager.cmd.CopyXMLRouteCommand;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;


public class CopyXMLRouteCommandMultipleSelectionTest{
	private static final Logger LOG = ULog.getLogger(CopyXMLRouteCommandMultipleSelectionTest.class);

	List<Route> mDummyRoutes;
	
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
		mDummyRoutes = CopyXMLRouteCommandTestHelper.selectMultipleRoutes();
		assertTrue(CopyXMLRouteCommandTestHelper.didSelectRoutes());
		assertTrue(mDummyRoutes.size() > 0);		
	}
	
	@Test
	public void testCopyRouteMultipleSelections() {
		Assume.assumeFalse(GraphicsEnvironment.isHeadless());

		//  Multiple routes are selected, test if only first route is copied to clipboard
		CopyXMLRouteCommand cpRouteCmd = new CopyXMLRouteCommand();
		cpRouteCmd.execute();
		Object obj = CopyXMLRouteCommandTestHelper.readObjectFromClipboard();
		assertTrue(obj instanceof Route );
		
		Route clipboardRoute = (Route) obj;
		assertNotNull(clipboardRoute);
		assertTrue(UCoreUtils.equals(mDummyRoutes.get(0), clipboardRoute));			
	}
}
