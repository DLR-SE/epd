package de.emir.tuml.runtime.epf.utils;

import java.io.File;
import java.util.List;

import de.emir.tuml.ucore.runtime.logging.ULog;
import org.eclipse.aether.DefaultRepositorySystemSession;

import de.emir.tuml.runtime.epf.ObservableRepository;
import de.emir.tuml.runtime.epf.internal.LogTransferListener;
import io.tesla.aether.Repository;
import io.tesla.aether.internal.DefaultTeslaAether;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.VersionRangeRequest;
import org.eclipse.aether.resolution.VersionRangeResolutionException;
import org.eclipse.aether.resolution.VersionRangeResult;
import org.slf4j.Logger;

/**
 * Custom tesla Aether implementation which extends the Aether implementation by providing a offline mode
 * toggle and utility methods.
 */
public class CustomMavenAether extends DefaultTeslaAether {
    private Logger log = ULog.getLogger(CustomMavenAether.class);

    /**
     * Creates a new CustomMavenAether instance.
     * @param file Path of the local maven repository folder (by default ~/.m2/repository to use for resolving
     *             locally downloaded maven artifacts.
     * @param strings URLs of maven repositories to use. If a product.xml is loaded, the list of repositories is
     *                overridden by the values of the XML. Note: it is important that the id of the repository needs
     *                to match the ending of the metadata file in the repository folder, i.e. central for
     *                maven-metadata-central.xml since this id is used for detecting the versions of each artifact.
     */
    public CustomMavenAether(File file, String[] strings) {
        super(file, strings);
        DefaultRepositorySystemSession ds = (DefaultRepositorySystemSession) session;
        ds.setOffline(true);
        ds.setTransferListener(new LogTransferListener());
        // suppress extreme long logging output
        ds.setTransferListener(null);
        ds.setRepositoryListener(null);
    }

    /**
     * Creates a new CustomMavenAether instance.
     * @param localRepos Path of the local maven repository folder (by default ~/.m2/repository to use for resolving
     *             locally downloaded maven artifacts.
     * @param remoteRepositories ObservableRepositories objects of maven repositories to use.
     *                           If a product.xml is loaded, the list of repositories is
     *                           overridden by the values of the XML. Note: it is important that the id of the repository
     *                           needs to match the ending of the metadata file in the repository folder, i.e. central
     *                           for maven-metadata-central.xml since this id is used for detecting the versions of each
     *                           artifact.
     */
    public CustomMavenAether(File localRepos, List<ObservableRepository> remoteRepositories) {
        super(localRepos.getAbsolutePath(), remoteRepositories.toArray(new Repository[remoteRepositories.size()]));

        DefaultRepositorySystemSession ds = (DefaultRepositorySystemSession) session;
        ds.setOffline(true);
        ds.setTransferListener(new LogTransferListener());
        // suppress extreme long logging output
        ds.setTransferListener(null);
        ds.setRepositoryListener(null);
    }

    /**
     * Gets the newest version of a maven artifact currently installed in the local repository folder from a version
     * range when the maven range syntax was used for specifying the version (i.e. [1.0,) etc., see
     * <a href="https://docs.oracle.com/middleware/1212/core/MAVEN/maven_version.htm">here</a>).
     * @param versionRange Coordinate with range of versions for an artifact, (i.e. [1.0,) etc., see
     *      * <a href="https://docs.oracle.com/middleware/1212/core/MAVEN/maven_version.htm">here</a>).
     *                     A coordinate is supplied in the format grouId:artifactId:version.
     * @return Highest version number if found, else 0.0.0.
     * @throws VersionRangeResolutionException If the version range is malformed.
     */
    public String findNewestVersion(String versionRange) throws VersionRangeResolutionException {
        Artifact artifact = new DefaultArtifact(versionRange);
        VersionRangeRequest rangeRequest = new VersionRangeRequest();
        rangeRequest.setArtifact(artifact);

        for (RemoteRepository remoteRepository : this.remoteRepositories) {
            rangeRequest.addRepository(remoteRepository);
        }

        VersionRangeResult rangeResult = this.system.resolveVersionRange(this.session, rangeRequest);
        if(rangeResult.getHighestVersion() != null) {
            return rangeResult.getHighestVersion().toString();
        } else {
            ULog.error(String.format("Could not find any version for the version range %s.", versionRange));
            return "0.0.0";
        }
    }

    /**
     * Toggles the offline mode. This prevents aether from loading artifacts from remote repositories.
     * @param offline True if offline mode should be enabled.
     */
    public void setOffline(boolean offline) {
        DefaultRepositorySystemSession ds = (DefaultRepositorySystemSession) session;
        ds.setOffline(offline);
        ds.setIgnoreArtifactDescriptorRepositories(offline);
    }

    /**
     * Returns the path of the local maven repository.
     * @return Path of the local maven repository.
     */
    public File getLocalRepositoryPath() {
        return session.getLocalRepository().getBasedir();
    }

}
