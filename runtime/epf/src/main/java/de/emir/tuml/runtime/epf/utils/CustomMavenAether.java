package de.emir.tuml.runtime.epf.utils;

import java.io.File;
import java.util.List;

import org.eclipse.aether.DefaultRepositorySystemSession;

import de.emir.tuml.runtime.epf.ObservableRepository;
import de.emir.tuml.runtime.epf.internal.LogTransferListener;
import io.tesla.aether.Repository;
import io.tesla.aether.internal.DefaultTeslaAether;

public class CustomMavenAether extends DefaultTeslaAether {

    public CustomMavenAether(File file, String[] strings) {
        super(file, strings);

        DefaultRepositorySystemSession ds = (DefaultRepositorySystemSession) session;
        ds.setOffline(true);
        ds.setTransferListener(new LogTransferListener());
        // suppress extrem long logging output
        ds.setTransferListener(null);
        ds.setRepositoryListener(null);
    }

    public CustomMavenAether(File localRepos, List<ObservableRepository> remoteRepositories) {
        super(localRepos.getAbsolutePath(), remoteRepositories.toArray(new Repository[remoteRepositories.size()]));

        DefaultRepositorySystemSession ds = (DefaultRepositorySystemSession) session;
        ds.setOffline(true);
        ds.setTransferListener(new LogTransferListener());
        // suppress extrem long logging output
        ds.setTransferListener(null);
        ds.setRepositoryListener(null);
    }

    public void setOffline(boolean offline) {
        DefaultRepositorySystemSession ds = (DefaultRepositorySystemSession) session;
        ds.setOffline(offline);
        ds.setIgnoreArtifactDescriptorRepositories(offline);
    }

    public File getLocalRepositoryPath() {
        return session.getLocalRepository().getBasedir();
    }

}
