package de.emir.rcp.pluginmanager.doxygen.extensions;

import de.emir.rcp.pluginmanager.doxygen.AbstractDoxygenExtension;
import de.emir.rcp.pluginmanager.doxygen.DoxygenUtils;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.tuml.runtime.epf.ProductFile;
import org.apache.maven.model.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ExcludeFoldersExtension extends AbstractDoxygenExtension {

    private static String createExcludeFolders(ProductFile pf) {
        StringBuilder builder = new StringBuilder();
        Collection<Model> workspaces = DoxygenUtils.getResolvedWorkspaces(pf);

        List<File> excludes = new ArrayList<>();
        for (Model model : workspaces) {
            File workspace = model.getPomFile();
            String path = null;
            if (workspace.isDirectory() == false) {
                path = workspace.getParent();
            } else {
                path = workspace.getAbsolutePath();
            }

            excludes.add(new File(path + "/target"));
        }

        for (File folder : excludes) {
            int index = excludes.indexOf(folder);
            String path = null;
            if (folder.isDirectory() == false) {
                path = folder.getParent();
            } else {
                path = folder.getAbsolutePath();
            }

            if (index == (excludes.size() - 1)) {
                builder.append("                       \"" + path + "\" ");
            } else if (index == 0) {
                builder.append("\"" + path + "\" \\ \n");
            } else {
                builder.append("                       \"" + path + "\" \\ \n");
            }
        }

        return builder.toString();
    }

    @Override
    public String replaceTextContent(String textContent, ProductFile pf, ExportData data) {
        try {
            String excludePaths = createExcludeFolders(pf);
            String startString = "EXCLUDE";
            String replaceText = "EXCLUDE                = " + excludePaths;

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
