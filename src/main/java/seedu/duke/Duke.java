package seedu.duke;

import seedu.parser.Parser;
import seedu.ui.UI;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static UI ui;
    private static Parser parser;

    public Duke() {
        ui = new UI();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String response = ui.readCommand();
            parser.process(response);
            if (response.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
