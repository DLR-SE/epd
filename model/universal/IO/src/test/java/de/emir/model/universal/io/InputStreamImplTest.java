package de.emir.model.universal.io;

import de.emir.model.universal.io.impl.InputStreamImpl;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public class InputStreamImplTest {

    public static void main(String[] args) throws URISyntaxException, IOException {
        InputStreamImplTest test = new InputStreamImplTest();
        test.testInputStreamNullPointer();
    }

    /**
     * Test a previously buggy InputStreamImpl constructor which had a uninitialized variable. This produced
     * nullpointer exceptions
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test
    public void testInputStreamNullPointer() throws URISyntaxException, IOException {
        // get the path to the current file (class/jar) as a simulated input stream
        String path = InputStreamImplTest.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        if (new File(path).isDirectory()) {
            // path might point to the class directory, however we want the class or jar here directly
            path += File.separator
                    + InputStreamImplTest.class.getPackage().getName().replace(".", File.separator)
                    + File.separator
                    + InputStreamImplTest.class.getSimpleName() + ".class";
        }

        File myself = new File(path);
        try (FileInputStream fileStream = new FileInputStream(myself)) {
            // create the constructor with the previously buggy constructor
            IInputStream stream = new InputStreamImpl(fileStream);
            // check stream output
            byte[] buffer = stream.toArray();
            Assert.assertNotNull(buffer);
            Assert.assertTrue(buffer.length > 0);
        }
    }
}
