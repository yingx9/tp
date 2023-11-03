package seedu.commands;

import seedu.data.Book;
import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;
import seedu.ui.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FindCommand extends Command {
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
    public CommandResult execute(String statement, Parser parser) throws IllegalArgumentException, SysLibException {
        assert parser != null : "Parser cannot be null!";
        feedbackToUser = "";
        String[] value = parseArgument(statement);
        validateStatement(statement, value);

        if (value[3]==null && value[2]==null && value[1]==null && value[0]==null) {
            throw new IllegalArgumentException(INVALID_ARGUMENT_MESSAGE + System.lineSeparator());
        }

        ArrayList<Resource> matchedResources = new ArrayList<>();
        for (Resource r: parser.resourceList){
            Book b = (Book) r;
            if (b.getTitle().equals(value[3]) || b.getISBN().equals(value[1]) || b.getAuthor().equals(value[2])) {
                matchedResources.add(b);
            }
        }

        if (matchedResources.isEmpty()) {
            LOGGER.warning("No resources matched the given filters.");
            System.out.println(NO_RESOURCE_FOUND_MESSAGE);
            ui.showLine();
        } else {
            LOGGER.info("Resources matched the given filters.");
            System.out.println(RESOURCE_FOUND_MESSAGE);
            for (Resource r : matchedResources) {
                System.out.println(r);
            }
            ui.showLine();
        }

        return new CommandResult(feedbackToUser);
    }

}
