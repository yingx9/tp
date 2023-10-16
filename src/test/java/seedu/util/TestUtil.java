package seedu.util;

import seedu.commands.Command;
import seedu.data.Book;
import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {


    public String getOutputMessage(Command c, String m) throws SysLibException {
        Parser parser = new Parser();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        c.execute(m, parser);
        return outputStream.toString();
    }

    public String getOutputMessage(Command c, String m, List<Resource> resourceList) throws SysLibException {
        Parser parser = new Parser();
        parser.resourceList = resourceList;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        c.execute(m, parser);
        return outputStream.toString();
    }

    public static List<Resource> fillTestList() {
        List<Resource> testResourceList = new ArrayList<>();
        String[] genres = {"Horror", "Fiction"};
        String[] genresAdventure = {"Adventure"};

        Resource test1 = new Resource("title1", "123123");
        Book testBook = new Book("title2", "123123", "author", genres, 123123);
        Book testBook2 = new Book("title3", "123123", "author", genresAdventure, 123123);

        testResourceList.add(test1);
        testResourceList.add(testBook);
        testResourceList.add(testBook2);
        return testResourceList;
    }

}
