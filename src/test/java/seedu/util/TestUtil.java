package seedu.util;

import seedu.commands.Command;
import seedu.data.resources.Book;
import seedu.data.resources.CD;
import seedu.data.resources.EBook;
import seedu.data.resources.EMagazine;
import seedu.data.resources.ENewspaper;
import seedu.data.resources.Magazine;
import seedu.data.resources.Newspaper;
import seedu.data.resources.Resource;
import seedu.data.Status;
import seedu.exception.SysLibException;
import seedu.parser.Parser;
import seedu.commands.CommandResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {


    public String getOutputMessage(Command c, String m, List<Resource> resourceList) throws SysLibException {
        Parser parser = new Parser();
        parser.container.setResourceList(resourceList);
        CommandResult commandResult = c.execute(m, parser.container);
        return commandResult.feedbackToUser;
    }

    public static String getCurrentDate(){
        LocalDateTime dateReceived = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = dateReceived.format(formatter);
        return formattedDate;
    }

    public static List<Resource> fillTestList() {
        List<Resource> testResourceList = new ArrayList<>();
        String[] genres = {"Horror", "Fiction"};
        String[] genresAdventure = {"Adventure"};
        String[] genresNull = {null};


        Book testBook = new Book("title2", "TMOBM00000001", "author", genres, 2,
                Status.AVAILABLE);
        Book testBook2 = new Book("title3", "TMOBM00000001", "author", genresAdventure, 3,
                Status.AVAILABLE);
        Book testBook3 = new Book("title3", "TMOBM00000001", "author", genresNull, 4,
                Status.AVAILABLE);

        EBook testEBook = new EBook("title4", "TMOBM00000001", "author", genresNull, 5,
                Status.AVAILABLE, "abc.com");

        Magazine testMagazine = new Magazine("title5", "TMOBM00000001", "brand", "issue", 6,
                Status.AVAILABLE);
        EMagazine testEMagazine = new EMagazine("title6", "TMOBM00000001", "brand", "issue", 7,
                Status.AVAILABLE, "def.com");

        CD testCD = new CD("title7", "TMOBM00000001", "creator", "type", 8,
                Status.AVAILABLE);

        Newspaper testNewspaper = new Newspaper("title8", "TMOBM00000001", "publisher",
                "edition", 9, Status.AVAILABLE);

        ENewspaper testENewspaper = new ENewspaper("title8", "TMOBM00000001", "publisher",
                "edition",10,Status.AVAILABLE, "xyz.com");



        testResourceList.add(testBook);
        testResourceList.add(testBook2);
        testResourceList.add(testBook3);
        testResourceList.add(testEBook);
        testResourceList.add(testMagazine);
        testResourceList.add(testEMagazine);
        testResourceList.add(testCD);
        testResourceList.add(testNewspaper);
        testResourceList.add(testENewspaper);

        return testResourceList;
    }

    public List<Resource> addDummyResource(List<Resource> resourceList){
        Resource dummyResource = new Resource("title", "TMOBM00000001",1, Status.AVAILABLE);
        resourceList.add(dummyResource);
        List<Resource> dummyList = resourceList;
        return dummyList;
    }

}
