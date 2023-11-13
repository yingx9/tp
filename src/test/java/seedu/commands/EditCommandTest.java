package seedu.commands;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.data.resources.Book;
import seedu.data.resources.CD;
import seedu.data.resources.EBook;
import seedu.data.resources.EMagazine;
import seedu.data.resources.ENewspaper;
import seedu.data.resources.Magazine;
import seedu.data.resources.Newspaper;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import seedu.parser.Parser;
import seedu.util.TestUtil;

import static seedu.ui.EditCommandMessages.BOOK_ARGS_MESSAGE;
import static seedu.ui.EditCommandMessages.INVALID_EDIT_ARGS;
import static seedu.ui.EditCommandMessages.MAGAZINE_ARGS_MESSAGE;
import static seedu.ui.EditCommandMessages.MISSING_ARG_MESSAGE;
import static seedu.ui.EditCommandMessages.NEWSPAPERS_ARGS_MESSAGE;
import static seedu.ui.EditCommandMessages.RESOURCE_NOT_FOUND;
import static seedu.ui.EditCommandMessages.EDIT_SUCCESS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.ui.ListCommandMessages.STATUS_ERROR_MESSAGE;
import static seedu.ui.MessageFormatter.formatLastLineDivider;

public class EditCommandTest {
    private static List<Resource> testResourcesList = new ArrayList<>();
    private static Parser parser = new Parser();
    private List<Resource> emptyResourceList = new ArrayList<>();
    private TestUtil testUtil = new TestUtil();
    private Command editCommand = new EditCommand();

    @BeforeAll
    public static void setup()  {
        testResourcesList = TestUtil.fillTestList();
        parser.container.setResourcesList(testResourcesList);

    }

    @Test
    public void testEditTitleBehavior() throws SysLibException {
        Resource targetResource = testResourcesList.get(0);
        String expectedMessage = targetResource.toString();
        expectedMessage = expectedMessage.replace(targetResource.getTitle(), "NEW TITLE");
        executeEditSuccessBehavior("/id 2 /t NEW TITLE", expectedMessage );
    }

    @Test
    public void testEditStatusBehavior() throws SysLibException {
        Resource targetResource = testResourcesList.get(0);
        String expectedMessage = targetResource.toString();
        expectedMessage = expectedMessage.replace(targetResource.getStatus().name(), "LOST");
        executeEditSuccessBehavior("/id 2 /s LOST", expectedMessage );
        expectedMessage = expectedMessage.replace(targetResource.getStatus().name(), "AVAILABLE");
        executeEditSuccessBehavior("/id 2 /s AVAILABLE", expectedMessage );
        expectedMessage = expectedMessage.replace(targetResource.getStatus().name(), "BORROWED");
        executeEditSuccessBehavior("/id 2 /s BORROWED", expectedMessage );
    }

    @Test
    public void testEditISBNBehavior() throws SysLibException {
        Resource targetResource = testResourcesList.get(0);
        String expectedMessage = targetResource.toString();
        expectedMessage = expectedMessage.replace(targetResource.getISBN(), "1234567891234");
        executeEditSuccessBehavior("/id 2 /i 1234567891234", expectedMessage );
    }

    @Test
    public void testEditBookAuthorBehavior() throws SysLibException {
        Book targetBook = (Book) testResourcesList.get(0);
        String expectedMessage = targetBook.toString();
        expectedMessage = expectedMessage.replace(targetBook.getAuthor(), "NEW AUTHOR");
        executeEditSuccessBehavior("/id 2 /a NEW AUTHOR", expectedMessage);
    }

    @Test
    public void testEditBookGenreBehavior() throws SysLibException {
        Book targetBook = (Book) testResourcesList.get(0);
        String expectedMessage = targetBook.toString();
        expectedMessage = expectedMessage.replace(targetBook.getGenreString(), "Horror, Action, Fantasy");
        executeEditSuccessBehavior("/id 2 /g Horror, Action, Fantasy",expectedMessage);
    }

    @Test
    public void testEditMagazineBrandBehavior() throws SysLibException {
        Magazine targetMagazine = (Magazine) testResourcesList.get(4);
        String expectedMessage = targetMagazine.toString();
        expectedMessage = expectedMessage.replace(targetMagazine.getBrand(), "NEW BRAND");
        executeEditSuccessBehavior("/id 6 /b NEW BRAND",expectedMessage);
    }

    @Test
    public void testEditMagazineIssueBehavior() throws SysLibException {
        Magazine targetMagazine = (Magazine) testResourcesList.get(4);
        String expectedMessage = targetMagazine.toString();
        expectedMessage = expectedMessage.replace(targetMagazine.getIssue(), "NEW ISSUE");
        executeEditSuccessBehavior("/id 6 /is NEW ISSUE",expectedMessage);
    }


    @Test
    public void testEditCDCreatorBehavior() throws SysLibException {
        CD targetCD = (CD) testResourcesList.get(6);
        String expectedMessage = targetCD.toString();
        expectedMessage = expectedMessage.replace(targetCD.getCreator(), "NEW CREATOR");
        executeEditSuccessBehavior("/id 8 /c NEW CREATOR",expectedMessage);
    }

