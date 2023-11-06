package seedu.parser.Resources;

import seedu.exception.SysLibException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.parser.Resources.ParseResource.*;
import static seedu.parser.Resources.ParseResource.parseStatus;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class ParseENewspaper {
    public static String[] args = new String[7];
    public static String[] parseAddENewspaper(String statement) throws SysLibException {
        try {
            String inputPattern = "^(?=.*/i ([a-zA-Z0-9]+))(?=.*/t ([^/]+))(?=.*/p ([^/]+))" +
                    "(?=.*/tag ([\\sa-zA-Z]+))(?=.*/ed ([^/]+))(?=.*/s ([a-zA-Z]+))?(?=.*/l ([^\\s]+)).*$";
            Matcher matcher = Pattern.compile(inputPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
            boolean isMatching = matcher.find();

            Boolean isStatusMatching = parseEMagazineArgs(statement);
            if (isMatching) {
                args[0] = matcher.group(1).trim(); // isbn
                args[1] = matcher.group(2).trim(); // title
                args[2] = matcher.group(3).trim(); // publisher
                args[3] = matcher.group(5).trim(); // edition
                if (isStatusMatching) {
                    args[4] = matcher.group(6).trim(); // status
                }
                args[5] = matcher.group(4).trim(); // tag
                args[6] = matcher.group(7).trim(); // link
                checkEmptyENewspaperArgs(args);
            } else {
                throw new SysLibException("Please use the format " +
                        "'add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag TAG /l LINK [/s STATUS]'."
                        + SEPARATOR_LINEDIVIDER);
            }
            return args;
        } catch (IllegalStateException e) {
            throw new SysLibException("Please use the format " +
                    "'add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag TAG /l LINK [/s STATUS]'."
                    + SEPARATOR_LINEDIVIDER);
        }
    }

    public static Boolean parseEMagazineArgs(String statement) throws SysLibException {
        parseIsbn(statement);
        parseTitle(statement);
        parsePublisher(statement);
        parseEdition(statement);
        parseLink(statement);

        return parseStatus(statement);
    }

    public static void checkEmptyENewspaperArgs(String[] args) throws SysLibException {
        if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty() || args[5].isEmpty()
                || args[6].isEmpty()) {
            throw new SysLibException("Please enter the ISBN, title, publisher, edition, link, and tag." +
                    SEPARATOR_LINEDIVIDER);
        }
    }

    public static void resetENewspaperArgs() {
        args = new String[7];
    }
}
