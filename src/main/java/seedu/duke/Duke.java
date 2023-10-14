package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        Parser parser = new Parser();
        waitForResponse(parser);

    }
    public static void waitForResponse(Parser parser) {
        Scanner scanner = new Scanner(System.in);
        String response;
        do {
            response = scanner.nextLine();
            if (!"bye".equalsIgnoreCase(response)) {
                parser.process(response);
            }
        } while (!"bye".equalsIgnoreCase(response));

        scanner.close();
    }
}
