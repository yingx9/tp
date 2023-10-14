package seedu.commands;

import data.Resource;
import seedu.parser.Parser;

public class AddCommand extends Command{
    @Override
    public void execute(String statement, Parser parser) throws IllegalArgumentException {
        int isbnIndex = statement.indexOf("/isbn");
        String title = "";
        String isbn = "";
        if(isbnIndex == -1){
            title = statement;
            isbn = "blank";
        } else {
            title = statement.substring(0,isbnIndex);
            isbn = statement.substring(isbnIndex + 6);
        }
        System.out.println("This task is added: " + title);
        parser.taskList.add(new Resource(title,isbn));
    }

}
