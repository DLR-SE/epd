package de.emir.rcp.pluginmanager.doxygen.extensions;

import de.emir.rcp.pluginmanager.doxygen.AbstractDoxygenExtension;
import de.emir.rcp.pluginmanager.doxygen.DoxygenUtils;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.tuml.runtime.epf.ProductFile;
import org.apache.maven.model.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImagePathExtension extends AbstractDoxygenExtension {
    private static String createAllImageFolder(ProductFile pf) {
        StringBuilder builder = new StringBuilder();
        List<Model> workspaces = new ArrayList<>(DoxygenUtils.getResolvedWorkspaces(pf));
        for (Model model : workspaces) {
            File workspace = model.getPomFile();
            int index = workspaces.indexOf(workspace);
            String path = null;
            if (workspace.isDirectory() == false) {
                path = workspace.getParent();
            } else {
                path = workspace.getAbsolutePath();
            }

            String imagePath = path + "/doc/images";

            if (index == (workspaces.size() - 1)) {
                builder.append("                       " + imagePath + " ");
            } else if (index == 0) {
                builder.append(imagePath + " \\ \n");
            } else {
                builder.append("                       " + imagePath + " \\ \n");
            }
        }

        return builder.toString();
    }

    @Override
    public String replaceTextContent(String textContent, ProductFile pf, ExportData data) {
        try {
            String imagePaths = createAllImageFolder(pf);
            String startString = "IMAGE_PATH";
            String replaceText = "IMAGE_PATH           = " + imagePaths;

            List<String> fileContent = new ArrayList<>(Arrays.asList(textContent.split("\n")));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).startsWith(startString)) {
                    int removeCounter = 0;

                    for (int x = i; x < fileContent.size(); x++) {
                        String check = fileContent.get(x);
                        if (check.trim().endsWith("\\") == false) {
                            break;
                        } else {
                            removeCounter++;
                        }
                    }

                    for (int z = 0; z < removeCounter; z++) {
                        fileContent.remove(i + 1);
                    }

                    fileContent.set(i, replaceText);

                    break;
                }
            }

            StringBuilder builder = new StringBuilder();
            for (String str : fileContent) {
                builder.append(str + "\n");
            }

            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return textContent;
        }
    }
}
