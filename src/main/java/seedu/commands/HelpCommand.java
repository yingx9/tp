package seedu.commands;

import seedu.parser.Parser;

public class HelpCommand extends Command {
    @Override
    public void execute(String statement, Parser parser) throws IllegalArgumentException {
        System.out.println("Commands available:");
        System.out.println("add: add an item (e.g. add /n NAME /a AUTHOR /t TAG /IBSN IBSN [/g GENRE])");
        System.out.println("delete: delete an item (e.g. delete /id 123456789)");
        System.out.println("list: (e.g. list /tag B /genre Fiction /author J. K. Rowling)");
        System.out.println("find: (e.g. find /ISBN 9780763630188)");
        System.out.println("exit: (e.g. exit");
        System.out.println("For more information, please refer to our user guide at:" +
                "https://ay2324s1-cs2113t-w11-1.github.io/tp/");
    }
}
