package seedu.commands.events;

import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;

import static seedu.ui.UI.LINEDIVIDER;

public class EventDeleteCommand extends Command {

    private static String feedbackToUser;
    public EventDeleteCommand() {
        args = new String[]{"i"};
        required = new boolean[]{true};
    }

    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container)
            throws IllegalArgumentException, IllegalStateException, SysLibException {
        feedbackToUser = "";
        String[] values = parseArgument(statement);
        int index = parseCalendarInt(values[0], container);
        System.out.println("This event is removed:");
        System.out.println(container.getEventList().get(index).toString());
        System.out.println(LINEDIVIDER);
        container.getEventList().remove(index);
        return new CommandResult(feedbackToUser);
    }

    public int parseCalendarInt(String value, GenericList<Resource, Event> container) {
        int index = parseInt(value);
        int size = container.getEventList().size();
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Index is out of range of the event list!");
        }
        return index;
    }
}
