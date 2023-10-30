package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.data.SysLibException;
import seedu.parser.Parser;

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
        addCommand.execute("/id 123456789 /t The Minds of Billy Milligan /a Daniel Keyes /tag B /i 987654321 "
                , parser);

        deleteCommand.execute("/id 123456789", parser);

        assertEquals(parser.resourceList.size(), 0);
    }

    @Test
    public void deleteCommandOutput() throws SysLibException {
        addCommand.execute("/id 123456789 /t The Minds of Billy Milligan /a Daniel Keyes /tag B /i 987654321 "
                , parser);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        deleteCommand.execute("/id 123456789", parser);
        String output = outputStream.toString();
        String expectedOutput = "Looking for ID: 123456789..." + System.lineSeparator()+
                "This resource is removed: " + System.lineSeparator() +
                "[B]  ID: 123456789 Title: The Minds of Billy Milligan ISBN: 987654321 Author: Daniel Keyes Genre: - " +
                "Status: AVAILABLE" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    public void deleteCommandInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> deleteCommand.execute("", parser));
    }
}
