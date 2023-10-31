package seedu.commands;

import seedu.data.SysLibException;
import seedu.parser.Parser;

public class AddCommand extends Command{
    private static String feedbackToUser;

    public AddCommand(){
        args = new String[]{"id", "t", "a", "tag", "i", "g", "s"};
        required = new boolean[]{true, true, true, true, true, false, false};
    }
    @Override
    public CommandResult execute(String statement, Parser parser) throws
            IllegalStateException, NumberFormatException, SysLibException {
        feedbackToUser = "";
        String[] values = parseArgument(statement);
        validateStatement(statement, values);
        String title = values[1];
        String tag = values[3];
        if (tag.equalsIgnoreCase("b")) {
            parser.resourceList.add(Parser.createBook(values));
            System.out.println("This book is added: " + title);
            System.out.println("____________________________________________________________");
        } else {
            throw new SysLibException("Please enter a valid tag." + System.lineSeparator() +
                    "____________________________________________________________");
        }

        return new CommandResult(feedbackToUser);
    }

}
