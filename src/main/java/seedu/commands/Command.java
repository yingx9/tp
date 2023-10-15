package seedu.commands;

import seedu.data.SysLibException;
import seedu.parser.Parser;

public abstract class Command {
    public abstract void execute(String statement, Parser parser) throws IllegalArgumentException, SysLibException;

    public int parseInt(String value, Parser processor){
        try {
            int num = Integer.parseInt(value) - 1;
            if ((0 <= num) && (num < processor.resourceList.size())){
                return num;
            }
            throw new IllegalArgumentException ("That is not a valid number!");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException ("Please put in a number!");
        }
    }

}

