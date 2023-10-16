package seedu.commands;

import seedu.data.SysLibException;
import seedu.parser.Parser;

public abstract class Command {
    public abstract void execute(String statement, Parser parser) throws
            IllegalArgumentException, IllegalStateException, SysLibException;

    public int parseInt(String value){
        try {
            int num = Integer.parseInt(value);
            if (0 < num){
                return num;
            }
            throw new IllegalArgumentException ("That is not a valid number!");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException ("Please put in a number!");
        }
    }

}

