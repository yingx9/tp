package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.data.SysLibException;

import javax.annotation.processing.AbstractProcessor;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    public void testProcess_ExitCommand() {
        Parser parser = new Parser();
        String validResponse = "exit";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        parser.process(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();

        String expectedOutput = "Bye, hope to see you again soon!\n"+
                "____________________________________________________________\n";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testProcess_HelpCommand() {
        Parser parser = new Parser();
        String validResponse = "help";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        parser.process(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();

        String expectedOutput = "Commands available:\n" +
                "add: add an item (e.g. add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE])\n" +
                "delete: delete an item (e.g. delete /id 123456789)\n" +
                "list: (e.g. list /tag B /g Fiction /a J. K. Rowling /i 9780763630188)\n" +
                "find: (e.g. find /i 9780763630188)\n" +
                "exit: (e.g. exit)\n" +
                "For more information, please refer to our user guide at:" +
                "https://ay2324s1-cs2113t-w11-1.github.io/tp/UserGuide.html\n" +
                "____________________________________________________________\n";

        assertEquals(expectedOutput, output);
    }

    @Test
    public void testProcess_UnknownCommand() {
        Parser parser = new Parser();
        String validResponse = "bye";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        parser.process(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();

        String expectedOutput = "no commands found. Enter \"help\" for a list of commands.\n" +
                "____________________________________________________________\n";

        assertEquals(expectedOutput, output);
    }

    @Test
    public void testProcess_Commands() {
        //Test add
        Parser parser = new Parser();
        String validResponse = "add /id 1 /t Surrounded by Idiots /a Thomas Erikson /tag B /i 9781250255174 /g Self-help";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        parser.process(validResponse);

        System.setOut(System.out);
        String output = outputStream.toString();
        String expectedOutput = "This book is added: Surrounded by Idiots\n";
        assertEquals(expectedOutput, output);
        //Add second book
        validResponse = "add /id 2 /t The Subtle Art of Not Giving a F*ck /a Mark Manson /tag B /i 9780062457714 /g Self-help";
        parser.process(validResponse);
        expectedOutput += "This book is added: The Subtle Art of Not Giving a F*ck\n";
        //Test list
        validResponse = "list";
        parser.process(validResponse);
        expectedOutput += "Listing all resources in the Library: \n\n" +
                "1. [B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 Author: Thomas Erikson Genre: Self-help\n\n" +
                "2. [B]  ID: 2 Title: The Subtle Art of Not Giving a F*ck ISBN: 9780062457714 Author: Mark Manson Genre: Self-help\n\n" +
                "There are currently 2 resource(s).\n\n";
        System.setOut(System.out);
        output = outputStream.toString();
        assertEquals(expectedOutput, output);
        //Test find
        validResponse = "find /t The Subtle Art of Not Giving a F*ck";
        parser.process(validResponse);
        expectedOutput += "Here are resources that matched the given filters: \n" +
                "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 Author: Thomas Erikson Genre: Self-help\n\n";
        output = outputStream.toString();
        assertEquals(expectedOutput, output);
        //Test delete
        validResponse = "delete /id 1";
        parser.process(validResponse);
        output = outputStream.toString();
        expectedOutput += "Looking for ID: 1...\n" +
                "This resource is removed: \n" +
                "[B]  ID: 1 Title: Surrounded by Idiots ISBN: 9781250255174 Author: Thomas Erikson Genre: Self-help\n\n";
        assertEquals(expectedOutput, output);
    }
}