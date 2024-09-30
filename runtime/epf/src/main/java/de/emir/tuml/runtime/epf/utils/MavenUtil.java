package de.emir.tuml.runtime.epf.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.PrettyPrintXMLWriter;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.collection.CollectRequest;
import org.eclipse.aether.graph.DependencyFilter;
import org.eclipse.aether.graph.DependencyNode;
import org.eclipse.aether.resolution.*;
import org.eclipse.aether.util.artifact.JavaScopes;
import org.eclipse.aether.util.filter.DependencyFilterUtils;

import de.emir.tuml.runtime.epf.ObservableRepository;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * Utility for loading maven artifacts at the runtime based on the local repository and the dependencies of each plugin.
 */
public class MavenUtil {

    private CustomMavenAether mTA = new CustomMavenAether(
            new File(System.getProperty("user.home"), ".m2" + File.separator + "repository"), new String[] {});

    private static HashMap<String, Model> mCoordinateToModel = new HashMap<>();
    private static HashMap<String, Artifact> mCoordinateToArtifact = new HashMap<>();
    private static MavenXpp3Reader mModelReader = new MavenXpp3Reader();

    public MavenUtil(ProductFile pf, boolean offline) {
        this(pf.getLocalRepository(), pf.getRemoteRepositories(), offline);
    }

    public MavenUtil(String localRepos, List<ObservableRepository> remoteRepositories, boolean offline) {
        mTA = new CustomMavenAether(new File(localRepos), remoteRepositories);
        mTA.setOffline(offline);
    }

    public MavenUtil(boolean isOffline) {
        setOfflineMode(isOffline);
    }

    public void setOfflineMode(boolean isOffline) {
        mTA.setOffline(isOffline);
    }

    public File getLocalRepositoryPath() {
        return mTA.getLocalRepositoryPath();
    }

    public static String getCoordinate(Model m) {
        return getGroupId(m) + ":" + m.getArtifactId() + ":" + getVersion(m);
    }

    public static String getCoordinate(Dependency dep) {
//        if (dep.getVersion() == null) {
//            System.out.println();
//        }
//        if (dep.getVersion().contains("${")) {
//            System.out.println();
//        }
        return dep.getGroupId() + ":" + dep.getArtifactId() + ":" + dep.getVersion(); // for now assume we always have a
                                                                                      // version and groupID
    }

    public static String getCoordinate(Dependency dep, String backupVersion) {
        String v = dep.getVersion() == null ? backupVersion : dep.getVersion();
        return dep.getGroupId() + ":" + dep.getArtifactId() + ":" + v;
    }

    public static String getCoordinate(Artifact artifact) {
        return artifact.getGroupId() + ":" + artifact.getArtifactId() + ":" + artifact.getVersion();
    }

    public static String getCoordinate(Parent p) {
        return p.getGroupId() + ":" + p.getArtifactId() + ":" + p.getVersion();
    }

    public static String getVersion(Model m) {
        if (m == null)
            return null;
        String v = null;
        if (m.getVersion() != null)
            v = m.getVersion();
        if (v == null && m.getParent() != null)
            v = m.getParent().getVersion();
        if (v != null) {
//            if (v.contains("${")) {
//                System.out.println();
//            }
            return v;
        }

        return null;
    }

    public static String getVersion(Artifact m) {
        if (m == null)
            return null;
        if (m.getVersion() != null) {
            String v = m.getVersion();
            String[] s = v.split("\\.");
            if (s.length == 2)
                return v + ".0";
            return v;
        }
        return null;
    }

    /**
     * Gets the latest version of a maven coordinate when the version is specified in the maven range format (i.e. [1.0,)).
     * @param version Maven coordinate to use for retrieving latest version, i.e. groupId:artifactId:version.
     * @return Latest version based on the coordinate if found, else 0.0.0.
     */
    public String getLatestVersion(String version) {
        try {
            return mTA.findNewestVersion(version);
        } catch (VersionRangeResolutionException e) {
            ULog.error(String.format("Trying to get the latest version for %s failed. The version format is unparsable.", version));
            return "0.0.0";
        }
    }

    public static String getGroupId(Model m) {
        if (m == null)
            return null;
        if (m.getGroupId() != null)
            return m.getGroupId();
        if (m.getParent() != null)
            return m.getParent().getGroupId();
        return null;
    }

