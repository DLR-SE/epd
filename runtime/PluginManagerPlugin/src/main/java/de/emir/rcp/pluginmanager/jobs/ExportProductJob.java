package de.emir.rcp.pluginmanager.jobs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.xml.sax.SAXException;

import de.emir.rcp.jobs.IJob;
import de.emir.rcp.pluginmanager.doxygen.Doxygen;
import de.emir.rcp.pluginmanager.model.ExportData;
import de.emir.runtime.plugin.windows.MainWindow;
import de.emir.tuml.runtime.epf.ClasspathEntry;
import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;
import de.emir.tuml.ucore.runtime.progress.NullProgressMonitor;
import de.emir.tuml.ucore.runtime.utils.Zip;
import de.emir.tuml.ucore.runtime.utils.ZipFileException;

public class ExportProductJob implements IJob {

    private static final String LAYOUT_FILE = MainWindow.LAYOUT_FILE;
    private static final String PROPERTIES_FILE = "properties.data";

    private boolean cancelRequested;
    private ProductFile productFile;
    private File targetRoot;
    private ExportData data;
    private String exportError;
    private File productFileFolder;
    private Path[] skipOnCopy;
    private Path propFilePath;
    private Path layoutFilePath;
    private List<File> workspaces;

    public ExportProductJob(ProductFile pf, ExportData data) {
        this.productFile = pf;
        this.productFileFolder = pf.getFile().getParentFile();
        this.workspaces = new ArrayList<>(pf.getWorkspaces());

        File targetFolder = new File(data.getOutputPath());

        Path targetPath = targetFolder.toPath().resolve(productFile.getName());
        targetRoot = targetPath.toFile();

        propFilePath = productFileFolder.toPath().resolve("properties.data");
        layoutFilePath = productFileFolder.toPath().resolve("application-layout.xml");

        skipOnCopy = new Path[] { pf.getFile().toPath(), propFilePath, layoutFilePath };

        this.data = data;
    }

    @Override
    public void run(IProgressMonitor monitor) {

        exportError = copyProductFile(monitor);

        if (exportError != null || cancelRequested == true) {
            return;
        }

        exportError = createOrClearOutputFolder();

        if (exportError != null || cancelRequested == true) {
            return;
        }

        exportError = runDoxygen(monitor);

        if (exportError != null || cancelRequested == true) {
            return;
        }

        exportError = copyEntryPointJar();

        if (exportError != null || cancelRequested == true) {
            return;
        }

        exportError = copyEntryPointPom();

        if (exportError != null || cancelRequested == true) {
            return;
        }

        exportError = saveProductFile(monitor);

        if (exportError != null || cancelRequested == true) {
            return;
        }

        exportError = copySettingsFiles();

        if (exportError != null || cancelRequested == true) {
            return;
        }

        exportError = createZipFile(monitor);

        if (exportError != null || cancelRequested == true) {
            return;
        }

    }

    private String createZipFile(IProgressMonitor monitor) {
        if (data.isCreateZip()) {
            if (monitor == null) {
                monitor = new NullProgressMonitor();
            }

            monitor.setProgress(10f);

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

                        finalMonitor.setMessage("Creating zip file" + points);

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

            try {
                Date currentDate = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                String formattedDate = formatter.format(currentDate);
                String target = targetRoot.getParent() + File.separator + formattedDate + "-" + productFile.getName()
                        + ".zip";
                Zip.compressZip(targetRoot.getAbsolutePath(), target, true);
            } catch (ZipFileException e) {
                return e.getMessage();
            } finally {
                thread.interrupt();
            }
        }

        return null;
    }

    private String runDoxygen(IProgressMonitor monitor) {
        if (data.isAddDocumentation()) {
            for (File workspace : workspaces) {
                productFile.addWorkspace(workspace);
            }
            try {
                return Doxygen.runDoxygen(monitor, productFile, data);
            }finally {
                productFile.clearWorkspaces();
            }
        }
        return null;
    }

    private String copySettingsFiles() {

        if (data.isCopyAdditionalFiles() == true) {
            try {
                copyFolder(productFileFolder, targetRoot, skipOnCopy);
            } catch (IOException e) {
                return "Can't copy files\n" + e.getMessage();
            }
        }

        if (data.isCopyLayout() == true) {

            if (layoutFilePath.toFile().exists() == true) {

                Path target = targetRoot.toPath().resolve(LAYOUT_FILE);

                try {
                    Files.copy(layoutFilePath, target);
                } catch (IOException e) {
                    return "Can't copy layout file " + e.getMessage();
                }

            }

        }

        if (data.isCopyProperties() == true) {

            if (propFilePath.toFile().exists() == true) {

                Path target = targetRoot.toPath().resolve(PROPERTIES_FILE);

                try {
                    Files.copy(propFilePath, target);
                } catch (IOException e) {
                    return "Can't copy properties file " + e.getMessage();
                }

            }

        }

        return null;
    }

