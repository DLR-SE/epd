package de.emir.rcp.pluginmanager.doxygen;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.rcp.pluginmanager.model.TextUtils;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;
import de.emir.tuml.ucore.runtime.progress.NullProgressMonitor;
import org.apache.log4j.Logger;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Doxygen {

    private static final Logger logger = Logger.getLogger(Doxygen.class);

    private static final String CMD_PROJECT_OVERVIEW = "PROJECT_OVERVIEW";
    private static final String CMD_COMMON_REFERENCES = "COMMON_REFERENCES";

    public static String runDoxygen(IProgressMonitor monitor, ProductFile pf, ExportData exportData) {

        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }


        File doxyfile = prepareDoxygenFile(monitor, pf, exportData);

        if (doxyfile == null || doxyfile.exists() == false) {
            return "Doxyfile cannot be created";
        }

        File doxygen = new File(exportData.getDoxygenPath());

        if (doxygen == null || doxygen.exists() == false) {
            return "Doxygen executable does not exist";
        }

        Process process = runDoxygenProcess(doxygen, doxyfile);

        if (process != null) {
            monitor.setProgress(30f);
            IProgressMonitor finalMonitor = monitor;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    int counter = 0;

                    while (true) {
                        String points = "";
                        for (int i = 0; i < counter; i++) {
                            points += ".";
                        }

                        finalMonitor.setMessage("Running Doxygen" + points);
                        logger.debug("Running Doxygen" + points);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            return;
                        }

                        counter++;

                        if (counter > 3) {
                            counter = 0;
                        }
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();

            logger.info("Running Doxygen....");
            logger.info("Please wait...");
            try {
                while (process.getInputStream().read() != -1) ;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                thread.interrupt();
            }
            logger.info("Done");
        } else {
            return "Doxygen Process failed";
        }

        return null;

    }

    private static Process runDoxygenProcess(File doxygenExec, File doxyfile) {
        if ((doxyfile.exists() == false)) {
            logger.error("Missing or bad doxyfile");
            return null;
        }
        try {
            // create process builder with doxygen command and doxyfile
            ProcessBuilder pb = new ProcessBuilder(doxygenExec.getAbsolutePath(), doxyfile.getAbsolutePath());

            // set working directory
            pb.directory(doxyfile.getParentFile().getAbsoluteFile());

            pb.redirectErrorStream(true);

            // return the process
            return pb.start();
        } catch (IOException ioException) {
            logger.error(ioException);
            return null;
        }
    }

    private static File prepareDoxygenFile(IProgressMonitor monitor, ProductFile pf, ExportData data) {
        InputStream stream = Doxygen.class.getClass().getResourceAsStream("/doxygen/Doxyfile");

        File targetDoxy = null;
        if (stream != null) {
            //raw doxygen template
            String textContent = TextUtils.getFileContent(stream);

            DoxygenExtensionPoint extensionPoint = ExtensionPointManager.getExtensionPoint(DoxygenExtensionPoint.class);
            List<AbstractDoxygenExtension> extensions = extensionPoint.getExtensions();

            monitor.setMessage("Preparing Files");
            monitor.setProgress(0f);

            for (int i = 0; i < extensions.size(); i++) {
                monitor.setProgress((i / extensions.size()) * 100);
                textContent = extensions.get(i).replaceTextContent(textContent, pf, data);
            }

            File outputPath = new File(data.getOutputPath() + File.separator + pf.getName() + "_doxygen");
            if (outputPath.exists() == false) {
                outputPath.mkdirs();
            }

            createProjectStructure(monitor, outputPath.getAbsolutePath(), pf, data);

            targetDoxy = new File(outputPath.getAbsolutePath() + "/Doxyfile");
            DoxygenUtils.writeFile(targetDoxy, textContent);
        }

        return targetDoxy;
    }

    private static void createProjectStructure(IProgressMonitor monitor, String outputPath, ProductFile pf, ExportData data) {
        monitor.setMessage("Creating Structure");
        monitor.setProgress(0f);

        Collection<Model> realWorkspaces = DoxygenUtils.getResolvedWorkspaces(pf.getWorkspaces());

        List<List<MavenModel>> allWorkspaces = new ArrayList<>();

        for (Model model : realWorkspaces) {

            List<MavenModel> allModels = new ArrayList<>();

            MavenModel mavenModel = new MavenModel(model);
            allModels.add(mavenModel);

            while (mavenModel.hasParent()) {
                mavenModel = mavenModel.getParentModel();
                allModels.add(mavenModel);
            }

            allWorkspaces.add(Lists.reverse(allModels));
        }

        monitor.setProgress(10f);


        List<DirectedGraph<MavenModel>> graphs = DoxygenUtils.createGraph(allWorkspaces);

        monitor.setProgress(30f);

        monitor.setProgress(40f);

        executeCommands(graphs, outputPath);

        monitor.setProgress(60f);

        StringBuilder projectStructure = new StringBuilder();

        projectStructure.append("/**\n\n");

        addProjectOverview("Projects", projectStructure, graphs, "");
        monitor.setProgress(70f);
        addProjectOverview("User Manuals", projectStructure, graphs, "UserManual");
        addProjectOverview("Documentation", projectStructure, graphs, "DevDocumentation");
        addProjectOverview("Licenses", projectStructure, graphs, "Licenses");

        monitor.setProgress(100f);

        projectStructure.append("*/");

        DoxygenUtils.writeFile(new File(outputPath + "/ProjectStructure.doxy"), projectStructure.toString());
    }

    private static void executeCommands(List<DirectedGraph<MavenModel>> graphs, String outputPath){

        File docFolder = new File(outputPath + "/doxyfiles");
        if (docFolder.exists() == false) {
            boolean done = docFolder.mkdirs();
            if (done == false) {
                logger.error("Couldn't create folder: " + docFolder.getAbsolutePath());
                return;
            }
        }

        for (DirectedGraph<MavenModel> modelDirectedGraph : graphs){
            MavenModel project = modelDirectedGraph.getElement();
            File doc = new File(project.getModel().getProjectDirectory().getAbsolutePath() + "/doc");
            File[] files = doc.listFiles();
            if (files == null) continue;
            for (File docFile : files) {
                if (docFile.isFile() && (docFile.getAbsolutePath().endsWith(".doxy") || docFile.getAbsolutePath().endsWith(".doxyh"))) {
                    try {
                        File copiedFile = new File(docFolder.getAbsolutePath() + "/" +
                                TextUtils.replaceSpecialChars(project.getModel().getArtifactId()) +"_"+ docFile.getName());
                        Files.copy(docFile, copiedFile);

                        String ending = "";

                        if (docFile.getName().contains("Project")){
                            ending = "";
                        }else if (docFile.getName().contains("License")){
                            ending = "Licenses";
                        }else if (docFile.getName().contains("DevGuide")){
                            ending = "DevDocumentation";
                        }else if (docFile.getName().contains("UserManual")){
                            ending = "UserManual";
                        }

                        executeCommands(project, copiedFile, modelDirectedGraph, ending);

                    } catch (IOException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }

            if (modelDirectedGraph.hasChildren()){
                executeCommands(modelDirectedGraph.getChildren(), outputPath);
            }
        }
    }

    private static void executeCommands(MavenModel project, File copiedFile, DirectedGraph<MavenModel> graph, String ending) {
        try {

            String textContent = TextUtils.getFileContent(new FileInputStream(copiedFile));
            textContent = TextUtils.replaceText(textContent, CMD_PROJECT_OVERVIEW, executeProjectOverview(project, graph, ending));
            textContent = TextUtils.replaceText(textContent, CMD_COMMON_REFERENCES, executeCommonReferences(project, graph));
            DoxygenUtils.writeFile(copiedFile, textContent);
        } catch (IOException e) {
            logger.error("Cannot execute commands for "+copiedFile.getAbsolutePath(),e);
        }
    }

    private static String executeProjectOverview(MavenModel project, DirectedGraph<MavenModel> currentGraph, String ending) {
        StringBuilder builder = new StringBuilder();
        if (currentGraph.hasChildren()){
            for (DirectedGraph<MavenModel> child : currentGraph.getChildren()){
                String name = child.getElement().getModel().getArtifactId();
                String pageName = "page" + TextUtils.replaceSpecialChars(name) + ending;
                builder.append("\\subpage " + pageName + "\n");
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private static String executeCommonReferences(MavenModel project, DirectedGraph<MavenModel> graphs) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("<a href=\"page" + TextUtils.replaceSpecialChars(project.getModel().getArtifactId()) + "UserManual.html\">User Manual</a>");
        builder.append("\n");
        builder.append("\n");
        builder.append("<a href=\"page" + TextUtils.replaceSpecialChars(project.getModel().getArtifactId()) + "DevDocumentation.html\">Developer Guide</a>");
        builder.append("\n");
        return builder.toString();
    }

    private static void addProjectOverview(String siteName, StringBuilder projectStructure, List<DirectedGraph<MavenModel>> graphs, String ending) {
        for (DirectedGraph<MavenModel> mavenModel : graphs) {

            Model parent = mavenModel.getElement().getModel();

            projectStructure.append("\\page " + TextUtils.replaceSpecialChars(siteName) + " " + siteName + "\n\n");

            projectStructure.append("\\subpage page" + TextUtils.replaceSpecialChars(parent.getArtifactId()) + ending
                    + " " + TextUtils.replaceSpecialChars(parent.getArtifactId()) + "\n");
            projectStructure.append("\n");
            projectStructure.append("\\page page" + TextUtils.replaceSpecialChars(parent.getArtifactId()) + ending
                    + " " + TextUtils.replaceSpecialChars(parent.getArtifactId()) + "\n");
            projectStructure.append("\n");

//            for (DirectedGraph<MavenModel> child : mavenModel.getChildren()) {
//                if (child.getChildren().size() != 0) {
//                    addProjectOverview(projectStructure, child, ending);
//                } else {
//                    addProjectLeaf(child, projectStructure, ending);
//                }
//
//                projectStructure.append("\\page page" + TextUtils.replaceSpecialChars(parent.getArtifactId()) + ending
//                        + " " + TextUtils.replaceSpecialChars(parent.getArtifactId()) + "\n");
//                projectStructure.append("\n");
//            }
        }
    }
//
//    private static void addProjectOverview(StringBuilder projectStructure, DirectedGraph<MavenModel> graph, String ending) {
//        Model parent = graph.getElement().getModel();
//
//        projectStructure.append("\\subpage page" + TextUtils.replaceSpecialChars(parent.getArtifactId()) + ending
//                + " " + TextUtils.replaceSpecialChars(parent.getArtifactId()) + "\n");
//        projectStructure.append("\n");
//        projectStructure.append("\\page page" + TextUtils.replaceSpecialChars(parent.getArtifactId()) + ending
//                + " " + TextUtils.replaceSpecialChars(parent.getArtifactId()) + "\n");
//        projectStructure.append("\n");
//
//        for (DirectedGraph<MavenModel> child : graph.getChildren()) {
//            if (child.getChildren().size() != 0) {
//                addProjectOverview(projectStructure, child, ending);
//            } else {
//                addProjectLeaf(child, projectStructure, ending);
//            }
//
//            projectStructure.append("\\page page" + TextUtils.replaceSpecialChars(parent.getArtifactId()) + ending
//                    + " " + TextUtils.replaceSpecialChars(parent.getArtifactId()) + "\n");
//            projectStructure.append("\n");
//        }
//    }
//
//    private static void addProjectLeaf(DirectedGraph<MavenModel> graph, StringBuilder projectStructure, String ending) {
//        String name = graph.getElement().getModel().getArtifactId();
//        String pageName = "page" + TextUtils.replaceSpecialChars(name) + ending;
//
//        projectStructure.append("\\subpage " + pageName + "\n");
//        projectStructure.append("\n");
//    }

    private static List<MavenModel> searchForProjectFolders(List<DirectedGraph<MavenModel>> graphs) {
        List<MavenModel> mavenModels = new ArrayList<>();

        for (DirectedGraph<MavenModel> graph : graphs) {
            mavenModels.add(graph.getElement());
            if (graph.hasChildren()) {
                mavenModels.addAll(searchForProjectFolders(graph.getChildren()));
            }
        }

        return mavenModels;
    }

    public static class MavenModel {

        private Model model;

        public MavenModel() {
            super();
        }

        public MavenModel(Model model) {
            this.model = model;
        }

        public MavenModel getParentModel() {
            if (hasParent() == false) {
                return null;
            }

            Parent parent = model.getParent();
            Path path = model.getPomFile().toPath();
            path = path.getParent().resolve(parent.getRelativePath());
            path = path.normalize();
            try {
                Model model = new MavenXpp3Reader().read(new FileReader(path.toFile()));

                if (model.getGroupId() == null) {
                    model.setGroupId(parent.getGroupId());
                }

                if (model.getVersion() == null) {
                    model.setVersion(parent.getVersion());
                }

                model.setPomFile(path.toFile());

                return new MavenModel(model);
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
                return null;
            }
        }

        public boolean hasParent() {
            return model.getParent() != null;
        }

        public Model getModel() {
            return model;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof MavenModel) {
                MavenModel other = (MavenModel) obj;

                boolean equals;
                equals = model.getArtifactId().equals(other.model.getArtifactId());
                if (equals == false) {
                    return equals;
                }

                equals = model.getGroupId().equals(other.model.getGroupId());
                if (equals == false) {
                    return equals;
                }

                if (model.getVersion() != null) {
                    equals = model.getVersion().equals(other.model.getVersion());
                    if (equals == false) {
                        return equals;
                    }
                }

                return equals;
            }
            return super.equals(obj);
        }

        @Override
        public String toString() {
            return model.getGroupId() + ":" + model.getArtifactId() + ":" + model.getVersion();
        }
    }
}
