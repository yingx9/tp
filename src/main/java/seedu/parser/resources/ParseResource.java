package seedu.parser.resources;

import seedu.data.Status;
import seedu.exception.SysLibException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class ParseResource {
    public static void parseIsbn(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        String pattern = "(?=.*/i ([\\d]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException("Please enter a valid ISBN." + SEPARATOR_LINEDIVIDER);
        }

        if (matcher.group(1).trim().length() != 13) {
            throw new SysLibException("Please enter a valid ISBN with 13 digits." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static void parseTitle(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        String pattern = "(?=.*/t ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException("Please enter a valid title." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static void parseAuthor(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        String pattern = "(?=.*/a ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException("Please enter a valid author." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static void parseTag(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        String pattern = "(?=.*/tag ([\\s\\w]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException("Please enter a valid tag." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static boolean parseGenre(String statement) {
        assert statement != null : "Statement should not be null";

        String pattern = "(?=.*/g ([\\w-]+(,\\s[\\w-]+)*))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        return matcher.find();
    }

    public static void parseCreator(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        String pattern = "(?=.*/c ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException("Please enter a valid creator." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static void parseType(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        String pattern = "(?=.*/ty ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException("Please enter a valid type." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static void parseBrand(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        String pattern = "(?=.*/b ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException("Please enter a valid brand." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static void parseIssue(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        String pattern = "(?=.*/is ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException("Please enter a valid issue." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static void parsePublisher(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        String pattern = "(?=.*/p ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException("Please enter a valid publisher." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static void parseEdition(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        String pattern = "(?=.*/ed ([^/]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException("Please enter a valid edition." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static void parseLink(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

        String pattern = "(?=.*/l ([^\\s]+))";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean isMatching = matcher.find();

        if (!isMatching) {
            throw new SysLibException("Please enter a valid link." + SEPARATOR_LINEDIVIDER);
        }
    }

    public static boolean parseStatus(String statement) throws SysLibException {
        assert statement != null : "Statement should not be null";

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
                throw new SysLibException("Please enter a valid status." + SEPARATOR_LINEDIVIDER);
            }
        } else {
            System.out.println("Attention: Status is not stated. Status set to default: AVAILABLE.");
        }

        return isMatching;
    }
}
