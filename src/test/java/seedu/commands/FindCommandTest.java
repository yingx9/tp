package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seedu.data.resources.Book;
import seedu.data.resources.Magazine;
import seedu.data.resources.Newspaper;
import seedu.data.resources.CD;
import seedu.data.Status;
import seedu.exception.SysLibException;
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

        parser.resourceList = new ArrayList<>();
        parser.resourceList.add(new Book("Title1", "ISBN1", "Author1", new String[]{"horror"}, 1234, Status.AVAILABLE));
        parser.resourceList.add(new Magazine("Title2", "ISBN2", "VOGUE2", "1234", 5678, Status.AVAILABLE));
        parser.resourceList.add(new Newspaper("Title3", "ISBN3", "Publisher3", "1234", 9101, Status.AVAILABLE));
        parser.resourceList.add(new CD("Title4", "Creator4", "Creator4", "1234", 1121, Status.AVAILABLE));

        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
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
        findCommand.execute("/t NonexistentTitle", parser);
        assertTrue(outContent.toString().contains("There are no resources found matching the given filters."));
    }

    @Test
    void testExecuteFindMagazineBrandMatch() throws SysLibException {
        findCommand.execute("/a VOGUE2", parser);
        assertTrue(outContent.toString().contains("VOGUE2"));
    }

    @Test
    void testExecuteFindNewspaperPublisherMatch() throws SysLibException {
        findCommand.execute("/a Publisher3", parser);
        assertTrue(outContent.toString().contains("Publisher3"));
    }

    @Test
    void testExecuteFindCDMatch() throws SysLibException {
        findCommand.execute("/a Creator4", parser);
        assertTrue(outContent.toString().contains("Creator4"));
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
