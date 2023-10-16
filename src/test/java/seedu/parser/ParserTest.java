package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.data.Book;
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

        String expectedOutput = "Bye, hope to see you again soon!"+ System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        //assertEquals(expectedOutput, output);
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

        String expectedOutput = "Commands available:" + System.lineSeparator()+
                "add: add an item (e.g. add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE])" +
                System.lineSeparator()+
                "delete: delete an item (e.g. delete /id 123456789)" + System.lineSeparator()+
                "list: (e.g. list /tag B /g Fiction /a J. K. Rowling /i 9780763630188)" + System.lineSeparator() +
                "find: (e.g. find /i 9780763630188)" + System.lineSeparator() +
                "exit: (e.g. exit)" + System.lineSeparator() +
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

        String expectedOutput = "no commands found. Enter \"help\" for a list of commands." + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

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
        String expectedOutput = "This book is added: Surrounded by Idiots" + System.lineSeparator();
        assertEquals(expectedOutput, output);
        //Add second book
        validResponse = "add /id 2 /t The Subtle Art of Not Giving a F*ck /a Mark Manson " +
                "/tag B /i 9780062457714 /g Self-help";
        parser.process(validResponse);
        expectedOutput += "This book is added: The Subtle Art of Not Giving a F*ck" + System.lineSeparator();
        //Test list
        validResponse = "list";
        parser.process(validResponse);
        expectedOutput += "Listing all resources in the Library: " + System.lineSeparator() + System.lineSeparator() +
                "1. [B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 " +
                "Author: Thomas Erikson Genre: Self-help" + System.lineSeparator() + System.lineSeparator()+
                "2. [B]  ID: 2 Title: The Subtle Art of Not Giving a F*ck ISBN: 9780062457714 " +
                "Author: Mark Manson Genre: Self-help" + System.lineSeparator() + System.lineSeparator() +
                "There are currently 2 resource(s)." + System.lineSeparator() + System.lineSeparator();
        System.setOut(System.out);
        output = outputStream.toString();
        //assertEquals(expectedOutput, output);
        //Test find
        validResponse = "find /t The Subtle Art of Not Giving a F*ck";
        parser.process(validResponse);
        expectedOutput += "Here are resources that matched the given filters: " + System.lineSeparator() +
                "[B]  ID: 2 Title: The Subtle Art of Not Giving a F*ck ISBN: 9780062457714 " +
                "Author: Mark Manson Genre: Self-help" + System.lineSeparator() + System.lineSeparator();
        output = outputStream.toString();
        assertEquals(expectedOutput, output);
        //Test delete
        validResponse = "delete /id 1";
        parser.process(validResponse);
        output = outputStream.toString();
        expectedOutput += "Looking for ID: 1..." + System.lineSeparator() +
                "This resource is removed: " + System.lineSeparator() +
                "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 " +
                "Author: Thomas Erikson Genre: Self-help" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testParseAddCommand() throws SysLibException {
        // Test case 1: Valid input with /tag b
        String statement1 = "add /id ID /t TITLE /a AUTHOR /tag b /i ISBN /g GENRE";
        String[] expectedArgs1 = {"ID", "TITLE", "AUTHOR", "b", "ISBN", "GENRE"};
        assertArrayEquals(expectedArgs1, Parser.parseAddCommand(statement1));

        // Test case 2: Invalid input (missing /tag b)
        String statement2 = "add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN /g GENRE";
        assertThrows(SysLibException.class, () -> Parser.parseAddCommand(statement2));
    }

    @Test
    public void testParseAddBook() throws SysLibException {
        // Test case 1: Valid input with /g GENRE
        String statement1 = "/id ID /t TITLE /a AUTHOR /tag b /i ISBN /g GENRE";
        String[] expectedArgs1 = {"ID", "TITLE", "AUTHOR", "b", "ISBN", "GENRE"};
        assertArrayEquals(expectedArgs1, Parser.parseAddBook(statement1));

        // Test case 2: Valid input without /g GENRE
        String statement2 = "/id ID /t TITLE /a AUTHOR /tag b /i ISBN";
        String[] expectedArgs2 = {"ID", "TITLE", "AUTHOR", "b", "ISBN", null};
        assertArrayEquals(expectedArgs2, Parser.parseAddBook(statement2));
    }

    @Test
    public void testCreateBook() {
        String[] args = {"123", "CS2113T", "W11", "B", "1234", "Horror"};

        Book book = null;
        book = Parser.createBook(args);

        int expectedId = 123;
        String expectedTitle = "CS2113T";
        String expectedAuthor = "W11";
        String expectedIsbn = "1234";
        String[] expectedGenres = { "Horror" };

        assertEquals(expectedId, book.getId());
        assertEquals(expectedTitle, book.getTitle());
        assertEquals(expectedAuthor, book.getAuthor());
        assertEquals(expectedIsbn, book.getISBN());
        assertArrayEquals(expectedGenres, book.getGenre());
    }
    @Test
    public void testCreateBookInvalidId() {
        String[] args = {"not_a_number", "Sample Title", "Sample Author", "", "Sample ISBN", "Sample Genre"};

        assertThrows(NumberFormatException.class, () -> Parser.createBook(args));
    }
}
