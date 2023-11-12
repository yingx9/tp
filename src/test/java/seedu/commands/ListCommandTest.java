package seedu.commands;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import seedu.parser.Parser;
import seedu.util.TestUtil;


import static seedu.ui.ListCommandMessages.GENERIC_MESSAGE;
import static seedu.ui.ListCommandMessages.FILTER_MESSAGE;
import static seedu.ui.ListCommandMessages.STATUS_ERROR_MESSAGE;
import static seedu.ui.ListCommandMessages.ZERO_RESOURCES_MESSAGE;
import static seedu.commands.ListCommand.matchedResources;
import static seedu.ui.UI.showResourcesDetails;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListCommandTest {

    private static List<Resource> testResourceList = new ArrayList<>();
    private static Parser parser = new Parser();
    private List<Resource> emptyResourceList = new ArrayList<>();
    private TestUtil testUtil = new TestUtil();

    private Command listCommand = new ListCommand();


    @BeforeAll
    public static void setup() throws SysLibException {
        testResourceList = TestUtil.fillTestList();
        parser.container.setResourceList(testResourceList);

    }

    @Test
    public void testEmptyListMessage() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(listCommand, "", emptyResourceList);
        String expectedMessage = GENERIC_MESSAGE;
        expectedMessage +=  ZERO_RESOURCES_MESSAGE;
        assertEquals(expectedMessage, outputMessage);

    }

    @Test
    public void testNoTagArgBehavior() {
        assertThrows(IllegalArgumentException.class, ()->listCommand.execute("/tag", parser.container));

    }
    @Test
    public void testNoGenreArgBehavior()  {
        assertThrows(IllegalArgumentException.class, ()->listCommand.execute("/g", parser.container));

    }

    @Test
    public void testNoStatusArgBehavior()  {
        assertThrows(IllegalArgumentException.class, ()->listCommand.execute("/s", parser.container));

    }

    @Test
    public void testListByTagFilterBehavior() throws SysLibException {
        executeListFilterBehavior("/tag B");
    }

    @Test
    public void testListByGenreFilterBehavior() throws SysLibException {
        executeListFilterBehavior("/g Horror");
        executeListFilterBehavior("/g Adventure");
        executeListFilterBehavior("/g Fiction");
    }

    @Test
    public void testListByStatusFilterBehavior() throws SysLibException {
        executeListFilterBehavior("/s AVAILABLE");
        executeListFilterBehavior("/s BORROWED");
        executeListFilterBehavior("/s LOST");

    }

    public void executeListFilterBehavior(String argument) throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(listCommand, argument, testResourceList);
        String expectedMessage = FILTER_MESSAGE + showResourcesDetails(matchedResources);
        assertEquals(expectedMessage, outputMessage);

    }

    @Test
    public void testNoFilteredListDisplay() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(listCommand, "/g Thriller", testResourceList);
        String expectedMessage = FILTER_MESSAGE;
        expectedMessage += ZERO_RESOURCES_MESSAGE;
        assertEquals(expectedMessage, outputMessage);

    }

    @Test
    public void testInvalidStatusInput(){
        executeAssertSysLibExceptionThrown("/s INVALIDSTATUS",STATUS_ERROR_MESSAGE);
    }

    private void executeAssertSysLibExceptionThrown(String arguments, String expectedMessage){
        SysLibException exception = assertThrows(SysLibException.class, ()->listCommand.execute(
                arguments, parser.container));
        assertEquals(expectedMessage, exception.getMessage());
    }
}
