package de.emir.tuml.ucore.runtime.logging;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logging Interface for UCore Projects
 * 
 * Logging within UCore Projects havily depends on the ULog class, as it will be used to initialize the logging for the
 * whole system.
 * 
 * There are two ways of logging within UCore. 1) Using the ULog's static methods for easy logging, without need to
 * create a logger for each class. Thereby the ULog automatically detects the calling class 2) Use the ULog method to
 * create a static logger for a specific classifier.
 * 
 * The second method should be used if heavily logging is required and performance is required, since the static methods
 * of ULog do need to detect the Loggers name at every method invoktion.
 * 
 * @author sschweigert
 *
 */
public class ULog {

    public static interface ULogFactory {
        public void initialize(final URL url);

        public Logger createLogger(final String qualifiedName);
    }

    private static class DefaultSLF4JLogFactory implements ULogFactory {
        @Override
        public void initialize(URL url) {
            if (url != null) {
                DOMConfigurator.configure(url);
            } else {
                System.err.println("No valid Log configuration provided, using default configuration");
                BasicConfigurator.configure();
            }
        }

        @Override
        public Logger createLogger(String qualifiedName) {
            return LoggerFactory.getLogger(qualifiedName);
        }
    }

    private static ULog theInstance = new ULog();
    private ULogFactory mLoggerFactory = new DefaultSLF4JLogFactory();

    public static ULog getInstance() {
        return theInstance;
    }

    private boolean mInitialized = false;

    private int mIntendation = 0;
    private String mIntendationStr = "";

    private ULog() {
    }

    public void initialize(final File logFile) {
        URL url = null;
        if (logFile != null && logFile.exists()) {
            try {
                url = logFile.toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                url = null;
            }
        }
        initialize(url);
    }

    public void initialize(URL url) {
        mInitialized = true;
        if (mLoggerFactory != null)
            mLoggerFactory.initialize(url);
    }

    public static String getCallerClassName() {
        // using reflections should be quite fast, but unfortunately oracle removed (not only deprecated!) this method
        // return sun.reflect.Reflection.getCallerClass(callStackDepth).getName();
        StackTraceElement[] st = Thread.currentThread().getStackTrace();

        if (st[3].getClassName().equals("de.emir.tuml.ucore.runtime.logging.ULog") && st.length > 3)
            return st[4].getClassName(); // called by one of the methods, with intendation
        return st[3].getClassName();
    }

    public static int getIntendation() {
        if (theInstance == null)
            return -1;
        return theInstance.mIntendation;// mFormatter.getIntendation();
    }

    public static void setIntendation(final int intendation) {
        if (theInstance == null)
            return;

        int oldInt = theInstance.mIntendation;
        theInstance.mIntendation = intendation;
        if (theInstance.mIntendation < 0)
            theInstance.mIntendation = 0;
        theInstance.mIntendationStr = "";
        for (int i = 0; i < theInstance.mIntendation; i++)
            theInstance.mIntendationStr += "\t";

        Logger l = getLogger(Logger.ROOT_LOGGER_NAME);
        Enumeration app = org.apache.log4j.Logger.getRootLogger().getAllAppenders();
        while (app.hasMoreElements()) {
            Object obj = app.nextElement();
            if (obj instanceof Appender) {
                Appender a = (Appender) obj;
                if (a.getLayout() instanceof PatternLayout) {
                    PatternLayout pl = (PatternLayout) a.getLayout();
                    String pattern = pl.getConversionPattern();
                    int idx = pattern.indexOf("%m");
                    String pre = pattern.substring(0, idx - oldInt);
                    String pos = pattern.substring(idx);
                    pattern = pre + theInstance.mIntendationStr + pos;
                    pl.setConversionPattern(pattern);
                }
            }
        }
    }

    public Appender getAppender(String name) {
        Logger l = getLogger(Logger.ROOT_LOGGER_NAME);
        Enumeration app = org.apache.log4j.Logger.getRootLogger().getAllAppenders();
        while (app.hasMoreElements()) {
            Object obj = app.nextElement();
            if (obj instanceof Appender) {
                Appender a = (Appender) obj;
                if (a.getName().equals(name))
                    return a;
            }
        }
        return null;
    }

    public void changeLogLevel(Appender appender, Level newLevel) {
        if (appender instanceof AppenderSkeleton) {
            LogManager.getRootLogger().setLevel(newLevel);
            ((AppenderSkeleton) appender).setThreshold(newLevel);
        } else {
            ULog.error("Failed to change appender threshold");
        }
    }

    public static void error(final int intendationDelta, final String message) {
        if (theInstance != null)
            addIndentation(intendationDelta);
        error(message);
    }

    public static void error(final String message, final int intendationDelta) {
        error(message);
        if (theInstance != null)
            addIndentation(intendationDelta);
    }

