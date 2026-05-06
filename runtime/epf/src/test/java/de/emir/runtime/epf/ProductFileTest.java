package de.emir.runtime.epf;

import de.emir.tuml.runtime.epf.ObservableDependency;
import de.emir.tuml.runtime.epf.ObservableRepository;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.UCoreModel;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

public class ProductFileTest {

    static {
        UCoreModel.init();// just init the model
    }

    public static void main(String[] args) {
        ProductFileTest test = new ProductFileTest();
        test.localRepositoryTest();
        test.productFileTest();
    }

    private ProductFile getProductFile(String productFile){
        try {
            // find product xml within resource folder
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(productFile);
            assertNotNull(resource);
            File file = new File(resource.getFile());
            // create a new product file, might throw if file fails to load
            return new ProductFile(file);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void productFileTest() {
        ProductFile product = getProductFile("TestProduct.xml");

        assertEquals("EPD", product.getName());
        assertEquals("eMaritime Prototype Display", product.getDescription());
        assertEquals("1.0.0", product.getVersion().toString());
        List<File> workspaces = product.getWorkspaces();
        assertEquals(1, workspaces.size());
        assertEquals("/home/user/code/pom.xml", workspaces.getFirst().getAbsolutePath());

        List<ObservableDependency> dependencies = product.getDependencies();
        assertEquals(1, dependencies.size());
        assertEquals("de.dlr-se.emir.epd", dependencies.getFirst().getGroupId());
        assertEquals("MapView", dependencies.getFirst().getArtifactId());
        assertEquals("1.0.0", dependencies.getFirst().getVersion());

        List<ObservableRepository> repositories = product.getRemoteRepositories();
        assertEquals(1, repositories.size());
        assertEquals("MavenCentral", repositories.getFirst().getId());
        assertEquals("https://repo1.maven.org/maven2/", repositories.getFirst().getUrl());
        assertEquals("", repositories.getFirst().getUsername());
        assertEquals("", repositories.getFirst().getPassword());
    }

    @Test
    public void localRepositoryTest() {
        // folder needs to exist, therefore we create a default one here and delete it later
        File relativeLocalRepositoryPath = new File("repository");
        try {
            if (!relativeLocalRepositoryPath.exists()) {
                assertTrue(relativeLocalRepositoryPath.mkdir());
            }
            // "normal" relative path in localRepos
            ProductFile relativeProduct = getProductFile("RelativeLocalRepositoryProduct.xml");
            assertEquals(relativeLocalRepositoryPath.getAbsolutePath(), relativeProduct.getLocalRepository());

            // path using the CWD_FOLDER variable
            ProductFile cwdProduct = getProductFile("CWDVariableProduct.xml");
            assertEquals(relativeLocalRepositoryPath.getAbsolutePath(), cwdProduct.getLocalRepository());
        } finally {
            assertTrue(relativeLocalRepositoryPath.delete());
        }

        // create local repository directory where the product file lies and delete it after the test
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("ProductVariableProduct.xml");
        File productFolder = new File(resource.getFile()).getParentFile();
        ProductFile productVariableProduct = getProductFile("ProductVariableProduct.xml");
        // folder is equal to the folder where the product xml is defined
        assertEquals(productFolder.getAbsolutePath(), productVariableProduct.getLocalRepository());

        // path using the HOME_FOLDER variable
        File defaultRepository = new File(System.getProperty("user.home") + File.separator + ".m2" + File.separator + "repository");
        // file needs to exist, otherwise we cannot test it!
        if (defaultRepository.exists()){
            ProductFile homeProduct = getProductFile("HomeVariableProduct.xml");
            assertEquals(defaultRepository.getAbsolutePath(), homeProduct.getLocalRepository());
        }
    }
}
