package seedu.parser.resources;

import seedu.exception.SysLibException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static seedu.parser.resources.ParseResource.parseIsbn;
import static seedu.parser.resources.ParseResource.parseTitle;
import static seedu.parser.resources.ParseResource.parseCreator;
import static seedu.parser.resources.ParseResource.parseType;
import static seedu.parser.resources.ParseResource.parseStatus;
import static seedu.ui.Messages.ASSERT_STATEMENT;
import static seedu.ui.Messages.ASSERT_ARGUMENTS;
import static seedu.ui.Messages.ERROR_FORMAT_CD;
import static seedu.ui.Messages.ERROR_EMPTY_CD;

public class ParseCD {
    public static String[] args = new String[6];
    public static String[] parseAddCD(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        try {
            String inputPattern = "^(?=.*/i ([\\d]+))(?=.*/t ([^/]+))(?=.*/c ([^/]+))" +
                    "(?=.*/tag ([\\s\\w]+))(?=.*/ty ([^/]+))(?=.*/s ([\\w]+))?.*$";
            Matcher matcher = Pattern.compile(inputPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
            boolean isInputMatching = matcher.find();

            Boolean isStatusMatching = parseCDArgs(statement);

            if (isInputMatching) {
                args[0] = matcher.group(1).trim(); // isbn
                args[1] = matcher.group(2).trim(); // title
                args[2] = matcher.group(3).trim(); // creator
                args[3] = matcher.group(5).trim(); // type
                if (isStatusMatching) {
                    args[4] = matcher.group(6).trim(); // status
                }
                args[5] = matcher.group(4).trim(); // tag

                checkEmptyCDArgs(args);
            } else {
                throw new SysLibException(ERROR_FORMAT_CD);
            }

            return args;
        } catch (IllegalStateException e) {
            throw new SysLibException(ERROR_FORMAT_CD);
        }
    }

    public static Boolean parseCDArgs(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        parseIsbn(statement);
        parseTitle(statement);
        parseCreator(statement);
        parseType(statement);

        return parseStatus(statement);
    }

    public static void checkEmptyCDArgs(String[] args) throws SysLibException {
        assert args != null : ASSERT_ARGUMENTS;

        if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty() || args[5].isEmpty()) {
            throw new SysLibException(ERROR_EMPTY_CD);
        }
    }

    public static void resetCDArgs() {
        args = new String[6];
    }
}
