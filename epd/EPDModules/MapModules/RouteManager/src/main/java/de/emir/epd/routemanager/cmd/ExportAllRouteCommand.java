package de.emir.epd.routemanager.cmd;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.epd.routemanager.util.RouteUtil;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class ExportAllRouteCommand extends AbstractCommand {
    private static Logger logger = LoggerFactory.getLogger(ExportRouteCommand.class);
    private File lastPath = null;

    private VoyageCharacteristic currentVC;
    private ITreeValueChangeListener modelListener;
	private IRouteManager mRouteManager;

    public ExportAllRouteCommand(IRouteManager rm) {
    	mRouteManager = rm;
    	rm.subscribeRouteChanges(consumer -> handleModelChanged());
    	rm.subscribeStructureChanges(consumer -> handleModelChanged());
    }

    private void handleCanExecute() {
    	setCanExecute(mRouteManager.getAllRoutes().isEmpty() == false);
    }

    private void handleModelChanged() {
    	handleCanExecute();
    }

    @Override
    public void execute() {
        for (Route route : currentVC.getRoutes()) {
            exportRoute(route);
        }
    }

    protected void exportRoute(Route route) {
        PropertyContext routeManagerContext = PropertyStore.getContext(RouteManagerBasic.ROUTE_MANAGER_PROPERTY_CTX);
        String lastPath = (String) routeManagerContext.getProperty("lastPath").getValue();
        if ("".equals(lastPath)) {
            lastPath = ResourceManager.get((String) null).getHomePath().resolve(".routes").toString();
            routeManagerContext.setValue("lastPath", lastPath);
        }

        JFileChooser fc = new JFileChooser(lastPath);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Simple route text format", "txt", "TXT"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Route plan exchange format", "rtz", "RTZ"));

        fc.setAcceptAllFileFilterUsed(true);

        if (fc.showSaveDialog(PlatformUtil.getWindowManager().getMainWindow()) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File file = fc.getSelectedFile();

        if (file.exists()) {
            if (JOptionPane.showConfirmDialog(PlatformUtil.getWindowManager().getMainWindow(), "File exists. Overwrite?", "Overwrite?",
                    JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                routeManagerContext.setValue("lastPath", fc.getCurrentDirectory().getAbsolutePath());
                execute();
                return;
            }
        }else {
            String fileExtension = FilenameUtils.getExtension(file.getAbsolutePath());
            if (fileExtension == null || fileExtension.trim().equals("")){
                FileFilter fileFilter = fc.getFileFilter();
                if (fileFilter instanceof  FileNameExtensionFilter){
                    FileNameExtensionFilter extensionFilter = (FileNameExtensionFilter) fileFilter;

                    String[] possibleExtensions = extensionFilter.getExtensions();
                    if (possibleExtensions == null || possibleExtensions.length == 0){
                        JOptionPane.showMessageDialog(PlatformUtil.getWindowManager().getMainWindow(), "Route save error", "Route not saved",
                                JOptionPane.ERROR_MESSAGE);
                        logger.error("Cannot save route, no extension is provided");
                    }else {
                        file = new File(file.getAbsolutePath() + "." + possibleExtensions[0]);
                    }

                }
            }
        }

        try {
            boolean bool = RouteUtil.saveRouteToFile(file, route);
            routeManagerContext.setValue("lastPath", fc.getCurrentDirectory().getAbsolutePath());
            if (bool) {
                throw new IOException();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(PlatformUtil.getWindowManager().getMainWindow(), "Route save error", "Route not saved",
                    JOptionPane.ERROR_MESSAGE);
            logger.error(e.getMessage(), e);
        }
    }

}
