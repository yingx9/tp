package seedu.commands;

import seedu.data.Book;
import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;
import java.util.ArrayList;

import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class DeleteCommand extends Command {
    public DeleteCommand(){
        args = new String[]{"id"};
        required = new boolean[]{true};
    }
    @Override
    public void execute(String statement, Parser parser) throws SysLibException {
        int id = parseInt(parseArgument(statement)[0]);
        assert id > 0;
        ArrayList<Resource> removals = new ArrayList<>();
        System.out.println("Looking for ID: " + id + "...");
        for (Resource r: parser.resourceList){
            Book b = (Book) r;
            if (b.getId() == id){
                System.out.println("This resource is removed: ");
                System.out.println(b + SEPARATOR_LINEDIVIDER);
                removals.add(r);
            }
        }
        if(removals.isEmpty()) {
            System.out.println("No resources with id matching " + id + System.lineSeparator() + SEPARATOR_LINEDIVIDER);
        } else {
            parser.resourceList.removeAll(removals);
        }
    }

}
