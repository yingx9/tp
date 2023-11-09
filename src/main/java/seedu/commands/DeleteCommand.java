package seedu.commands;

import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Book;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import java.util.ArrayList;

import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class DeleteCommand extends Command {
    private static String feedbackToUser;
    public DeleteCommand(){
        args = new String[]{"id"};
        required = new boolean[]{true};
    }
    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container) throws SysLibException {
        String[] values = parseArgument(statement);
        int id = parseInt(values[0]);
        assert id > 0;
        feedbackToUser = "";
        ArrayList<Resource> removals = new ArrayList<>();
        System.out.println("Looking for ID: " + id + "...");
        for (Resource r: container.getResourceList()){
            if (r.getId() == id){
                System.out.println("This resource is removed:");
                System.out.println(r + SEPARATOR_LINEDIVIDER);
                removals.add(r);
            }
        }
        if(removals.isEmpty()) {
            System.out.println("No resources with id matching " + id +SEPARATOR_LINEDIVIDER);
        } else {
            container.getResourceList().removeAll(removals);
        }
        return new CommandResult(feedbackToUser);
    }

}
