package seedu.parser.Resources;

import seedu.exception.SysLibException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.parser.Resources.ParseResource.*;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class ParseNewspaper {
    public static String[] args = new String[6];
    public static String[] parseAddNewspaper(String statement) throws SysLibException {
        try {
            String inputPattern = "^(?=.*/i ([a-zA-Z0-9]+))(?=.*/t ([^/]+))(?=.*/p ([^/]+))" +
                    "(?=.*/tag ([\\sa-zA-Z]+))(?=.*/ed ([^/]+))(?=.*/s ([a-zA-Z]+))?.*$";
            Matcher matcher = Pattern.compile(inputPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
            boolean isMatching = matcher.find();

            Boolean isStatusMatching = parseNewspaperArgs(statement);

            if (isMatching) {
                args[0] = matcher.group(1).trim(); // isbn
                args[1] = matcher.group(2).trim(); // title
                args[2] = matcher.group(3).trim(); // publisher
                args[3] = matcher.group(5).trim(); // edition
                if (isStatusMatching) {
                    args[4] = matcher.group(7).trim(); // status
                }
                args[5] = matcher.group(4).trim(); // tag
                checkEmptyNewspaperArgs(args);
            } else {
                throw new SysLibException("Please use the format " +
                        "'add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag TAG [/s STATUS]'."
                        + SEPARATOR_LINEDIVIDER);
            }

            return args;
        } catch (IllegalStateException e) {
            throw new SysLibException("Please use the format " +
                    "'add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag TAG [/s STATUS]'." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static Boolean parseNewspaperArgs(String statement) throws SysLibException {
        parseIsbn(statement);
        parseTitle(statement);
        parsePublisher(statement);
        parseEdition(statement);

        return parseStatus(statement);
    }

    public static void checkEmptyNewspaperArgs(String[] args) throws SysLibException {
        if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty() || args[5].isEmpty()) {
            throw new SysLibException("Please enter the ISBN, title, publisher, edition, and tag." +
                    SEPARATOR_LINEDIVIDER);
        }
    }

    public static void resetNewspaperArgs() {
        args = new String[6];
    }
}
