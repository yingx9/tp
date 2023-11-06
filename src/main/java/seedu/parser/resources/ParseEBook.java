package seedu.parser.resources;

import seedu.exception.SysLibException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.parser.resources.ParseResource.parseIsbn;
import static seedu.parser.resources.ParseResource.parseTitle;
import static seedu.parser.resources.ParseResource.parseAuthor;
import static seedu.parser.resources.ParseResource.parseTag;
import static seedu.parser.resources.ParseResource.parseLink;
import static seedu.parser.resources.ParseResource.parseGenre;
import static seedu.parser.resources.ParseResource.parseStatus;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class ParseEBook {
    public static String[] args = new String[7];
    public static String[] parseAddEBook(String statement) throws SysLibException {
        try {
            String inputPattern = "^(?=.*/i ([a-zA-Z0-9]+))(?=.*/t ([^/]+))(?=.*/a ([^/]+))" +
                    "(?=.*/tag ([\\sa-zA-Z]+))(?=.*/g ([\\w-]+(,\\s[\\w-]+)*))?(?=.*/s ([a-zA-Z]+))?" +
                    "(?=.*/l ([^\\s]+)).*$";
            Matcher matcher = Pattern.compile(inputPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
            boolean isMatching = matcher.find();
            Boolean[] isOptionalMatching = parseEBookArgs(statement);
            if (isMatching) {
                args[0] = matcher.group(1).trim(); // isbn
                args[1] = matcher.group(2).trim(); // title
                args[2] = matcher.group(3).trim(); // author
                if (isOptionalMatching[0]) {
                    args[3] = matcher.group(5).trim(); // genre
                }
                if (isOptionalMatching[1]) {
                    args[4] = matcher.group(7).trim(); // status
                }
                args[5] = matcher.group(4).trim(); // tag
                args[6] = matcher.group(8).trim(); // link
                checkEmptyEBookArgs(args);
            } else {
                throw new SysLibException("Please use the format 'add /i ISBN /t TITLE /a AUTHOR /tag TAG /l LINK " +
                        "[/g GENRE /s STATUS]'." + SEPARATOR_LINEDIVIDER);
            }
            return args;
        } catch (IllegalStateException e) {
            throw new SysLibException("Please use the format 'add /i ISBN /t TITLE /a AUTHOR /tag TAG /l LINK " +
                    "[/g GENRE /s STATUS]'." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static Boolean[] parseEBookArgs(String statement) throws SysLibException {
        parseIsbn(statement);
        parseTitle(statement);
        parseAuthor(statement);
        parseTag(statement);
        parseLink(statement);

        return new Boolean[]{parseGenre(statement), parseStatus(statement)};
    }

    public static void checkEmptyEBookArgs(String[] args) throws SysLibException {
        if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[5].isEmpty() || args[6].isEmpty()) {
            throw new SysLibException("Please enter the ISBN, title, author, link, and tag." +
                    SEPARATOR_LINEDIVIDER);
        }
    }

    public static void resetEBookArgs() {
        args = new String[7];
    }
}
