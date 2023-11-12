package seedu.parser;

import seedu.commands.events.EventAddCommand;
import seedu.commands.events.EventDeleteCommand;
import seedu.commands.events.EventEditCommand;
import seedu.commands.events.EventListCommand;
import seedu.data.GenericList;
import seedu.data.Status;
import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;

import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.commands.AddCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.FindCommand;
import seedu.commands.ListCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ExitCommand;
import seedu.commands.EditCommand;
import seedu.commands.SummaryCommand;

import static seedu.ui.Messages.ASSERT_STATEMENT;
import static seedu.ui.Messages.ERROR_TAG;
import static seedu.ui.UI.LINEDIVIDER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public List<Resource> resourcesList = new ArrayList<>();
    public List<Event> eventsList = new ArrayList<>();
    public GenericList<Resource, Event> container = new GenericList<>(resourcesList, eventsList);

    // @@author DavinciDelta
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
            put("eventedit", new EventEditCommand());
            put("eventlist", new EventListCommand());
            put("summary", new SummaryCommand());
        }
    };

    public ArrayList<String> commands = new ArrayList<>(commandProcessor.keySet());

    // @@author DavinciDelta
    public void processUserResponse(String response) {
        String command = response.split(" ")[0].toLowerCase();
        if (commandProcessor.containsKey(command)) {
            String statement = removeFirstWord(response);
            try {
                CommandResult commandResult = commandProcessor.get(command).execute(statement, container);
                System.out.print(commandResult.feedbackToUser);
            } catch (IllegalArgumentException | IllegalStateException | SysLibException e) {
                System.out.println(e.getMessage());
            }
        } else {
            String suggestion = SuggestParser.suggest(command, commands);
            System.out.println("no commands found. Enter \"help\" for a list of commands.");
            if (suggestion != null){
                System.out.println("Did you mean: '" + suggestion + "'");
            }
            System.out.println(LINEDIVIDER);
        }

    }

    // @@author DavinciDelta
    public static String removeFirstWord(String response) {
        int index = response.indexOf(" ");
        if (index == -1) {
            return "";
        }
        return response.substring(index + 1);
    }

    // @@author JoanneJo
    public static String parseAddCommand(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String inputPattern = "/tag ([^/]+)";

        Pattern pattern = Pattern.compile(inputPattern);
        Matcher matcher = pattern.matcher(statement);
        boolean isMatching = matcher.find();

        if (isMatching) {
            return matcher.group(1).trim();
        } else {
            throw new SysLibException(ERROR_TAG);
        }
    }


    // @@author bnjm2000
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
