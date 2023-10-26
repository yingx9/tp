package seedu.commands;


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;
import seedu.util.TestUtil;

import static seedu.commands.ListCommand.*;
import static seedu.ui.UI.LINESEPARATOR;


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
    private void assertEmptyListMessage() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(listCommand, "", emptyResourceList);
        String expectedMessage = GENERIC_MESSAGE;
        expectedMessage +=  ZERO_RESOURCES_MESSAGE + LINESEPARATOR;
        assertEquals(expectedMessage, outputMessage);

    }

    @Test
    private void assertListByTagBehavior() {
        parser.resourceList = testResourceList;
        assertThrows(IllegalArgumentException.class, ()->listCommand.execute("/tag", parser));

    }
    @Test
    private void assertListByGenreBehavior()  {
        parser.resourceList = testResourceList;
        assertThrows(IllegalArgumentException.class, ()->listCommand.execute("/g", parser));

    }

    @Test
    private void assertNoFilteredListDisplay() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(listCommand, "/g Thriller", testResourceList);
        String expectedMessage = FILTER_MESSAGE;
        expectedMessage += ZERO_RESOURCES_MESSAGE + LINESEPARATOR;
        assertEquals(expectedMessage, outputMessage);

    }
}
