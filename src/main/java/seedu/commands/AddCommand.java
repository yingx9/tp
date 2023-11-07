package seedu.commands;

import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.CreateResource;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;

import static seedu.data.resources.Book.BOOK_TAG;
import static seedu.data.resources.CD.CD_TAG;
import static seedu.data.resources.Magazine.MAGAZINE_TAG;
import static seedu.data.resources.Newspaper.NEWSPAPER_TAG;
import static seedu.data.resources.EBook.EBOOK_TAG;
import static seedu.data.resources.EMagazine.EMAGAZINE_TAG;
import static seedu.data.resources.ENewspaper.ENEWSPAPER_TAG;
import static seedu.ui.UI.LINEDIVIDER;


public class AddCommand extends Command{
    private static String feedbackToUser;
    public AddCommand(){
        args = new String[]{"id", "t", "a", "tag", "i", "g", "s", "l", "c", "b", "p", "ty", "is", "ed"};
        required = new boolean[]{true, true, false, true, true, false, false, false, false, false, false, false, false
                , false};
    }


    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container) throws
            IllegalStateException, NumberFormatException, SysLibException {
        feedbackToUser = "";
        String[] values = parseArgument(statement);
        validateStatement(statement, values);
        String title = values[1];
        String tag = values[3];

        if (tag.equalsIgnoreCase(BOOK_TAG)) {
            container.getResourceList().add(CreateResource.createBook(values));
            System.out.println("This book is added: " + title);
        } else if (tag.equalsIgnoreCase(EBOOK_TAG)) {
            container.getResourceList().add(CreateResource.createEBook(values));
            System.out.println("This eBook is added: " + title);
        } else if (tag.equalsIgnoreCase(CD_TAG)) {
            container.getResourceList().add(CreateResource.createCD(values));
            System.out.println("This CD is added: " + title);
        } else if (tag.equalsIgnoreCase(MAGAZINE_TAG)) {
            container.getResourceList().add(CreateResource.createMagazine(values));
            System.out.println("This magazine is added: " + title);
        } else if (tag.equalsIgnoreCase(EMAGAZINE_TAG)) {
            container.getResourceList().add(CreateResource.createEMagazine(values));
            System.out.println("This eMagazine is added: " + title);
        } else if (tag.equalsIgnoreCase(NEWSPAPER_TAG)) {
            container.getResourceList().add(CreateResource.createNewspaper(values));
            System.out.println("This newspaper is added: " + title);
        } else if (tag.equalsIgnoreCase(ENEWSPAPER_TAG)) {
            container.getResourceList().add(CreateResource.createENewspaper(values));
            System.out.println("This eNewspaper is added: " + title);
        } else {
            throw new SysLibException("Please enter a valid tag." + System.lineSeparator() +
                    LINEDIVIDER);
        }
        System.out.println(LINEDIVIDER);

        return new CommandResult(feedbackToUser);
    }
}
