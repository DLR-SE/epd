package de.emir.rcp.pluginmanager.doxygen.extensions;

import de.emir.rcp.pluginmanager.doxygen.AbstractDoxygenExtension;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.rcp.pluginmanager.model.TextUtils;
import de.emir.tuml.runtime.epf.ProductFile;

public class PlantUMLExtension extends AbstractDoxygenExtension {
    @Override
    public String replaceTextContent(String textContent, ProductFile pf, ExportData data) {
        try {
            String startString = "PLANTUML_JAR_PATH";
            String replaceText = "PLANTUML_JAR_PATH      = \"" + data.getPlantUMLPath() + "\"";
            return TextUtils.replaceText(textContent, startString, replaceText);
        } catch (Exception e) {
            e.printStackTrace();
            return textContent;
        }
    }
}
