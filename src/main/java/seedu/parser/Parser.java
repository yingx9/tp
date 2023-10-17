package seedu.parser;

import seedu.data.Book;
import seedu.data.Resource;

import seedu.data.SysLibException;
import seedu.commands.Command;
import seedu.commands.AddCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.FindCommand;
import seedu.commands.ListCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ExitCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public List<Resource> resourceList = new ArrayList<>();
    public HashMap<String, Command> commandProcessor = new HashMap<>() {
        {
            put("list", new ListCommand());
            put("delete", new DeleteCommand());
            put("find", new FindCommand());
            put("help", new HelpCommand());
            put("exit", new ExitCommand());
            put("add", new AddCommand());
        }
    };
    public void process(String response) {
        String command = response.split(" ")[0];
        if (commandProcessor.containsKey(command)) {
            String statement = removeFirstWord(response);
            try {
                commandProcessor.get(command).execute(statement, this);
            } catch (IllegalArgumentException | IllegalStateException | SysLibException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("no commands found. Enter \"help\" for a list of commands." + System.lineSeparator() +
                    "____________________________________________________________");
        }

    }
    public static String removeFirstWord(String response) {
        int index = response.indexOf(" ");
        if (index == -1) {
            return "";
        }
        return response.substring(index + 1);
    }
    public static String[] parseArgument(String statement) throws IllegalArgumentException, IllegalStateException {
        String[] keys = {"id", "t", "a", "tag", "i", "g"};
        String[] orderedArgs = new String[6];
        int pointer = 0;
        // Loop over each key and find its value if it exists
        for (String key : keys) {
            Pattern pattern = Pattern.compile("/" + key + " (.+?)(\\s|$)");
            Matcher matcher = pattern.matcher(statement);
            if (matcher.find()) {
                orderedArgs[pointer] = matcher.group(1).trim();
            }
            pointer++;
        }
        return orderedArgs;
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
                        "'add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE]'." + System.lineSeparator() +
                        "____________________________________________________________");
            }
        } else {
            throw new SysLibException("Please use the format " +
                    "'add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE]'." + System.lineSeparator() +
                    "____________________________________________________________");
        }
    }

    public static String[] parseAddBook(String statement) throws SysLibException, IllegalStateException {
        try {
            String inputPattern = "/id (.+?) /t (.+?) /a (.+?) /tag (.+?) /i (.+)";
            String genrePattern = "(.+) /g (.+)";

            Pattern pattern = Pattern.compile(inputPattern);
            Matcher matcher = pattern.matcher(statement);
            boolean matchFound = matcher.find();

            Pattern gPattern = Pattern.compile(genrePattern);
            Matcher gMatcher = gPattern.matcher(matcher.group(5));
            boolean gMatchFound = gMatcher.find();

            String[] args = new String[6];

            if (matchFound) {
                args[0] = matcher.group(1).trim(); // id
                args[1] = matcher.group(2).trim(); // title
                args[2] = matcher.group(3).trim(); // author
                args[3] = matcher.group(4).trim(); // tag
                if (gMatchFound) {
                    args[4] = gMatcher.group(1).trim(); // isbn
                    args[5] = gMatcher.group(2).trim(); // genre
                } else {
                    args[4] = matcher.group(5).trim(); // isbn
                }

                if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty()
                        || args[4].isEmpty()) {
                    throw new SysLibException("Please state the id, title, author, tag, and ISBN." +
                            System.lineSeparator() + "____________________________________________________________");
                }
            } else {
                throw new SysLibException("Please use the format " +
                        "'add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE]'." + System.lineSeparator() +
                        "____________________________________________________________");
            }
            return args;
        } catch (IllegalStateException | SysLibException e) {
            throw new SysLibException("Please use the format " +
                    "'add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE]'." + System.lineSeparator() +
                    "____________________________________________________________");
        }
    }

    public static Book createBook(String[] args) throws IllegalStateException, NumberFormatException {
        int id;
        try {
            id = Integer.parseInt(args[0]); // id
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter a valid id." + System.lineSeparator() +
                    "____________________________________________________________");
        }

        String title = args[1]; // title
        String author = args[2]; // author
        String isbn = args[4]; // isbn

        String genre;
        String[] genres = new String[1];
        if (args[5] != null) {
            genre = args[5]; // genre
            genres = genre.split(", ");
        }

        return new Book(title, isbn, author, genres, id);
    }

    public Matcher parseFindCommand(String command) throws SysLibException{
        // Define a regular expression pattern to match optional flags and their values
        Pattern pattern = Pattern.compile("/(t|a|i|id)\\s+([^/]+)");
        return pattern.matcher(command);
    }



}
