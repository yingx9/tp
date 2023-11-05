package seedu.syslib;

import seedu.data.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import seedu.parser.Parser;
import seedu.storage.Storage;
import seedu.ui.UI;

import java.util.ArrayList;
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
            List<Resource> resourceListLoad = new ArrayList<>();
            List<Event> eventListLoad = new ArrayList<>();
            storage.load(resourceListLoad, eventListLoad);
            parser.setResourceList(resourceListLoad);
            parser.setEventList(eventListLoad);

            if (!resourceListLoad.isEmpty()){
                ui.showLoadMessage(filePath, resourceListLoad);
            }
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
