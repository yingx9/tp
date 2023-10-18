package seedu.commands;

import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
    protected String[] args;
    protected boolean[] required;
    public abstract void execute(String statement, Parser parser) throws
            IllegalArgumentException, IllegalStateException, SysLibException;

    public void validate(String argument, int index) throws IllegalArgumentException{
        if(argument == null && required[index]){
            throw new IllegalArgumentException(args[index] + " is missing in the argument!");
        }
    }
    public String[] parseArgument(String statement) throws IllegalArgumentException, IllegalStateException {
        String[] orderedArgs = new String[args.length];
        for(int pointer = 0; pointer < args.length; pointer ++) {
            String key = args[pointer];
            Pattern pattern = Pattern.compile("/" + key + " (.+?)(?=\\s?/|$)");
            Matcher matcher = pattern.matcher(statement);
            if (matcher.find()) {
                orderedArgs[pointer] = matcher.group(1).trim();
            }
            validate(orderedArgs[pointer], pointer);
        }
        return orderedArgs;
    }
    public int parseInt(String value){
        try {
            int num = Integer.parseInt(value);
            if (0 < num){
                return num;
            }
            throw new IllegalArgumentException ("The argument for id/isbn is not a valid number!");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException ("The argument for id/isbn is not a number!");
        }
    }

}

