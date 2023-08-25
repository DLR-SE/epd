package de.emir.rcp.pluginmanager.doxygen.extensions;

import de.emir.rcp.pluginmanager.doxygen.AbstractDoxygenExtension;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.rcp.pluginmanager.model.TextUtils;
import de.emir.tuml.runtime.epf.ProductFile;

import java.io.File;
import java.nio.file.Path;

public class OutputDirectoryExtension extends AbstractDoxygenExtension {
    @Override
    public String replaceTextContent(String textContent, ProductFile pf, ExportData data) {
        // replace output directory
        try {
            File targetFolder = new File(data.getOutputPath());

            Path targetPath = targetFolder.toPath().resolve(pf.getName());

            String path = targetPath.toFile() + "/Documentation";
            File file = new File(path);
            if (file.exists() == false) {
                file.mkdirs();
            }
            String startString = "OUTPUT_DIRECTORY";
            String replaceText = "OUTPUT_DIRECTORY       = \"" + path + "\"";
            return TextUtils.replaceText(textContent, startString, replaceText);
        } catch (Exception e) {
            e.printStackTrace();
            return textContent;
        }
    }
}
