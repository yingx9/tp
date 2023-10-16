package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.data.SysLibException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteParserTest {

    @Test
    public void testParseDeleteWithValidInput() throws SysLibException {
        String statement = "delete /id 1";
        String result = DeleteParser.parseDelete(statement);
        assertEquals("1", result);
    }

    @Test
    public void testParseDeleteWithInvalidInput() {
        String statement = "delete /t hello";
        assertThrows(SysLibException.class, () -> DeleteParser.parseDelete(statement));
    }
}
