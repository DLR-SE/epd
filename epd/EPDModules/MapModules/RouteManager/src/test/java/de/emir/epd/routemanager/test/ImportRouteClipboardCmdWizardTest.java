/**
 * 
 */
package de.emir.epd.routemanager.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.GraphicsEnvironment;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.epd.routemanager.cmd.ImportRouteClipboardCommand;
import de.emir.epd.routemanager.cmd.popup.ImportRouteClipboardCmdPage;
import de.emir.epd.routemanager.cmd.popup.ImportRouteClipboardCmdWizard;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.epd.routemanager.impl.RouteManager;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;

/**
 * 
 */
public class ImportRouteClipboardCmdWizardTest {
	private static final Logger LOG = ULog.getLogger(ImportRouteClipboardCmdWizardTest.class);
	
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

		String clipboardText = "Dummy Clipboard Content - not needed for test";
		Route testRoute = CopyXMLRouteCommandTestHelper.createDummyRouteObj();
		assertNotNull(testRoute);
		
		IRouteManager defaultRouteManager = ServiceManager.getByID(RouteManagerBasic.DEFAULT_ROUTE_MANAGER);
		assertNotNull(defaultRouteManager);
		ImportRouteClipboardCommand importRouteClipboardCmd= new ImportRouteClipboardCommand(defaultRouteManager);
		List<IRouteAccessModel> routeAMs = defaultRouteManager.getRouteModels();
		assertFalse(routeAMs.isEmpty());
		IRouteAccessModel selectedTarget = routeAMs.getFirst();
		assertNotNull(selectedTarget);
		
		// Class under test:
		ImportRouteClipboardCmdWizard importRouteClipboardCmdWizardUnderTest = new ImportRouteClipboardCmdWizard(
				PlatformUtil.getWindowManager().getActiveFrame(), routeAMs, testRoute, clipboardText);
		
		importRouteClipboardCmdWizardUnderTest.getInitialPages();
		// Test if targetSelectionPage is available:
		assertNotNull(importRouteClipboardCmdWizardUnderTest);
		try {
			Field wizard_mSelectTargetPage = importRouteClipboardCmdWizardUnderTest.getClass().getDeclaredField("mImportRouteClipboardCmdPage");
			wizard_mSelectTargetPage.setAccessible(true);
			ImportRouteClipboardCmdPage mSelectTargetPage = (ImportRouteClipboardCmdPage) wizard_mSelectTargetPage.get(importRouteClipboardCmdWizardUnderTest); // Cast to ImportRouteClipboardCmdPage allowed since mImportRouteClipboardCmdPage is a ImportRouteClipboardCmdPage
			assertNotNull(mSelectTargetPage);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			LOG.debug("Failed to get ImportRouteClipboardCmdPage");
			fail();
		}
		
		// Simulate target selection
		try {
			Field wizard_mSelectFeaturePage = importRouteClipboardCmdWizardUnderTest.getClass().getDeclaredField("mImportTarget");
			wizard_mSelectFeaturePage.setAccessible(true);
			wizard_mSelectFeaturePage.set(importRouteClipboardCmdWizardUnderTest, selectedTarget);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			LOG.debug("Failed to simulate target selection");
			fail();
		}
		// Test importing of route in selected (unassigned) IRouteMangager
		try {
			Method fn_importRouteAndCloseWizard = importRouteClipboardCmdWizardUnderTest.getClass().getDeclaredMethod("importRouteAndCloseWizard");
			fn_importRouteAndCloseWizard.setAccessible(true);
			fn_importRouteAndCloseWizard.invoke(importRouteClipboardCmdWizardUnderTest);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
			LOG.debug("Failed to import route in selected target");
			fail();
		}
		assertNotNull(selectedTarget);
		assertFalse(selectedTarget.getAllRoutes().isEmpty());
		// adjust name of testRoute, to match with imported object
		testRoute.setName("Copy_" + testRoute.getName());
		assertTrue(UCoreUtils.equals(testRoute, selectedTarget.getAllRoutes().getFirst()));
	}
}
