package de.emir.rcp.pluginmanager.doxygen.extensions;

import de.emir.rcp.pluginmanager.doxygen.AbstractDoxygenExtension;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.rcp.pluginmanager.model.TextUtils;
import de.emir.tuml.runtime.epf.ProductFile;

import java.io.File;

public class MainPageExtension extends AbstractDoxygenExtension {
    @Override
    public String replaceTextContent(String textContent, ProductFile pf, ExportData data) {

        File baseFolder = new File(data.getEntryPointPomPath());
        if (baseFolder.isFile()) {
            baseFolder = baseFolder.getParentFile();
        }

        baseFolder = new File(baseFolder.getAbsolutePath() + "/doc");

        if (baseFolder.exists() == false) {
            return textContent;
        }

        File[] files = baseFolder.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }

            if (file.getName().trim().toLowerCase().contains("readme") || file.getAbsolutePath().toLowerCase().endsWith(".md")) {
                String startString = "USE_MDFILE_AS_MAINPAGE";
                String replaceText = "USE_MDFILE_AS_MAINPAGE           = \"" + file.getAbsolutePath() + "\"";
                textContent = TextUtils.replaceText(textContent, startString, replaceText);
                break;
            }
        }

        return textContent;
    }
}
