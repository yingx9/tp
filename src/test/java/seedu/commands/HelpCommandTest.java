package seedu.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import seedu.parser.Parser;

class HelpCommandTest {

    @Test
    void testExecute() {
        Parser parser = new Parser();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute("", parser.container);
/*
        System.lineSeparator() + "[add] (Book) Adds a new book. " +
                "(e.g. add /i ISBN /t TITLE /a AUTHOR /tag TAG [/g GENRE /s STATUS])"+
        System.lineSeparator() + "[add] (eBook) Adds a new eBook. " +
                "(e.g. add /i ISBN /t TITLE /a AUTHOR /tag eb /l LINK [/g GENRE /s STATUS])"+
        System.lineSeparator() + "[add] (CD) Adds a new CD. " +
                "(e.g. add /i ISBN /t TITLE /c CREATOR /ty TYPE /tag cd [/s STATUS])"+
        System.lineSeparator() + "[add] (Magazine) Adds a new magazine. " +
                "(e.g. add /i ISBN /t TITLE /b BRAND /is ISSUE /tag m [/s STATUS])"+
        System.lineSeparator() + "[add] (eMagazine) Adds a new eMagazine. " +
                "(e.g. add /i ISBN /t TITLE /b BRAND /is ISSUE /tag em /l LINK [/s STATUS])"+
        System.lineSeparator() + "[add] (Newspaper) Adds a new newspaper. " +
                "(e.g. add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag n [/s STATUS])"+
        System.lineSeparator() + "[add] (eNewspaper) Adds a new eNewspaper. " +
                "(e.g. add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag en /l LINK [/s STATUS])"+
 */
        String output = outputStream.toString();
        String expectedOutput = "Commands available:" +
        System.lineSeparator() + "[add] (Book) Adds a new book. " +
                "(e.g. add /i ISBN /t TITLE /a AUTHOR /tag TAG [/g GENRE /s STATUS])" +
                System.lineSeparator() + "[add] (eBook) Adds a new eBook. " +
                "(e.g. add /i ISBN /t TITLE /a AUTHOR /tag eb /l LINK [/g GENRE /s STATUS])" +
                System.lineSeparator() + "[add] (CD) Adds a new CD. " +
                "(e.g. add /i ISBN /t TITLE /c CREATOR /ty TYPE /tag cd [/s STATUS])" +
                System.lineSeparator() + "[add] (Magazine) Adds a new magazine. " +
                "(e.g. add /i ISBN /t TITLE /b BRAND /is ISSUE /tag m [/s STATUS])" +
                System.lineSeparator() + "[add] (eMagazine) Adds a new eMagazine. " +
                "(e.g. add /i ISBN /t TITLE /b BRAND /is ISSUE /tag em /l LINK [/s STATUS])" +
                System.lineSeparator() + "[add] (Newspaper) Adds a new newspaper. " +
                "(e.g. add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag n [/s STATUS])" +
                System.lineSeparator() + "[add] (eNewspaper) Adds a new eNewspaper. " +
                "(e.g. add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag en /l LINK [/s STATUS])"+
                System.lineSeparator() +
                "[delete] deletes the resource with the specified ID from the library inventory. " +
                "(e.g. delete /id 123456789)" + System.lineSeparator() +
                "[list] lists all resources OR filter by certain tags, genre, or status. " +
                "(e.g. list /tag B /g Fiction /s AVAILABLE)" + System.lineSeparator() +
                "[find] finds a resource by title, author, ISBN or given id. " +
                "(e.g. find /i 9780763630188 /a AUTHOR)" + System.lineSeparator() +
                "[edit] edits a listing by entering its id to update its details. " +
                "(e.g. edit /id 123 /t NEW_TITLE /a NEW_AUTHOR)" + System.lineSeparator() +
                "[eventadd] adds an event to the database. " +
                "(e.g. eventadd /t TITLE /date 23 Dec 2023 [/desc DESCRIPTION])" + System.lineSeparator() +
                "[eventlist] lists out all events in the database. " +
                "(e.g. eventlist)" + System.lineSeparator() +
                "[eventdelete] deletes an event from the database based on the index. " +
                "(e.g. eventdelete /i INDEX)" + System.lineSeparator() +
                "[eventedit] edits an event in the event list based on the information given. " +
                "(e.g. eventedit /i INDEX [/t TITLE /date DATE /desc DESCRIPTION])" + System.lineSeparator() +
                "[summary] shows a summary of all resources and the next 3 events. " +
                "(e.g. summary)" + System.lineSeparator() +
                "[exit] displays a farewell message and exits the program. " +
                "(e.g. exit)" + System.lineSeparator() + System.lineSeparator() +
                "For more information, please refer to our user guide at: https://bit.ly/SyslibUserGuide"
                + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }
}
