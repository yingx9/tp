package seedu.commands;

import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Book;
import seedu.data.resources.CD;
import seedu.data.resources.EBook;
import seedu.data.resources.Resource;
import seedu.data.resources.Magazine;
import seedu.data.resources.EMagazine;
import seedu.data.resources.Newspaper;
import seedu.data.resources.ENewspaper;
import seedu.exception.SysLibException;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.data.CreateResource.createBook;
import static seedu.data.CreateResource.createEBook;
import static seedu.data.CreateResource.createCD;
import static seedu.data.CreateResource.createMagazine;
import static seedu.data.CreateResource.createEMagazine;
import static seedu.data.CreateResource.createNewspaper;
import static seedu.data.CreateResource.createENewspaper;
import static seedu.data.resources.Book.BOOK_TAG;
import static seedu.data.resources.CD.CD_TAG;
import static seedu.data.resources.EBook.EBOOK_TAG;
import static seedu.data.resources.EMagazine.EMAGAZINE_TAG;
import static seedu.data.resources.ENewspaper.ENEWSPAPER_TAG;
import static seedu.data.resources.Magazine.MAGAZINE_TAG;
import static seedu.data.resources.Newspaper.NEWSPAPER_TAG;
import static seedu.parser.resources.ParseBook.parseAddBook;
import static seedu.parser.resources.ParseBook.resetBookArgs;
import static seedu.parser.Parser.parseAddCommand;
import static seedu.parser.resources.ParseCD.parseAddCD;
import static seedu.parser.resources.ParseCD.resetCDArgs;
import static seedu.parser.resources.ParseEBook.parseAddEBook;
import static seedu.parser.resources.ParseEBook.resetEBookArgs;
import static seedu.parser.resources.ParseEMagazine.parseAddEMagazine;
import static seedu.parser.resources.ParseEMagazine.resetEMagazineArgs;
import static seedu.parser.resources.ParseENewspaper.parseAddENewspaper;
import static seedu.parser.resources.ParseENewspaper.resetENewspaperArgs;
import static seedu.parser.resources.ParseMagazine.parseAddMagazine;
import static seedu.parser.resources.ParseMagazine.resetMagazineArgs;
import static seedu.parser.resources.ParseNewspaper.parseAddNewspaper;
import static seedu.parser.resources.ParseNewspaper.resetNewspaperArgs;
import static seedu.ui.Messages.ASSERT_STATEMENT;
import static seedu.ui.Messages.ASSERT_CONTAINER;
import static seedu.ui.Messages.ERROR_TAG;
import static seedu.ui.UI.LINEDIVIDER;

public class AddCommand extends Command{
    private static final String TITLE_OPTION = "t";
    private static final String AUTHOR_OPTION = "a";
    private static final String TAG_OPTION = "tag";
    private static final String ISBN_OPTION = "i";
    private static final String GENRE_OPTION = "g";
    private static final String STATUS_OPTION = "s";
    private static final String LINK_OPTION = "l";
    private static final String CREATOR_OPTION = "c";
    private static final String BRAND_OPTION = "b";
    private static final String PUBLISHER_OPTION = "p";
    private static final String TYPE_OPTION = "ty";
    private static final String ISSUE_OPTION = "is";
    private static final String EDITION_OPTION = "ed";
    private static final int RESOURCEID_INCREMENT = 1;
    private static final Logger ADDLOGGER = Logger.getLogger(AddCommand.class.getName());
    private static String feedbackToUser;
    private int resourceID;

    static {
        try {
            String logDir = System.getProperty("user.dir") + "/logs";
            String logFile = logDir + "/addCommandLogs.log";
            File directory = new File(logDir);
            if (!directory.exists()) {
                directory.mkdir();
            }

            FileHandler fileHandler = new FileHandler(logFile, true);
            fileHandler.setFormatter(new SimpleFormatter());
            ADDLOGGER.addHandler(fileHandler);
            ADDLOGGER.setLevel(Level.INFO);
        } catch (IOException e) {
            ADDLOGGER.log(Level.SEVERE, "Error occured while initializing log.");
            throw new RuntimeException(e);
        }
    }

    public AddCommand(){
        args = new String[] {TAG_OPTION, ISBN_OPTION, TITLE_OPTION, AUTHOR_OPTION, GENRE_OPTION,
            CREATOR_OPTION, BRAND_OPTION, PUBLISHER_OPTION, TYPE_OPTION, ISSUE_OPTION, EDITION_OPTION, LINK_OPTION,
            STATUS_OPTION};
        required = new boolean[]{true, true, true, false, false, false, false, false, false, false, false, false,
            false};
    }

    private void addBook(String statement, GenericList<Resource, Event> container) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;
        assert container != null : ASSERT_CONTAINER;

