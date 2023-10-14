package seedu.duke;

import UI.Ui;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static Ui UI;

    public Duke(){
        // init here
        UI = new Ui();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run(){
        UI.showWelcomeMessage();

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        Parser parser = new Parser();
        waitForResponse(parser);

        UI.showExitMessage();
    }

    public static void waitForResponse(Parser parser) {
        String response;
        do {
            response = UI.readCommand();
            if (!"bye".equalsIgnoreCase(response)) {
                parser.process(response);
            }
        } while (!"bye".equalsIgnoreCase(response));
    }

}
