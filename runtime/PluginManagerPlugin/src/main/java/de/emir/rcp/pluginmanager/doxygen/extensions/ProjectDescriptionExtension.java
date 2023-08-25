package de.emir.rcp.pluginmanager.doxygen.extensions;

import de.emir.rcp.pluginmanager.doxygen.AbstractDoxygenExtension;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.rcp.pluginmanager.model.TextUtils;
import de.emir.tuml.runtime.epf.ProductFile;

public class ProjectDescriptionExtension extends AbstractDoxygenExtension {
    @Override
    public String replaceTextContent(String textContent, ProductFile pf, ExportData data) {
        // replace project description
        try {
            String startString = "PROJECT_BRIEF";
            String replaceText = "PROJECT_BRIEF          = \"" + TextUtils.replaceSpecialChars(pf.getDescription())
                    + "\"";
            return TextUtils.replaceText(textContent, startString, replaceText);
        } catch (Exception e) {
            e.printStackTrace();
            return textContent;
        }
    }
}
