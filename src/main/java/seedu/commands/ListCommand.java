package seedu.commands;


import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.common.Messages.formatLineSeparator;
import static seedu.common.Messages.formatLastLineDivider;
import static seedu.common.Messages.formatFirstLine;


public class ListCommand extends Command {

    public static final String FILTER_MESSAGE  = formatFirstLine("Listing resources matching given filters: ");
    public static final String GENERIC_MESSAGE =  formatFirstLine("Listing all resources in the Library:");
    public static final String ZERO_RESOURCES_MESSAGE =  formatLastLineDivider("There are currently 0 resources.");
    private static final Logger LIST_LOGGER = Logger.getLogger(ListCommand.class.getName());

    private static final FileHandler LIST_FILEHANDLER;
    private static String tagKeyword;
    private static String genreKeyword;
    private static String feedbackToUser;


    static {
        try {
            LIST_FILEHANDLER = new FileHandler("logs/listCommandLogs.log", true);
            LIST_FILEHANDLER.setFormatter(new SimpleFormatter());
            LIST_LOGGER.addHandler(LIST_FILEHANDLER);
        } catch (IOException e){
            LIST_LOGGER.warning("Failed to set up Logging File Handler");
            throw new RuntimeException();
        }
    }

    public ListCommand(){
        args = new String[]{"tag", "g"};
        required = new boolean[]{false, false};
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

        List<Resource> matchedResources = new ArrayList<>();

        if(hasFilters){
            boolean isTagEqualToKeyword = true;
            boolean isGenreEqualToKeyword = true;
            for (Resource resource : resourceList) {

                if (tagKeyword != null) {
                    String resourceTag = resource.getTag();
                    isTagEqualToKeyword = resourceTag.equals(tagKeyword);
                }

                if (genreKeyword != null) {
                    isGenreEqualToKeyword = Resource.hasGenre(resource, genreKeyword);
                }

                if (isTagEqualToKeyword && isGenreEqualToKeyword) {
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

    public String displayResourcesDetails(List<Resource> resourcesList) {

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

        boolean hasFilters = true;
        if (values[0] == null && values[1] == null) {
            return false;
        }

        if (values[0] != null) {
            tagKeyword = values[0];
        }

        if (values[1] != null) {
            genreKeyword = values[1];
        }
        return hasFilters;
    }
}
