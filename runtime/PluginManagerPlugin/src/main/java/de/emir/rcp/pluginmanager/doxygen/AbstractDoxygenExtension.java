package de.emir.rcp.pluginmanager.doxygen;

import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.tuml.runtime.epf.ProductFile;

public abstract class AbstractDoxygenExtension {
    public abstract String replaceTextContent(String textContent, ProductFile pf, ExportData data);
}