    @Test
    public void testEditCDTypeBehavior() throws SysLibException {
        CD targetCD = (CD) testResourcesList.get(6);
        String expectedMessage = targetCD.toString();
        expectedMessage = expectedMessage.replace(targetCD.getType(), "NEW TYPE");
        executeEditSuccessBehavior("/id 8 /ty NEW TYPE",expectedMessage);
    }

    @Test
    public void testEditNewspaperPublisherBehavior() throws SysLibException {
        Newspaper targetNewspaper = (Newspaper) testResourcesList.get(7);
        String expectedMessage = targetNewspaper.toString();
        expectedMessage = expectedMessage.replace(targetNewspaper.getPublisher(), "NEW PUBLISHER");
        executeEditSuccessBehavior("/id 9 /p NEW PUBLISHER",expectedMessage);
    }

    @Test
    public void testEditNewspaperEditionBehavior() throws SysLibException {
        Newspaper targetNewspaper = (Newspaper) testResourcesList.get(7);
        String expectedMessage = targetNewspaper.toString();
        expectedMessage = expectedMessage.replace(targetNewspaper.getEdition(), "NEW EDITION");
        executeEditSuccessBehavior("/id 9 /ed NEW EDITION",expectedMessage);
    }
    @Test
    public void testEditEBookLinkBehavior() throws SysLibException {
        EBook targetEBook = (EBook) testResourcesList.get(3);
        String expectedMessage = targetEBook.toString();
        expectedMessage = expectedMessage.replace(targetEBook.getLink(), "NEW LINK");
        executeEditSuccessBehavior("/id 5 /l NEW LINK",expectedMessage);
    }
    @Test
    public void testEditEMagazineLinkBehavior() throws SysLibException {
        EMagazine targetEMagazine = (EMagazine) testResourcesList.get(5);
        String expectedMessage = targetEMagazine.toString();
        expectedMessage = expectedMessage.replace(targetEMagazine.getLink(), "NEW LINK");
        executeEditSuccessBehavior("/id 7 /l NEW LINK",expectedMessage);
    }

    @Test
    public void testEditENewspaperLinkBehavior() throws SysLibException {
        ENewspaper targetENewspaper = (ENewspaper) testResourcesList.get(8);
        String expectedMessage = targetENewspaper.toString();
        expectedMessage = expectedMessage.replace(targetENewspaper.getLink(), "NEW LINK");
        executeEditSuccessBehavior("/id 10 /l NEW LINK",expectedMessage);
    }

    private void executeEditSuccessBehavior(String argument, String expectedMessage) throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(editCommand, argument, testResourcesList);
        expectedMessage = EDIT_SUCCESS + formatLastLineDivider(expectedMessage);
        assertEquals(expectedMessage, outputMessage);
    }


    @Test
    public void testEditResourceNotFound() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(editCommand, "/id 123 /t NEWTITLE", emptyResourceList);
        String expectedMessage =  RESOURCE_NOT_FOUND;
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    public void testNoArgumentGiven()  {
        executeAssertSysLibExceptionThrown("/id 123", MISSING_ARG_MESSAGE);

    }

    @Test
    public void testNotCorrectResourceTypeError() {
        List<Resource> dummyList = testUtil.addDummyResource(testResourcesList);
        parser.container.setResourcesList(dummyList);
        executeAssertSysLibExceptionThrown("/id 1 /t dummyTitle", "Invalid Resource!");

    }

    @Test
    public void testEditStatusError(){
        executeAssertSysLibExceptionThrown("/id 2 /s INVALIDSTATUS",
                STATUS_ERROR_MESSAGE);
    }

    @Test
    public void testEditISBNError(){
        executeAssertSysLibExceptionThrown("/id 2 /i invalid",
                "ISBN must be 13 characters!");
    }

    @Test
    public void testEditBookLinkError(){
        executeAssertSysLibExceptionThrown("/id 2 /l dummyLink",
                INVALID_EDIT_ARGS + BOOK_ARGS_MESSAGE);
    }

    @Test
    public void testEditNewspaperLinkError(){
        executeAssertSysLibExceptionThrown("/id 9 /l dummyLink",
                INVALID_EDIT_ARGS + NEWSPAPERS_ARGS_MESSAGE);
    }

    @Test
    public void testEditMagazineLinkError(){
        executeAssertSysLibExceptionThrown("/id 6 /l dummyLink",
                INVALID_EDIT_ARGS + MAGAZINE_ARGS_MESSAGE);
    }

    @Test
    public void testEditBookInvalidArgsGiven(){
        executeAssertSysLibExceptionThrown("/id 2 /t TITLE /s LOST /p PUBLISHER /g GENRES /ed EDITION " +
                "/c CREATOR /ty TYPE /b BRAND /is ISSUE", INVALID_EDIT_ARGS+BOOK_ARGS_MESSAGE);
    }

    private void executeAssertSysLibExceptionThrown(String arguments, String expectedMessage){
        SysLibException exception = assertThrows(SysLibException.class, ()->editCommand.execute(
                arguments, parser.container));
        assertEquals(expectedMessage, exception.getMessage());
    }

}
