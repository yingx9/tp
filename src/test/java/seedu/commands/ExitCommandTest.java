package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExitCommandTest {
    @Test
    void execute() {
        Parser parser = new Parser();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute("", parser.container);

        String output = outputStream.toString();
        String expectedOutput = "Thanks for using SysLib CLI! We have saved the current resources and " +
                "events created." + System.lineSeparator() +
                "Hope to see you again soon!" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

}
