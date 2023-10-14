package commands;

import seedu.duke.Parser;

public class DeleteCommand extends Command {
    @Override
    public void execute(String statement, Parser parser) throws IllegalArgumentException {
        int number = parseInt(statement, parser);
        System.out.println("This task is removed: ");
        System.out.println(parser.taskList.get(number));
        parser.taskList.remove(number);
    }

}
