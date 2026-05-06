package test.de.emir.tuml.ucore.runtime;

import de.emir.tuml.ucore.UCoreModel;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.apache.logging.log4j.Level;
import org.junit.Test;


/**
 * This test just checks for compatibility issues if log methods are changed.
 */
public class ULogTest {

    static {
        UCoreModel.init();// just init the model
    }

    public static void main(String[] args) {
        new ULogTest().VariadicArgsTest();
    }

    /**
     * Some general logging tests. Since it is complicated to check what is actually printed for each logging call, this
     * test needs to be performed manually. Thus, check if all given information are printed correctly.
     */
    @Test
    public void VariadicArgsTest() {
        ULog.getInstance().changeAllLogLevel(Level.ALL);

        ULog.log(Level.ALL, "Hello World");
        ULog.log(Level.ALL, "Hello", " World", 1);
        System.out.println(); // just visual separation

        ULog.trace("Hello World");
        ULog.trace("Hello", "World");
        ULog.trace("Hello", "World", 1);
        ULog.trace("{} {}", "Hello", "World");
        ULog.trace("{} {}", "Hello", "World", 1);
        System.out.println(); // just visual separation


        ULog.debug("Hello World");
        ULog.debug("Hello", "World");
        ULog.debug("Hello", "World", 1);
        ULog.debug("{} {}", "Hello", "World");
        ULog.debug("{} {}", "Hello", "World", 1);
        System.out.println(); // just visual separation

        ULog.info("Hello World");
        ULog.info("Hello", "World");
        ULog.info("Hello", "World", 1);
        ULog.info("{} {}", "Hello", "World");
        ULog.info("{} {}", "Hello", "World", 1);
        System.out.println(); // just visual separation

        ULog.warn("Hello World");
        ULog.warn("Hello", "World");
        ULog.warn("Hello", "World", 1);
        ULog.warn("{} {}", "Hello", "World");
        ULog.warn("{} {}", "Hello", "World", 1);
        System.out.println(); // just visual separation

        ULog.error("Hello World");
        ULog.error("Hello", "World");
        ULog.error("Hello", "World", 1);
        ULog.error("{} {}", "Hello", "World");
        ULog.error("{} {}", "Hello", "World", 1);
        ULog.error("Hello", "World", 1, new RuntimeException("World"));
        ULog.error(new RuntimeException("Hello"), new RuntimeException("World"));
        System.out.println(); // just visual separation

        ULog.fatal("Hello World");
        ULog.fatal("Hello", "World");
        ULog.fatal("Hello", "World", 1);
        ULog.fatal("{} {}", "Hello", "World");
        ULog.fatal("{} {}", "Hello", "World", 1);
        ULog.fatal("Hello", "World", 1, new RuntimeException("World"));
        ULog.fatal(new RuntimeException("Hello"), new RuntimeException("World"));
        System.out.println(); // just visual separation
    }
}
