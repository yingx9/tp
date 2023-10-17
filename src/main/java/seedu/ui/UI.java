package seedu.ui;
import java.util.Scanner;

public class UI {
    protected static String logo =
            " ____            _     _ _        ____ _     ___ \n" +
                    "/ ___| _   _ ___| |   (_) |__    / ___| |   |_ _|\n" +
                    "\\___ \\| | | / __| |   | | '_ \\  | |   | |    | | \n" +
                    " ___) | |_| \\__ \\ |___| | |_) | | |___| |___ | | \n" +
                    "|____/ \\__, |___/_____|_|_.__/   \\____|_____|___|\n" +
                    "       |___/                                     ";
    protected static final String LINEDIVIDER = "____________________________________________________________";
    protected Scanner myScanner;

    public UI(){
        this.myScanner = new Scanner(System.in);
    }

    public void showWelcomeMessage(){
        showLine();
        System.out.println(logo);
        System.out.println("What do you want to do?");
        showLine();
    }

    public void showExitMessage(){
        System.out.println("Bye, hope to see you again soon!");
        showLine();
    }

    public void showHelpMessage(){
        System.out.println("Commands available:");
<<<<<<< HEAD
        System.out.println("add: adds a new resource to the library inventory." +
                "(e.g. add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE])");
        System.out.println("delete: deletes the resource with the specified ID from the library inventory. " +
                "(e.g. delete /id 123456789)");
        System.out.println("list: list all resources OR filter by certain tags or genre." +
                "(e.g. list /tag B /g Fiction /a J. K. Rowling /i 9780763630188)");
        System.out.println("find: find a resource by title, author, ISBN or given id. (e.g. find /i 9780763630188)");
        System.out.println("exit: displays a farewell message and exits the program (e.g. exit)");
        System.out.println("For more information, please refer to our user guide at:" +
=======
        System.out.println("add: add an item (e.g. add /id 123456789 /t Moby Dick /a Herman Melville " +
                "/tag B /i 9780763630188 /g Adventure, Fiction)");
        System.out.println("delete: delete an item (e.g. delete /id 123456789)");
        System.out.println("list: (e.g. list /tag B /g Fiction)");
        System.out.println("find: (e.g. find /i 9780763630188)");
        System.out.println("exit: (e.g. exit)");
        System.out.println("For more information, please refer to our user guide at: " +
>>>>>>> 446c9c1b9d6b76ce5b9b366d67eba021b3075894
                "https://ay2324s1-cs2113t-w11-1.github.io/tp/UserGuide.html");
        showLine();
    }

    public void showError(Exception e){
        System.out.println(e);
    }

    public String readCommand(){
        System.out.print("> ");
        return myScanner.nextLine();
    }

    public void showLine(){
        System.out.println(LINEDIVIDER);
    }
}
