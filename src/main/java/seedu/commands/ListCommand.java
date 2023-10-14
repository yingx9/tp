package seedu.commands;

import seedu.parser.Parser;

public class ListCommand extends Command {
    @Override
    public void execute(String statement, Parser parser) throws IllegalArgumentException {
        int size = parser.taskList.size();
        System.out.println("list:");
        for(int temp = 0; temp < size; temp += 1){
            System.out.print((temp+1) + ": ");
            System.out.println(parser.taskList.get(temp).getTitle());
        }
    }
}
