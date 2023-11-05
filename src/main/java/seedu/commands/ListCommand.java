package seedu.commands;


import seedu.data.Status;
import seedu.data.resources.Resource;
import seedu.data.resources.Book;
import seedu.data.resources.EBook;
import seedu.data.resources.Magazine;
import seedu.data.resources.EMagazine;
import seedu.data.resources.Newspaper;
import seedu.data.resources.ENewspaper;
import seedu.data.resources.CD;

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
import java.util.Formatter;

import static seedu.common.FormatMessages.formatADivider;
import static seedu.common.FormatMessages.formatLastLineDivider;
import static seedu.common.FormatMessages.formatFirstLine;
import static seedu.ui.UI.LINESEPARATOR;


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
            feedbackToUser += displayResourcesDetails(matchedResources);

        } else {
            feedbackToUser += GENERIC_MESSAGE;

            feedbackToUser += displayResourcesDetails(resourceList);
        }


    }

    public static String displayResourcesDetails(List<Resource> resourcesList) throws SysLibException {

        String messageToDisplay = "";

        if (resourcesList.isEmpty()){
            messageToDisplay += ZERO_RESOURCES_MESSAGE;

        } else {

            String displayFormat = "%-15s %-5s %-25s %-13s %-25s %-40s %-10s %-15s %-15s" + LINESEPARATOR;

            Formatter bookDisplayFormatter = buildBookFormatter(displayFormat);
            Formatter magazineDisplayFormatter = buildMagazineFormatter(displayFormat);
            Formatter cdDisplayFormatter = buildCDFormatter(displayFormat);
            Formatter newspaperDisplayFormatter = buildNewspaperFormatter(displayFormat);


            for (int i = 0; i < resourcesList.size(); i += 1) {
                Resource resource = resourcesList.get(i);

                if (resource instanceof Book || resource instanceof EBook){
                    bookDisplayFormatter = resource.toTableFormat(displayFormat, bookDisplayFormatter);
                } else if (resource instanceof Magazine || resource instanceof EMagazine){
                    magazineDisplayFormatter = resource.toTableFormat(displayFormat, magazineDisplayFormatter);
                } else if(resource instanceof CD ) {
                    cdDisplayFormatter = resource.toTableFormat(displayFormat, cdDisplayFormatter);
                } else if(resource instanceof Newspaper || resource instanceof ENewspaper){
                    newspaperDisplayFormatter = resource.toTableFormat(displayFormat, newspaperDisplayFormatter);
                }

            }

            messageToDisplay += bookDisplayFormatter.toString() + LINESEPARATOR;
            messageToDisplay += magazineDisplayFormatter.toString()  + LINESEPARATOR;
            messageToDisplay += cdDisplayFormatter.toString()  + LINESEPARATOR;
            messageToDisplay += newspaperDisplayFormatter.toString() + LINESEPARATOR;

            messageToDisplay += formatLastLineDivider("There are currently " + resourcesList.size() +
                    " resource(s).");

        }


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

    public static Formatter buildBookFormatter(String displayFormat){
        Object[] bookArgs = {"ID", "Tag", "Title", "ISBN", "Author", "Genre", "Link", "Status", "Received Date"};
        String bookHeader = String.format("%89s"+ LINESEPARATOR, "[BOOKS]");
        Formatter bookDisplayFormatter = buildDisplayFormatter(displayFormat, bookArgs, bookHeader, "%-170s");

        return bookDisplayFormatter;
    }
    public static Formatter buildMagazineFormatter(String displayFormat){
        Object[] magazineArgs = {"ID", "Tag", "Title", "ISBN", "Brand", "Issue", "Link", "Status", "Received Date"};
        String magazineHeader = String.format("%91s"+ LINESEPARATOR, "[MAGAZINES]");
        Formatter magazineDisplayFormatter = buildDisplayFormatter(displayFormat, magazineArgs, magazineHeader,
                "%-170s");
        return magazineDisplayFormatter;
    }

    public static Formatter buildCDFormatter(String displayFormat){
        Object[] cdArgs = { "ID", "Tag", "Title", "ISBN", "Creator", "Type", "Link", "Status", "Received Date"};
        String cdHeader = String.format("%86s"+ LINESEPARATOR, "[CDS]");
        Formatter cdDisplayFormatter = buildDisplayFormatter(displayFormat, cdArgs, cdHeader, "%-170s");
        return cdDisplayFormatter;
    }

    public static Formatter buildNewspaperFormatter(String displayFormat){
        Object[] newspaperArgs = {"ID", "Tag", "Title", "ISBN", "Publisher", "Edition", "Link",
            "Status", "Received Date"};
        String newspaperHeader = String.format("%91s"+ LINESEPARATOR, "[NEWSPAPERS]");
        Formatter newspaperFormatter = buildDisplayFormatter(displayFormat, newspaperArgs, newspaperHeader, "%-170s");
        return newspaperFormatter;
    }
    public static Formatter buildDisplayFormatter(String displayFormat, Object[] displayArgs, String header,
                                                  String padding){
        String customDivider = formatADivider(padding);
        Formatter displayFormatter = new Formatter();
        displayFormatter.format(header);
        displayFormatter.format(customDivider);
        displayFormatter.format(displayFormat, displayArgs);
        displayFormatter.format(customDivider);
        return displayFormatter;

    }




}
