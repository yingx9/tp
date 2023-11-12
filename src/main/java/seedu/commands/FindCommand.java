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
import static seedu.ui.FindCommandMessages.INVALID_ARGUMENT_MESSAGE;
import static seedu.ui.FindCommandMessages.NO_RESOURCE_FOUND_MESSAGE;
import static seedu.ui.FindCommandMessages.RESOURCE_FOUND_MESSAGE;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.ui.UI.showResourcesDetails;

/**
 * The FindCommand class is responsible for handling the "find" command within the application.
 * It allows the user to search for resources in the system based on various criteria such as ID, ISBN,
 * author/publisher/brand/creator, or title. It extends the Command class and overrides the execute method
 * to perform the search operation.
 */
public class FindCommand extends Command {
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;
    public static final int THIRD_INDEX = 2;
    public static final int FOURTH_INDEX = 3;
    private static final Logger LOGGER = Logger.getLogger(FindCommand.class.getName());
    private static String feedbackToUser;

    protected String title;
    protected String author;
    protected String isbn;
    protected String id;
    protected UI ui;

    static {
        setupLogger();
    }


    /**
     * Constructs a FindCommand object for handling find command instances.
     */
    public FindCommand() {
        args = new String[]{"id", "i", "a", "t"};
        required = new boolean[]{false, false, false, false};
        ui = new UI();
        LOGGER.info("FindCommand instance created.");
    }

    /**
     * Sets up the logger for this class.
     */
    private static void setupLogger() {
        try {
            // remove logs from showing in stdout
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

    /**
     * Executes the find command using the provided statement and container.
     * @param statement The command statement.
     * @param container The container of resources and events.
     * @return The result of the command execution.
     * @throws IllegalArgumentException If the input statement is invalid.
     * @throws SysLibException If there is an error in processing the find command.
     */
    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container)
            throws IllegalArgumentException, SysLibException {
        assert container != null : "Container cannot be null";
        feedbackToUser = "";
        String[] values = parseArgument(statement);
        validateStatement(statement, values);

        if (areAllValuesNull(values)) {
            throw new IllegalArgumentException(INVALID_ARGUMENT_MESSAGE);
        }

        List<Resource> matchedResources = filterResources(container.getResourcesList(), values);
        displayResults(matchedResources);

        return new CommandResult(feedbackToUser);

    }

    private boolean areAllValuesNull(String[] values) {
        for (String value : values) {
            if (value != null) {
                return false;
            }
        }
        return true;
    }

    private void displayResults(List<Resource> matchedResources) throws IllegalArgumentException, SysLibException{
        if (matchedResources.isEmpty()) {
            LOGGER.info("No resources matched the given filters.");
            System.out.println(NO_RESOURCE_FOUND_MESSAGE);
            ui.showLine();
        } else {
            LOGGER.info("Resources matched the given filters.");
            System.out.println(RESOURCE_FOUND_MESSAGE);
            System.out.println(showResourcesDetails(matchedResources));
        }
    }

    /**
     * Filters the resources based on the specified search criteria.
     * @param resourceList The list of resources to filter.
     * @param values The search criteria.
     * @return A list of resources that match the criteria.
     */
    public List<Resource> filterResources(List<Resource> resourceList, String[] values) {
        List<Resource> matchedResources = new ArrayList<>();
        for (Resource resource : resourceList) {
            if (isResourceMatch(resource, values)) {
                LOGGER.info(String.format("Resource with name: %s matched given arguments.", resource.getTitle()));
                matchedResources.add(resource);
            }
        }
        return matchedResources;
    }

    /**
     * Determines if a resource matches the given search criteria.
     * @param resource The resource to check.
     * @param values The search criteria values.
     * @return true if the resource matches the criteria, false otherwise.
     */
    private boolean isResourceMatch(Resource resource, String[] values) {
        try {
            boolean isMatch = true;
            String resourceType = resource.getTag();

            if (values[FIRST_INDEX] != null && resource.getId() != Integer.parseInt(values[FIRST_INDEX])) {
                if (Integer.parseInt(values[FIRST_INDEX]) < 0){
                    throw new SysLibException("ID cannot be negative.");
                }
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
            }
            return isMatch;


        } catch (SysLibException SysLibEx) {
            LOGGER.log(Level.SEVERE, "Find ID supplied is null");
            System.out.println(SysLibEx);
        }
        return false;
    }
}
