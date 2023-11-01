package seedu.commands.events;

import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.data.SysLibException;
import seedu.parser.Parser;

public class EventListCommand extends Command {

    private static String feedbackToUser;

    @Override
    public CommandResult execute(String statement, Parser parser) throws
            IllegalArgumentException, IllegalStateException, SysLibException {
        if (!statement.isEmpty()){
            throw new IllegalArgumentException("'eventlist' command does not require arguments!");
        }
        if(parser.eventList.isEmpty()){
            System.out.println("The event list is empty!");
            System.out.println("____________________________________________________________");
        }
        else {
            System.out.println("This is the current event list:");
            for(int index = 1; index < parser.eventList.size(); index += 1){
                System.out.println(index + ": " + parser.eventList.get(index).toString());
            }
            System.out.println("____________________________________________________________");
        }

        return new CommandResult(feedbackToUser);
    }
}
