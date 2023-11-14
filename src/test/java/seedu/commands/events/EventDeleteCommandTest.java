package seedu.commands.events;

import org.junit.jupiter.api.Test;
import seedu.exception.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventDeleteCommandTest {
    private final Parser parser = new Parser();
    private final EventAddCommand eventAddCommand = new EventAddCommand();
    private final EventDeleteCommand eventDeleteCommand = new EventDeleteCommand();

    @Test
    public void eventDeleteCommandOutput() throws SysLibException {
        eventAddCommand.execute("/t testrun /date 1 dec 2001 /desc testing 123", parser.container);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        eventDeleteCommand.execute("/id 0", parser.container);
        String output = outputStream.toString();
        String expectedOutput = "This event is removed:" + System.lineSeparator() +
                "testrun | 01 Dec 2001 | testing 123" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    public void eventDeleteCommandInvalidIndex() {
        assertThrows(IllegalArgumentException.class, ()->eventDeleteCommand.execute(
                "/id aaaaa", parser.container));
    }

    @Test
    public void eventDeleteCommandInsufficientData() {
        assertThrows(IllegalArgumentException.class, ()->eventDeleteCommand.execute("/id ", parser.container));
    }

    @Test
    public void eventDeleteCommandOutOfBounds() throws SysLibException {
        eventAddCommand.execute("/t testrun /date 1 dec 2001 /desc testing 123", parser.container);
        assertThrows(IllegalArgumentException.class, ()->eventDeleteCommand.execute("/id 1", parser.container));
    }

    @Test
    public void eventDeleteCommandEmpty(){
        assertThrows(SysLibException.class, ()->eventDeleteCommand.execute("/id 0", parser.container));
    }

    @Test
    public void eventDeleteDuplicateID() throws SysLibException{
        eventAddCommand.execute("/t test1 /date 1 dec 2001 /desc testing 123", parser.container);
        eventAddCommand.execute("/t test2 /date 2 dec 2001 /desc testing 456", parser.container);
        assertThrows(IllegalArgumentException.class, ()->eventDeleteCommand.execute("/id 0 /id 1", parser.container));
    }

    @Test
    public void eventDeleteInvalidArgument() throws SysLibException{
        eventAddCommand.execute("/t test1 /date 1 dec 2001 /desc testing 123", parser.container);
        eventAddCommand.execute("/t test2 /date 2 dec 2001 /desc testing 456", parser.container);
        assertThrows(IllegalArgumentException.class, ()->eventDeleteCommand.execute("not /id 0", parser.container));
    }
}
