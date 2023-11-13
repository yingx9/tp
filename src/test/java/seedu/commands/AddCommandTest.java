package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.data.resources.Book;
import seedu.data.resources.EBook;
import seedu.data.resources.CD;
import seedu.data.resources.Magazine;
import seedu.data.resources.EMagazine;
import seedu.data.resources.Newspaper;
import seedu.data.resources.ENewspaper;
import seedu.data.Status;
import seedu.exception.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;
import static seedu.util.TestUtil.getCurrentDate;

public class AddCommandTest {
    private final Parser parser = new Parser();
    private final AddCommand addCommand = new AddCommand();

    // ----------------------------------------------------------------------------------------------------
    // Test Resources with Valid Data
    // ----------------------------------------------------------------------------------------------------
    @Test
    public void addCommandValidBook() throws SysLibException {
        addCommand.execute("/i 9783161484100 /t The Minds of Billy Milligan /a Daniel Keyes /tag B " +
                "/g Non-Fiction, Biography /s LOST", parser.container);

        Book newBook = (Book) parser.container.getResourcesList().get(0);

        assertEquals(newBook.getId(), 1);
        assertEquals(newBook.getTitle(), "The Minds of Billy Milligan");
        assertEquals(newBook.getAuthor(), "Daniel Keyes");
        assertEquals(newBook.getTag(), "B");
        assertEquals(newBook.getISBN(), "9783161484100");
        assertEquals(newBook.getGenreString(), "Non-Fiction, Biography");
        assertEquals(newBook.getStatus(), Status.LOST);
    }

    @Test
    public void addCommandValidEBook() throws SysLibException {
        addCommand.execute("/i 9783161484100 /t The Minds of Billy Milligan /a Daniel Keyes /tag EB " +
                "/g Non-Fiction, Biography /s LOST /l www.daniel.com/tmobm", parser.container);

        EBook newEBook = (EBook) parser.container.getResourcesList().get(0);

        assertEquals(newEBook.getId(), 1);
        assertEquals(newEBook.getTitle(), "The Minds of Billy Milligan");
        assertEquals(newEBook.getAuthor(), "Daniel Keyes");
        assertEquals(newEBook.getTag(), "EB");
        assertEquals(newEBook.getISBN(), "9783161484100");
        assertEquals(newEBook.getGenreString(), "Non-Fiction, Biography");
        assertEquals(newEBook.getStatus(), Status.LOST);
        assertEquals(newEBook.getLink(), "www.daniel.com/tmobm");
    }

    @Test
    public void addCommandValidCD() throws SysLibException {
        addCommand.execute("/i 9783161484100 /t The Minds of Billy Milligan /c Daniel Keyes /tag CD " +
                "/ty Audio Book /s LOST", parser.container);

        CD newCD = (CD) parser.container.getResourcesList().get(0);

        assertEquals(newCD.getId(), 1);
        assertEquals(newCD.getTitle(), "The Minds of Billy Milligan");
        assertEquals(newCD.getCreator(), "Daniel Keyes");
        assertEquals(newCD.getTag(), "CD");
        assertEquals(newCD.getISBN(), "9783161484100");
        assertEquals(newCD.getType(), "Audio Book");
        assertEquals(newCD.getStatus(), Status.LOST);
    }

    @Test
    public void addCommandValidMagazine() throws SysLibException {
        addCommand.execute("/i 9783161484101 /t Exploring the Outer Space /b Astronomy 101 /tag M " +
                "/is Planets Edition, January 2023 /s LOST", parser.container);

        Magazine newMagazine = (Magazine) parser.container.getResourcesList().get(0);

        assertEquals(newMagazine.getId(), 1);
        assertEquals(newMagazine.getTitle(), "Exploring the Outer Space");
        assertEquals(newMagazine.getBrand(), "Astronomy 101");
        assertEquals(newMagazine.getTag(), "M");
        assertEquals(newMagazine.getISBN(), "9783161484101");
        assertEquals(newMagazine.getIssue(), "Planets Edition, January 2023");
        assertEquals(newMagazine.getStatus(), Status.LOST);
    }

