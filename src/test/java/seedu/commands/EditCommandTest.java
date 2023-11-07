package seedu.commands;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import seedu.parser.Parser;
import seedu.util.TestUtil;


import static seedu.commands.EditCommand.RESOURCE_NOT_FOUND;
import static seedu.commands.EditCommand.EDIT_SUCCESS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.ui.MessageFormatter.formatLastLineDivider;

public class EditCommandTest {
    private static List<Resource> testResourceList = new ArrayList<>();
    private Parser parser = new Parser();
    private List<Resource> emptyResourceList = new ArrayList<>();
    private TestUtil testUtil = new TestUtil();
    private Command editCommand = new EditCommand();

    @BeforeAll
    public static void setup()  {
        testResourceList = TestUtil.fillTestList();

    }

    @Test
    public void testEditResourceNotFound() throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(editCommand, "/id 123 /t NEWTITLE", emptyResourceList);
        String expectedMessage =  RESOURCE_NOT_FOUND;
        assertEquals(expectedMessage, outputMessage);
    }

    @Test
    public void testNoArgumentGiven() throws SysLibException {
        assertThrows(SysLibException.class, ()->editCommand.execute("/id 123", parser.container));

    }

    @Test
    public void testEditTitleBehavior() throws SysLibException {
        executeEditSuccessBehavior("/id 2 /t NEWTITLE");
    }

    @Test
    public void testEditAuthorBehavior() throws SysLibException {
        executeEditSuccessBehavior("/id 2 /a NEWAUTHOR");
    }

    @Test
    public void testEditGenreBehavior() throws SysLibException {
        executeEditSuccessBehavior("/id 2 /g Horror, Action, Fantasy");
    }

    private void executeEditSuccessBehavior(String argument) throws SysLibException {
        String outputMessage = testUtil.getOutputMessage(editCommand, argument, testResourceList);
        String expectedMessage = EDIT_SUCCESS + formatLastLineDivider((testResourceList.get(0)).toString());
        assertEquals(expectedMessage, outputMessage);
    }
}
