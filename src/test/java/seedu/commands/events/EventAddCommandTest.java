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
    public void testeventAddCommandValidData() throws SysLibException {
        eventAddCommand.execute("/t testrun /date 1 Dec 2001 /desc testing 123", parser.container);
        String output = parser.eventsList.get(0).toString();
        String expectedOutput = "testrun | 01 Dec 2001 | testing 123";
        assertEquals(output, expectedOutput);
    }

    @Test
    public void testeventAddCommandWithoutDescription() throws SysLibException {
        eventAddCommand.execute("/t testrun2 /date 1 Dec 2001", parser.container);
        String output = parser.eventsList.get(0).toString();
        String expectedOutput = "testrun2 | 01 Dec 2001 | null";
        assertEquals(output, expectedOutput);
    }

    @Test
    public void eventAddCommandOutput() throws SysLibException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        eventAddCommand.execute("/t testrun /date 1 DEC 2001 /desc testing 123", parser.container);
        String output = outputStream.toString();
        String expectedOutput = "Event inserted at: 0" + System.lineSeparator() +
                "0: testrun | 01 Dec 2001 | testing 123" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    public void eventAddCommandOutputSoonerDate() throws SysLibException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        eventAddCommand.execute("/t testrun /date 1 DEC 2001 /desc testing 123", parser.container);
        String output = outputStream.toString();
        String expectedOutput = "Event inserted at: 0" + System.lineSeparator() +
                "0: testrun | 01 Dec 2001 | testing 123" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        eventAddCommand.execute("/t testrun2 /date 1 NOV 2001", parser.container);
        output = outputStream.toString();
        expectedOutput += "Event inserted at: 0" + System.lineSeparator() +
                "0: testrun2 | 01 Nov 2001 | null" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }
    @Test
    public void eventAddCommandInvalidDate() {
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute(
                "/t testrun /date tmr /desc testing 123", parser.container));
    }

    @Test
    public void eventAddCommandInvalidDateFormat() {
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute(
                "/t testrun /date 23-12-2023", parser.container));
    }

    @Test
    public void eventAddCommandInvalidTitle() {
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute(
                "/t /date 12 Jan 2022", parser.container));
    }

    @Test
    public void eventAddCommandInsufficientData() {
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute("/t ", parser.container));
    }

    @Test
    public void eventAddCommandInsufficientData2() {
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute("/t Hello", parser.container));
    }

    @Test
    public void eventAddCommandDuplicateTitle() {
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute(
                "/t Hello /t Hello2 /date 20 Jan 2023", parser.container));
    }

    @Test
    public void eventAddCommandDuplicateDate() {
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute(
                "/t Hello /date 17 Jan 2023 /date 20 Jan 2023", parser.container));
    }

    @Test
    public void eventAddCommandInvalidArguments() {
        assertThrows(IllegalArgumentException.class, ()->eventAddCommand.execute(
                "Invalid /t Hello /date 20 Jan 2023", parser.container));
    }
}
