package seedu.commands;

import java.io.IOException;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import seedu.parser.Parser;

import seedu.ui.UI;

/**
 * Command to print exit message
 */
public class ExitCommand extends Command{
    private static final Logger LOGGER = Logger.getLogger(FindCommand.class.getName());
    private static String feedbackToUser;
    static {
        // remove logs from showing in stdout
        try {
            Logger rootLogger = Logger.getLogger("");
            for (java.util.logging.Handler handler : rootLogger.getHandlers()) {

                if (handler instanceof java.util.logging.ConsoleHandler) {

                    rootLogger.removeHandler(handler);

                }

            }

            FileHandler fileHandler = new FileHandler("logs/exitCommandLogs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to set up log file handler", e);

        }

    }

    @Override
    public CommandResult execute(String statement, Parser parser) throws IllegalArgumentException {

        assert statement != null : "Statement to execute cannot be null";
        assert parser != null : "Parser must not be null";
        feedbackToUser = "";
        LOGGER.log(Level.INFO, "Executing ExitCommand...");
        UI ui = new UI();
        ui.showExitMessage();
        return new CommandResult(feedbackToUser);
    }

}
