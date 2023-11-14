package seedu.commands.events;

import org.junit.jupiter.api.Test;
import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EventEditCommandTest {
    private final Parser parser = new Parser();
    private final EventAddCommand eventAddCommand = new EventAddCommand();
    private final EventEditCommand eventEditCommand = new EventEditCommand();
    private GenericList<Resource, Event> container;

    @Test
    public void testInvalidIndex() throws SysLibException {
        eventAddCommand.execute("/t testrun /date 1 dec 2001 /desc testing 123", parser.container);
        assertThrows(IllegalArgumentException.class,
                () -> eventEditCommand.execute("/id 10", parser.container));
    }

    @Test
    public void testeventEditCommandOutputNothingChanged() throws SysLibException {
        eventAddCommand.execute("/t testrun /date 1 dec 2001 /desc testing 123", parser.container);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        eventEditCommand.execute("/id 0", parser.container);
        String output = outputStream.toString();
        String expectedOutput = "Event was not edited as nothing was changed." + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }


    @Test
    void testExecuteWithDateChanges() throws SysLibException {
        eventAddCommand.execute("/t testrun /date 1 dec 2001 /desc testing 123", parser.container);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        eventEditCommand.execute("/id 0 /t newtest /date 23 Dec 2023 /desc test456", parser.container);
        String output = outputStream.toString();
        String expectedOutput = "Event edited successfully. New event details:" + System.lineSeparator() +
                "0: newtest | 23 Dec 2023 | test456" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    void testExecuteWithDateChange() throws SysLibException {
        eventAddCommand.execute("/t test1 /date 1 dec 2001 /desc testing 123", parser.container);
        eventAddCommand.execute("/t test2 /date 2 dec 2001 /desc testing 123", parser.container);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        eventEditCommand.execute("/id 0 /date 23 Dec 2023", parser.container);
        String output = outputStream.toString();
        String expectedOutput = "Event index has changed as the date was changed." + System.lineSeparator() +
                "Event edited successfully. New event details:" + System.lineSeparator() +
                "1: test1 | 23 Dec 2023 | testing 123" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        eventEditCommand.execute("/id 1 /date 23 Nov 2001", parser.container);
        output = outputStream.toString();
        expectedOutput += "Event index has changed as the date was changed." + System.lineSeparator() +
                "Event edited successfully. New event details:" + System.lineSeparator() +
                "0: test1 | 23 Nov 2001 | testing 123" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    @Test
    void testExecuteWithEmptyList() throws SysLibException {
        assertThrows(SysLibException.class, () ->
                eventEditCommand.execute("/id 0", parser.container));
    }

    @Test
    void testParseDateWithInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> EventEditCommand.parseDate("2023-01-01"));
    }

    @Test
    void testCheckDate() {
        String formattedDate = EventEditCommand.checkDate("1 Jan 2023");
        assertEquals("01 Jan 2023", formattedDate);
    }

    @Test
    void testCheckDateWithInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> EventEditCommand.checkDate("01-Jan-2023"));
    }

    @Test
    public void eventEditCommandDuplicateTitle() throws SysLibException {
        eventAddCommand.execute("/t test1 /date 1 dec 2001 /desc testing 123", parser.container);
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute(
                "/id 0 /t Hello /t Hello2 /date 20 Jan 2023", parser.container));
    }

    @Test
    public void eventAddCommandDuplicateDate() throws SysLibException {
        eventAddCommand.execute("/t test1 /date 1 dec 2001 /desc testing 123", parser.container);
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute(
                "/id 0 /t Hello /date 17 Jan 2023 /date 20 Jan 2023", parser.container));
    }

    @Test
    public void eventAddCommandInvalidArguments() throws SysLibException {
        eventAddCommand.execute("/t test1 /date 1 dec 2001 /desc testing 123", parser.container);
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute(
                "Invalid /id 0 /t Hello /date 20 Jan 2023", parser.container));
    }

}
