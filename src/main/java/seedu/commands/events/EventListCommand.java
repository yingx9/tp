package seedu.commands.events;

import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;

import static seedu.ui.UI.LINEDIVIDER;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class EventListCommand extends Command {

    private static String feedbackToUser;

    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container) throws
            IllegalArgumentException, IllegalStateException, SysLibException {
        feedbackToUser = "";
        if (!statement.isEmpty()){
            throw new IllegalArgumentException("'eventlist' command does not require arguments!"
                    + SEPARATOR_LINEDIVIDER);
        }
        if(container.getEventList().isEmpty()){
            System.out.println("The event list is empty!");
            System.out.println(LINEDIVIDER);
        } else {
            System.out.println("This is the current event list:");
            for(int index = 0; index < container.getEventList().size(); index += 1){
                System.out.println(index + ": " + container.getEventList().get(index).toString());
            }
            System.out.println(LINEDIVIDER);
        }

        return new CommandResult(feedbackToUser);
    }
}
