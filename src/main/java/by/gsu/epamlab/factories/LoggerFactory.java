package by.gsu.epamlab.factories;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.exceptions.InitException;
import by.gsu.epamlab.managers.ConfigurationManager;
import by.gsu.epamlab.managers.MessageManager;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerFactory {

    private static Logger logger = Logger.getLogger(LoggerFactory.class.getName());

    public static Logger getClassFromFactory() {
        return logger;
    }

    public static void init() throws InitException {
        try {
            FileHandler fh = new FileHandler(ConfigurationManager.getProperty(Constants.PATH_FILE_LOG_FILE), true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            throw new InitException(MessageManager.getProperty(Constants.FILE_NOT_FOUND_MESSAGE));
        }

    }
}
