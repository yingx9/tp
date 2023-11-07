package seedu.util;

import seedu.commands.Command;
import seedu.data.resources.Book;
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
        

        testResourceList.add(testBook);
        testResourceList.add(testBook2);
        testResourceList.add(testBook3);
        return testResourceList;
    }
}
