package seedu.commands;

import seedu.data.resources.Book;
import seedu.data.resources.EBook;
import seedu.data.resources.CD;
import seedu.data.resources.Magazine;
import seedu.data.resources.EMagazine;
import seedu.data.resources.Newspaper;
import seedu.data.resources.ENewspaper;
import seedu.exception.SysLibException;
import seedu.parser.Parser;


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
import static seedu.ui.UI.LINEDIVIDER;


public class AddCommand extends Command{
    public static final String ID_OPTION = "id";
    public static final String TITLE_OPTION = "t";
    public static final String AUTHOR_OPTION = "a";
    public static final String TAG_OPTION = "tag";
    public static final String ISBN_OPTION = "i";
    public static final String GENRE_OPTION = "g";
    public static final String STATUS_OPTION = "s";
    public static final String LINK_OPTION = "l";
    public static final String CREATOR_OPTION = "c";
    public static final String BRAND_OPTION = "b";
    public static final String PUBLISHER_OPTION = "p";
    public static final String TYPE_OPTION = "ty";
    public static final String ISSUE_OPTION = "is";
    public static final String EDITION_OPTION = "ed";

    private static String feedbackToUser;
    private int resourceID;

    public AddCommand(){
        args = new String[]{ID_OPTION, TITLE_OPTION, AUTHOR_OPTION, TAG_OPTION, ISBN_OPTION, GENRE_OPTION,
            STATUS_OPTION, LINK_OPTION, CREATOR_OPTION, BRAND_OPTION, PUBLISHER_OPTION, TYPE_OPTION,
            ISSUE_OPTION, EDITION_OPTION};
        required = new boolean[]{true, true, false, true, true, false, false, false, false, false, false, false, false
                , false};
    }

    private void addBook(String statement, Parser parser) throws SysLibException {
        String[] values = parseAddBook(statement);
        Book newBook = createBook(values, resourceID);
        parser.resourceList.add(newBook);
        System.out.println("This book is added: \n" + newBook.toString());
        resetBookArgs();
    }

    private void addEBook(String statement, Parser parser) throws SysLibException {
        String[] values = parseAddEBook(statement);
        EBook newEBook = createEBook(values, resourceID);
        parser.resourceList.add(newEBook);
        System.out.println("This eBook is added: \n" + newEBook.toString());
        resetEBookArgs();
    }

    private void addCD(String statement, Parser parser) throws SysLibException {
        String[] values = parseAddCD(statement);
        CD newCD = createCD(values, resourceID);
        parser.resourceList.add(newCD);
        System.out.println("This CD is added: \n" + newCD.toString());
        resetCDArgs();
    }

    private void addMagazine(String statement, Parser parser) throws SysLibException {
        String[] values = parseAddMagazine(statement);
        Magazine newMagazine = createMagazine(values, resourceID);
        parser.resourceList.add(newMagazine);
        System.out.println("This magazine is added: \n" + newMagazine.toString());
        resetMagazineArgs();
    }

    private void addEMagazine(String statement, Parser parser) throws SysLibException {
        String[] values = parseAddEMagazine(statement);
        EMagazine newEMagazine = (EMagazine) createEMagazine(values, resourceID);
        parser.resourceList.add(newEMagazine);
        System.out.println("This eMagazine is added: \n" + newEMagazine.toString());
        resetEMagazineArgs();
    }

    private void addNewspaper(String statement, Parser parser) throws SysLibException {
        String[] values = parseAddNewspaper(statement);
        Newspaper newNewspaper = createNewspaper(values, resourceID);
        parser.resourceList.add(newNewspaper);
        System.out.println("This newspaper is added: \n" + newNewspaper.toString());
        resetNewspaperArgs();
    }

    private void addENewspaper(String statement, Parser parser) throws SysLibException {
        String[] values = parseAddENewspaper(statement);
        ENewspaper newENewspaper = createENewspaper(values, resourceID);
        parser.resourceList.add(newENewspaper);
        System.out.println("This eNewspaper is added: \n" + newENewspaper.toString());
        resetENewspaperArgs();
    }

    @Override
    public CommandResult execute(String statement, Parser parser) throws
            IllegalStateException, NumberFormatException, SysLibException {
        feedbackToUser = "";

        resourceID = parser.resourceList.size() + 1;
        String tag = parseAddCommand(statement);

        if (tag.equalsIgnoreCase(BOOK_TAG)) {
            addBook(statement, parser);
        } else if (tag.equalsIgnoreCase(EBOOK_TAG)) {
            addEBook(statement, parser);
        } else if (tag.equalsIgnoreCase(CD_TAG)) {
            addCD(statement, parser);
        } else if (tag.equalsIgnoreCase(MAGAZINE_TAG)) {
            addMagazine(statement, parser);
        } else if (tag.equalsIgnoreCase(EMAGAZINE_TAG)) {
            addEMagazine(statement, parser);
        } else if (tag.equalsIgnoreCase(NEWSPAPER_TAG)) {
            addNewspaper(statement, parser);
        } else if (tag.equalsIgnoreCase(ENEWSPAPER_TAG)) {
            addENewspaper(statement, parser);
        } else {
            throw new SysLibException("Please enter a valid tag." + System.lineSeparator() + LINEDIVIDER);
        }
        System.out.println(LINEDIVIDER);
        return new CommandResult(feedbackToUser);
    }
}
