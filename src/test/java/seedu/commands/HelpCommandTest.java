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
        String expectedOutput = "Commands available:" + System.lineSeparator() +
                "add: adds a new resource to the library inventory.(e.g. add /i ISBN /t TITLE /a AUTHOR " +
                "/tag TAG [/g GENRE /s STATUS])" + System.lineSeparator() +
                "delete: deletes the resource with the specified ID from the library inventory. " +
                "(e.g. delete /id 123456789)" + System.lineSeparator() +
                "list: list all resources OR filter by certain tags or genre.(e.g. list /tag B /g Fiction" +
                 System.lineSeparator() +
                "find: find a resource by title, author, ISBN or given id. (e.g. find /i 9780763630188)" +
                System.lineSeparator() + "edit: Edit a listing by entering its isbn to update its details. " +
                "(e.g. edit /i 123 /t NEW_TITLE /a NEW_AUTHOR)" + System.lineSeparator() +
                "eventadd: Add an event to the event list (e.g. eventadd /t TITLE " +
                "/date 23 Dec 2023 [/desc DESCRIPTION])" +
                System.lineSeparator() + "eventlist: List out all the event list (e.g. eventlist)" +
                System.lineSeparator() + "eventdelete: Delete an event in the event list based on the index " +
                "(e.g. eventdelete /i INDEX)" + System.lineSeparator() +
                "exit: displays a farewell message and exits the program (e.g. exit)" +
                System.lineSeparator() +
                "For more information, please refer to our user guide at:" +
                "https://ay2324s1-cs2113t-w11-1.github.io/tp/UserGuide.html" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }
}
