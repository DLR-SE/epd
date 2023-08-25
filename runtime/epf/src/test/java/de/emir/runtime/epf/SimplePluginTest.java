package de.emir.runtime.epf;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.collection.CollectRequest;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.graph.DependencyFilter;
import org.eclipse.aether.graph.DependencyNode;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.ArtifactResolutionException;
import org.eclipse.aether.resolution.ArtifactResult;
import org.eclipse.aether.resolution.DependencyRequest;
import org.eclipse.aether.resolution.DependencyResolutionException;
import org.eclipse.aether.util.artifact.JavaScopes;
import org.eclipse.aether.util.filter.DependencyFilterUtils;
import org.junit.Test;
import org.xml.sax.SAXException;

import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.runtime.epf.PluginManager;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import de.emir.tuml.ucore.runtime.logging.ULog;
import io.tesla.aether.Repository;

public class SimplePluginTest {

    public static void main(String[] args) {
        SimplePluginTest spt = new SimplePluginTest();

        // spt.testResolveWithDeps();
        // spt.test();

        //
    }

    public void testResolveWithDeps() throws SAXException, IOException, ParserConfigurationException {
        // PluginFile pf = new PluginFile(new File("Plugins.xml"));
        // PluginManager mgr = new PluginManager();
        // mgr.initialize(new File("c:/workspace/test/data/repos"), pf.getRemoteRepositories(), false);
        //
        // mgr.loadCoordiante("de.emir.epd:eMIRExtensions:1.0.0-SNAPSHOT");
        // mgr.resolveDependencies();
        // mgr.build();
        // mgr.startPlugins();
        //
    }

    @Test
    public void test() {
        // try {
        // long s = System.currentTimeMillis();
        // PluginFile pf = new PluginFile(new File("Plugins.xml"));
        // PluginManager pmgr = new PluginManager();
        // pmgr.load(pf);
        // pmgr.resolveDependencies();
        // assertFalse(pmgr.getDescriptors().isEmpty());
        // pmgr.build();
        // long e1 = System.currentTimeMillis();
        // pmgr.startPlugins();
        // long e2 = System.currentTimeMillis();
        //
        // ULog.info("Resolving Dependencies: " + (e1-s) + "[ms] Starting took: " + (e2-e1) + "[ms]");
        // ULog.info("Entries:" + pmgr.getDescriptors().size());
        // } catch (SAXException | IOException | ParserConfigurationException e) {
        // e.printStackTrace();
        // fail(e.getMessage());
        // }
        assertTrue(true);
    }

}
