package seedu.commands.events;

import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.data.SysLibException;
import seedu.parser.Parser;

public class EventDeleteCommand extends Command {

    private static String feedbackToUser;
    public EventDeleteCommand() {
        args = new String[]{"i"};
        required = new boolean[]{true};
    }

    @Override
    public CommandResult execute(String statement, Parser parser)
            throws IllegalArgumentException, IllegalStateException, SysLibException {
        feedbackToUser = "";
        String[] values = parseArgument(statement);
        int index = parseCalendarInt(values[0], parser);
        System.out.println("This event is removed:");
        System.out.println(parser.eventList.get(index).toString());
        System.out.println("____________________________________________________________");
        parser.eventList.remove(index);
        return new CommandResult(feedbackToUser);
    }

    public int parseCalendarInt(String value, Parser parser) {
        int index = parseInt(value);
        int size = parser.eventList.size();
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Index is out of range of the event list!");
        }
        return index;
    }
}
