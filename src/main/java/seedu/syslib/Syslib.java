package seedu.syslib;

import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;
import seedu.storage.Storage;
import seedu.ui.UI;

import java.util.List;

public class Syslib {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static final String FILEPATH = ".\\storage.txt";
    private static UI ui;
    private static Parser parser;
    private static Storage storage;

    public Syslib(String filePath) {
        ui = new UI();
        parser = new Parser();
        storage = new Storage(filePath, parser);
        try{
            List<Resource> resourceListLoad = storage.load();
            parser.setResourceList(resourceListLoad);

        } catch (SysLibException SysLibEx){
            System.out.println(SysLibEx);
        }

    }

    public static void main(String[] args) {
        new Syslib(FILEPATH).run();
    }

    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String response = ui.readCommand();
            parser.process(response);
            try {
                storage.save();
            } catch (SysLibException SysLibEx){
                System.out.println(SysLibEx);
            }


            if (response.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
