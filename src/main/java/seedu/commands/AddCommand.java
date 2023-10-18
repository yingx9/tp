package seedu.commands;

import seedu.data.SysLibException;
import seedu.parser.Parser;

public class AddCommand extends Command{
    public AddCommand(){
        args = new String[]{"id", "t", "a", "tag", "i", "g"};
        required = new boolean[]{true, true, true, true, true, false};
    }
    @Override
    public void execute(String statement, Parser parser) throws
            IllegalStateException, NumberFormatException, SysLibException {
        String[] args = parseArgument(statement);
        String title = args[1];
        String tag = args[3];
        if (tag.equalsIgnoreCase("b")) {
            parser.resourceList.add(Parser.createBook(args));
            System.out.println("This book is added: " + title);
            System.out.println("____________________________________________________________");
        } else {
            throw new SysLibException("Please enter a valid tag." + System.lineSeparator() +
                    "____________________________________________________________");
        }
    }

}
