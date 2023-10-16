package seedu.util;

import seedu.commands.Command;
import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

}
