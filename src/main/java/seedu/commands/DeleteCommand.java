package seedu.commands;

import seedu.parser.Parser;

public class DeleteCommand extends Command {
    @Override
    public void execute(String statement, Parser parser) throws IllegalArgumentException {
        int number = parseInt(statement, parser);
        System.out.println("This resource is removed: ");
        System.out.println(parser.resourceList.get(number));
        parser.resourceList.remove(number);
    }

}
