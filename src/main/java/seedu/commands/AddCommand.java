package seedu.commands;

import seedu.data.SysLibException;
import seedu.parser.Parser;

import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class AddCommand extends Command{
    public AddCommand(){
        args = new String[]{"id", "t", "a", "tag", "i", "g", "s"};
        required = new boolean[]{true, true, true, true, true, false, false};
    }
    @Override
    public void execute(String statement, Parser parser) throws
            IllegalStateException, NumberFormatException, SysLibException {
        String[] values = parseArgument(statement);
        validateStatement(statement, values);
        String title = values[1];
        String tag = values[3];
        if (tag.equalsIgnoreCase("b")) {
            parser.resourceList.add(Parser.createBook(values));
            System.out.println("This book is added: " + title + SEPARATOR_LINEDIVIDER);
        } else {
            throw new SysLibException("Please enter a valid tag." + System.lineSeparator() + SEPARATOR_LINEDIVIDER);
        }
    }
}
