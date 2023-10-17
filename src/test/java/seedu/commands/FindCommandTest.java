package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seedu.data.Book;
import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class FindCommandTest {

    private FindCommand findCommand;
    private Parser parser;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        findCommand = new FindCommand();
        parser = new Parser();


        // Mock resourceList for Parser
        parser.resourceList = new ArrayList<>();
        String[] genreTest1 = {"horror"};
        String[] genreTest2 = {"comedy"};
        parser.resourceList.add(new Book("Title1", "ISBN1", "Author1", genreTest1, 1234));
        parser.resourceList.add(new Book("Title2", "ISBN2", "Author2", genreTest2, 5678));
        outContent.reset();  // Clearing any old content
        System.setOut(new PrintStream(outContent));  // Redirect System.out
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);  // Reset System.out after each test
    }

    @Test
    void testSetAndGetTitle() {
        findCommand.setTitle("TitleTest");
        assertEquals("TitleTest", findCommand.getTitle());
    }

    @Test
    void testSetAndGetAuthor() {
        findCommand.setAuthor("AuthorTest");
        assertEquals("AuthorTest", findCommand.getAuthor());
    }

    @Test
    void testSetAndGetISBN() {
        findCommand.setISBN("ISBNTest");
        assertEquals("ISBNTest", findCommand.getISBN());
    }

    @Test
    void testSetAndGetID() {
        findCommand.setID("IDTest");
        assertEquals("IDTest", findCommand.getID());
    }

    @Test
    void testExecuteWithValidTitle() throws SysLibException {
        findCommand.execute("/t Title1", parser);
        assertEquals("Title1", findCommand.getTitle());
    }
    @Test
    void testExecuteWithValidAuthor() throws SysLibException {
        findCommand.execute("/a Author1", parser);
        assertEquals("Author1", findCommand.getAuthor());
    }

    @Test
    void testExecuteWithInvalidFlag() {
        assertThrows(IllegalArgumentException.class, () -> findCommand.execute("/x InvalidFlag", parser));
    }

    @Test
    void testExecuteWithNoFilter() {
        assertThrows(IllegalArgumentException.class, () -> findCommand.execute("", parser));
    }

    @Test
    void testExecuteFindTitleMatch() throws SysLibException {
        findCommand.execute("/t Title1", parser);
        assertTrue(outContent.toString().contains("Title1"));
    }

    @Test
    void testExecuteFindAuthorMatch() throws SysLibException {
        findCommand.execute("/a Author1", parser);
        assertTrue(outContent.toString().contains("Author1"));
    }

    @Test
    void testExecuteFindISBNMatch() throws SysLibException {
        findCommand.execute("/i ISBN1", parser);
        assertTrue(outContent.toString().contains("ISBN1"));
    }

    @Test
    void testExecuteNoMatchesFound() throws SysLibException {
        findCommand.execute("/t Title3", parser);
        assertTrue(outContent.toString().contains("There are no resources found matching the given filters."));
    }

    @Test
    void testExecuteMultipleFilters() throws SysLibException {
        findCommand.execute("/t Title1 /a Author1", parser);
        assertTrue(outContent.toString().contains("Title1"));
        assertTrue(outContent.toString().contains("Author1"));
    }

    @Test
    void testExecuteInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> findCommand.execute("find /z Invalid", parser));
    }
}
