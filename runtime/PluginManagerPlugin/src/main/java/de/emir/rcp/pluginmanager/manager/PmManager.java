package de.emir.rcp.pluginmanager.manager;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.maven.model.Model;
import org.xml.sax.SAXException;

import de.emir.rcp.manager.ArgumentsManager;
import de.emir.rcp.manager.WindowManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.pluginmanager.model.DependencyData;
import de.emir.rcp.pluginmanager.model.DependencyState;
import de.emir.rcp.pluginmanager.model.PmModelProvider;
import de.emir.rcp.pluginmanager.views.dialogs.SelectProductFileDialog;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import de.emir.tuml.ucore.runtime.extension.IService;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

/**
 * @author Florian
 * 
 *         The PluginManager Manager
 */
public class PmManager implements IService {

    private Map<ObservableDependency, DependencyData> depDataMap = new HashMap<>();
    private PublishSubject<Map<ObservableDependency, DependencyData>> depDataMapSubject = PublishSubject.create();

    public Disposable subscribeDependencyData(Consumer<Map<ObservableDependency, DependencyData>> c) {
        return depDataMapSubject.subscribe(c);
    }

    public DependencyData getDependencyData(ObservableDependency d) {

        if (depDataMap.containsKey(d) == true) {
            return depDataMap.get(d);
        }

        DependencyData dd = new DependencyData(d);
        depDataMap.put(d, dd);
        depDataMapSubject.onNext(depDataMap);

        return dd;

    }

    public void resetDependencyData() {
        for (DependencyData dd : depDataMap.values()) {
            dd.setState(DependencyState.UNKNOWN);
        }
    }

    public void loadProductFile() {

        PmModelProvider pmp = getModelProvider();
        if (pmp == null)
            return;
        pmp.subscribeProductFile(new ProductFileChangedConsumer());

        // Highest Priority: Commandline
        ProductFile pf = getProductFileFromCommandline();

        if (pf != null) {
            pmp.setProductFile(pf);
            return;
        }

        // If commandline arg not set get last file from props
        pf = getLastProductFile();

        if (pf != null) {
            pmp.setProductFile(pf);
            return;
        }

        // Ask the user
        while (pf == null) {
            pf = getProductFileByUserDialog(null);

            if (pf == null) {
                showInvalidProductFileDialog();
            }

        }

        pmp.setProductFile(pf);

    }

    public PmModelProvider getModelProvider() {

        AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();

        if (mp instanceof PmModelProvider == false) {
            // throw new IllegalStateException("ModelProvider has to be of type " + PmModelProvider.class);
            return null;
        }

        return (PmModelProvider) mp;

    }

    public void showProductFileChangeDialog() {

        String currentPath = null;
        PmModelProvider pmp = getModelProvider();

        ProductFile productFile = pmp.getProductFile();

        if (productFile != null) {
            currentPath = productFile.getFile().getAbsolutePath();
        }

        ProductFile pf = getProductFileByUserDialog(currentPath);

        if (pf != null) {

            pmp.setProductFile(pf);

        }

    }

    private ProductFile getProductFileFromCommandline() {

        ArgumentsManager am = PlatformUtil.getArgumentsManager();
        String productPath = am.getArgumentValue(PMBasics.EDIT_ARG);

        if (productPath == null) {
            return null;
        }

        if (productPath.endsWith(PMBasics.PRODUCT_FILE_NAME) == false) {

            showInvalidProductArgumentDialog(productPath);
            return null;
        }

        File file = new File(productPath);

        // Also checks if it exists
        if (file.isFile() == false) {

            showInvalidProductArgumentDialog(productPath);
            return null;
        }

        ProductFile pf = null;

        try {

            pf = new ProductFile(file);

        } catch (SAXException | IOException | ParserConfigurationException e) {

            showInvalidProductArgumentDialog(productPath);
            return null;

        }

        return pf;

    }

    private ProductFile getProductFileByUserDialog(String currentFilePath) {

        WindowManager wm = PlatformUtil.getWindowManager();
        MainWindow mw = wm.getMainWindow();

        Window parentWindow = SwingUtilities.windowForComponent(mw);

        PmModelProvider pmp = getModelProvider();
        ProductFile productFile = pmp.getProductFile();

        SelectProductFileDialog dialog = new SelectProductFileDialog(parentWindow, productFile == null);

        dialog.setCurrentFile(currentFilePath);
        dialog.setModal(true);
        dialog.setVisible(true);

        String result = dialog.getResult();

        if (result == null) {
            return null;
        }

        File f = new File(result);
        ProductFile pf = null;

        try {
            pf = new ProductFile(f);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }

        return pf;
    }

    private ProductFile getLastProductFile() {

        PropertyContext ctx = PropertyStore.getContext(PMBasics.PM_PROP_CTX);

        String lastPath = ctx.getValue(PMBasics.PM_PROP_LAST_PRODUCT_PATH, null);

        if (lastPath == null) {
            return null;
        }

        File f = new File(lastPath);

        if (f.isFile() == false) {
            return null;
        }

        ProductFile pf = null;

        try {

            pf = new ProductFile(f);

        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }

        return pf;

    }

    private void showInvalidProductArgumentDialog(String argument) {

        MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();

        String message = "The specified file\n" + argument + "\ndoes not exist or is not a valid Product.xml\nUse "
                + PMBasics.EDIT_ARG + " <AbsolutePathTo\\" + PMBasics.PRODUCT_FILE_NAME + ">";

        JOptionPane.showMessageDialog(mw, message, "Invalid Product File", JOptionPane.ERROR_MESSAGE);

    }

    private void showInvalidProductFileDialog() {

        MainWindow mw = PlatformUtil.getWindowManager().getMainWindow();

        String message = "The specified file is not a valid Product.xml\nYou have to choose a valid file in order to use this application.";

        JOptionPane.showMessageDialog(mw, message, "Invalid Product File", JOptionPane.ERROR_MESSAGE);

    }

    
    private Map<File, Model>		mKnownWorkspaceModels = new HashMap<>();
    
	public Model getWorkspaceModel(File file) {
		if (file == null || file.exists() == false)
			return null;
		try {
			File cf = file.getCanonicalFile();
			if (mKnownWorkspaceModels.containsKey(cf))
				return mKnownWorkspaceModels.get(cf);
			Model m = MavenUtil.readModel(cf);
			mKnownWorkspaceModels.put(cf, m);
			return m;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
