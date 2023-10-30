package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.data.Book;
import seedu.data.Status;
import seedu.data.SysLibException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
                "add: adds a new resource to the library inventory.(e.g. add /id ID /t TITLE /a AUTHOR " +
                "/tag TAG /i ISBN [/g GENRE])" + System.lineSeparator() +
                "delete: deletes the resource with the specified ID from the library inventory. " +
                "(e.g. delete /id 123456789)" + System.lineSeparator() +
                "list: list all resources OR filter by certain tags or genre.(e.g. list /tag B /g Fiction" +
                System.lineSeparator() +
                "find: find a resource by title, author, ISBN or given id. (e.g. find /i 9780763630188)" +
                System.lineSeparator() + "exit: displays a farewell message and exits the program (e.g. exit)" +
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
        //Test add
        Parser parser = new Parser();
        String validResponse = "add /id 1 /t Surrounded by Idiots /a Thomas Erikson " +
                "/tag B /i 9781250255174 /g Self-help";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        parser.process(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();
        String expectedOutput = "This book is added: Surrounded by Idiots" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();;
        assertEquals(expectedOutput, output);
        //Add second book
        validResponse = "add /id 2 /t The Subtle Art of Not Giving a F*ck /a Mark Manson " +
                "/tag B /i 9780062457714 /g Self-help";
        parser.process(validResponse);
        expectedOutput += "This book is added: The Subtle Art of Not Giving a F*ck" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator()
                + System.lineSeparator();;
        //Test list
        validResponse = "list";
        parser.process(validResponse);
        expectedOutput += "Listing all resources in the Library:" + System.lineSeparator() + System.lineSeparator() +
                "1. [B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 " +
                "Author: Thomas Erikson Genre: Self-help Status: AVAILABLE" + System.lineSeparator()+
                "2. [B]  ID: 2 Title: The Subtle Art of Not Giving a F*ck ISBN: 9780062457714 " +
                "Author: Mark Manson Genre: Self-help Status: AVAILABLE" + System.lineSeparator()
                + System.lineSeparator() + "There are currently 2 resource(s)." + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        System.setOut(System.out);
        output = outputStream.toString();
        assertEquals(expectedOutput, output);
        //Test find
        validResponse = "find /t The Subtle Art of Not Giving a F*ck";
        parser.process(validResponse);
        expectedOutput += "Here are resources that matched the given filters:" + System.lineSeparator() +
                "[B]  ID: 2 Title: The Subtle Art of Not Giving a F*ck ISBN: 9780062457714 " +
                "Author: Mark Manson Genre: Self-help Status: AVAILABLE" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();;
        output = outputStream.toString();
        assertEquals(expectedOutput, output);
        //Negative find test
        validResponse = "find /t No Such Book";
        parser.process(validResponse);
        expectedOutput += "There are no resources found matching the given filters." + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();;
        output = outputStream.toString();
        assertEquals(expectedOutput, output);
        //Test delete
        validResponse = "delete /id 1";
        parser.process(validResponse);
        output = outputStream.toString();
        expectedOutput += "Looking for ID: 1..." + System.lineSeparator() +
                "This resource is removed: " + System.lineSeparator() +
                "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 " +
                "Author: Thomas Erikson Genre: Self-help Status: AVAILABLE" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testParseAddCommand() throws SysLibException {
        // Test case 1: Valid input with /tag b
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

        // Test case 2: Invalid input (missing /tag b)
        String statement2 = "add /id 123456789 /t Moby Dick /a Herman Melville /tag C /i 9780763630188 " +
                "/g Adventure, Fiction /s Borrowed";
        assertThrows(SysLibException.class, () -> Parser.parseAddCommand(statement2));
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

    @Test
    public void testCreateBook() {
        String[] args = {"123", "CS2113T", "W11", "B", "1234", "Horror", "lost"};

        Book book = null;
        book = Parser.createBook(args);

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

        assertThrows(NumberFormatException.class, () -> Parser.createBook(args));
    }
}
