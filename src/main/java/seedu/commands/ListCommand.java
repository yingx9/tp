package seedu.commands;

import seedu.data.GenericList;
import seedu.data.Status;
import seedu.data.events.Event;
import seedu.data.resources.Resource;

import seedu.exception.SysLibException;
import seedu.ui.ListCommandMessages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;
import static seedu.ui.UI.showResourcesDetails;

public class ListCommand extends Command {

    public static List<Resource> matchedResources;
    private static final Logger LIST_LOGGER = Logger.getLogger(ListCommand.class.getName());
    private static String tagKeyword;
    private static String genreKeyword;
    private static String statusKeyword;
    private static String feedbackToUser;

    static {

        FileHandler listFileHandler;
        try {
            String loggingDirectoryPath = System.getProperty("user.dir") + "/logs";
            String logFilePath = loggingDirectoryPath + "/listCommandLogs.log";
            File directory = new File(loggingDirectoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            listFileHandler = new FileHandler(logFilePath, true);

        } catch (IOException e) {
            LIST_LOGGER.log(Level.SEVERE, "Failed to initialize list logging handler.");
            throw new RuntimeException(e);
        }
        listFileHandler.setFormatter(new SimpleFormatter());
        LIST_LOGGER.addHandler(listFileHandler);
    }

    public ListCommand() {
        args = new String[]{"tag", "g", "s"};
        required = new boolean[]{false, false, false};
    }

    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container)
            throws SysLibException, IllegalArgumentException {
        feedbackToUser = "";
        LIST_LOGGER.info("List Command execute with " + statement);

        String[] givenParameters = parseArgument(statement);
        validateStatement(statement, givenParameters);
        if (container.getResourcesList().isEmpty()) {
            LIST_LOGGER.warning("ResourcesList is empty");
            throw new IllegalArgumentException("There are currently no Resources in Syslib!" + SEPARATOR_LINEDIVIDER);
        }
        filterResources(givenParameters, container.getResourcesList());
        LIST_LOGGER.info("List Command ends");
        return new CommandResult(feedbackToUser);

    }

    private void filterResources(String[] givenParameters, List<Resource> resourcesList) throws SysLibException {

        boolean hasFilters = hasFilters((givenParameters));
        boolean isTagEqualToKeyword = true;
        boolean isGenreEqualToKeyword = true;
        boolean isStatusEqualToKeyword = true;

        matchedResources = new ArrayList<>();

        if (!hasFilters) {
            feedbackToUser += ListCommandMessages.GENERIC_MESSAGE;
            feedbackToUser += showResourcesDetails(resourcesList);
        } else {

            for (Resource resource : resourcesList) {

                if (tagKeyword != null) {
                    String resourceTag = resource.getTag();
                    isTagEqualToKeyword = resourceTag.equals(tagKeyword);
                }

                if (genreKeyword != null) {
                    isGenreEqualToKeyword = Resource.hasGenre(resource, genreKeyword);
                }

                if (statusKeyword != null) {
                    Status resourceStatus = resource.getStatus();
                    isStatusEqualToKeyword = statusKeyword.equals(resourceStatus.name());

                }

                if (isTagEqualToKeyword && isGenreEqualToKeyword && isStatusEqualToKeyword) {
                    matchedResources.add(resource);
                }
            }
            feedbackToUser +=  ListCommandMessages.FILTER_MESSAGE;
            feedbackToUser += showResourcesDetails(matchedResources);
        }

    }

    private static boolean hasFilters(String[] givenParameters) throws SysLibException {
        tagKeyword = null;
        genreKeyword = null;
        statusKeyword = null;

        boolean hasFilters = true;
        if (givenParameters[0] == null && givenParameters[1] == null && givenParameters[2] == null) {
            return false;
        }

        if (givenParameters[0] != null) {
            tagKeyword = givenParameters[0];
        }

        if (givenParameters[1] != null) {
            genreKeyword = givenParameters[1];
        }

        if (givenParameters[2] != null) {
            Status status = EditCommand.getStatusFromString(givenParameters[2]);
            statusKeyword = status.name();
        }
        return hasFilters;
    }

}
