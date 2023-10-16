package seedu.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import seedu.parser.Parser;

class HelpCommandTest {

    @Test
    void execute() {
        Parser parser = new Parser();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute("", parser);

        String output = outputStream.toString();
        String expectedOutput = "Commands available:\n" +
                "add: add an item (e.g. add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE])\n" +
                "delete: delete an item (e.g. delete /id 123456789)\n" +
                "list: (e.g. list /tag B /g Fiction /a J. K. Rowling /i 9780763630188)\n" +
                "find: (e.g. find /i 9780763630188)\n" +
                "exit: (e.g. exit)\n" +
                "For more information, please refer to our user guide at:" +
                "https://ay2324s1-cs2113t-w11-1.github.io/tp/UserGuide.html\n"+
                "____________________________________________________________\n";

        assertEquals(expectedOutput, output);
    }
}
