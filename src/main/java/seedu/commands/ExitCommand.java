package seedu.commands;

import seedu.parser.Parser;
import seedu.ui.UI;

public class ExitCommand extends Command{
    private static UI ui;
    @Override
    public void execute(String statement, Parser parser) throws IllegalArgumentException {
        ui = new UI();
        ui.showExitMessage();
    }
}
