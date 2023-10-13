package commands;

import seedu.duke.Parser;

public abstract class Command {
    public abstract void execute(String statement, Parser parser) throws IllegalArgumentException;

    public int parseInt(String value, Parser processor){
        try {
            int num = Integer.parseInt(value) - 1;
            if ((0 <= num) && (num < processor.taskList.size())){
                return num;
            }
            throw new IllegalArgumentException ("That is not a valid number!");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException ("Please put in a number!");
        }
    }

}
