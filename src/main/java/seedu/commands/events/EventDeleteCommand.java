package seedu.commands.events;

import seedu.commands.Command;
import seedu.data.SysLibException;
import seedu.parser.Parser;

public class EventDeleteCommand extends Command {
    public EventDeleteCommand() {
        args = new String[]{"i"};
        required = new boolean[]{true};
    }

    @Override
    public void execute(String statement, Parser parser)
            throws IllegalArgumentException, IllegalStateException, SysLibException {
        String[] values = parseArgument(statement);
        int index = parseCalendarInt(values[0], parser);
        System.out.println("This event is removed:");
        System.out.println(parser.eventList.get(index).toString());
        System.out.println("____________________________________________________________");
        parser.eventList.remove(index);
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
