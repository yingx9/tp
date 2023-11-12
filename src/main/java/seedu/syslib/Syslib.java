package seedu.syslib;

import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import seedu.parser.Parser;
import seedu.storage.Storage;
import seedu.ui.UI;

import java.util.ArrayList;
import java.util.List;

public class Syslib {
    /**
     * Main entry-point for the java.syslib.Syslib application.
     */
    public static final String FILEPATH = ".\\storage.txt";
    private static UI ui;
    private static Parser parser;
    private static Storage storage;


    public Syslib(String filePath) {
        ui = new UI();
        parser = new Parser();
        storage = new Storage(filePath, parser.container);
        try{
            List<Resource> resourceListLoad = new ArrayList<>();
            List<Event> eventListLoad = new ArrayList<>();
            storage.load(resourceListLoad, eventListLoad);
            ui.showLoadMessage(filePath, resourceListLoad, eventListLoad);

            parser.container.setResourceList(resourceListLoad);
            parser.container.setEventList(eventListLoad);


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
            parser.processUserResponse(response);
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
