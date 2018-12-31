package tech.overpass.conferwebforum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Log {

    private static final Logger LOGGER = LoggerFactory.getLogger(Log.class.getSimpleName());

    public static void d(String message, Throwable throwable) {
        LOGGER.debug(message, throwable);
    }

    public static void d(String message) {
        LOGGER.debug(message);
    }

    public static void e(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }

    public static void e(String message) {
        LOGGER.error(message);
    }

    public static void i(String message, Throwable throwable) {
        LOGGER.info(message, throwable);
    }

    public static void i(String message) {
        LOGGER.info(message);
    }

}
