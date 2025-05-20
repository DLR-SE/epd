package de.emir.tuml.ucore.runtime.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;

/**
 * Provides access to the extended logger from Log4J to enable correct display of the stacktrace in the logging
 * by using the Fully Qualified Class Name (FQCN) when wrapping the logger inside ULog.
 */
public class ExtendedLogger extends ExtendedLoggerWrapper {
    private final ExtendedLoggerWrapper logger;

    /**
     * Creates a new extended logger.
     * @apiNote This method is private. Use the create() method instead.
     * @param logger Logger to use for logging.
     */
    private ExtendedLogger(Logger logger) {
        super((AbstractLogger) logger, logger.getName(), logger.getMessageFactory());
        this.logger = this;
    }

    /**
     * Creates a new extended logger.
     * @param name Name of the logger to create.
     * @return New extended logger.
     */
    public static ExtendedLogger create(final String name) {
        final Logger wrapped = LogManager.getLogger(name);
        return new ExtendedLogger(wrapped);
    }

    /**
     * Logs a message with the trace level when using the logger inside a wrapper class.
     * @param FQCN Fully qualified class name of the wrapper class which uses this log method.
     * @param info Log message to log.
     */
    public void trace(final String FQCN,String info) {
        if(isTraceEnabled()) {
            logger.logIfEnabled(FQCN, Level.TRACE, null, info, (Throwable) null);
        }
    }

    /**
     * Logs a message with the debug level when using the logger inside a wrapper class.
     * @param FQCN Fully qualified class name of the wrapper class which uses this log method.
     * @param info Log message to log.
     */
    public void debug(final String FQCN,String info) {
        if(isDebugEnabled()) {
            logger.logIfEnabled(FQCN, Level.DEBUG, null, info, (Throwable) null);
        }
    }

    /**
     * Logs a message with the info level when using the logger inside a wrapper class.
     * @param FQCN Fully qualified class name of the wrapper class which uses this log method.
     * @param info Log message to log.
     */
    public void info(final String FQCN,String info) {
        if(isInfoEnabled()) {
            logger.logIfEnabled(FQCN, Level.INFO, null, info, (Throwable) null);
        }
    }

    /**
     * Logs a message with the warn level when using the logger inside a wrapper class.
     * @param FQCN Fully qualified class name of the wrapper class which uses this log method.
     * @param info Log message to log.
     */
    public void warn(final String FQCN,String info) {
        if(isWarnEnabled()) {
            logger.logIfEnabled(FQCN, Level.WARN, null, info, (Throwable) null);
        }
    }

    /**
     * Logs a message with the error level when using the logger inside a wrapper class.
     * @param FQCN Fully qualified class name of the wrapper class which uses this log method.
     * @param info Log message to log.
     */
    public void error(final String FQCN,String info) {
        if(isErrorEnabled()) {
            logger.logIfEnabled(FQCN, Level.ERROR, null, info, (Throwable) null);
        }
    }

    /**
     * Logs a message with the fatal level when using the logger inside a wrapper class.
     * @param FQCN Fully qualified class name of the wrapper class which uses this log method.
     * @param info Log message to log.
     */
    public void fatal(final String FQCN, String info) {
        if(isFatalEnabled()) {
            logger.logIfEnabled(FQCN, Level.FATAL, null, info, (Throwable) null);
        }
    }

    /**
     * Logs a message when using the logger inside a wrapper class.
     * @param FQCN Fully qualified class name of the wrapper class which uses this log method.
     * @param level Level to log.
     * @param info Log message to log.
     */
    public void log(final String FQCN, Level level, String info) {
        if(isEnabled(level)) {
            logger.logIfEnabled(FQCN, level, null, info, (Throwable) null);
        }
    }
}
