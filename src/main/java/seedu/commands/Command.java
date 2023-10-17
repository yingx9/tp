package seedu.commands;

import seedu.data.SysLibException;
import seedu.parser.Parser;

public abstract class Command {
    protected boolean[] required;
    private final String[] keys = {"id", "t", "a", "tag", "i", "g"};
    public abstract void execute(String statement, Parser parser) throws
            IllegalArgumentException, IllegalStateException, SysLibException;

    public void validate(String[] args) throws IllegalArgumentException{
        int pointer = 0;
        for (String arg : args){
            if(arg == null && required[pointer]){
                throw new IllegalArgumentException(keys[pointer] + " is missing in the argument!");
            }
            pointer++;
        }
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

