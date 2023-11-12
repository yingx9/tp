package seedu.commands.events;

import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.ui.UI.LINEDIVIDER;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class EventListCommand extends Command {

    private static String feedbackToUser;

    private static final Logger LOGGER = Logger.getLogger(EventListCommand.class.getName());

    static {
        // remove logs from showing in stdout
        try {
            Logger rootLogger = Logger.getLogger("");
            for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
                if (handler instanceof java.util.logging.ConsoleHandler) {
                    rootLogger.removeHandler(handler);
                }
            }

            FileHandler fileHandler = new FileHandler("logs/eventCommandLogs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to set up log file handler", e);
        }
    }

    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container) throws
            IllegalArgumentException, IllegalStateException, SysLibException {
        feedbackToUser = "";
        if (!statement.isEmpty()) {
            LOGGER.warning("EventList was given arguments when none was expected");
            throw new IllegalArgumentException("'eventlist' command does not require arguments!"
                    + SEPARATOR_LINEDIVIDER);
        }
        if (container.getEventsList().isEmpty()) {
            LOGGER.info("EventList is empty.");
            throw new SysLibException("There are currently no Events in Syslib!"
                    + SEPARATOR_LINEDIVIDER);
        }
        else {
            LOGGER.info("Printing events in EventList");
            System.out.println("This is the current event list:");
            for (int index = 0; index < container.getEventsList().size(); index += 1) {
                System.out.println(index + ": " + container.getEventsList().get(index).toString());
            }
            System.out.println(LINEDIVIDER);
            LOGGER.info("EventList has finish printing.");
        }

        return new CommandResult(feedbackToUser);
    }
}
