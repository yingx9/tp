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

public class EventDeleteCommand extends Command {

    private static String feedbackToUser;
    private static final Logger LOGGER = Logger.getLogger(EventDeleteCommand.class.getName());

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

    public EventDeleteCommand() {
        args = new String[]{"i"};
        required = new boolean[]{true};
        LOGGER.info("EventDelete Command created.");
    }

    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container)
            throws IllegalArgumentException, IllegalStateException, SysLibException {
        feedbackToUser = "";
        String[] values = parseArgument(statement);
        int index = parseCalendarInt(values[0], container);
        System.out.println("This event is removed:");
        System.out.println(container.getEventsList().get(index).toString());
        System.out.println(LINEDIVIDER);
        container.getEventsList().remove(index);
        LOGGER.info("Event removed");
        return new CommandResult(feedbackToUser);
    }

    public int parseCalendarInt(String value, GenericList<Resource, Event> container) {
        int index = parseInt(value);
        int size = container.getEventsList().size();
        if (index >= size || index < 0) {
            LOGGER.warning("Index is out of range");
            throw new IllegalArgumentException("Index is out of range of the event list!");
        }
        LOGGER.warning("Index found");
        return index;
    }
}
