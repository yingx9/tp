package ui;

import java.util.Scanner;

public class UI {
    protected static String logo =
            " ____            _     _ _        ____ _     ___ \n" +
            "/ ___| _   _ ___| |   (_) |__    / ___| |   |_ _|\n" +
            "\\___ \\| | | / __| |   | | '_ \\  | |   | |    | | \n" +
            " ___) | |_| \\__ \\ |___| | |_) | | |___| |___ | | \n" +
            "|____/ \\__, |___/_____|_|_.__/   \\____|_____|___|\n" +
            "       |___/                                     ";
    protected static String LINEDIVIDER = "____________________________________________________________";
    protected Scanner myScanner;

    public UI(){
        this.myScanner = new Scanner(System.in);
    }

    public void showWelcomeMessage(){
        showLine();
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        showLine();
    }

    public void showExitMessage(){
        System.out.println("Bye, hope to see you again soon!");
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