    @Test
    public void addCommandValidEMagazine() throws SysLibException {
        addCommand.execute("/i 9783161484101 /t Exploring the Outer Space /b Astronomy 101 /tag EM " +
                "/is Planets Edition, January 2023 /s LOST /l www.astronomy.com", parser.container);

        EMagazine newEMagazine = (EMagazine) parser.container.getResourcesList().get(0);

        assertEquals(newEMagazine.getId(), 1);
        assertEquals(newEMagazine.getTitle(), "Exploring the Outer Space");
        assertEquals(newEMagazine.getBrand(), "Astronomy 101");
        assertEquals(newEMagazine.getTag(), "EM");
        assertEquals(newEMagazine.getISBN(), "9783161484101");
        assertEquals(newEMagazine.getIssue(), "Planets Edition, January 2023");
        assertEquals(newEMagazine.getStatus(), Status.LOST);
        assertEquals(newEMagazine.getLink(), "www.astronomy.com");
    }

    @Test
    public void addCommandValidNewspaper() throws SysLibException {
        addCommand.execute("/i 9783161484102 /t Freshest Lemon in Town /p Healthy Food /tag N " +
                "/ed Fruity Fruits, 25 January 2023 /s LOST", parser.container);

        Newspaper newNewspaper = (Newspaper) parser.container.getResourcesList().get(0);

        assertEquals(newNewspaper.getId(), 1);
        assertEquals(newNewspaper.getTitle(), "Freshest Lemon in Town");
        assertEquals(newNewspaper.getPublisher(), "Healthy Food");
        assertEquals(newNewspaper.getTag(), "N");
        assertEquals(newNewspaper.getISBN(), "9783161484102");
        assertEquals(newNewspaper.getEdition(), "Fruity Fruits, 25 January 2023");
        assertEquals(newNewspaper.getStatus(), Status.LOST);
    }

    @Test
    public void addCommandValidENewspaper() throws SysLibException {
        addCommand.execute("/i 9783161484102 /t Freshest Lemon in Town /p Healthy Food /tag EN " +
                "/ed Fruity Fruits, 25 January 2023 /s LOST /l https://www.lemon.com", parser.container);

        ENewspaper newENewspaper = (ENewspaper) parser.container.getResourcesList().get(0);

        assertEquals(newENewspaper.getId(), 1);
        assertEquals(newENewspaper.getTitle(), "Freshest Lemon in Town");
        assertEquals(newENewspaper.getPublisher(), "Healthy Food");
        assertEquals(newENewspaper.getTag(), "EN");
        assertEquals(newENewspaper.getISBN(), "9783161484102");
        assertEquals(newENewspaper.getEdition(), "Fruity Fruits, 25 January 2023");
        assertEquals(newENewspaper.getStatus(), Status.LOST);
        assertEquals(newENewspaper.getLink(), "https://www.lemon.com");
    }


    // ----------------------------------------------------------------------------------------------------
    // Test Output
    // ----------------------------------------------------------------------------------------------------
    @Test
    public void addCommandOutput() throws SysLibException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        addCommand.execute("/i 9783161484100 /t The Minds of Billy Milligan /a Daniel Keyes /tag B " +
                "/g Non-Fiction, Biography", parser.container);

        String output = outputStream.toString();

