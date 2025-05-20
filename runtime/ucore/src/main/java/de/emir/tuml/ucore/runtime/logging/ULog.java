package de.emir.tuml.ucore.runtime.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Logging Interface for UCore Projects
 * Logging within UCore Projects depends on the ULog class, as it will be used to initialize the logging for the
 * whole system.
 * There are two ways of logging within UCore. 1) Using the ULog's static methods for easy logging. ULog automatically
 * retrieves the caller class name and creates a logger for each class. 2) Use the ULog method to create a
 * logger for a specific classifier. This is synonymous to calling LogManager.getLogger().
 *
 */
public class ULog {

    private static ULog instance;
    // Reference table for loggers automatically created in the static methods. Since the logging methods
    // are wrapped in ULog, the ExtendedLogger interface is used which makes use of the fully qualified class name of ULog
    // to enable displaying correct line numbers when calling via static methods.
    private static Map<String, ExtendedLogger> loggers = new ConcurrentHashMap<>();

    /**
     * Creates a ULog instance. Private because of singleton. Use getInstance() instead.
     */
    private ULog() {
    }

    /**
     * Gets the ULog singleton instance or creates one if it does not exist.
     * @return ULog singleton instance.
     */
    public static synchronized ULog getInstance() {
        if(instance == null) {
            instance = new ULog();
        }
        return instance;
    }

    private boolean mInitialized = false;

    /**
     * Configures the logger based on a Log2J 2.0 XML configuration file.
     * @param logFile Log4J 2.0 configuration file.
     */
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

    /**
     * Configures the logger based on a Log2J 2.0 XML configuration file path.
     * @param url Log4J 2.0 configuration file url.
     */
    public void initialize(URL url) {
        if (url != null) {
            try {
                Configurator.reconfigure(url.toURI());
                return;
            } catch (URISyntaxException e) {
                System.err.printf("Could not parse URL: %s.", e.getMessage());
            }
        }
        System.err.println("No valid Log configuration provided, using default configuration");
        //Configurator.initialize(new DefaultConfiguration());
        mInitialized = true;
    }

    /**
     * Changes the log level of all loggers currently registered.
     * @param newLevel New level of the loggers currently registered.
     */
    public void changeAllLogLevel(Level newLevel) {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        ctx.getRootLogger().setLevel(newLevel);
        Configurator.setLevel(ctx.getRootLogger(), newLevel);
        Collection<org.apache.logging.log4j.core.Logger> loggers = ctx.getLoggers();
        for(org.apache.logging.log4j.core.Logger logger : loggers) {
            logger.setLevel(newLevel);
            Configurator.setLevel(logger, newLevel);
        }
        ctx.updateLoggers();
    }

    /**
     * Retrieves the static logger for the given name and logs an event.
     * @implNote This method is used by all static logging methods in ulog. The Logger name should equal the fully
     * qualified class name of the caller class and is automatically retrieved from the stack trace when using
     * ULogs static logging methods.
     * @param loggerName Name of the logger to retrieve.
     * @param level Level to log.
     * @param message Message to log.
     */
    private static void log(String loggerName, Level level, String message) {
        getStaticLogger(loggerName).log(ULog.class.getName(), level, message);
    }

    /**
     * Static logging method which retrieves the logger name from the caller class.
     * @param level Level to log.
     * @param message Message to log.
     */
    public static void log(Level level, String message) {
        log(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass().getName(), level, message);
    }

    /**
     * Logs an exception with the level fatal.
     * @param exception Exception to log.
     */
    public static void fatal(final Exception exception) {
        log(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass().getName(), Level.FATAL, exception.getMessage());
    }

    /**
     * Logs a fatal message.
     * @param message Message to log.
     */
    public static void fatal(final String message) {
        log(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass().getName(), Level.FATAL, message);
    }

    /**
     * Logs an exception with the level error.
     * @param exception Exception to log.
     */
    public static void error(final Exception exception) {
        log(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass().getName(), Level.ERROR, exception.getMessage());
    }

    /**
     * Logs an error message.
     * @param message Message to log.
     */
    public static void error(final String message) {
        log(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass().getName(), Level.ERROR, message);
    }

    /**
     * Logs a warn message.
     * @param message Message to log.
     */
    public static void warn(final String message) {
        log(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass().getName(), Level.WARN, message);
    }

    /**
     * Logs an info message.
     * @param message Message to log.
     */
    public static void info(final String message) {
        log(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass().getName(), Level.INFO, message);
    }

    /**
     * Logs a debug message.
     * @param message Message to log.
     */
    public static void debug(final String message) {
        log(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass().getName(), Level.DEBUG, message);
    }

    /**
     * Logs a debug message. This overloaded method exists to handle legacy code. Indentation argument is ignored.
     * @param message Message to log.
     * @param indentation is ignored.
     */
    public static void debug(final String message, int indentation) {
        log(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass().getName(), Level.DEBUG, message);
    }

    /**
     * Logs a debug message. This overloaded method exists to handle legacy code. Indentation argument is ignored.
     * @param message Message to log.
     * @param indentation is ignored.
     */
    public static void debug(int indentation, final String message) {
        log(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass().getName(), Level.DEBUG, message);
    }

    /**
     * Logs a trace message.
     * @param message Message to log.
     */
    public static void trace(final String message) {
        log(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass().getName(), Level.TRACE, message);
    }

    /**
     * Gets or creates a logger with a qualified name.
     *
     * @param fqcn Name of the logger.
     * @return logger.
     */
    public static org.apache.logging.log4j.Logger getLogger(String fqcn) {
        try {
            return LogManager.getLogger(fqcn);
        } catch (Exception | Error err) {
            err.printStackTrace();
            return null;
        }
    }

    /**
     * Gets or creates a logger by passing the caller class.
     *
     * @param clazz Class of the caller which requests the logger.
     * @return logger.
     */
    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    /**
     * Gets or creates a logger for each class which accesses the ULog static logging methods.
     * @param loggerName Name of the logger to retrieve. Should be the fully qualified class name of the caller class.
     * @return Extended logger based on the caller class.
     */
    private static ExtendedLogger getStaticLogger(String loggerName) {
        if(!loggers.containsKey(loggerName)) {
            loggers.put(loggerName, ExtendedLogger.create(loggerName));
        }
        return loggers.get(loggerName);
    }

    /**
     * Loads the log4j config to configure the loggers.
     */
    public void initialize() {
        if (mInitialized)
            return;
        // this method is called as automatic init, if no manual initialisation has been done before
        // we try to use a standard configuration file, e.g. <ProjectDir>/log/log4j.xml or use the default configuration
        initialize(new File("log/log4j.xml"));
    }
}
