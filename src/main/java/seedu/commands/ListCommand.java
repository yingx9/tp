package seedu.commands;


import seedu.data.GenericList;
import seedu.data.Status;
import seedu.data.events.Event;
import seedu.data.resources.Resource;


import seedu.exception.SysLibException;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.ui.MessageFormatter.formatLastLineDivider;
import static seedu.ui.MessageFormatter.formatFirstLine;
import static seedu.ui.UI.showResourcesDetails;


public class ListCommand extends Command {

    public static final String FILTER_MESSAGE  = formatFirstLine("Listing resources matching given filters: ");
    public static final String GENERIC_MESSAGE =  formatFirstLine("Listing all resources in the Library:");
    public static final String ZERO_RESOURCES_MESSAGE =  formatLastLineDivider("There are currently 0 resources.");

    public static final String STATUS_ERROR_MESSAGE =  formatLastLineDivider("Invalid Status! Status must be: " +
            "AVAILABLE, BORROWED, OR LOST");
    public static List<Resource> matchedResources;
    private static final Logger LIST_LOGGER = Logger.getLogger(ListCommand.class.getName());

    private static String tagKeyword;
    private static String genreKeyword;
    private static String statusKeyword;
    private static String feedbackToUser;

    static {

        FileHandler listFileHandler = null;
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

    public ListCommand(){
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
        filterResources(givenParameters, container.getResourceList());
        LIST_LOGGER.info("List Command ends");
        return new CommandResult(feedbackToUser);

    }


    public void filterResources(String[] givenParameters, List<Resource> resourceList) throws SysLibException{

        boolean hasFilters = hasFilters((givenParameters));
        boolean isTagEqualToKeyword = true;
        boolean isGenreEqualToKeyword = true;
        boolean isStatusEqualToKeyword = true;

        matchedResources = new ArrayList<>();

        if(!hasFilters){
            feedbackToUser += GENERIC_MESSAGE;
            feedbackToUser += showResourcesDetails(resourceList);
        } else{

            for (Resource resource : resourceList) {

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
            feedbackToUser += FILTER_MESSAGE;
            feedbackToUser += showResourcesDetails(matchedResources);
        }


    }


    public static boolean hasFilters(String[] givenParameters) throws SysLibException {
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

        if (givenParameters[2] != null){
            statusKeyword = givenParameters[2].toUpperCase();
            validateStatus();
        }
        return hasFilters;
    }

    public static void validateStatus() throws SysLibException {

        switch(statusKeyword){
        case "AVAILABLE":
            //fallthrough
        case "BORROWED":
            //fallthrough
        case "LOST":
            break;
        default:
            throw new SysLibException(STATUS_ERROR_MESSAGE);

        }
    }




}
