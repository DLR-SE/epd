package de.emir.epd.routemanager.cmd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.epd.routemanager.util.RouteUtil;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

/**
 * Imports a route fromo a file into the default group of the associated route manager (RouteManagerBasic.UNASIGNED_ROUTE_GROUP)
 * @author sschweigert
 *
 */
public class ImportRouteCommand extends AbstractCommand {
    //TODO use propertystore
    private File lastPath = null;
	private IRouteManager mRouteManager;


    public ImportRouteCommand(IRouteManager rm) {
    	mRouteManager = rm;
    }

    @Override
    public void execute() {
        // Get filename from dialog
        String path = lastPath != null ? lastPath.getAbsolutePath()
                : ResourceManager.get((String) null).getHomePath().resolve("routes").toString();
        JFileChooser fc = new JFileChooser(path);
        for (FileFilter fileFilter : getRouteFormatFilters()) {
            fc.addChoosableFileFilter(fileFilter);
        }
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(true);

        fc.setAcceptAllFileFilterUsed(true);

        if (fc.showOpenDialog(PlatformUtil.getWindowManager().getMainWindow()) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        for (File file : fc.getSelectedFiles()) {
            Route route = RouteUtil.loadRouteFromFile(file);
            route.setName(file.getName());

            IRouteAccessModel ram = mRouteManager.getRouteModel(RouteManagerBasic.UNASIGNED_ROUTE_GROUP);
            assert(ram != null);
            ram.assignRoute(route);
            lastPath = file;
        }
    }


    protected List<FileFilter> getRouteFormatFilters() {
        List<FileFilter> fileFilters = new ArrayList<>();
        fileFilters.add(new FileNameExtensionFilter("Simple route text format", "txt", "TXT"));
        fileFilters.add(new FileNameExtensionFilter("ECDIS900 V3 route", "rou", "ROU"));
        fileFilters.add(new FileNameExtensionFilter("Navisailor 3000 route", "rt3", "RT3"));
        fileFilters.add(new FileNameExtensionFilter("Google KML", "kml", "KML"));
        fileFilters.add(new FileNameExtensionFilter("Route plan exchange format", "rtz", "RTZ"));
        return fileFilters;
    }

}
