package seedu.util;

import seedu.commands.Command;
import seedu.data.Book;
import seedu.data.Resource;
import seedu.data.Status;
import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {


    public String getOutputMessage(Command c, String m, List<Resource> resourceList) throws SysLibException {
        Parser parser = new Parser();
        parser.resourceList = resourceList;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        c.execute(m, parser);
        return outputStream.toString();
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

        Resource test1 = new Resource("title1", "1", 12345, Status.AVAILABLE);
        Book testBook = new Book("title2", "2", "author", genres, 123123, Status.AVAILABLE);
        Book testBook2 = new Book("title3", "3", "author", genresAdventure, 123123,
                Status.AVAILABLE);
        Book testBook3 = new Book("title3", "4", "author", genresNull, 123123, Status.AVAILABLE);

        testResourceList.add(test1);
        testResourceList.add(testBook);
        testResourceList.add(testBook2);
        testResourceList.add(testBook3);
        return testResourceList;
    }
}
