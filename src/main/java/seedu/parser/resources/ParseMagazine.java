package seedu.parser.resources;

import seedu.exception.SysLibException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.parser.resources.ParseResource.parseIsbn;
import static seedu.parser.resources.ParseResource.parseTitle;
import static seedu.parser.resources.ParseResource.parseBrand;
import static seedu.parser.resources.ParseResource.parseIssue;
import static seedu.parser.resources.ParseResource.parseStatus;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class ParseMagazine {
    public static String[] args = new String[6];
    public static String[] parseAddMagazine(String statement) throws SysLibException {
        try {
            String inputPattern = "^(?=.*/i ([a-zA-Z0-9]+))(?=.*/t ([^/]+))(?=.*/b ([^/]+))" +
                    "(?=.*/tag ([\\sa-zA-Z]+))(?=.*/is ([^/]+))(?=.*/s ([a-zA-Z]+))?.*$";
            Matcher matcher = Pattern.compile(inputPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
            boolean isMatching = matcher.find();

            Boolean isStatusMatching = parseMagazineArgs(statement);

            if (isMatching) {
                args[0] = matcher.group(1).trim(); // isbn
                args[1] = matcher.group(2).trim(); // title
                args[2] = matcher.group(3).trim(); // brand
                args[3] = matcher.group(5).trim(); // issue
                if (isStatusMatching) {
                    args[4] = matcher.group(7).trim(); // status
                }
                args[5] = matcher.group(4).trim(); // tag

                checkEmptyMagazineArgs(args);
            } else {
                throw new SysLibException("Please use the format " +
                        "'add /i ISBN /t TITLE /b BRAND /is ISSUE /tag TAG [/s STATUS]'." + SEPARATOR_LINEDIVIDER);
            }

            return args;
        } catch (IllegalStateException e) {
            throw new SysLibException("Please use the format " +
                    "'add /i ISBN /t TITLE /b BRAND /is ISSUE /tag TAG [/s STATUS]'." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static Boolean parseMagazineArgs(String statement) throws SysLibException {
        parseIsbn(statement);
        parseTitle(statement);
        parseBrand(statement);
        parseIssue(statement);

        return parseStatus(statement);
    }

    public static void checkEmptyMagazineArgs(String[] args) throws SysLibException {
        if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty() || args[5].isEmpty()) {
            throw new SysLibException("Please enter the ISBN, title, brand, issue, and tag." +
                    SEPARATOR_LINEDIVIDER);
        }
    }

    public static void resetMagazineArgs() {
        args = new String[6];
    }
}