    private void copyFolder(File src, File dest, Path[] exclude) throws IOException {

        try (Stream<Path> stream = Files.walk(src.toPath())) {
            stream.forEach(sourcePath -> {

                try {
                    boolean skip = false;
                    for (Path path : exclude) {
                        if (sourcePath.equals(path) == true) {

                            skip = true;
                            break;

                        }
                    }

                    if (skip == false) {

                        Path target = dest.toPath().resolve(src.toPath().relativize(sourcePath));

                        if (target.equals(dest.toPath()) == false) {
                            Files.copy(sourcePath, target);
                        }

                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }

            });

        }

    }

    private String copyEntryPointPom() {

        File entryPointPomFile = new File(data.getEntryPointPomPath());

        String entryPointPomFileName = entryPointPomFile.getName();

        try {

            FileUtils.copyFile(entryPointPomFile, targetRoot.toPath().resolve(entryPointPomFileName).toFile());

        } catch (IOException e) {
            return "Can't copy entry point pom file\n " + e.getMessage();

        }

        return null;
    }

    private String copyProductFile(IProgressMonitor monitor) {

        try {

            String name = productFile.getName();
            String localRepos = productFile.getLocalRepository();

            productFile = new ProductFile(productFile.getFile());
            productFile.setName(name);
            productFile.setLocalRepository(localRepos);

            List<File> workspaces = productFile.getWorkspaces();

            PluginManager pm = new PluginManager(monitor, productFile, true);

            pm.subscribeWorkspaceProjects(m -> {

                productFile.addDependency(MavenUtil.getCoordinate(m));

            });

            for (File file : workspaces) {
                pm.loadWorkspace(file);
            }

            productFile.clearWorkspaces();

        } catch (SAXException | IOException | ParserConfigurationException e) {
            return "Can't load product file\n " + e.getMessage();
        }

        return null;

    }

    private String saveProductFile(IProgressMonitor monitor) {

        if (data.isLeanRelease() == false) {

            String targetRepoPath = targetRoot.getAbsolutePath() + File.separator + "repository";
            String sourceRepoPath = productFile.getLocalRepository();

            Path sourceRepo = new File(sourceRepoPath).toPath();
            Path targetRepo = new File(targetRepoPath).toPath();

            try {
                Files.createDirectories(targetRepo);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // boolean offline = data.isResolveLocally();
            boolean offline = true;
            PluginManager pm = new PluginManager(monitor, productFile, offline);

            pm.subscribeClasspathEntry(ce -> copyClassPathEntry(ce, sourceRepo, targetRepo, pm));

            pm.load();
            pm.build();
            productFile.setLocalRepository("repository");

            if(data.isRemoveCredentials()) {
                productFile.clearCredentials();
            }

            if (data.isRemoveAllRepositories() == true) {
                productFile.clearRepositories();
            }


        } else if (data.isOnlineRelease()) {
            String targetRepoPath = targetRoot.getAbsolutePath() + File.separator + "repository";
            productFile.setLocalRepository(targetRepoPath);
            String sourceRepoPath = productFile.getLocalRepository();

            Path sourceRepo = new File(sourceRepoPath).toPath();
            Path targetRepo = new File(targetRepoPath).toPath();

            try {
                Files.createDirectories(targetRepo);
            } catch (IOException e) {
                e.printStackTrace();
            }

            boolean online = true;
            PluginManager pm = new PluginManager(monitor, productFile, !online);

            pm.subscribeClasspathEntry(ce -> copyClassPathEntry(ce, sourceRepo, targetRepo, pm));

            pm.load();
            pm.build();
            productFile.setLocalRepository("repository");

            if(data.isRemoveCredentials()) {
                productFile.clearCredentials();
            }

            if (data.isRemoveAllRepositories() == true) {
                productFile.clearRepositories();
            }
        }

        productFile.setFile(targetRoot.toPath().resolve(ProductFile.PRODUCT_DEFINITION).toFile());

        try {
            productFile.write();
        } catch (IOException e) {
            return "Can't save product file\n " + e.getMessage();
        }

        return null;
    }

    private void copyClassPathEntry(ClasspathEntry<?> ce, Path sourceRepo, Path targetRepo, PluginManager pm) {

        ClassLoader cl = ce.getClassLoader();

        if (cl instanceof URLClassLoader) {

            URL[] urls = ((URLClassLoader) cl).getURLs();
            URL[] descURLs = ce.getDescriptor().getURLs();

            try {

                for (URL url : urls) {
                    File f = new File(url.toURI());

                    if (f.exists() == false) {
                        // TODO: ERROR?
                        continue;
                    }

                    copyFile(f.toPath(), sourceRepo, targetRepo);

                    // Find all metadata files for each dependency and copy to the exported repo to enable
                    // support for maven version ranges which is only possible if the metadata files are present
                    File metadataPath = f.getParentFile().getParentFile();
                    try(Stream<Path> stream = Files.list(metadataPath.toPath())) {
                        List<Path> files = stream
                                .filter(path -> !Files.isDirectory(path))
                                .filter(path -> path.getFileName().toString().startsWith("maven-metadata"))
                                .filter(path -> path.getFileName().toString().endsWith(".xml"))
                                .collect(Collectors.toList());
                        for(Path path : files) {
                            copyFile(path, sourceRepo, targetRepo);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



                for (URL url : descURLs) {
                    File f = new File(url.toURI());

                    if (f.exists() == false) {
                        // TODO: ERROR?
                        continue;
                    }

                    copyFile(f.toPath(), sourceRepo, targetRepo);

                    // Search for parents in local repo
                    if (url.toString().endsWith(".pom")) {

                        MavenUtil mu = pm.getMavenUtil();
                        Model model = mu.readModel(url);
                        Parent parent = model.getParent();
                        if (parent != null) {

                            copyParentsToOutputRepo(parent, mu, sourceRepo, targetRepo);
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void copyFile(Path filePath, Path sourceRepo, Path targetRepo) {
    	if (!sourceRepo.isAbsolute()) {
    		sourceRepo = Path.of(this.productFile.getLocalRepository());
    		//Path absoluteSrc = Path.of(this.productFileFolder.toString(), sourceRepo.toString());
    		//sourceRepo = absoluteSrc;
    	}
        Path relativeToSource = sourceRepo.relativize(filePath);
        Path targetFullPath = targetRepo.resolve(relativeToSource);
        Path targetFolder = targetFullPath.getParent();

        try {
            Files.createDirectories(targetFolder);
//            System.out.println("copy " + filePath.toString() + " " + targetFullPath.toString());
            Files.copy(filePath, targetFullPath);
        } catch (FileAlreadyExistsException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void copyParentsToOutputRepo(Parent parent, MavenUtil mu, Path sourceRepo, Path targetRepo) {

        String groupID = parent.getGroupId();
        String artifactID = parent.getArtifactId();
        String version = parent.getVersion();

        String pom = artifactID + "-" + version + ".pom";

        String[] folders = groupID.split("\\.");

        String folderPart = "";

        for (String f : folders) {
            folderPart += f + File.separator;
        }

        Path parentPomPath = sourceRepo
                .resolve(folderPart + artifactID + File.separator + version + File.separator + pom);

        if (parentPomPath.toFile().exists() == false) {
            return;
        }

        copyFile(parentPomPath, sourceRepo, targetRepo);

        Model parentModel = mu.readModel(parentPomPath.toFile());

        Parent parentParent = parentModel.getParent();
        if (parentParent != null) {

            copyParentsToOutputRepo(parentParent, mu, sourceRepo, targetRepo);
        }

    }

    private String copyEntryPointJar() {

        File entryPointFile = new File(data.getEntryPointPath());

        String entryPointFileName = entryPointFile.getName();

        try {

            FileUtils.copyFile(entryPointFile, targetRoot.toPath().resolve(entryPointFileName).toFile());

        } catch (IOException e) {
            return "Can't copy entry point jar\n " + e.getMessage();

        }

        return null;

    }

    private String createOrClearOutputFolder() {

        if (targetRoot.exists() == true) {

            try {
                FileUtils.deleteDirectory(targetRoot);
            } catch (IOException e) {
                return "Can't delete output directory \n " + e.getMessage();
            }
        }

        targetRoot.mkdirs();

        return null;

    }

    @Override
    public boolean isBlocking() {
        return true;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }

    @Override
    public boolean isBackground() {
        return false;
    }

    @Override
    public void cancel() {
        cancelRequested = true;

    }

    @Override
    public String getTitle() {
        return "Product Export";
    }

    public String getExportError() {
        return exportError;
    }

}
