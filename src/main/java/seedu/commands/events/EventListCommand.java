package seedu.commands.events;

import seedu.commands.Command;
import seedu.data.SysLibException;
import seedu.parser.Parser;

public class EventListCommand extends Command {
    @Override
    public void execute(String statement, Parser parser) throws
            IllegalArgumentException, IllegalStateException, SysLibException {
        if (!statement.isEmpty()){
            throw new IllegalArgumentException("'eventlist' command does not require arguments!");
        }
        if(parser.calendar.isEmpty()){
            System.out.println("The event list is empty!");
            System.out.println("____________________________________________________________");
        }
        else {
            System.out.println("This is the current event list:");
            for(int index = 0; index < parser.calendar.size(); index += 1){
                System.out.println(index + ": " + parser.calendar.get(index).toString());
            }
            System.out.println("____________________________________________________________");
        }
    }
}
