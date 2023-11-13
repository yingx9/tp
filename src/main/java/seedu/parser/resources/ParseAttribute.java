package seedu.parser.resources;

import seedu.data.Status;
import seedu.exception.SysLibException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.parser.resources.ParseResource.countDuplicate;
import static seedu.ui.Messages.ASSERT_STATEMENT;
import static seedu.ui.Messages.ERROR_ISBN;
import static seedu.ui.Messages.ERROR_TITLE;
import static seedu.ui.Messages.ERROR_AUTHOR;
import static seedu.ui.Messages.ERROR_TAG;
import static seedu.ui.Messages.ERROR_GENRE;
import static seedu.ui.Messages.ERROR_CREATOR;
import static seedu.ui.Messages.ERROR_TYPE;
import static seedu.ui.Messages.ERROR_BRAND;
import static seedu.ui.Messages.ERROR_ISSUE;
import static seedu.ui.Messages.ERROR_PUBLISHER;
import static seedu.ui.Messages.ERROR_EDITION;
import static seedu.ui.Messages.ERROR_LINK;
import static seedu.ui.Messages.ERROR_STATUS;
import static seedu.ui.Messages.ATTENTION_STATUS;
import static seedu.ui.Messages.ERROR_DUPLICATE_ISBN;
import static seedu.ui.Messages.ERROR_DUPLICATE_TITLE;
import static seedu.ui.Messages.ERROR_DUPLICATE_AUTHOR;
import static seedu.ui.Messages.ERROR_DUPLICATE_TAG;
import static seedu.ui.Messages.ERROR_DUPLICATE_GENRE;
import static seedu.ui.Messages.ERROR_DUPLICATE_CREATOR;
import static seedu.ui.Messages.ERROR_DUPLICATE_TYPE;
import static seedu.ui.Messages.ERROR_DUPLICATE_BRAND;
import static seedu.ui.Messages.ERROR_DUPLICATE_ISSUE;
import static seedu.ui.Messages.ERROR_DUPLICATE_PUBLISHER;
import static seedu.ui.Messages.ERROR_DUPLICATE_EDITION;
import static seedu.ui.Messages.ERROR_DUPLICATE_LINK;
import static seedu.ui.Messages.ERROR_DUPLICATE_STATUS;

public class ParseAttribute {
    private static final String TITLE_ARG = "/t\\s";
    private static final String AUTHOR_ARG = "/a\\s";
    private static final String TAG_ARG = "/tag\\s";
    private static final String ISBN_ARG = "/i\\s";
    private static final String GENRE_ARG = "/g\\s";
    private static final String STATUS_ARG = "/s\\s";
    private static final String LINK_ARG = "/l\\s";
    private static final String CREATOR_ARG = "/c\\s";
    private static final String BRAND_ARG = "/b\\s";
    private static final String PUBLISHER_ARG = "/p\\s";
    private static final String TYPE_ARG = "/ty\\s";
    private static final String ISSUE_ARG = "/is\\s";
    private static final String EDITION_ARG = "/ed\\s";
    private static final int ISBN_LENGTH = 13;
    public static void parseIsbn(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, ISBN_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_ISBN);
        }

        String pattern = "(?=.*/i (\\S+)(?=\\s?/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_ISBN);
        }

        boolean isDigit = matcher.group(1).trim().matches("\\d+");
        boolean isCorrectLength = matcher.group(1).trim().length() == ISBN_LENGTH;

        if (!isDigit | !isCorrectLength) {
            throw new SysLibException(ERROR_ISBN);
        }
    }

    public static void parseTitle(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, TITLE_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_TITLE);
        }

        String pattern = "(?=.*/t (.+?)(?=\\s/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_TITLE);
        }
    }

    public static void parseAuthor(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, AUTHOR_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_AUTHOR);
        }

        String pattern = "(?=.*/a (.+?)(?=\\s/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_AUTHOR);
        }
    }

    public static void parseTag(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, TAG_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_TAG);
        }

        String pattern = "(?=.*/tag (.+?)(?=\\s/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_TAG);
        }
    }

    public static boolean parseGenre(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, GENRE_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_GENRE);
        }

        String pattern = "(?=.*/g (.+?)(?=\\s/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (isMatching) {
            boolean isEmpty = matcher.group(1).trim().isEmpty();
            boolean isSpace = matcher.group(1).trim().matches("\\s+");

            if (isEmpty || isSpace) {
                throw new SysLibException(ERROR_GENRE);
            }
        }

        return matcher.find();
    }

    public static void parseCreator(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, CREATOR_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_CREATOR);
        }

        String pattern = "(?=.*/c (.+?)(?=\\s/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_CREATOR);
        }
    }

    public static void parseType(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, TYPE_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_TYPE);
        }

        String pattern = "(?=.*/ty (.+?)(?=\\s/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_TYPE);
        }
    }

    public static void parseBrand(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, BRAND_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_BRAND);
        }

        String pattern = "(?=.*/b (.+?)(?=\\s/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_BRAND);
        }
    }

    public static void parseIssue(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, ISSUE_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_ISSUE);
        }

        String pattern = "(?=.*/is (.+?)(?=\\s/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_ISSUE);
        }
    }

    public static void parsePublisher(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, PUBLISHER_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_PUBLISHER);
        }

        String pattern = "(?=.*/p (.+?)(?=\\s/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_PUBLISHER);
        }
    }

    public static void parseEdition(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, EDITION_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_EDITION);
        }

        String pattern = "(?=.*/ed (.+?)(?=\\s/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_EDITION);
        }
    }

    public static void parseLink(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, LINK_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_LINK);
        }

        String pattern = "(?=.*/l (.+?)(?=\\s/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException(ERROR_LINK);
        }
    }

    public static boolean parseStatus(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        boolean hasDuplicate = countDuplicate(statement, STATUS_ARG) > 1;
        if (hasDuplicate) {
            throw new SysLibException(ERROR_DUPLICATE_STATUS);
        }

        String pattern = "(?=.*/s (.+?)(?=\\s/|$))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (isMatching) {
            boolean isEmpty = matcher.group(1).trim().isEmpty();
            boolean isSpace = matcher.group(1).trim().matches("\\s+");

            if (isEmpty || isSpace) {
                throw new SysLibException(ERROR_STATUS);
            }

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
