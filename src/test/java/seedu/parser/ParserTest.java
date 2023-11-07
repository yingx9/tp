package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.data.resources.Book;
//import seedu.data.CreateResource;
import seedu.data.Status;
import seedu.data.resources.Resource;
//import seedu.exception.SysLibException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;
import static seedu.util.TestUtil.getCurrentDate;

class ParserTest {

    @Test
    public void testProcessExitCommand() {
        Parser parser = new Parser();
        String validResponse = "exit";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        parser.process(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();

        String expectedOutput = "Bye, hope to see you again soon!";
        expectedOutput += System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testProcessHelpCommand() {
        Parser parser = new Parser();
        String validResponse = "help";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        parser.process(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();

        String expectedOutput = "Commands available:" + System.lineSeparator() +
                "add: adds a new resource to the library inventory.(e.g. add /i ISBN /t TITLE /a AUTHOR " +
                "/tag TAG [/g GENRE /s STATUS])" + System.lineSeparator() +
                "delete: deletes the resource with the specified ID from the library inventory. " +
                "(e.g. delete /id 123456789)" + System.lineSeparator() +
                "list: list all resources OR filter by certain tags or genre.(e.g. list /tag B /g Fiction" +
                System.lineSeparator() +
                "find: find a resource by title, author, ISBN or given id. (e.g. find /i 9780763630188)" +
                System.lineSeparator() + "edit: Edit a listing by entering its isbn to update its details. " +
                "(e.g. edit /i 123 /t NEW_TITLE /a NEW_AUTHOR)" + System.lineSeparator() +
                "eventadd: Add an event to the event list (e.g. eventadd /t TITLE " +
                "/date 23 Dec 2023 [/desc DESCRIPTION])" +
                System.lineSeparator() + "eventlist: List out all the event list (e.g. eventlist)" +
                System.lineSeparator() + "eventdelete: Delete an event in the event list based on the index " +
                "(e.g. eventdelete /i INDEX)" + System.lineSeparator() +
                "exit: displays a farewell message and exits the program (e.g. exit)" +
                System.lineSeparator() +
                "For more information, please refer to our user guide at:" +
                "https://ay2324s1-cs2113t-w11-1.github.io/tp/UserGuide.html" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    public void testProcessUnknownCommand() {
        Parser parser = new Parser();
        String validResponse = "bye";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        parser.process(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();

        String expectedOutput = "no commands found. Enter \"help\" for a list of commands.";
        expectedOutput += System.lineSeparator();
        expectedOutput += "____________________________________________________________";
        expectedOutput += System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    public void testProcessCommands() {
        //temporary fix
        List<Resource> resources = new ArrayList<>();
        Book book = new Book("The Subtle Art of Not Giving a F*ck /a Mark Manson",
                "9780062457714", "Mark Manson", new String[]{"Self-help"}, 2, Status.AVAILABLE);
        resources.add(book);
        //Test add
        Parser parser = new Parser();
        String validResponse = "add /i 9781250255174 /t Surrounded by Idiots /a Thomas Erikson " +
                "/tag B /g Self-help";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        parser.process(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();
        String expectedOutput = "This book is added:" + System.lineSeparator() +
                "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 Author: Thomas Erikson Genre: Self-help " +
                "Status: AVAILABLE Received Date: 07 Nov 2023" + SEPARATOR_LINEDIVIDER + System.lineSeparator();
        assertEquals(expectedOutput, output);
        //Add second book
        validResponse = "add /i 9780062457714 /t The Subtle Art of Not Giving a F*ck /a Mark Manson " +
                "/tag B /g Self-help";
        parser.process(validResponse);
        expectedOutput += "This book is added:" + System.lineSeparator() +
                "[B]  ID: 2 Title: The Subtle Art of Not Giving a F*ck ISBN: 9780062457714 Author: Mark Manson " +
                "Genre: Self-help Status: AVAILABLE Received Date: 07 Nov 2023" + SEPARATOR_LINEDIVIDER +
                System.lineSeparator();
        //            Test list
        //            validResponse = "list";
        //            parser.process(validResponse);
        //            expectedOutput += "Listing all resources in the Library:" + System.lineSeparator()
        //                    + System.lineSeparator() +
        //                    "1. [B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 " +
        //                    "Author: Thomas Erikson Genre: Self-help Status: AVAILABLE" + " Received Date: "
        //                    + getCurrentDate() + System.lineSeparator() +
        //                    "2. [B]  ID: 2 Title: The Subtle Art of Not Giving a F*ck ISBN: 9780062457714 " +
        //                    "Author: Mark Manson Genre: Self-help Status: AVAILABLE" + " Received Date: "
        //                    + getCurrentDate() + System.lineSeparator() + System.lineSeparator() +
        //                    "There are currently 2 resource(s)." + System.lineSeparator() +
        //                    "____________________________________________________________" + System.lineSeparator();
        System.setOut(System.out);
        output = outputStream.toString();
        assertEquals(expectedOutput, output);
        //Test find
        validResponse = "find /t The Subtle Art of Not Giving a F*ck";
        parser.process(validResponse);
        output = outputStream.toString();
        expectedOutput = "This book is added:" + System.lineSeparator() +
                "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 Author: Thomas Erikson Genre: Self-help " +
                "Status: AVAILABLE Received Date: 07 Nov 2023" +  System.lineSeparator() +
                "____________________________________________________________" +System.lineSeparator() +
                "This book is added:" + System.lineSeparator() +
                "[B]  ID: 2 Title: The Subtle Art of Not Giving a F*ck ISBN: 9780062457714 Author: Mark Manson " +
                "Genre: Self-help Status: AVAILABLE Received Date: 07 Nov 2023" + System.lineSeparator() +
                "____________________________________________________________" +System.lineSeparator() +
                "Here are resources that matched the given filters:" +System.lineSeparator() +
                "                                                                                  [BOOKS]"
                + System.lineSeparator() +
                "-----------------------------------------------------------------------------------------------" +
                "------------------------------------------------------------" +System.lineSeparator() +
                "ID             Tag  Title                               ISBN          Author        " +
                "           Genre               Link           Status    Received Date  " +System.lineSeparator() +
                "---------------------------------------------------------------------------------------------------"
                +"--------------------------------------------------------" +System.lineSeparator() +
                "2              B    The Subtle Art of Not Giving a F*ck 9780062457714 Mark Manson        "+
                "      Self-help           null           AVAILABLE 07 Nov 2023    " +System.lineSeparator() +
                System.lineSeparator() +
                System.lineSeparator() +
                "There are currently 1 resource(s)." +System.lineSeparator() +
                "____________________________________________________________" +System.lineSeparator()
                + System.lineSeparator();
        assertEquals(expectedOutput, output);
        //Negative find test
        validResponse = "find /t No Such Book";
        parser.process(validResponse);
        expectedOutput += "There are no resources found matching the given filters." + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        output = outputStream.toString();
        assertEquals(expectedOutput, output);
        //Test edit
        validResponse = "edit /i 9781250255174 /a Thomas";
        parser.process(validResponse);
        output = outputStream.toString();
        expectedOutput += "Successfully updated! Your updated resource:" + System.lineSeparator()
                + System.lineSeparator() + "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 " +
                "Author: Thomas Genre: Self-help Status: AVAILABLE" + " Received Date: " + getCurrentDate()
                +System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, output);

        validResponse = "edit /i 9781250255174 /s lost";
        parser.process(validResponse);
        output = outputStream.toString();
        expectedOutput += "Successfully updated! Your updated resource:" + System.lineSeparator()
                + System.lineSeparator() + "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 " +
                "Author: Thomas Genre: Self-help Status: LOST" + " Received Date: " + getCurrentDate()
                +System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, output);
        //Test delete
        validResponse = "delete /id 1";
        parser.process(validResponse);
        output = outputStream.toString();
        expectedOutput += "Looking for ID: 1..." + System.lineSeparator() +
                "This resource is removed: " + System.lineSeparator() +
                "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 " +
                "Author: Thomas Genre: Self-help Status: LOST" + " Received Date: " + getCurrentDate()
                + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    // ADDCOMMANDS PARSER TEST
    /*

    @Test
    public void testParseAddCommand() throws SysLibException {
        // Test case 1: Valid input with both genre and status
        String statement1 = "add /id 123456789 /t Moby Dick /a Herman Melville /tag B /i 9780763630188 " +
                "/g Adventure, Fiction /s lost";
        String[] result = Parser.parseAddCommand(statement1);

        assertEquals("123456789", result[0]);
        assertEquals("Moby Dick", result[1]);
        assertEquals("Herman Melville", result[2]);
        assertEquals("B", result[3]);
        assertEquals("9780763630188", result[4]);
        assertEquals("Adventure, Fiction", result[5]);
        assertEquals("lost", result[6]);

        // Test case 2: Valid input without genre
        String statement2 = "add /id 123 /t Moby Dick /a Herman Melville /tag B /i 9780763630188 /s lost";
        result = Parser.parseAddCommand(statement2);

        assertEquals("123", result[0]);
        assertEquals("Moby Dick", result[1]);
        assertEquals("Herman Melville", result[2]);
        assertEquals("B", result[3]);
        assertEquals("9780763630188", result[4]);
        assertNull(result[5]);
        assertEquals("lost", result[6]);

        // Test case 3: Valid input without status and genre
        String statement3 = "add /id 456 /t Moby Dick /a Herman Melville /tag B /i 9780763630188";
        result = Parser.parseAddCommand(statement3);

        assertEquals("456", result[0]);
        assertEquals("Moby Dick", result[1]);
        assertEquals("Herman Melville", result[2]);
        assertEquals("B", result[3]);
        assertEquals("9780763630188", result[4]);
        assertNull(result[5]);
        assertEquals("Available", result[6]);

        // Test case 4: Invalid input (missing /tag b)
        String statement4 = "add /id 123456789 /t Moby Dick /a Herman Melville /tag C /i 9780763630188 " +
                "/g Adventure, Fiction /s Borrowed";
        assertThrows(SysLibException.class, () -> Parser.parseAddCommand(statement4));
    }

    @Test
    public void testParseAddBook() throws SysLibException {
        // Test case 1: Valid input with /g GENRE and /s STATUS
        String statement1 = "/id ID /t TITLE /a AUTHOR /tag b /i ISBN /g GENRE /s lost";
        String[] expectedArgs1 = {"ID", "TITLE", "AUTHOR", "b", "ISBN", "GENRE", "lost"};
        assertArrayEquals(expectedArgs1, Parser.parseAddBook(statement1));

        // Test case 2: Valid input without /g GENRE or /s STATUS
        String statement2 = "/id ID /t TITLE /a AUTHOR /tag b /i ISBN";
        String[] expectedArgs2 = {"ID", "TITLE", "AUTHOR", "b", "ISBN", null, "Available"};
        assertArrayEquals(expectedArgs2, Parser.parseAddBook(statement2));
    }

    // CreateResourceTest
    @Test
    public void testCreateBook() {
        String[] args = {"123", "CS2113T", "W11", "B", "1234", "Horror", "lost"};

        Book book = null;
        book = CreateResource.createBook(args);

        int expectedId = 123;
        String expectedTitle = "CS2113T";
        String expectedAuthor = "W11";
        String expectedIsbn = "1234";
        String[] expectedGenres = { "Horror" };
        Status expectedStatus = Status.LOST;

        assertEquals(expectedId, book.getId());
        assertEquals(expectedTitle, book.getTitle());
        assertEquals(expectedAuthor, book.getAuthor());
        assertEquals(expectedIsbn, book.getISBN());
        assertArrayEquals(expectedGenres, book.getGenre());
        assertEquals(expectedStatus, book.getStatus());
    }
    @Test
    public void testCreateBookInvalidId() {
        String[] args = {"not_a_number", "Sample Title", "Sample Author", "", "Sample ISBN", "Sample Genre"};

        assertThrows(NumberFormatException.class, () -> CreateResource.createBook(args));
    }

    */
}
