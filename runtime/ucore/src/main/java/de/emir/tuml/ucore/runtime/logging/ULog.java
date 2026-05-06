package de.emir.tuml.ucore.runtime.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.message.ParameterizedMessage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

    // Reference table for loggers automatically created in the static methods. Since the logging methods
    // are wrapped in ULog, the ExtendedLogger interface is used which makes use of the fully qualified class name of ULog
    // to enable displaying correct line numbers when calling via static methods.
    private static final Map<String, ExtendedLogger> loggers = new ConcurrentHashMap<>();

    private static ULog instance;
    private boolean mInitialized = false;

    /**
     * Gets the ULog singleton instance or creates one if it does not exist.
     *
     * @return ULog singleton instance.
     */
    public static synchronized ULog getInstance() {
        if (instance == null) {
            instance = new ULog();
        }
        return instance;
    }

    /**
     * Creates a ULog instance. Private because of singleton. Use getInstance() instead.
     */
    private ULog() {
    }

    /**
     * Configures the logger based on a Log2J 2.0 XML configuration file.
     *
     * @param logFile Log4J 2.0 configuration file.
     */
    public void initialize(final File logFile) {
        URL url = null;
        if (logFile != null && logFile.exists()) {
            try {
                url = logFile.toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        initialize(url);
    }

    /**
     * Configures the logger based on a Log2J 2.0 XML configuration file path.
     *
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
     *
     * @param newLevel New level of the loggers currently registered.
     */
    public void changeAllLogLevel(Level newLevel) {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        ctx.getRootLogger().setLevel(newLevel);
        Configurator.setLevel(ctx.getRootLogger(), newLevel);
        Collection<org.apache.logging.log4j.core.Logger> loggers = ctx.getLoggers();
        for (org.apache.logging.log4j.core.Logger logger : loggers) {
            logger.setLevel(newLevel);
            Configurator.setLevel(logger, newLevel);
        }
        ctx.updateLoggers();
    }

    /**
     * Retrieves the static logger for the given name and logs an event.
     *
     * @param loggerName Name of the logger to retrieve.
     * @param level      Level to log.
     * @param message    Message to log.
     * @implNote This method is used by all static logging methods in ulog. The Logger name should equal the fully
     * qualified class name of the caller class and is automatically retrieved from the stack trace when using
     * ULogs static logging methods.
     */
    private static void log(String loggerName, Level level, String message) {
        getStaticLogger(loggerName).log(ULog.class.getName(), level, message);
    }

    /**
     * Static logging method which retrieves the logger name from the caller class.
     *
     * @param level   Level to log.
     * @param message Message to log.
     */
    public static void log(Level level, String message) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                level,
                message
        );
    }

    /**
     * Static logging method which forwards messages to the internal logger implementation. This method preserves some
     * unique features of the internal logger and extends its functionality further. It enables logging of messages
     * with and without a template (for formatting). If the last argument is an exception, all messages will be printed
     * and the exception will be printed with its full stack trace. It simplifies exception logging.
     *
     * @param level Level to log.
     * @param template Message template
     * @param objects Messages to log.
     */
    public static void log(Level level, String template, Object... objects) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                level,
                template,
                objects
        );
    }

    /**
     * Static logging method which forwards messages to the internal logger implementation. This method preserves some
     * unique features of the internal logger and extends its functionality further. It enables logging of messages
     * with and without a template (for formatting). If the last argument is an exception, all messages will be printed
     * and the exception will be printed with its full stack trace. It simplifies exception logging.
     *
     * @param loggerName Name of the logger
     * @param level Level to log.
     * @param template Message template
     * @param objects Messages to log.
     */
    public static void log(final String loggerName, Level level, String template, Object... objects) {
        ExtendedLogger logger = getStaticLogger(loggerName);
        // check if level is enabled, otherwise skip the following logging overhead
        if(logger.isEnabled(level)) {
            // check if last object is an exception. This will be printed with its complete stacktrace
            boolean isLastException = objects[objects.length - 1] instanceof Throwable;
            // check how many format placeholders are present and check how many objects are given. This is
            int nbArguments = ParameterizedMessage.countArgumentPlaceholders(template);
            int nbObjects = objects.length;
            int difference = isLastException ? nbObjects - nbArguments - 1 : nbObjects - nbArguments;
            int lastIndex = isLastException ? nbObjects - 1 : nbObjects;
            // if we have more arguments than actual format placeholders, we just concatenate the messages. This
            // prevents errors if a user regularly changes the number of arguments in a logging function for debugging
            // purposes. Everything else is formatted as usual.
            if (difference > 0) {
                final String message = Arrays.stream(
                                Arrays.copyOfRange(objects, lastIndex - difference, lastIndex)
                        )
                        .map(o -> o == null ? "null" : o) // avoid null pointer due to toString in the next call
                        .map(Object::toString)
                        .collect(Collectors.joining(" "));

                template += " " + message;
            }

            if (isLastException) {
                // if the last message object is an exception the complete stack trace will be printed. Everything else
                // before this will be printed as usual. This is a unique feature of the internal logger
                final Throwable t = (Throwable) objects[objects.length - 1];
                logger.logIfEnabled(
                        loggerName,
                        level,
                        null,
                        template,
                        Arrays.copyOf(objects, objects.length - 1),
                        t
                );
            } else {
                logger.logIfEnabled(loggerName, level, null, template, objects);
            }
        }
    }

    /**
     * Logs an exception with the level fatal.
     *
     * @param exception Exception to log.
     */
    public static void fatal(final Exception exception) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.FATAL,
                exception.getMessage()
        );
    }


    /**
     * Logs a fatal message.
     *
     * @param message Message to log.
     */
    public static void fatal(final String message) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.FATAL,
                message
        );
    }

    /**
     * Logs fatal messages.
     *
     * @param messages Messages to log.
     */
    public static void fatal(final Object... messages) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.FATAL,
                "",
                messages
        );
    }

    /**
     * Logs fatal messages.
     *
     * @param template Message template
     * @param messages Messages to log.
     */
    public static void fatal(final String template, final Object... messages) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.FATAL,
                template,
                messages
        );
    }

    /**
     * Logs an exception with the level error.
     *
     * @param exception Exception to log.
     */
    public static void error(final Exception exception) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.ERROR,
                exception.getMessage()
        );
    }

    /**
     * Logs an error message.
     *
     * @param message Message to log.
     */
    public static void error(final String message) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.ERROR,
                message
        );
    }

    /**
     * Logs error messages.
     *
     * @param messages Messages to log.
     */
    public static void error(final Object... messages) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.ERROR,
                "",
                messages
        );
    }

    /**
     * Logs error messages.
     *
     * @param template Message template
     * @param messages Messages to log.
     */
    public static void error(final String template, final Object... messages) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.ERROR,
                template,
                messages
        );
    }

    /**
     * Logs a warn message.
     *
     * @param message Message to log.
     */
    public static void warn(final String message) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.WARN,
                message
        );
    }

    /**
     * Logs warn messages.
     *
     * @param messages Messages to log.
     */
    public static void warn(final Object... messages) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.WARN,
                "",
                messages
        );
    }

    /**
     * Logs warn messages.
     *
     * @param template Message template
     * @param messages Messages to log.
     */
    public static void warn(final String template, final Object... messages) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.WARN,
                template,
                messages
        );
    }

    /**
     * Logs an info message.
     *
     * @param message Message to log.
     */
    public static void info(final String message) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.INFO,
                message
        );
    }

    /**
     * Logs info messages.
     *
     * @param messages Messages to log.
     */
    public static void info(final Object... messages) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.INFO,
                "",
                messages
        );
    }

    /**
     * Logs info messages.
     *
     * @param template Message template
     * @param messages Messages to log.
     */
    public static void info(final String template, final Object... messages) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.INFO,
                template,
                messages
        );
    }

    /**
     * Logs a debug message.
     *
     * @param message Message to log.
     */
    public static void debug(final String message) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.DEBUG,
                message
        );
    }

    /**
     * Logs debug messages.
     *
     * @param messages Messages to log.
     */
    public static void debug(final Object... messages) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.DEBUG,
                "",
                messages
        );
    }

    /**
     * Logs debug messages.
     *
     * @param template Message template
     * @param messages Messages to log.
     */
    public static void debug(final String template, final Object... messages) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.DEBUG,
                template,
                messages
        );
    }

    /**
     * Logs a debug message. This overloaded method exists to handle legacy code. Indentation argument is ignored.
     *
     * @param message     Message to log.
     * @param indentation is ignored.
     */
    public static void debug(final String message, int indentation) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.DEBUG,
                message
        );
    }

    /**
     * Logs a debug message. This overloaded method exists to handle legacy code. Indentation argument is ignored.
     *
     * @param message     Message to log.
     * @param indentation is ignored.
     */
    public static void debug(int indentation, final String message) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.DEBUG,
                message
        );
    }

    /**
     * Logs a trace message.
     *
     * @param message Message to log.
     */
    public static void trace(final String message) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.TRACE,
                message
        );
    }

    /**
     * Logs trace messages.
     *
     * @param messages Messages to log.
     */
    public static void trace(final Object... messages) {
        final String callerName = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName();
        final String message = Arrays.stream(messages).map(Object::toString).collect(Collectors.joining(" "));
        log(
                callerName,
                Level.TRACE,
                message
        );
    }

    /**
     * Logs trace messages.
     *
     * @param template Message template
     * @param messages Messages to log.
     */
    public static void trace(final String template, final Object... messages) {
        log(
                StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName(),
                Level.TRACE,
                template,
                messages
        );
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
     *
     * @param loggerName Name of the logger to retrieve. Should be the fully qualified class name of the caller class.
     * @return Extended logger based on the caller class.
     */
    private static ExtendedLogger getStaticLogger(String loggerName) {
        if (!loggers.containsKey(loggerName)) {
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
