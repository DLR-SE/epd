/**
 * 
 */
package de.emir.epd.routemanager.cmd.popup;

import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import org.apache.logging.log4j.Logger;

import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.rcp.ui.wizard.AbstractWizard;
import de.emir.rcp.ui.wizard.AbstractWizardPage;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * ImportRouteClipboardCmdWizard is a wizard to select the import target for the route object in clipboard.
 * Wrapper for ImportRouteClipboardCmdPage, where the actual selection of the target happens. (-> method getInitialPages)
 * Executes actual import of route into selected target. (-> method onFinish)
 */
public class ImportRouteClipboardCmdWizard extends AbstractWizard {
	private static final Logger LOG = ULog.getLogger(ImportRouteClipboardCmdWizard.class);

	private List<IRouteAccessModel> mRouteAccessModels;
	private IRouteAccessModel mImportTarget;
	private Route mImportRoute;
	private String mClipboardText = "";
	private ImportRouteClipboardCmdPage mImportRouteClipboardCmdPage;

	public ImportRouteClipboardCmdWizard(JFrame parent, List<IRouteAccessModel> routeAccessModels, Route importRoute,
			String clipboardText) {
		super(parent, "Import Model From Clipboard Wizard");
		assert (routeAccessModels != null);
		assert (routeAccessModels.size() > 0);
		assert (importRoute != null);

		mRouteAccessModels = routeAccessModels;
		mImportRoute = importRoute;
		mClipboardText = clipboardText;
	}

	@Override
	public List<AbstractWizardPage> getInitialPages() {
		return Arrays.asList(mImportRouteClipboardCmdPage = new ImportRouteClipboardCmdPage(this, mRouteAccessModels,
				mClipboardText));
	}

	@Override
	public void onFinish() {
		mImportTarget = mImportRouteClipboardCmdPage.getSelectedTarget();
		importRouteAndCloseWizard();
	}
	
	private void importRouteAndCloseWizard() {
		mImportTarget.assignRoute(mImportRoute);
		close();
	}

}
