package de.emir.epd.routemanager.test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.GraphicsEnvironment;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.cmd.ImportRouteClipboardCommand;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.epd.routemanager.impl.RouteManager;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;

/**
 * Tests if ImportRouteClipboardCommand can get Route from clipboard.
 */
public class ImportRouteClipboardCommandTest {
	private static final Logger LOG = ULog.getLogger(ImportRouteClipboardCommandTest.class);

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
		IRouteManager defaultRouteManager = new RouteManager(RouteManagerBasic.DEFAULT_ROUTE_MANAGER,
				PlatformUtil.getModelManager().getModelProvider());
		ServiceManager.register(RouteManagerBasic.DEFAULT_ROUTE_MANAGER, defaultRouteManager);
		CopyXMLRouteCommandTestHelper.clearSelections();
		CopyXMLRouteCommandTestHelper.clearClipboard();
		assertTrue(CopyXMLRouteCommandTestHelper.isClipboardEmpty());
		assertTrue(CopyXMLRouteCommandTestHelper.isSelectionEmpty());;
	}
	
	@Test
	public void testMethodGetRouteFromClipboard() {
		// Tests method getRouteFromClipboard of ImportRouteClipboardCommand
		Assume.assumeFalse(GraphicsEnvironment.isHeadless());
		
		IRouteManager defaultRouteManager = ServiceManager.getByID(RouteManagerBasic.DEFAULT_ROUTE_MANAGER);
		assertNotNull(defaultRouteManager);
		ImportRouteClipboardCommand importCmdUnderTest = new ImportRouteClipboardCommand(defaultRouteManager);

		Route testRoute = CopyXMLRouteCommandTestHelper.createDummyRouteObj();
		assertNotNull(testRoute);

		// Copy Object to Clipboard
		CopyXMLRouteCommandTestHelper.writeXMLObjectToClipboard(testRoute);
		assertFalse(CopyXMLRouteCommandTestHelper.isClipboardEmpty());

		// Test create object from clipboard:
		try {
			Method fn_getRouteFromClipboard = importCmdUnderTest.getClass().getDeclaredMethod("getRouteFromClipboard");
			fn_getRouteFromClipboard.setAccessible(true);
			fn_getRouteFromClipboard.invoke(importCmdUnderTest);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			fail();
		}
		try {
			Field clipboardObject = importCmdUnderTest.getClass().getDeclaredField("mClipboardRoute");
			clipboardObject.setAccessible(true);
			// adjust name of testRoute, to match with imported object
			testRoute.setName("Copy_" + testRoute.getName());
			assertTrue(UCoreUtils.equals(testRoute, (UObject) clipboardObject.get(importCmdUnderTest))); // Cast to UObject allowed since mClipboardObject is a UObject
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			fail();
		}	
	}
}
