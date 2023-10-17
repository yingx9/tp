package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    void execute() throws SysLibException {
        Parser parser = new Parser();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        DeleteCommand deleteCommand = new DeleteCommand();
        deleteCommand.execute("/id 1111", parser);

        String output = outputStream.toString();

        String expectedOutput = "Looking for ID: 1111..." + System.lineSeparator()+
                "No resources with id matching 1111" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);

    }
}
