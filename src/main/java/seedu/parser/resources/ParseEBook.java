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
import static seedu.ui.Messages.ASSERT_STATEMENT;
import static seedu.ui.Messages.ASSERT_ARGUMENTS;
import static seedu.ui.Messages.ERROR_FORMAT_EBOOK;
import static seedu.ui.Messages.ERROR_EMPTY_EBOOK;

public class ParseEBook {
    public static String[] args = new String[7];
    public static String[] parseAddEBook(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        try {
            String inputPattern = "^(?=.*/i ([\\d]+))(?=.*/t ([^/]+))(?=.*/a ([^/]+))" +
                    "(?=.*/tag ([\\s\\w]+))(?=.*/g ([\\w-]+(,\\s[\\w-]+)*))?(?=.*/s ([\\w]+))?" +
                    "(?=.*/l ([^\\s]+)).*$";
            Matcher matcher = Pattern.compile(inputPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
            boolean isInputMatching = matcher.find();

            Boolean[] isOptionalsMatching = parseEBookArgs(statement);

            if (isInputMatching) {
                args[0] = matcher.group(1).trim(); // isbn
                args[1] = matcher.group(2).trim(); // title
                args[2] = matcher.group(3).trim(); // author
                if (isOptionalsMatching[0]) {
                    args[3] = matcher.group(5).trim(); // genre
                }
                if (isOptionalsMatching[1]) {
                    args[4] = matcher.group(7).trim(); // status
                }
                args[5] = matcher.group(4).trim(); // tag
                args[6] = matcher.group(8).trim(); // link

                checkEmptyEBookArgs(args);
            } else {
                throw new SysLibException(ERROR_FORMAT_EBOOK);
            }

            return args;
        } catch (IllegalStateException e) {
            throw new SysLibException(ERROR_FORMAT_EBOOK);
        }
    }

    public static Boolean[] parseEBookArgs(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        parseIsbn(statement);
        parseTitle(statement);
        parseAuthor(statement);
        parseTag(statement);
        parseLink(statement);

        return new Boolean[]{parseGenre(statement), parseStatus(statement)};
    }

    public static void checkEmptyEBookArgs(String[] args) throws SysLibException {
        assert args != null : ASSERT_ARGUMENTS;

        if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[5].isEmpty() || args[6].isEmpty()) {
            throw new SysLibException(ERROR_EMPTY_EBOOK);
        }
    }

    public static void resetEBookArgs() {
        args = new String[7];
    }
}