    public Model resolveModel(URL url) {
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (Exception e) {
        }
        if (file.getName().endsWith(".jar"))
            file = new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) + ".pom");
        if (file != null && file.exists())
            return resolveModel(file);
        return readModel(url);
    }

    public Model resolveModel(InputStream inputStream) throws IOException, XmlPullParserException {
        return mModelReader.read(inputStream);
    }

    public Model resolveModel(File pomFile) {
        // if we got the file use the fast method
        if (pomFile == null || pomFile.exists() == false || pomFile.isFile() == false)
            return null;
        try {
            // use the ather to resolve the file, this will also resolve dependencies (versions and groupids - given as
            // property)
            return mTA.resolveModel(pomFile);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return readModel(pomFile.toURI().toURL());
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
                return null;
            }
        }
    }

   

    public Model resolveJarModel(File jarFile) {
        if (jarFile == null || jarFile.exists() == false || jarFile.isFile() == false)
            return null;

        try {
            return resolveJarModel(jarFile.toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Model resolveJarModel(URL url) {
        if (url.toString().endsWith(".jar")) {
            File jarFile = null;
            JarFile jf = null;
            try {
                jarFile = new File(url.toURI());
                jf = new JarFile(jarFile);
            } catch (Exception e) {
            }
            if (jf == null)
                return null;

            Enumeration<JarEntry> entries = jf.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                // TODO: Isn't it always META_INF/maven/pom.xml?
                if (entry.getName().endsWith("/pom.xml")) { // this could give problems, if more than one pom.xml is
                    // available, for example in resources
                    try {
                        InputStream pomInputStream = jf.getInputStream(entry);
                        Model model = resolveModel(pomInputStream);
                        // just a sanity check
                        String artifName = model.getArtifactId();
                        if (jarFile.getName().contains(artifName) == false) {
                            continue; // check if there are other pom files
                        }
                        model.setPomFile(jarFile);
                        return model;
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        return null;
    }

    public Artifact resolveArtifact(String coordinate) {
        if (coordinate == null || coordinate.isEmpty())
            return null;
        if (mTA == null)
            return null;

        Artifact artifact = null;
        if (mCoordinateToArtifact.containsKey(coordinate))
            artifact = mCoordinateToArtifact.get(coordinate);
        try {
            ArtifactResult ar = mTA.resolveArtifact(coordinate);
            if (ar != null)
                artifact = ar.getArtifact();
        } catch (ArtifactResolutionException e) {
            int i = coordinate.lastIndexOf(':');
            String start = null;
            if (i > 0) { // try with other version
                start = coordinate.substring(0, i);
                for (String c : mCoordinateToArtifact.keySet()) {
                    if (c.startsWith(start)) {
                        ULog.error("Could not find Artifact: " + coordinate + " replace with: " + c);
                        return mCoordinateToArtifact.get(c);
                    }
                }
            }
            // if we reach this point, try just the artifact name
            if (start != null) {
                start = start.split(":")[1];
                for (String c : mCoordinateToArtifact.keySet()) {
                    if (c.split(":")[1].equals(start)) {
                        ULog.error("Could not find Artifact: " + coordinate + " replace with: " + c);
                        return mCoordinateToArtifact.get(c);
                    }
                }
            }
            ULog.error(e);
        }
        if (artifact != null)
            mCoordinateToArtifact.put(coordinate, artifact);
        return artifact;
    }

    public static boolean isRequired(Dependency dep) {
        if (dep.isOptional())
            return false; // if we don't need it, we don't load it
        String scope = dep.getScope();
        if (scope != null) {
            if (scope.equals("test"))
                return false; // we do not want to test
            if (scope.equals("provided"))
                return false; // part of the file?
            // if (scope.equals("compile"))
            // return false; //part of the file?
        }
        return true;
    }

    public List<Artifact> resolveDependencies(Model model) {
        Artifact artifact = resolveArtifact(getCoordinate(model));
        if (artifact == null)
            return null;
        DependencyFilter classpathFlter = DependencyFilterUtils.classpathFilter(JavaScopes.RUNTIME);
        CollectRequest collectRequest = new CollectRequest();
        collectRequest.setRoot(new org.eclipse.aether.graph.Dependency(artifact, JavaScopes.RUNTIME));

        DependencyRequest dependencyRequest = new DependencyRequest(collectRequest, classpathFlter);
        dependencyRequest.setFilter((node, parents) -> true);
        try {
            List<Artifact> res3 = mTA.resolveArtifacts(dependencyRequest);
            for (Artifact r : res3)
                mCoordinateToArtifact.put(getCoordinate(r), r);// remember for later
            return res3;
        } catch (DependencyResolutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Model readModel(File file) {
        try {

            return readModel(file.toURI().toURL());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Reads a Maven Model from an URL
     * 
     * @note this method do not throw any exceptions. if the url does not point to an pom file this method returns null
     * @param url
     * @return
     */
    public static Model readModel(URL url) {
        if (url == null)
            return null;
        try {
            Model m = mModelReader.read(url.openStream());
            // if the url is a file, we do save it, since the readeer doesn't
            if (m.getPomFile() == null)
                try {
                    File file = new File(url.toURI());
                    if (file != null && file.exists())
                        m.setPomFile(file);
                } catch (Exception e1) {
                }
            mCoordinateToModel.put(getCoordinate(m), m);// remember if we need it in future times
            return m;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Artifact> resolveArtifacts(Artifact resolveResult) {
        try {

            return mTA.resolveArtifacts(resolveResult);

        } catch (DependencyResolutionException e) {

            e.printStackTrace();
        }

        return null;
    }

}
