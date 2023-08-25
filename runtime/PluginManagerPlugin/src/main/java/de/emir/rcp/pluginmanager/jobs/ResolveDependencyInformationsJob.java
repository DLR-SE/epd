package de.emir.rcp.pluginmanager.jobs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.maven.model.Model;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.aether.artifact.Artifact;

import de.emir.rcp.jobs.IJob;
import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.pluginmanager.manager.PmManager;
import de.emir.rcp.pluginmanager.model.DependencyData;
import de.emir.rcp.pluginmanager.model.DependencyState;
import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;

public class ResolveDependencyInformationsJob implements IJob {

    private boolean cancelled;

    @Override
    public void run(IProgressMonitor monitor) {

        PmManager pm = ServiceManager.get(PmManager.class);
        ProductFile pf = pm.getModelProvider().getProductFile();

        if (pf == null) {
            return;
        }

        MavenUtil mu = new MavenUtil(pf, false);

        List<ObservableDependency> deps = pf.getDependencies();

        float steps = deps.size();
        int i = 0;
        for (ObservableDependency d : deps) {

            monitor.setProgress(i / steps * 100.0f);
            monitor.setMessage(d.getGroupId() + "." + d.getArtifactId() + "-" + d.getVersion());
            i++;

            if (cancelled == true) {
                return;
            }

            DependencyData dd = pm.getDependencyData(d);
            Artifact artifact = mu.resolveArtifact(d.getCoordinate());
            if (artifact == null) {
                dd.setState(DependencyState.NOT_FOUND);
                continue;
            }

            File file = artifact.getFile();

            if (file.exists() == false) {
                dd.setState(DependencyState.NOT_FOUND);
                continue;
            }

            Model model = null;

            if (cancelled == true) {
                return;
            }

            model = mu.resolveJarModel(file);


            if (model == null) {
                dd.setState(DependencyState.NOT_FOUND);
                continue;
            }

            Properties props = model.getProperties();

            if (props == null) {
                dd.setState(DependencyState.RESOLVED_LIB);
                continue;
            }

            Object pluginProp = props.get(PMBasics.MAVEN_PLUGIN_PROPERTY);

            if (pluginProp != null) {

                dd.setState(DependencyState.RESOLVED_PLUGIN);

            } else {
                dd.setState(DependencyState.RESOLVED_LIB);
            }

            // TODO: How to handle implicit dependencies?

        }

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
        cancelled = true;

    }

    @Override
    public String getTitle() {
        return "Resolving Dependency Informations...";
    }

}
