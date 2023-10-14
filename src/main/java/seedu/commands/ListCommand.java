package seedu.commands;

import seedu.parser.Parser;
public class ListCommand extends Command {
    @Override
    public void execute(String statement, Parser parser) throws IllegalArgumentException {
        int size = parser.taskList.size();

        if (size == 0){
            System.out.println("There are 0 resources in the library. \n");
        } else {
            System.out.println("Listing all Resources in the Library: \n");
            for (int i = 0; i < size; i += 1) {
                String resourceDetails = parser.taskList.get(i).toString();
                System.out.println(i+1 + ". " + resourceDetails + System.lineSeparator());

            }

            System.out.println("There are currently " + size + " resource(s).\n");
        }
    }

}
