package seedu.commands;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import seedu.parser.Parser;
import seedu.util.TestUtil;


import static seedu.commands.ListCommand.GENERIC_MESSAGE;
import static seedu.commands.ListCommand.FILTER_MESSAGE;
import static seedu.commands.ListCommand.ZERO_RESOURCES_MESSAGE;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListCommandTest {

    private static List<Resource> testResourceList = new ArrayList<>();
    private Parser parser = new Parser();
    private List<Resource> emptyResourceList = new ArrayList<>();
    private TestUtil testUtil = new TestUtil();

    private Command listCommand = new ListCommand();


    @BeforeAll
    public static void setup() throws SysLibException {
        testResourceList = TestUtil.fillTestList();

    }

    @Test
    public void testEmptyListMessage() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(listCommand, "", emptyResourceList);
        String expectedMessage = GENERIC_MESSAGE;
        expectedMessage +=  ZERO_RESOURCES_MESSAGE;
        assertEquals(expectedMessage, outputMessage);

    }

    @Test
    public void testListByTagBehavior() {
        parser.resourceList = testResourceList;
        assertThrows(IllegalArgumentException.class, ()->listCommand.execute("/tag", parser));

    }
    @Test
    public void testListByGenreBehavior()  {
        parser.resourceList = testResourceList;
        assertThrows(IllegalArgumentException.class, ()->listCommand.execute("/g", parser));

    }

    @Test
    public void testNoFilteredListDisplay() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(listCommand, "/g Thriller", testResourceList);
        String expectedMessage = FILTER_MESSAGE;
        expectedMessage += ZERO_RESOURCES_MESSAGE;
        assertEquals(expectedMessage, outputMessage);

    }
}
