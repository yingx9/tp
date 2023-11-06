package seedu.parser.Resources;

import seedu.exception.SysLibException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.parser.Resources.ParseResource.*;
import static seedu.parser.Resources.ParseResource.parseStatus;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class ParseCD {
    public static String[] args = new String[6];
    public static String[] parseAddCD(String statement) throws SysLibException {
        try {
            String inputPattern = "^(?=.*/i ([a-zA-Z0-9]+))(?=.*/t ([^/]+))(?=.*/c ([^/]+))" +
                    "(?=.*/tag ([\\sa-zA-Z]+))(?=.*/ty ([^/]+))(?=.*/s ([a-zA-Z]+))?.*$";
            Matcher matcher = Pattern.compile(inputPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
            boolean isMatching = matcher.find();

            Boolean isStatusMatching = parseCDArgs(statement);

            if (isMatching) {
                args[0] = matcher.group(1).trim(); // isbn
                args[1] = matcher.group(2).trim(); // title
                args[2] = matcher.group(3).trim(); // creator
                args[3] = matcher.group(5).trim(); // type
                if (isStatusMatching) {
                    args[4] = matcher.group(7).trim(); // status
                }
                args[5] = matcher.group(4).trim(); // tag

                checkEmptyCDArgs(args);
            } else {
                throw new SysLibException("Please use the format " +
                        "'add /i ISBN /t TITLE /c CREATOR /ty TYPE /tag TAG [/s STATUS]'." + SEPARATOR_LINEDIVIDER);
            }

            return args;
        } catch (IllegalStateException e) {
            throw new SysLibException("Please use the format " +
                    "'add /i ISBN /t TITLE /c CREATOR /ty TYPE /tag TAG [/s STATUS]'." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static Boolean parseCDArgs(String statement) throws SysLibException {
        parseIsbn(statement);
        parseTitle(statement);
        parseCreator(statement);
        parseType(statement);
        return parseStatus(statement);
    }

    public static void checkEmptyCDArgs(String[] args) throws SysLibException {
        if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty() || args[5].isEmpty()) {
            throw new SysLibException("Please enter the ISBN, title, creator, type, and tag." +
                    SEPARATOR_LINEDIVIDER);
        }
    }

    public static void resetCDArgs() {
        args = new String[6];
    }
}
