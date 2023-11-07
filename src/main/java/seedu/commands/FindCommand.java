package seedu.commands;

import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Book;
import seedu.data.resources.Magazine;
import seedu.data.resources.Newspaper;
import seedu.data.resources.Resource;
import seedu.data.resources.CD;
import seedu.exception.SysLibException;
import seedu.ui.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.ui.UI.showResourcesDetails;

public class FindCommand extends Command {
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;
    public static final int THIRD_INDEX = 2;
    public static final int FOURTH_INDEX = 3;
    private static final String INVALID_ARGUMENT_MESSAGE = "Please use the format 'find [/t TITLE OR "
            + "/i ISBN OR /a AUTHOR OR /id ID]'\n" + "____________________________________________________________";
    private static final String NO_RESOURCE_FOUND_MESSAGE = "There are no resources found matching the given filters.";
    private static final String RESOURCE_FOUND_MESSAGE = "Here are resources that matched the given filters:";
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

            FileHandler fileHandler = new FileHandler("logs/findCommandLogs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to set up log file handler", e);
        }
    }
    protected String title;
    protected String author;
    protected String isbn;
    protected String id;
    protected UI ui;

    public FindCommand(){
        args = new String[]{"id", "i", "a", "t"};
        required = new boolean[]{false, false, false, false};
        ui = new UI();
        LOGGER.info("FindCommand instance created.");
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setISBN(String isbn){
        this.isbn = isbn;
    }

    public String getISBN(){
        return this.isbn;
    }

    public void setID(String id){
        this.id = id;
    }

    public String getID(){
        return this.id;
    }

    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container)
            throws IllegalArgumentException, SysLibException {
        assert container != null : "Parser cannot be null!";
        feedbackToUser = "";
        String[] values = parseArgument(statement);
        validateStatement(statement, values);

        // all null
        if (values[FOURTH_INDEX]==null && values[THIRD_INDEX]==null && values[SECOND_INDEX]==null
                && values[FIRST_INDEX]==null) {
            throw new IllegalArgumentException(INVALID_ARGUMENT_MESSAGE + System.lineSeparator());
        }

        List<Resource> matchedResources = filterResources(container.getResourceList(), values);


        if (matchedResources.isEmpty()) {
            LOGGER.info("No resources matched the given filters.");
            System.out.println(NO_RESOURCE_FOUND_MESSAGE);
            ui.showLine();
        } else {
            LOGGER.info("Resources matched the given filters.");
            System.out.println(RESOURCE_FOUND_MESSAGE);
            System.out.println(showResourcesDetails(matchedResources));
        }

        return new CommandResult(feedbackToUser);
    }


    public List<Resource> filterResources(List<Resource> resourceList, String[] values) throws SysLibException{
        List<Resource> matchedResources = new ArrayList<>();
        for (Resource resource: resourceList){
            boolean isMatch = true;
            String resourceType = resource.getTag();

            if (values[FIRST_INDEX] != null && resource.getId() != Integer.parseInt(values[FIRST_INDEX])) {
                isMatch = false;
            }

            if (values[SECOND_INDEX] != null && !resource.getISBN().equalsIgnoreCase(values[SECOND_INDEX])) {
                isMatch = false;
            }

            switch (resourceType) {
            case "B":
            case "EB":
                Book b = (Book) resource;
                if (values[THIRD_INDEX] != null && !b.getAuthor().trim().equalsIgnoreCase((values[THIRD_INDEX]))) {
                    isMatch = false;
                }
                break;
            case "M":
            case "EM":
                Magazine m = (Magazine) resource;
                if (values[THIRD_INDEX] != null && !m.getBrand().trim().equalsIgnoreCase(values[THIRD_INDEX])) {
                    isMatch = false;
                }
                break;
            case "N":
            case "EN":
                Newspaper n = (Newspaper) resource;
                if (values[THIRD_INDEX] != null && !n.getPublisher().trim().equalsIgnoreCase(values[THIRD_INDEX])) {
                    isMatch = false;
                }
                break;
            case "CD":
                CD cd = (CD) resource;
                if (values[THIRD_INDEX] != null && !cd.getCreator().trim().equalsIgnoreCase(values[THIRD_INDEX])) {
                    isMatch = false;
                }
                break;

            default:
                throw new SysLibException("Unknown resource type found.");
            }

            if (values[FOURTH_INDEX] != null && !resource.getTitle().equalsIgnoreCase(values[FOURTH_INDEX])) {
                isMatch = false;
            }

            // If all non-null criteria matched, add the book to the list
            if (isMatch) {
                LOGGER.info(String.format("Resource with name: %s matched given arguments.", resource.getTitle()));
                matchedResources.add(resource);
            }
        }

        return matchedResources;
    }

}
