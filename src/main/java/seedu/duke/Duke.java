package seedu.duke;

import ui.UI;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static UI ui;
    private static Parser parser;

    public Duke(){
        ui = new UI();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run(){
        ui.showWelcomeMessage();

        parser.process(ui.readCommand());
        waitForResponse(parser);

        ui.showExitMessage();
    }

    public static void waitForResponse(Parser parser) {
        String response;
        do {
            response = ui.readCommand();
            if (!"bye".equalsIgnoreCase(response)) {
                parser.process(response);
            }
        } while (!"bye".equalsIgnoreCase(response));
    }

}
