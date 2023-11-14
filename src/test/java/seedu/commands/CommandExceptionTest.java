package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.commands.events.EventAddCommand;
import seedu.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandExceptionTest {
    private final Parser parser = new Parser();
    private final EventAddCommand eventAddCommand = new EventAddCommand();

    @Test
    public void extraCommand() {
        String input = "/t abc /date 22 May 2023 /jesus";
        assertThrows(IllegalArgumentException.class, () -> eventAddCommand.execute(input, parser.container));
    }
    @Test
    public void extraCommandVariable() {
        String input = "/t abc /date 22 May 2023 /jesus save";
        assertThrows(IllegalArgumentException.class, () -> eventAddCommand.execute(input, parser.container));
    }

    @Test
    public void extraVariable() {
        String input = "jesus /t abc /date 22 May 2023";
        assertThrows(IllegalArgumentException.class, () -> eventAddCommand.execute(input, parser.container));
    }

    @Test
    public void illegalDateFormat() {
        String input = "/t abc /date 12/12/2024";
        assertThrows(IllegalArgumentException.class, () -> eventAddCommand.execute(input, parser.container));
    }


}
