package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest {
    @Test
    void execute() {
        Parser parser = new Parser();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute("", parser);

        String output = outputStream.toString();
        String expectedOutput = "Bye, hope to see you again soon!\n"+
                "____________________________________________________________\n";

        assertEquals(expectedOutput, output);
    }

}