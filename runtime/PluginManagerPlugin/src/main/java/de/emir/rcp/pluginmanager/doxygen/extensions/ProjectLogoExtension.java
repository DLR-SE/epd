package de.emir.rcp.pluginmanager.doxygen.extensions;

import de.emir.rcp.pluginmanager.doxygen.AbstractDoxygenExtension;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.rcp.pluginmanager.model.TextUtils;
import de.emir.tuml.runtime.epf.ProductFile;

import java.io.File;

public class ProjectLogoExtension extends AbstractDoxygenExtension {
    @Override
    public String replaceTextContent(String textContent, ProductFile pf, ExportData data) {
        File baseFolder = new File(data.getEntryPointPomPath());
        if (baseFolder.isFile()) {
            baseFolder = baseFolder.getParentFile();
        }

        baseFolder = new File(baseFolder.getAbsolutePath() + "/src/main/resources");

        if (baseFolder.exists() == false) {
            return textContent;
        }

        File[] files = baseFolder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }

            if (file.getName().contains("Splash") && file.getAbsolutePath().endsWith(".png")) {
                try {
                    String startString = "PROJECT_LOGO";
                    String replaceText = "PROJECT_LOGO           = \"" + file.getAbsolutePath() + "\"";
                    textContent = TextUtils.replaceText(textContent, startString, replaceText);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    break;
                }
            }
        }


        return textContent;
    }
}
