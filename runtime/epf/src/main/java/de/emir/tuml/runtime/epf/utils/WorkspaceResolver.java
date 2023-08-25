package de.emir.tuml.runtime.epf.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.maven.model.Model;

import de.emir.tuml.ucore.runtime.utils.FileOperations;

public class WorkspaceResolver {

    private MavenUtil mMavenUtil;

    public WorkspaceResolver(MavenUtil mu) {
        mMavenUtil = mu;
    }

    public Collection<Model> resolveWorkspaces(List<File> workspaces) {
        HashSet<File> pomFiles = new HashSet<>();
        for (File wsDir : workspaces) {
            if (wsDir != null && wsDir.exists()) {
                if (wsDir.isFile() && wsDir.getName().endsWith("pom.xml"))
                    pomFiles.add(wsDir);
                else {
                    if (wsDir.isFile())
                        wsDir = wsDir.getParentFile();
                    File f = FileOperations.searchFile("pom.xml", wsDir.getAbsolutePath(), 1);
                    if (f != null && f.exists())
                        pomFiles.add(f);
                }
            }
        }
        ArrayList<File> openFiles = new ArrayList<>(pomFiles);
        HashSet<Model> models = new HashSet<>();
        while (openFiles.isEmpty() == false) {
            try {
                Model m = mMavenUtil.resolveModel(openFiles.remove(0));
                if (m.getModules() == null || m.getModules().isEmpty()) {
                    models.add(m);
                } else {
                    for (String mod : m.getModules()) {
                        File modFile = new File(m.getPomFile().getParentFile() + "/" + mod + "/pom.xml");
                        if (modFile.exists())
                            openFiles.add(modFile);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return models;
    }
}
