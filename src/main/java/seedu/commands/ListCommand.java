package seedu.commands;


import seedu.data.Status;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import seedu.parser.Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.common.FormatMessages.formatLineSeparator;
import static seedu.common.FormatMessages.formatLastLineDivider;
import static seedu.common.FormatMessages.formatFirstLine;


public class ListCommand extends Command {

    public static final String FILTER_MESSAGE  = formatFirstLine("Listing resources matching given filters: ");
    public static final String GENERIC_MESSAGE =  formatFirstLine("Listing all resources in the Library:");
    public static final String ZERO_RESOURCES_MESSAGE =  formatLastLineDivider("There are currently 0 resources.");

    public static List<Resource> matchedResources;
    private static final Logger LIST_LOGGER = Logger.getLogger(ListCommand.class.getName());

    private static String tagKeyword;
    private static String genreKeyword;
    private static String statusKeyword;
    private static String feedbackToUser;



    static {

        FileHandler editFileHandler = null;
        try {
            String loggingDirectoryPath = System.getProperty("user.dir") + "/logs";
            String logFilePath = loggingDirectoryPath + "/editCommandLogs.log";
            File directory = new File(loggingDirectoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            editFileHandler = new FileHandler(logFilePath, true);

        } catch (IOException e) {
            LIST_LOGGER.log(Level.SEVERE, "Failed to initialize list logging handler.");
            throw new RuntimeException(e);
        }
        editFileHandler.setFormatter(new SimpleFormatter());
        LIST_LOGGER.addHandler(editFileHandler);
    }

    public ListCommand(){
        args = new String[]{"tag", "g", "s"};
        required = new boolean[]{false, false, false};
    }


    @Override
    public CommandResult execute(String statement, Parser parser) throws SysLibException, IllegalArgumentException {
        feedbackToUser = "";
        LIST_LOGGER.info("List Command execute with " + statement);

        String[] values = parseArgument(statement);
        validateStatement(statement, values);
        filterResources(values, parser.resourceList);
        LIST_LOGGER.info("List Command ends");
        return new CommandResult(feedbackToUser);

    }


    public void filterResources(String[] values, List<Resource> resourceList) throws SysLibException{

        boolean hasFilters = hasFilters((values));

        matchedResources = new ArrayList<>();

        if(hasFilters){
            boolean isTagEqualToKeyword = true;
            boolean isGenreEqualToKeyword = true;
            boolean isStatusEqualToKeyword = true;

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
            displayResourcesDetails(matchedResources);

        } else {
            feedbackToUser += GENERIC_MESSAGE;

            displayResourcesDetails(resourceList);
        }


    }

    public static String displayResourcesDetails(List<Resource> resourcesList) {

        String messageToDisplay = "";

        if (resourcesList.isEmpty()){
            messageToDisplay += ZERO_RESOURCES_MESSAGE;

        } else {

            for (int i = 0; i < resourcesList.size(); i += 1) {
                String resourceDetails = resourcesList.get(i).toString();
                messageToDisplay += formatLineSeparator(i+1 + ". " + resourceDetails);
            }

            messageToDisplay += formatLastLineDivider("There are currently " + resourcesList.size() +
                    " resource(s).");

        }

        feedbackToUser += messageToDisplay;
        return messageToDisplay;
    }

    public static boolean hasFilters(String[] values) throws SysLibException {
        tagKeyword = null;
        genreKeyword = null;
        statusKeyword = null;

        boolean hasFilters = true;
        if (values[0] == null && values[1] == null && values[2] == null) {
            return false;
        }

        if (values[0] != null) {
            tagKeyword = values[0];
        }

        if (values[1] != null) {
            genreKeyword = values[1];
        }

        if (values[2] != null){
            statusKeyword = values[2].toUpperCase();
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
            throw new SysLibException("Please enter a valid status: AVAILABLE / BORROWED / LOST");

        }
    }
}
