package seedu.commands;

import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public abstract class Command {
    protected String[] args;
    protected boolean[] required;
    public abstract void execute(String statement, Parser parser) throws
            IllegalArgumentException, IllegalStateException, SysLibException;

    /**
     * A method to check if statement given is valid compared to the values extracted
     * @param statement The input of user
     * @param values The extracted value(s) from the statement
     * @throws IllegalArgumentException The invalid arguments given
     */
    public void validateStatement(String statement, String[] values) throws IllegalArgumentException {
        statement = statement.toLowerCase();
        for(int pointer = 0; pointer < args.length; pointer ++) {
            if(values[pointer] != null){
                String arg = checkDuplicate(statement, pointer);
                statement = statement.replaceAll(arg+ "\\s*" + Pattern.quote(values[pointer].toLowerCase()), "");
            }
        }
        if (!statement.isBlank()) {
            throw new IllegalArgumentException("Unknown variable/command: " + statement);
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
        if(secondIndex != -1) {
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
        for(int pointer = 0; pointer < args.length; pointer ++) {
            orderedArgs[pointer] = getMatch(statement, pointer);
            if(orderedArgs[pointer] == null && required[pointer]){
                throw new IllegalArgumentException(args[pointer] + " is missing in the argument!"
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
            return matcher.group(1).trim();
        }
        return null;

    }
    public int parseInt(String value) {
        try {
            int tempNum = Integer.parseInt(value);
            if (0 <= tempNum){
                return tempNum;
            }
            throw new IllegalArgumentException ("The argument for id is not a valid number!");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException ("The argument for id is not a number!");
        }
    }
}

