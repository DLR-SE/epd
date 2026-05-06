package de.emir.rcp.pluginmanager.doxygen.extensions;

import de.emir.rcp.pluginmanager.doxygen.AbstractDoxygenExtension;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.rcp.pluginmanager.model.TextUtils;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class PlantUMLDirExtension extends AbstractDoxygenExtension {
    @Override
    public String replaceTextContent(String textContent, ProductFile pf, ExportData data) {
        // TODO set plant uml include path
        try {
            String startString = "PLANTUML_INCLUDE_PATH";
            String replaceText = "PLANTUML_INCLUDE_PATH  = ";
            return TextUtils.replaceText(textContent, startString, replaceText);
        } catch (Exception e) {
            ULog.error(e);
            return textContent;
        }
    }
}
