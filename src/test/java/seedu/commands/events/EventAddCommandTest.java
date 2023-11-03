package seedu.commands.events;

import org.junit.jupiter.api.Test;
import seedu.exception.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventAddCommandTest {
    private final Parser parser = new Parser();
    private final EventAddCommand eventAddCommand = new EventAddCommand();

    @Test
    public void eventAddCommandValidData() throws SysLibException {
        eventAddCommand.execute("/t testrun /date 1-12-2001 /desc testing 123", parser);
        String output = parser.eventList.get(0).toString();
        String expectedOutput = "testrun | 01-12-2001 | testing 123";
        assertEquals(output, expectedOutput);
    }

    @Test
    public void eventAddCommandOutput() throws SysLibException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        eventAddCommand.execute("/t testrun /date 1-12-2001 /desc testing 123", parser);
        String output = outputStream.toString();
        String expectedOutput = "Event inserted at: 0" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    public void eventAddCommandInvalidId() {
        String input = "/t testrun /date 1 May 2001 /desc testing 123";
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute(
                "/t testrun /date 1 May 2001 /desc testing 123", parser));
    }

    @Test
    public void eventAddCommandInsufficientData() {
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute("/t ", parser));
    }
}
