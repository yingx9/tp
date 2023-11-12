package seedu.parser.resources;

import seedu.exception.SysLibException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.parser.resources.ParseResource.parseIsbn;
import static seedu.parser.resources.ParseResource.parseTitle;
import static seedu.parser.resources.ParseResource.parseBrand;
import static seedu.parser.resources.ParseResource.parseIssue;
import static seedu.parser.resources.ParseResource.parseStatus;
import static seedu.ui.Messages.ASSERT_STATEMENT;
import static seedu.ui.Messages.ASSERT_ARGUMENTS;
import static seedu.ui.Messages.ERROR_FORMAT_MAGAZINE;
import static seedu.ui.Messages.ERROR_EMPTY_MAGAZINE;

public class ParseMagazine {
    public static String[] args = new String[6];
    public static String[] parseAddMagazine(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        try {
            String inputPattern = "^(?=.*/i ([\\d]+))(?=.*/t ([^/]+))(?=.*/b ([^/]+))" +
                    "(?=.*/tag ([\\s\\w]+))(?=.*/is ([^/]+))(?=.*/s ([\\w]+))?.*$";
            Matcher matcher = Pattern.compile(inputPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
            boolean isInputMatching = matcher.find();

            Boolean isStatusMatching = parseMagazineArgs(statement);

            if (isInputMatching) {
                args[0] = matcher.group(1).trim(); // isbn
                args[1] = matcher.group(2).trim(); // title
                args[2] = matcher.group(3).trim(); // brand
                args[3] = matcher.group(5).trim(); // issue
                if (isStatusMatching) {
                    args[4] = matcher.group(6).trim(); // status
                }
                args[5] = matcher.group(4).trim(); // tag

                checkEmptyMagazineArgs(args);
            } else {
                throw new SysLibException(ERROR_FORMAT_MAGAZINE);
            }

            return args;
        } catch (IllegalStateException e) {
            throw new SysLibException(ERROR_FORMAT_MAGAZINE);
        }
    }

    public static Boolean parseMagazineArgs(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        parseIsbn(statement);
        parseTitle(statement);
        parseBrand(statement);
        parseIssue(statement);

        return parseStatus(statement);
    }

    public static void checkEmptyMagazineArgs(String[] args) throws SysLibException {
        assert args != null : ASSERT_ARGUMENTS;

        if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty() || args[5].isEmpty()) {
            throw new SysLibException(ERROR_EMPTY_MAGAZINE);
        }
    }

    public static void resetMagazineArgs() {
        args = new String[6];
    }
}
