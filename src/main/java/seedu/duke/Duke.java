package seedu.duke;

import java.util.Scanner;
import ui.UI;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static UI ui;

    public Duke(){
        // init here
        ui = new UI();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run(){
        ui.showWelcomeMessage();

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        Parser parser = new Parser();
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
