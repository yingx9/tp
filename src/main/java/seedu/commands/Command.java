package seedu.commands;

import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
    protected String[] args;
    protected String[] aliasArgs;
    protected boolean[] required;
    public abstract void execute(String statement, Parser parser) throws
            IllegalArgumentException, IllegalStateException, SysLibException;

    /*
    validate will include str
     */
    public void validate(String statement, String[] value) throws IllegalArgumentException{
        statement = statement.toLowerCase();
        for(int pointer = 0; pointer < args.length; pointer ++) {
            if(value[pointer] != null){
                String arg = validateArg(statement, pointer);
                statement = statement.replaceAll(arg+ "\\s*" + Pattern.quote(value[pointer].toLowerCase()), "");
            }
        }
        if (!statement.isBlank()){
            throw new IllegalArgumentException("Unknown variable/command: " + statement);
        }
    }

    public String validateArg(String statement, int pointer){
        String arg = "/" + args[pointer] + " ";
        int firstindex = statement.indexOf(arg);
        if (firstindex == -1){
            arg = "/" + aliasArgs[pointer] + " ";
            firstindex = statement.indexOf(arg);
        } else if (!aliasArgs[pointer].equals(args[pointer])){
            if (statement.contains("/" + aliasArgs[pointer] + " ")){
                throw new IllegalArgumentException("Duplicate instances of" + arg
                        + ", " + arg + " and /" + aliasArgs[pointer] + " mean the same");
            }
        }
        int secondIndex = statement.indexOf(arg, firstindex + arg.length());
        if(secondIndex != -1){
            throw new IllegalArgumentException("Duplicate instances of " + arg);
        }
        return arg;
    }
    public String[] parseArgument(String statement) throws IllegalArgumentException, IllegalStateException {
        String[] orderedArgs = new String[args.length];
        for(int pointer = 0; pointer < args.length; pointer ++) {
            orderedArgs[pointer] = getMatch(statement, pointer);
            if(orderedArgs[pointer] == null && required[pointer]){
                throw new IllegalArgumentException(args[pointer] + " is missing in the argument!");
            }
        }
        return orderedArgs;
    }

    public String getMatch(String statement, int pointer){
        String key = args[pointer];
        Pattern pattern = Pattern.compile("/" + key + " (.+?)(?=\\s?/|$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(statement);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        if (aliasArgs[pointer] != null) {
            pattern = Pattern.compile("/" + aliasArgs[pointer] + " (.+?)(?=\\s?/|$)", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(statement);
            if (matcher.find()) {
                return matcher.group(1).trim();
            }
        }
        return null;

    }
    public int parseInt(String value){
        try {
            int num = Integer.parseInt(value);
            if (0 < num){
                return num;
            }
            throw new IllegalArgumentException ("The argument for id is not a valid number!");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException ("The argument for id is not a number!");
        }
    }

}

