package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.data.resources.Book;
import seedu.data.Status;
import seedu.data.resources.Resource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ui.UI.LINESEPARATOR;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;
import static seedu.util.TestUtil.getCurrentDate;

class ParserTest {

    @Test
    public void testProcessExitCommand() {
        Parser parser = new Parser();
        String validResponse = "exit";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        parser.processUserResponse(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();

        String expectedOutput = "Thanks for using SysLib CLI! We have saved the current resources and " +
                "events created." + System.lineSeparator() +
                "Hope to see you again soon!" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testProcessHelpCommand() {
        Parser parser = new Parser();
        String validResponse = "help";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        parser.processUserResponse(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();

        String expectedOutput = "Commands available:" + System.lineSeparator() +
                "[add] adds a new resource to the library inventory. " +
                "(e.g. add /i ISBN /t TITLE /a AUTHOR /tag TAG [/g GENRE /s STATUS])" + System.lineSeparator() +
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

    @Test
    public void testProcessUnknownCommand() {
        Parser parser = new Parser();
        String validResponse = "bye";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        parser.processUserResponse(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();

        String expectedOutput = "no commands found. Enter \"help\" for a list of commands.";
        expectedOutput += System.lineSeparator();
        expectedOutput += "____________________________________________________________";
        expectedOutput += System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    public void testProcessSuggestCommand() {
        Parser parser = new Parser();
        String validResponse = "hel";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        parser.processUserResponse(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();

        String expectedOutput = "no commands found. Enter \"help\" for a list of commands.";
        expectedOutput += System.lineSeparator();
        expectedOutput += "Did you mean: 'help'";
        expectedOutput += System.lineSeparator();
        expectedOutput += "____________________________________________________________";
        expectedOutput += System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testProcessCommands() {
        //temporary fix
        List<Resource> resources = new ArrayList<>();
        Book book = new Book("The Subtle Art of Not Giving a F*ck /a Mark Manson", "9780062457714",
                "Mark Manson", new String[]{"Self-help"}, 2, Status.AVAILABLE);
        resources.add(book);
        //Test add
        Parser parser = new Parser();
        String validResponse = "add /i 9781250255174 /t Surrounded by Idiots /a Thomas Erikson /tag B /g Self-help";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        parser.processUserResponse(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();
        String expectedOutput = "Attention: Status is not stated. Status set to default: AVAILABLE." +
                System.lineSeparator() + "This book is added:" + System.lineSeparator() +
                "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 Author: Thomas Erikson " +
                "Genre: Self-help Status: AVAILABLE Received Date: " + getCurrentDate() +
                SEPARATOR_LINEDIVIDER + System.lineSeparator();
        assertEquals(expectedOutput, output);
        //Add second book
        validResponse = "add /i 9780062457714 /t The Subtle Art of Not Giving a F*ck " +
                "/a Mark Manson /tag B /g Self-help";
        parser.processUserResponse(validResponse);
        expectedOutput += "Attention: Status is not stated. Status set to default: AVAILABLE." +
                System.lineSeparator() + "This book is added:" + System.lineSeparator()
                + "[B]  ID: 2 Title: The Subtle Art of Not Giving a F*ck ISBN: 9780062457714 " +
                "Author: Mark Manson Genre: Self-help Status: AVAILABLE Received Date: "
                + getCurrentDate() + SEPARATOR_LINEDIVIDER + System.lineSeparator();
        System.setOut(System.out);
        output = outputStream.toString();
        assertEquals(expectedOutput, output);
        //Test find
        validResponse = "find /t The Subtle Art of Not Giving a F*ck";
        parser.processUserResponse(validResponse);
        output = outputStream.toString();
        expectedOutput = "Attention: Status is not stated. Status set to default: AVAILABLE." +
                System.lineSeparator() + "This book is added:" + LINESEPARATOR +
                "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 Author: Thomas Erikson Genre:"+
                " Self-help Status: AVAILABLE Received Date: "+getCurrentDate() +LINESEPARATOR +
                "____________________________________________________________" +LINESEPARATOR +
                "Attention: Status is not stated. Status set to default: AVAILABLE." +
                System.lineSeparator() + "This book is added:" + LINESEPARATOR +
                "[B]  ID: 2 Title: The Subtle Art of Not Giving a F*ck ISBN: 9780062457714 Author: Mark "+
                "Manson Genre: Self-help Status: AVAILABLE Received Date: "+getCurrentDate() +LINESEPARATOR +
                "____________________________________________________________" +LINESEPARATOR +
                "Here are resources that matched the given filters:" +LINESEPARATOR +
                "                                                                         [BOOKS]" +LINESEPARATOR +
                "----------------------------------------------------------------------------------------"+
                "-----------------------------------------------------------" +LINESEPARATOR +
                "ID     Tag  Title                               ISBN          Author                "+
                "   Genre               Link           Status    Received Date  " +LINESEPARATOR +
                "----------------------------------------------------------------------------------------"+
                "-----------------------------------------------------------" +LINESEPARATOR +
                "2      B    The Subtle Art of Not Giving a F*ck 9780062457714 Mark Manson             "+
                " Self-help           null           AVAILABLE "+getCurrentDate()+"    " +LINESEPARATOR +
                 LINESEPARATOR + LINESEPARATOR +
                "There are currently 1 resource(s)." +LINESEPARATOR +
                "____________________________________________________________" +LINESEPARATOR + LINESEPARATOR ;
        assertEquals(expectedOutput, output);
        //Negative find test
        validResponse = "find /t No Such Book";
        parser.processUserResponse(validResponse);
        expectedOutput += "There are no resources found matching the given filters." + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        output = outputStream.toString();
        assertEquals(expectedOutput, output);
        //Test edit
        validResponse = "edit /id 1 /a Thomas";
        parser.processUserResponse(validResponse);
        output = outputStream.toString();
        expectedOutput += "Successfully updated! Your updated resource:" + System.lineSeparator()
                + System.lineSeparator() + "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 " +
                "Author: Thomas Genre: Self-help Status: AVAILABLE" + " Received Date: " + getCurrentDate()
                +System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, output);

        validResponse = "edit /id 1 /s lost";
        parser.processUserResponse(validResponse);
        output = outputStream.toString();
        expectedOutput += "Successfully updated! Your updated resource:" + System.lineSeparator()
                + System.lineSeparator() + "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 " +
                "Author: Thomas Genre: Self-help Status: LOST" + " Received Date: " + getCurrentDate()
                +System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, output);
        //Test delete
        validResponse = "delete /id 1";
        parser.processUserResponse(validResponse);
        output = outputStream.toString();
        expectedOutput += "Looking for ID: 1..." + System.lineSeparator() +
                "This resource is removed:" + System.lineSeparator() +
                "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 " +
                "Author: Thomas Genre: Self-help Status: LOST" + " Received Date: "
                + getCurrentDate() + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

}
