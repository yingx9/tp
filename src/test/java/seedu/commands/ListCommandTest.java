package seedu.commands;


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;
import seedu.util.TestUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListCommandTest {


    private Parser parser = new Parser();
    private List<Resource> emptyResourceList = new ArrayList<>();
    private List<Resource> testResourceList = new ArrayList<>();
    private TestUtil testUtil = new TestUtil();

    private Command listCommand = new ListCommand();



    @Test
    void execute() throws SysLibException {
        testResourceList = TestUtil.fillTestList();
        assertEmptyListMessage();
        assertListByTagBehavior();
        assertListByGenreBehavior();
        assertNoFilteredListDisplay();
    }
    @Test
    /**
     * Asserts that there are no errors when list is called and there are no resources in the library
     */
    private void assertEmptyListMessage() throws SysLibException {

        String outputMessage = testUtil.getOutputMessage(listCommand, "list");
        String expectedMessage = "There are 0 resources in the library. " + System.lineSeparator();
        expectedMessage += System.lineSeparator();
        assertEquals(expectedMessage, outputMessage);

    }

    @Test
    /**
     * Asserts that there an exception is thrown when the user enters a /tag without a value
     */
    private void assertListByTagBehavior() {
        parser.resourceList = testResourceList;
        assertThrows(SysLibException.class, ()->listCommand.execute("/tag", parser));

    }

    @Test
    /**
     * Asserts that there an exception is thrown when the user enters a /g without a value
     */
    private void assertListByGenreBehavior()  {
        parser.resourceList = testResourceList;
        assertThrows(SysLibException.class, ()->listCommand.execute("/g", parser));

    }

    @Test
    /**
     * Asserts that there is no error when there are no resources to list with given filters.
     */
    private void assertNoFilteredListDisplay() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(listCommand, "list /g Thriller", testResourceList);
        String expectedMessage = "Listing all resources matching given genre: " + System.lineSeparator()
                + System.lineSeparator();
        expectedMessage += "There are currently 0 resource(s)." + System.lineSeparator();
        expectedMessage += System.lineSeparator();
        assertEquals(expectedMessage, outputMessage);

    }

    

}