        String[] values = parseAddBook(statement);
        Book newBook = createBook(values, resourceID);
        container.getResourceList().add(newBook);
        System.out.println("This book is added:"  + System.lineSeparator() + newBook.toString());
        resetBookArgs();
        ADDLOGGER.log(Level.INFO, "Added Book: " + newBook.toString());
    }

    private void addEBook(String statement, GenericList<Resource, Event> container) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;
        assert container != null : ASSERT_CONTAINER;

        String[] values = parseAddEBook(statement);
        EBook newEBook = createEBook(values, resourceID);
        container.getResourceList().add(newEBook);
        System.out.println("This e-book is added:" + System.lineSeparator() + newEBook.toString());
        resetEBookArgs();
        ADDLOGGER.log(Level.INFO, "Added E-Book: " + newEBook.toString());
    }

    private void addCD(String statement, GenericList<Resource, Event> container) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;
        assert container != null : ASSERT_CONTAINER;

        String[] values = parseAddCD(statement);
        CD newCD = createCD(values, resourceID);
        container.getResourceList().add(newCD);
        System.out.println("This CD is added:" + System.lineSeparator() + newCD.toString());
        resetCDArgs();
        ADDLOGGER.log(Level.INFO, "Added CD: " + newCD.toString());
    }

    private void addMagazine(String statement, GenericList<Resource, Event> container) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;
        assert container != null : ASSERT_CONTAINER;

        String[] values = parseAddMagazine(statement);
        Magazine newMagazine = createMagazine(values, resourceID);
        container.getResourceList().add(newMagazine);
        System.out.println("This magazine is added:" + System.lineSeparator() + newMagazine.toString());
        resetMagazineArgs();
        ADDLOGGER.log(Level.INFO, "Added Magazine: " + newMagazine.toString());
    }

    private void addEMagazine(String statement, GenericList<Resource, Event> container) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;
        assert container != null : ASSERT_CONTAINER;

        String[] values = parseAddEMagazine(statement);
        EMagazine newEMagazine = createEMagazine(values, resourceID);
        container.getResourceList().add(newEMagazine);
        System.out.println("This e-magazine is added:" + System.lineSeparator() + newEMagazine.toString());
        resetEMagazineArgs();
        ADDLOGGER.log(Level.INFO, "Added E-Magazine: " + newEMagazine.toString());
    }

    private void addNewspaper(String statement, GenericList<Resource, Event> container) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;
        assert container != null : ASSERT_CONTAINER;

        String[] values = parseAddNewspaper(statement);
        Newspaper newNewspaper = createNewspaper(values, resourceID);
        container.getResourceList().add(newNewspaper);
        System.out.println("This newspaper is added:" + System.lineSeparator() + newNewspaper.toString());
        resetNewspaperArgs();
        ADDLOGGER.log(Level.INFO, "Added Newspaper: " + newNewspaper.toString());
    }

    private void addENewspaper(String statement, GenericList<Resource, Event> container) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;
        assert container != null : ASSERT_CONTAINER;

        String[] values = parseAddENewspaper(statement);
        ENewspaper newENewspaper = createENewspaper(values, resourceID);
        container.getResourceList().add(newENewspaper);
        System.out.println("This e-newspaper is added:" + System.lineSeparator() + newENewspaper.toString());
        resetENewspaperArgs();
        ADDLOGGER.log(Level.INFO, "Added E-Newspaper: " + newENewspaper.toString());
    }

    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container) throws
            IllegalStateException, NumberFormatException, SysLibException {
        assert statement != null : ASSERT_STATEMENT;
        assert container != null : ASSERT_CONTAINER;
        ADDLOGGER.log(Level.INFO, "Executing Add. Input Arguments: " + statement);
        feedbackToUser = "";

        resourceID = container.getResourceList().size() + RESOURCEID_INCREMENT;
        String tag = parseAddCommand(statement);

        if (tag.equalsIgnoreCase(BOOK_TAG)) {
            addBook(statement, container);
        } else if (tag.equalsIgnoreCase(EBOOK_TAG)) {
            addEBook(statement, container);
        } else if (tag.equalsIgnoreCase(CD_TAG)) {
            addCD(statement, container);
        } else if (tag.equalsIgnoreCase(MAGAZINE_TAG)) {
            addMagazine(statement, container);
        } else if (tag.equalsIgnoreCase(EMAGAZINE_TAG)) {
            addEMagazine(statement, container);
        } else if (tag.equalsIgnoreCase(NEWSPAPER_TAG)) {
            addNewspaper(statement, container);
        } else if (tag.equalsIgnoreCase(ENEWSPAPER_TAG)) {
            addENewspaper(statement, container);
        } else {
            throw new SysLibException(ERROR_TAG);
        }
        System.out.println(LINEDIVIDER);
        return new CommandResult(feedbackToUser);
    }
}
