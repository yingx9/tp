package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.data.Book;
import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    void execute() throws SysLibException {
        Parser parser = new Parser();
        parser.resourceList.add(new Book("Title1", "ISBN1", "Author1",null, 1234));
        parser.resourceList.add(new Book("Title2", "ISBN2", "Author2", null,5678));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        DeleteCommand deleteCommand = new DeleteCommand();
        deleteCommand.execute("/id 1234", parser);

        String output = outputStream.toString();

        String expectedOutput = "Looking for ID: 1234..." + System.lineSeparator()+
                "This resource is removed: " + System.lineSeparator()+
                "[B]  ID: 1234 Title: Title1 ISBN: ISBN1 Author: Author1 Genre: -" + System.lineSeparator()+
                "Bye, hope to see you again soon!" + System.lineSeparator()+
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);

        outputStream.reset();

        deleteCommand.execute("/id 1111", parser);

        output = outputStream.toString();
        expectedOutput = "Looking for ID: 1111..." + System.lineSeparator()+
                "No resources with id matching 1111" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);

    }
}
