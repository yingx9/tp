package seedu.commands;

import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class DeleteCommand extends Command {
    private static String feedbackToUser;
    private static final Logger LOGGER = Logger.getLogger(DeleteCommand.class.getName());

    static {
        // remove logs from showing in stdout
        try {
            Logger rootLogger = Logger.getLogger("");
            for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
                if (handler instanceof java.util.logging.ConsoleHandler) {
                    rootLogger.removeHandler(handler);
                }
            }

            FileHandler fileHandler = new FileHandler("logs/DeleteCommandLogs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to set up log file handler", e);
        }
    }

    public DeleteCommand(){
        args = new String[]{"id"};
        required = new boolean[]{true};
        LOGGER.info("DeleteCommand initialized.");
    }
    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container) throws SysLibException {
        LOGGER.info("Executing DeleteCommand");
        String[] values = parseArgument(statement);
        int id = parseInt(values[0]);
        assert id > 0;
        if (container.getResourcesList().isEmpty()) {
            LOGGER.info("DeleteCommand: List is empty");
            throw new SysLibException("There are currently no Resources in Syslib!" + SEPARATOR_LINEDIVIDER);
        }
        feedbackToUser = "";
        ArrayList<Resource> removals = new ArrayList<>();
        LOGGER.info("DeleteCommand: Looking for ID");
        System.out.println("Looking for ID: " + id + "...");
        for (Resource r: container.getResourcesList()) {
            if (r.getId() == id) {
                System.out.println("This resource is removed:");
                System.out.println(r + SEPARATOR_LINEDIVIDER);
                removals.add(r);
                LOGGER.info("DeleteCommand: Resource has been removed");

            }
        }
        if (removals.isEmpty()) {
            System.out.println("No resources with id matching " + id +SEPARATOR_LINEDIVIDER);
            LOGGER.info("DeleteCommand: Resource ID not found");
        } else {
            container.getResourcesList().removeAll(removals);
        }
        return new CommandResult(feedbackToUser);
    }

}
