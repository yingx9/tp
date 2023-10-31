package seedu.commands.events;

import org.junit.jupiter.api.Test;
import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventListCommandTest {

    private final Parser parser = new Parser();
    private final EventAddCommand eventAddCommand = new EventAddCommand();
    private final EventListCommand eventListCommand = new EventListCommand();

    @Test
    public void eventListCommandOutputEmpty() throws SysLibException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        eventListCommand.execute("", parser);
        String output = outputStream.toString();
        String expectedOutput = "The event list is empty!" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(output, expectedOutput);
    }

    @Test
    public void eventListCommandOutputMultiple() throws SysLibException {
        eventAddCommand.execute("/t testrun /date 1-12-2001 /desc testing 123", parser);
        eventAddCommand.execute("/t testrun2 /date 1-12-2002 /desc testing 1234", parser);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        eventListCommand.execute("", parser);
        String output = outputStream.toString();
        String expectedOutput = "This is the current event list:" + System.lineSeparator() +
                "0: testrun | 01-12-2001 | testing 123" + System.lineSeparator() +
                "1: testrun2 | 01-12-2002 | testing 1234" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    @Test
    public void eventListCommandInvalidInput() {
        assertThrows(IllegalArgumentException.class, ()->eventListCommand.execute(
                "random", parser));
    }

}
