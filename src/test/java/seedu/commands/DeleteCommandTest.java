package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.exception.SysLibException;
import seedu.parser.Parser;
import static seedu.util.TestUtil.getCurrentDate;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {
    private final Parser parser = new Parser();
    private final AddCommand addCommand = new AddCommand();
    private final DeleteCommand deleteCommand = new DeleteCommand();
    @Test
    public void deleteCommandValidData() throws SysLibException {
        addCommand.execute("/i 9783161484100 /t The Minds of Billy Milligan /a Daniel Keyes /tag B"
                , parser.container);

        deleteCommand.execute("/id 1", parser.container);
        assertEquals(parser.resourceList.size(), 0);
    }

    @Test
    public void deleteCommandOutput() throws SysLibException {
        addCommand.execute("/i 9783161484100 /t The Minds of Billy Milligan /a Daniel Keyes /tag B"
                , parser.container);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        deleteCommand.execute("/id 1", parser.container);
        String output = outputStream.toString();
        String expectedOutput = "Looking for ID: 1..." + System.lineSeparator()+
                "This resource is removed:" + System.lineSeparator() +
                "[B]  ID: 1 Title: The Minds of Billy Milligan ISBN: 9783161484100 Author: Daniel Keyes Genre: - " +
                "Status: AVAILABLE "  + "Received Date: " + getCurrentDate() + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    public void deleteCommandInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> deleteCommand.execute("", parser.container));
    }
}
