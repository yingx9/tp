package seedu.commands;

import seedu.data.CreateResource;
import seedu.data.SysLibException;
import seedu.parser.Parser;

import static seedu.data.Book.BOOK_TAG;
import static seedu.data.CD.CD_TAG;
import static seedu.data.Magazine.MAGAZINE_TAG;
import static seedu.data.Newspaper.NEWSPAPER_TAG;
import static seedu.data.eBook.EBOOK_TAG;
import static seedu.data.eMagazine.EMAGAZINE_TAG;
import static seedu.data.eNewspaper.ENEWSPAPER_TAG;
import static seedu.ui.UI.LINEDIVIDER;


public class AddCommand extends Command{

    public AddCommand(){
        args = new String[]{"id", "t", "a", "tag", "i", "g", "s", "l", "c", "b", "p", "ty", "is", "ed"};
        required = new boolean[]{true, true, false, true, true, false, false, false, false, false, false, false, false
                , false};
    }
    @Override
    public void execute(String statement, Parser parser) throws
            IllegalStateException, NumberFormatException, SysLibException {
        String[] values = parseArgument(statement);
        validateStatement(statement, values);
        String title = values[1];
        String tag = values[3];

        if (tag.equalsIgnoreCase(BOOK_TAG)) {
            parser.resourceList.add(CreateResource.createBook(values));
            System.out.println("This book is added: " + title);
        } else if (tag.equalsIgnoreCase(EBOOK_TAG)) {
            parser.resourceList.add(CreateResource.createEBook(values));
            System.out.println("This eBook is added: " + title);
        } else if (tag.equalsIgnoreCase(CD_TAG)) {
            parser.resourceList.add(CreateResource.createCD(values));
            System.out.println("This CD is added: " + title);
        } else if (tag.equalsIgnoreCase(MAGAZINE_TAG)) {
            parser.resourceList.add(CreateResource.createMagazine(values));
            System.out.println("This magazine is added: " + title);
        } else if (tag.equalsIgnoreCase(EMAGAZINE_TAG)) {
            parser.resourceList.add(CreateResource.createEMagazine(values));
            System.out.println("This eMagazine is added: " + title);
        } else if (tag.equalsIgnoreCase(NEWSPAPER_TAG)) {
            parser.resourceList.add(CreateResource.createNewspaper(values));
            System.out.println("This newspaper is added: " + title);
        } else if (tag.equalsIgnoreCase(ENEWSPAPER_TAG)) {
            parser.resourceList.add(CreateResource.createENewspaper(values));
            System.out.println("This eNewspaper is added: " + title);
        } else {
            throw new SysLibException("Please enter a valid tag." + System.lineSeparator() +
                    LINEDIVIDER);
        }
        System.out.println(LINEDIVIDER);
    }
}
