package seedu.commands;

import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import seedu.parser.SuggestParser;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public abstract class Command {
    protected String[] args;
    protected boolean[] required;
    public abstract CommandResult execute(String statement, GenericList<Resource, Event> container) throws
            IllegalArgumentException, IllegalStateException, SysLibException;

    /**
     * A method to check if statement given is valid compared to the values extracted
     * @param statement The input of user
     * @param values The extracted value(s) from the statement
     * @throws IllegalArgumentException The invalid arguments given
     */
    public void validateStatement(String statement, String[] values) throws IllegalArgumentException {
        statement = statement.toLowerCase();
        for (int pointer = 0; pointer < args.length; pointer ++) {
            if (values[pointer] != null) {
                String arg = checkDuplicate(statement, pointer);
                statement = statement.replaceAll(arg+ "\\s*" + Pattern.quote(values[pointer].toLowerCase()), "");
            }
        }
        if (!statement.isBlank()) {
            String message = getReason(statement);
            throw new IllegalArgumentException(message + SEPARATOR_LINEDIVIDER);
        }
    }

    /**
     * A method to check if for duplicate argument calls
     * @param statement The input of user
     * @param pointer The index of args being checked
     * @return The argument being checked
     */
    public String checkDuplicate(String statement, int pointer) {
        String arg = "/" + args[pointer] + " ";
        int firstIndex = statement.indexOf(arg);
        if (firstIndex == -1) {
            return arg;
        }
        int secondIndex = statement.indexOf(arg, firstIndex + arg.length());
        if (secondIndex != -1) {
            throw new IllegalArgumentException("Duplicate instances of " + arg);
        }
        return arg;
    }

    /**
     * A method to get information from user input to a list
     * @param statement The input of user
     * @return The list of information for each argument required
     * @throws IllegalArgumentException The missing argument that is required
     */
    public String[] parseArgument(String statement) throws IllegalArgumentException {
        String[] orderedArgs = new String[args.length];
        for (int pointer = 0; pointer < args.length; pointer ++) {
            orderedArgs[pointer] = getMatch(statement, pointer);
            if (orderedArgs[pointer] == null && required[pointer]) {
                throw new IllegalArgumentException(args[pointer] + " is missing in the argument!"
                        + SEPARATOR_LINEDIVIDER);
            }
            if (orderedArgs[pointer] != null && orderedArgs[pointer].isBlank()) {
                throw new IllegalArgumentException(args[pointer] + " has a blank argument!"
                        + SEPARATOR_LINEDIVIDER);
            }
        }
        return orderedArgs;
    }

    /**
     * Match the required argument to one in the user's input
     * @param statement The user input
     * @param pointer The index of the required argument
     * @return The matched argument information, null otherwise
     */

    public String getMatch(String statement, int pointer) {
        String key = args[pointer];
        Pattern pattern = Pattern.compile("/" + key + " (.+?)(?=\\s?/|$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(statement);
        if (matcher.find()) {
            String output = matcher.group(1).trim();
            checkMatch(output, pointer);
            return output;
        }
        return null;

    }

    /**
     * Check the matched strings is empty and considered the next argument it's variable
     * @param matched The matched string
     * @param pointer The argument
     */
    public void checkMatch (String matched, int pointer){
        if (matched.contains("/")) {
            throw new IllegalArgumentException("Avoid using '/' as names, your " + args[pointer] +
                    " may have been empty to give this error" + SEPARATOR_LINEDIVIDER);
        }
    }

    /**
     * Get the reason for a failed command
     * @param message the leftover variables/commands
     * @return reason for failure
     */
    public String getReason(String message) {
        message = message.stripLeading();
        if (!message.startsWith("/")){
            return "Unknown variable/command:" + message;
        } else {
            message = message.substring(1);
            List<String> variables = List.of(message.split("/"));
            if (variables.size() > 1){
                if(parseInt(variables.get(0)) != -1){
                    return "Unknown variable: " + message +  ", avoid using '/' in arguments. " + "\n" +
                            "Dates are in the format of DD MMM YYYY, e.g. 25 Dec 2023";
                }
                else{
                    return "You need spacing in between arguments";
                }
            } else {
                List<String> values = List.of(message.split(" "));
                String output = "";
                if (values.size() > 1){
                    output += "Invalid command and argument: /" + message;
                } else {
                    output += "Invalid command: /" + message;
                }
                String probCommand = SuggestParser.suggest(values.get(0), Arrays.asList(args));
                if (probCommand != null){
                    return output + "\n" + "Did you mean: /" + probCommand + "?";
                }
                return output;
            }
        }

    }
    public static int parseInt(String value) {
        try {
            int tempNum = Integer.parseInt(value);
            if (0 <= tempNum) {
                return tempNum;
            }
            throw new IllegalArgumentException ("The integer argument(s) given is not a valid number!"
                    + SEPARATOR_LINEDIVIDER);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException ("The integer argument(s) given is not a number!"
                    + SEPARATOR_LINEDIVIDER);
        }
    }
}