        String expectedOutput = "Attention: Status is not stated. Status set to default: AVAILABLE." +
                System.lineSeparator() + "This book is added:" + System.lineSeparator() +
                "[B]  ID: 1 Title: The Minds of Billy Milligan ISBN: 9783161484100 Author: Daniel Keyes Genre: " +
                "Non-Fiction, Biography Status: AVAILABLE Received Date: " + getCurrentDate() +
                SEPARATOR_LINEDIVIDER + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }


    // ----------------------------------------------------------------------------------------------------
    // Test Attributes with Invalid Input
    // ----------------------------------------------------------------------------------------------------
    @Test
    public void addCommandInvalidIsbn() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i TMOBM " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag B", parser.container));

    }

    @Test
    public void addCommandInvalidTag() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag ABC", parser.container));

    }

    @Test
    public void addCommandInvalidGenre() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag B /g [Biography]", parser.container));

    }

    @Test
    public void addCommandInsufficientData() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/tag ", parser.container));
    }

    @Test
    public void addCommandInvalidFormat() {
        assertThrows(SysLibException.class, ()->addCommand.execute("BOOK /i 9783161484100 " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag B", parser.container));
    }


    // ----------------------------------------------------------------------------------------------------
    // Test Resource Attributes Validation
    // ----------------------------------------------------------------------------------------------------
    @Test
    public void addCommandDuplicateIsbn() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 /i 9783161484102 " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag B", parser.container));
    }

    @Test
    public void addCommandWrongFormatIsbn() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i   " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag B", parser.container));
    }

    @Test
    public void addCommandDuplicateTitle() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds /t of Billy Milligan /a Daniel Keyes /tag B", parser.container));
    }

    @Test
    public void addCommandWrongFormatTitle() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t   /a Daniel Keyes /tag B", parser.container));
    }

    @Test
    public void addCommandDuplicateAuthor() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a Daniel /a Keyes /tag B", parser.container));
    }

    @Test
    public void addCommandWrongFormatAuthor() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a   /tag B", parser.container));
    }

    @Test
    public void addCommandDuplicateTag() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag B /tag EB", parser.container));
    }

    @Test
    public void addCommandWrongFormatTag() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag     ", parser.container));
    }

    @Test
    public void addCommandDuplicateGenre() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag B /g Non-Fiction /g Biography", parser.container));
    }

    @Test
    public void addCommandWrongFormatGenre() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag B /g   ", parser.container));
    }

    @Test
    public void addCommandDuplicateCreator() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /c Daniel /c Keyes /tag CD /ty Audio Book", parser.container));
    }

    @Test
    public void addCommandDuplicateType() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /c Daniel Keyes /tag CD /ty Audio /ty Book", parser.container));
    }

    @Test
    public void addCommandDuplicateBrand() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484101 /t Exploring the " +
                "Outer Space /b Astronomy /b 101 /tag M /is Planets Edition, January 2023 /s LOST", parser.container));
    }

    @Test
    public void addCommandDuplicateIssue() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484101 /t Exploring the " +
                "Outer Space /b Astronomy 101 /tag M /is Planets Edition, /is January 2023 /s LOST", parser.container));
    }

    @Test
    public void addCommandDuplicatePublisher() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484102 /t Freshest Lemon " +
                "in Town /p Healthy /p Food /tag N /ed Fruity Fruits, 25 January 2023 /s LOST", parser.container));
    }

    @Test
    public void addCommandDuplicateEdition() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484102 /t Freshest Lemon " +
                "in Town /p Healthy Food /tag N /ed Fruity Fruits, /ed 25 January 2023 /s LOST", parser.container));
    }

    @Test
    public void addCommandDuplicateLink() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484102 /t Freshest Lemon " +
                "in Town /p Healthy Food /tag EN /ed Fruity Fruits, 25 January 2023 /s LOST /l www.lemon.com " +
                "/l www.watermelon.com", parser.container));
    }

    @Test
    public void addCommandDuplicateStatus() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484102 /t Freshest Lemon " +
                "in Town /p Healthy Food /tag N /ed Fruity Fruits, 25 January 2023 /s LOST /s BORROWED",
                parser.container));
    }

    @Test
    public void addCommandWrongFormatStatus() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484102 /t Freshest Lemon " +
                "in Town /p Healthy Food /tag N /ed Fruity Fruits, 25 January 2023 /s   ", parser.container));
    }

    @Test
    public void addCommandWrongInvalidStatus() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484102 /t Freshest Lemon " +
                "in Town /p Healthy Food /tag N /ed Fruity Fruits, 25 January 2023 /s DAMAGED ", parser.container));
    }


    // ----------------------------------------------------------------------------------------------------
    // Test Resources with Invalid Input
    // ----------------------------------------------------------------------------------------------------
    @Test
    public void addCommandBookUnusedSlash() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan / / /a Daniel Keyes /tag B", parser.container));
    }

    @Test
    public void addCommandBookInvalidArgument() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /c Dan /tag B", parser.container));
    }

    @Test
    public void addCommandBookNoData() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a /tag B", parser.container));
    }

    @Test
    public void addCommandEBookUnusedSlash() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag EB /l www.daniel.com/tmobm/", parser.container));
    }

    @Test
    public void addCommandEBookInvalidArgument() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /c Dan /tag EB /l www.daniel.com/tmobm/",
                parser.container));
    }

    @Test
    public void addCommandEBookNoData() {
        assertThrows(SysLibException.class, ()->addCommand.execute("/i 9783161484100 " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag EB /l ", parser.container));
    }
}