    public static void error(final Exception exception) {
        error(exception.getMessage());
    }

    public static void error(final String message) {
        try {
            final Logger l = getLogger(getCallerClassName());
            if (l != null && l.isWarnEnabled())
                l.error(message);
        } catch (final Exception e) {
            System.err.println("Failed to Log message: " + message);
        }
    }

    public static void warn(final int intendationDelta, final String message) {
        if (theInstance != null)
            addIndentation(intendationDelta);
        warn(message);
    }

    public static void warn(final String message, final int intendationDelta) {
        warn(message);
        if (theInstance != null)
            addIndentation(intendationDelta);
    }

    public static void warn(final String message) {
        try {
            final Logger l = getLogger(getCallerClassName());
            if (l != null & l.isWarnEnabled())
                l.warn(message);
        } catch (final Exception e) {
            System.err.println("Failed to Log message: " + message);
        }
    }

    public static void info(final int intendationDelta, final String message) {
        if (theInstance != null)
            addIndentation(intendationDelta);
        info(message);
    }

    public static void info(final String message, final int intendationDelta) {
        info(message);
        if (theInstance != null)
            addIndentation(intendationDelta);
    }

    public static void info(final String message) {
        try {
            final Logger l = getLogger(getCallerClassName());
            if (l != null && l.isInfoEnabled())
                l.info(message);
        } catch (final Exception e) {
            System.out.println("Failed to Log message: " + message);
        }
    }

    public static void debug(final int intendationDelta, final String message) {
        if (theInstance != null)
            addIndentation(intendationDelta);
        debug(message);
    }

    public static void debug(final String message, final int intendationDelta) {
        debug(message);
        if (theInstance != null)
            addIndentation(intendationDelta);
    }

    public static void debug(final String message) {
        try {
            final Logger l = getLogger(getCallerClassName());
            if (l != null && l.isDebugEnabled())
                l.debug(message);
        } catch (final Exception e) {
            System.out.println("Failed to Log message: " + message);
        }
    }

    public static void trace(final int intendationDelta, final String message) {
        if (theInstance != null)
            addIndentation(intendationDelta);
        trace(message);
    }

    public static void trace(final String message, final int intendationDelta) {
        trace(message);
        if (theInstance != null)
            addIndentation(intendationDelta);
    }

    public static void addIndentation(int intendationDelta) {
        setIntendation(getIntendation() + intendationDelta);
    }

    public static void trace(final String message) {
        try {
            final Logger l = getLogger(getCallerClassName());
            if (l != null && l.isTraceEnabled())
                l.trace(message);
        } catch (final Exception e) {
            System.out.println("Failed to Log message: " + message);
        }
    }

    /**
     * get or create a logger with a qualified name
     * 
     * @note if the ULog has not been initialized this method will initialize the currently used ULog instance with
     *       default values
     * @param fqcn
     * @return logger
     */
    public static Logger getLogger(String fqcn) {
        try {
            if (theInstance != null && theInstance.mInitialized == false)
                theInstance.initialize();
            if (theInstance != null && theInstance.mLoggerFactory != null)
                return theInstance.mLoggerFactory.createLogger(fqcn);
            return LoggerFactory.getLogger(fqcn);
        } catch (Exception | Error err) {
            err.printStackTrace();
            return null;
        }
    }

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public void initialize() {
        if (mInitialized)
            return;
        // this method is called as automatic init, if no manual initialisation has been done before
        // we try to use a standard configuration file, e.g. <ProjectDir>/log/log4j.xml or use the default configuration
        initialize(new File("log/log4j.xml"));
    }

    /**
     * Allows to register a custumized ULogFactory to create a new Logger
     * 
     * @warn this method should not be used unless you are sure that you need it, also it will be applied system wide,
     *       e.g. changes the logging of the whole system.
     * @param fac
     */
    public void registerLogFactory(ULogFactory fac) {
        mLoggerFactory = fac;
    }

    /**
     * Utility to use a log level, depending on the variable
     * 
     * @param logger
     * @param level
     * @param message
     */
    public static void log(Logger logger, Level level, String message) {
        if (level == Level.ERROR)
            logger.error(message);
        else if (level == Level.WARN)
            logger.warn(message);
        else if (level == Level.INFO)
            logger.info(message);
        else if (level == Level.DEBUG)
            logger.debug(message);
        else if (level == Level.TRACE)
            logger.trace(message);
    }

    public static void log(Logger logger, Level level, String message, int indentationIncrement) {
        log(logger, level, message);
        setIntendation(getIntendation() + indentationIncrement);
    }

    public static void log(int indentationIncrement, Logger logger, Level level, String message) {
        setIntendation(getIntendation() + indentationIncrement);
        log(logger, level, message);
    }

}
