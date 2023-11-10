package seedu.parser.resources;

import seedu.exception.SysLibException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.parser.resources.ParseResource.parseIsbn;
import static seedu.parser.resources.ParseResource.parseTitle;
import static seedu.parser.resources.ParseResource.parseBrand;
import static seedu.parser.resources.ParseResource.parseIssue;
import static seedu.parser.resources.ParseResource.parseLink;
import static seedu.parser.resources.ParseResource.parseStatus;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class ParseEMagazine {
    public static String[] args = new String[7];
    public static String[] parseAddEMagazine(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        try {
            String inputPattern = "^(?=.*/i ([\\d]+))(?=.*/t ([^/]+))(?=.*/b ([^/]+))" +
                    "(?=.*/tag ([\\s\\w]+))(?=.*/is ([^/]+))(?=.*/s ([\\w]+))?(?=.*/l ([^\\s]+)).*$";
            Matcher matcher = Pattern.compile(inputPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
            boolean isMatching = matcher.find();

            Boolean isStatusMatching = parseEMagazineArgs(statement);
            if (isMatching) {
                args[0] = matcher.group(1).trim(); // isbn
                args[1] = matcher.group(2).trim(); // title
                args[2] = matcher.group(3).trim(); // brand
                args[3] = matcher.group(5).trim(); // issue
                if (isStatusMatching) {
                    args[4] = matcher.group(6).trim(); // status
                }
                args[5] = matcher.group(4).trim(); // tag
                args[6] = matcher.group(7).trim(); // link
                checkEmptyEMagazineArgs(args);
            } else {
                throw new SysLibException("Please use the format " +
                        "'add /i ISBN /t TITLE /b BRAND /is ISSUE /tag TAG /l LINK [/s STATUS]'."
                        + SEPARATOR_LINEDIVIDER);
            }

            return args;
        } catch (IllegalStateException e) {
            throw new SysLibException("Please use the format " +
                    "'add /i ISBN /t TITLE /b BRAND /is ISSUE /tag TAG /l LINK [/s STATUS]'." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static Boolean parseEMagazineArgs(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        parseIsbn(statement);
        parseTitle(statement);
        parseBrand(statement);
        parseIssue(statement);
        parseLink(statement);

        return parseStatus(statement);
    }

    public static void checkEmptyEMagazineArgs(String[] args) throws SysLibException {
        assert args != null : "Arguments should not be null";

        if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty() || args[5].isEmpty()
                || args[6].isEmpty()) {
            throw new SysLibException("Please enter the ISBN, title, brand, issue, link, and tag." +
                    SEPARATOR_LINEDIVIDER);
        }
    }

    public static void resetEMagazineArgs() {
        args = new String[7];
    }
}
