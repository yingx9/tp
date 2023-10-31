package seedu.parser;

import seedu.commands.events.EventAddCommand;
import seedu.commands.events.EventDeleteCommand;
import seedu.commands.events.EventListCommand;
import seedu.data.Book;
import seedu.data.Resource;
import seedu.data.Status;
import seedu.data.SysLibException;
import seedu.data.Event;

import seedu.commands.Command;
import seedu.commands.AddCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.FindCommand;
import seedu.commands.ListCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ExitCommand;
import seedu.commands.EditCommand;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public List<Resource> resourceList = new ArrayList<>();
    public List<Event> eventList = new ArrayList<>();

    public HashMap<String, Command> commandProcessor = new HashMap<>() {
        {
            put("list", new ListCommand());
            put("delete", new DeleteCommand());
            put("find", new FindCommand());
            put("help", new HelpCommand());
            put("exit", new ExitCommand());
            put("add", new AddCommand());
            put("edit", new EditCommand());
            put("eventadd", new EventAddCommand());
            put("eventdelete", new EventDeleteCommand());
            put("eventlist", new EventListCommand());
        }
    };

    public void process(String response) {
        String command = response.split(" ")[0].toLowerCase();
        if (commandProcessor.containsKey(command)) {
            String statement = removeFirstWord(response);
            try {
                commandProcessor.get(command).execute(statement, this);
            } catch (IllegalArgumentException | IllegalStateException | SysLibException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("no commands found. Enter \"help\" for a list of commands." + SEPARATOR_LINEDIVIDER);
        }

    }
    
    public static String removeFirstWord(String response) {
        int index = response.indexOf(" ");
        if (index == -1) {
            return "";
        }
        return response.substring(index + 1);
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public static String[] parseAddCommand(String statement) throws SysLibException {
        String inputPattern = "(.+?) /tag (.)(.+)";

        Pattern pattern = Pattern.compile(inputPattern);
        Matcher matcher = pattern.matcher(statement);
        boolean matchFound = matcher.find();

        if (matchFound) {
            if (matcher.group(2).equalsIgnoreCase("b")) {
                return parseAddBook(statement);
            } else {
                throw new SysLibException("Please use the format " +
                        "'add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE /s STATUS]'."
                        + SEPARATOR_LINEDIVIDER);
            }
        } else {
            throw new SysLibException("Please use the format " +
                    "'add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE /s STATUS]'." + SEPARATOR_LINEDIVIDER);
        }
    }

    /**
     * @param statement input of the user
     * @return string array with arguments of the user
     * @throws SysLibException missing arguments
     * @throws IllegalStateException
     */
    public static String[] parseAddBook(String statement) throws SysLibException, IllegalStateException {
        try {
            String inputPattern = "/id (.+?) /t (.+?) /a (.+?) /tag (.+?) /i (.+)";
            String genrePattern = "(.+) /g (.+)";
            String statusPattern = "(.+) /s (.+)";

            Pattern pattern = Pattern.compile(inputPattern);
            Matcher matcher = pattern.matcher(statement);
            boolean matchFound = matcher.find();

            Pattern gPattern = Pattern.compile(genrePattern);
            Matcher gMatcher = gPattern.matcher(matcher.group(5));
            boolean gMatchFound = gMatcher.find();

            Pattern sPattern = Pattern.compile(statusPattern);
            Matcher sMatcher = sPattern.matcher(matcher.group(5));
            boolean sMatchFound = sMatcher.find();

            String[] args = new String[7]; // Increase the array size to accommodate status

            if (matchFound) {
                args[0] = matcher.group(1).trim(); // id
                args[1] = matcher.group(2).trim(); // title
                args[2] = matcher.group(3).trim(); // author
                args[3] = matcher.group(4).trim(); // tag
                if (gMatchFound) {
                    args[4] = gMatcher.group(1).trim(); // isbn
                    if (sMatchFound){
                        args[5] = gMatcher.group(2).split("/s")[0].trim(); // genre
                        args[6] = sMatcher.group(2).trim(); // status
                    } else {
                        args[5] = gMatcher.group(2).trim(); // genre
                    }
                } else {
                    args[5] = null; //genre
                    if (sMatchFound) {
                        args[4] = sMatcher.group(1).trim(); // isbn
                        args[6] = sMatcher.group(2).trim(); // status
                    } else {
                        args[4] = matcher.group(5).trim(); // isbn
                        args[6] = "Available";
                    }
                }

                if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty()
                        || args[4].isEmpty()) {
                    throw new SysLibException("Please state the id, title, author, tag, and ISBN." +
                            SEPARATOR_LINEDIVIDER);
                }
            } else {
                throw new SysLibException("Please use the format " +
                        "'add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE /s STATUS]'."
                        + SEPARATOR_LINEDIVIDER);
            }
            return args;
        } catch (IllegalStateException | SysLibException e) {
            throw new SysLibException("Please use the format " +
                    "'add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE /s STATUS]'." + SEPARATOR_LINEDIVIDER);
        }
    }

    /**
     * @param args array with arguments of the user
     * @return Book object with arguments as attributes
     * @throws NumberFormatException invalid id number
     */
    public static Book createBook(String[] args) throws IllegalStateException, NumberFormatException {
        int id;
        try {
            id = Integer.parseInt(args[0]); // id
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter a valid id." + SEPARATOR_LINEDIVIDER);
        }

        String title = args[1]; // title
        String author = args[2]; // author
        String isbn = args[4]; // isbn
        Status status = getStatusFromString(args[6]); // status

        String genre;
        String[] genres = new String[1];
        if (args[5] != null) {
            genre = args[5]; // genre
            genres = genre.split(", ");
        }
        return new Book(title, isbn, author, genres, id, status);
    }

    public Matcher parseFindCommand(String command) throws SysLibException{
        // Define a regular expression pattern to match optional flags and their values
        Pattern pattern = Pattern.compile("/(t|a|i|id)\\s+([^/]+)");
        return pattern.matcher(command);
    }

    /**
     * @param statusString input string status
     * @return Status object
     */
    public static Status getStatusFromString(String statusString) {
        if (statusString != null) {
            statusString = statusString.toLowerCase().trim();
            if (statusString.equalsIgnoreCase("borrowed")) {
                return Status.BORROWED;
            } else if (statusString.equalsIgnoreCase("lost")) {
                return Status.LOST;
            }
        }
        // Default to Available if the status is not provided or unrecognized
        return Status.AVAILABLE;
    }
}
