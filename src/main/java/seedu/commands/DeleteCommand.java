package seedu.commands;

import seedu.data.Book;
import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.DeleteParser;
import seedu.parser.Parser;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    @Override
    public void execute(String statement, Parser parser) throws SysLibException {
        int id = parseInt(DeleteParser.parseDelete(statement));
        ArrayList<Resource> toRemove = new ArrayList<>();
        System.out.println("Looking for ID: " + id + "...");
        for (Resource r: parser.resourceList){
            Book b = (Book) r;
            if (b.getId() == id){
                System.out.println("This resource is removed: ");
                System.out.println(b);
                System.out.println("____________________________________________________________");
                toRemove.add(r);
            }
        }
        if(toRemove.isEmpty()){
            System.out.println("No resources with id matching " + id + System.lineSeparator() +
                    "____________________________________________________________");
        } else {
            parser.resourceList.removeAll(toRemove);
        }
    }

}
