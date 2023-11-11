package seedu.parser.resources;

import seedu.data.Status;
import seedu.exception.SysLibException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.ui.Messages.ASSERT_STATEMENT;
import static seedu.ui.Messages.ERROR_ISBN;
import static seedu.ui.Messages.ERROR_TITLE;
import static seedu.ui.Messages.ERROR_AUTHOR;
import static seedu.ui.Messages.ERROR_TAG;
import static seedu.ui.Messages.ERROR_CREATOR;
import static seedu.ui.Messages.ERROR_TYPE;
import static seedu.ui.Messages.ERROR_BRAND;
import static seedu.ui.Messages.ERROR_ISSUE;
import static seedu.ui.Messages.ERROR_PUBLISHER;
import static seedu.ui.Messages.ERROR_EDITION;
import static seedu.ui.Messages.ERROR_LINK;
import static seedu.ui.Messages.ERROR_STATUS;
import static seedu.ui.Messages.ATTENTION_STATUS;

public class ParseResource {
    private static final int ISBN_LENGTH = 13;
    public static void parseIsbn(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/i ([\\d]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_ISBN);
        }

        if (matcher.group(1).trim().length() != ISBN_LENGTH) {
            throw new SysLibException(ERROR_ISBN);
        }
    }

    public static void parseTitle(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/t ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_TITLE);
        }
    }

    public static void parseAuthor(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/a ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_AUTHOR);
        }
    }

    public static void parseTag(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/tag ([\\s\\w]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_TAG);
        }
    }

    public static boolean parseGenre(String statement) {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/g ([\\w-]+(,\\s[\\w-]+)*))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        return matcher.find();
    }

    public static void parseCreator(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/c ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_CREATOR);
        }
    }

    public static void parseType(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/ty ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_TYPE);
        }
    }

    public static void parseBrand(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/b ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_BRAND);
        }
    }

    public static void parseIssue(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/is ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_ISSUE);
        }
    }

    public static void parsePublisher(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/p ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_PUBLISHER);
        }
    }

    public static void parseEdition(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/ed ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_EDITION);
        }
    }

    public static void parseLink(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/l ([^\\s]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_LINK);
        }
    }

    public static boolean parseStatus(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        String pattern = "(?=.*/s ([\\w]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (isMatching) {
            boolean isValidAvailableStatus = matcher.group(1).trim().
                    equalsIgnoreCase(String.valueOf(Status.AVAILABLE));
            boolean isValidBorrowedStatus = matcher.group(1).trim().equalsIgnoreCase(String.valueOf(Status.BORROWED));
            boolean isValidLostStatus = matcher.group(1).trim().equalsIgnoreCase(String.valueOf(Status.LOST));

            if (isValidAvailableStatus || isValidBorrowedStatus || isValidLostStatus) {
                return isMatching;
            } else {
                throw new SysLibException(ERROR_STATUS);
            }
        } else {
            System.out.println(ATTENTION_STATUS);
        }

        return isMatching;
    }
}
