package de.emir.rcp.pluginmanager.doxygen.extensions;

import de.emir.rcp.pluginmanager.doxygen.AbstractDoxygenExtension;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.rcp.pluginmanager.model.TextUtils;
import de.emir.tuml.runtime.epf.ProductFile;

public class ProjectNameExtension extends AbstractDoxygenExtension {
    @Override
    public String replaceTextContent(String textContent, ProductFile pf, ExportData data) {
        // replace project name
        try {
            String startString = "PROJECT_NAME";
            String replaceText = "PROJECT_NAME           = \"" + TextUtils.replaceSpecialChars(pf.getName()) + "\"";
            return TextUtils.replaceText(textContent, startString, replaceText);
        } catch (Exception e) {
            e.printStackTrace();
            return textContent;
        }
    }
}
