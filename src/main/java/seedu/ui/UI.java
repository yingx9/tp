package seedu.ui;
import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.data.resources.Magazine;
import seedu.data.resources.Book;
import seedu.data.resources.CD;
import seedu.data.resources.Newspaper;

import seedu.exception.SysLibException;

import java.util.List;
import java.util.Scanner;

import static seedu.ui.MessageFormatter.formatLastLineDivider;

public class UI {
    public static final String LINESEPARATOR = System.lineSeparator();
    public static final String LINEDIVIDER = "____________________________________________________________";
    public static final String SEPARATOR_LINEDIVIDER = LINESEPARATOR + LINEDIVIDER;

    public static final String ZERO_RESOURCES_MESSAGE =  formatLastLineDivider("There are currently 0 resources.");
    protected static final String LOGO =
            "             .....................                  \n" +
            "          -##@*+*@*++++++++++#@++##                 \n" +
            "         .@. @-=%=            *#-+%                 \n" +
            "         :@  @+-  :----------. .=#%                 \n" +
            "         :@  @.  *%----------@-  =%                 \n" +
            "         :@  @.  #*          @=  =%                 \n" +
            "         :@  @.  #*          *:  :+                 \n" +
            "         :@  @.  *%-----.  .=+****+-.               \n" +
            "         :@  @.   :-----.-#*-.   .:-*#-             \n" +
            "         :@  @.        .%+.     .@*#+.*%.           \n" +
            "         :@  @:        %=       %*  +@.=%           \n" +
            "         :@  @*#*.    -@      *###***+. @-          \n" +
            "         :@ .@:.=@... -@ .+*#*####      @-          \n" +
            "         :@#*++++++++. %=.%+  +#       +%           \n" +
            "         :@. =++++++++-.%*.+%*@.      *%.           \n" +
            "          %+  ........   =#*-::   .-*%=             \n" +
            "           =*************. .=+****+-.               \n" +
            " ____            _     _ _        ____ _     ___    \n" +
            "/ ___| _   _ ___| |   (_) |__    / ___| |   |_ _|   \n" +
            "\\___ \\| | | / __| |   | | '_ \\  | |   | |    | | \n" +
            " ___) | |_| \\__ \\ |___| | |_) | | |___| |___ | |  \n" +
            "|____/ \\__, |___/_____|_|_.__/   \\____|_____|___| \n" +
            "       |___/                                        \n";

    protected Scanner myScanner;

    public UI(){
        this.myScanner = new Scanner(System.in);
    }

    public void showWelcomeMessage(){
        showLine();
        System.out.println(LOGO);
        System.out.println("Hello! What would you like to do?");
        showLine();
    }

    public void showExitMessage(){
        System.out.println("Thanks for using SysLib CLI! We have saved the current resources and events.");
        System.out.println("See you next time!");
        showLine();
    }

    public void showHelpMessage(){
        System.out.println("Commands available:");
        System.out.println("[add] adds a new resource to the library inventory. (e.g. add /i ISBN /t TITLE /a AUTHOR /tag TAG [/g GENRE /s STATUS])");
        System.out.println("[delete] deletes the resource with the specified ID from the library inventory. (e.g. delete /id 123456789)");
        System.out.println("[list] lists all resources OR filter by certain tags, genre, or status. (e.g. list /tag B /g Fiction /s AVAILABLE)");
        System.out.println("[find] finds a resource by title, author, ISBN or given id. (e.g. find /i 9780763630188 /a AUTHOR)");
        System.out.println("[edit] edits a listing by entering its id to update its details. (e.g. edit /id 123 /t NEW_TITLE /a NEW_AUTHOR)");
        System.out.println("[eventadd] adds an event to the database. (e.g. eventadd /t TITLE /date 23 Dec 2023 [/desc DESCRIPTION])");
        System.out.println("[eventlist] lists out all events in the database. (e.g. eventlist)");
        System.out.println("[eventdelete] deletes an event from the database based on the index. (e.g. eventdelete /i INDEX)");
        System.out.println("[eventedit] edits an event in the event list based on the information given. (e.g. eventedit /i INDEX [/t TITLE /date DATE /desc DESCRIPTION])");
        System.out.println("[summary] shows a summary of all resources and the next 3 events. (e.g. summary)");
        System.out.println("[exit] displays a farewell message and exits the program. (e.g. exit)" + System.lineSeparator());
        System.out.println("For more information, please refer to our user guide at: https://bit.ly/SyslibUserGuide");
        showLine();
    }

    public String readCommand(){
        System.out.print("> ");
        return myScanner.nextLine();
    }

    public void showNoFileFoundMessage(String filePath){
        showLine();
        System.out.println("Storage file not found.");
        System.out.println("Creating new data file @ " + filePath);
    }

    public void showFileFoundMessage(String filePath){
        showLine();
        System.out.println("Storage file found @ " + filePath);
    }

    public void showLoadMessage(String filepath, List<Resource> resourcelist, List<Event> eventlist){
        System.out.printf("Loaded %d resources and %d events!%n", resourcelist.size(), eventlist.size());
    }

    public void showLine(){
        System.out.println(LINEDIVIDER);
    }

    public static String showResourcesDetails(List<Resource> resourcesList) throws SysLibException {

        String messageToDisplay = "";

        if (resourcesList.isEmpty()) {
            messageToDisplay += ZERO_RESOURCES_MESSAGE;
            return messageToDisplay;
        }

        ResourceDisplayFormatter resourceDisplayFormatter = new ResourceDisplayFormatter(resourcesList);

        for (Resource resource : resourcesList) {

            if (resource instanceof Book) {
                resourceDisplayFormatter.setBookDisplayFormatter(resource);
            } else if (resource instanceof Magazine) {
                resourceDisplayFormatter.setMagazineDisplayFormatter(resource);
            } else if (resource instanceof CD ) {
                resourceDisplayFormatter.setCDDisplayFormatter(resource);
            } else if (resource instanceof Newspaper) {
                resourceDisplayFormatter.setNewspaperDisplayFormatter(resource);
            } else {
                throw new SysLibException("Invalid resource!");
            }

        }

        messageToDisplay += resourceDisplayFormatter.getFinalDisplayFormat();

        messageToDisplay += formatLastLineDivider("There are currently " + resourcesList.size() + " resource(s).");

        return messageToDisplay;
    }

}
