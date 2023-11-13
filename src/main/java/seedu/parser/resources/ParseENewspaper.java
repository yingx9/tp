package seedu.parser.resources;

import seedu.exception.SysLibException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.commands.AddCommand.ISBN_OPTION;
import static seedu.commands.AddCommand.TITLE_OPTION;
import static seedu.commands.AddCommand.PUBLISHER_OPTION;
import static seedu.commands.AddCommand.TAG_OPTION;
import static seedu.commands.AddCommand.EDITION_OPTION;
import static seedu.commands.AddCommand.STATUS_OPTION;
import static seedu.commands.AddCommand.LINK_OPTION;
import static seedu.parser.resources.ParseAttribute.parseIsbn;
import static seedu.parser.resources.ParseAttribute.parseTitle;
import static seedu.parser.resources.ParseAttribute.parsePublisher;
import static seedu.parser.resources.ParseAttribute.parseEdition;
import static seedu.parser.resources.ParseAttribute.parseLink;
import static seedu.parser.resources.ParseAttribute.parseStatus;
import static seedu.parser.resources.ParseResource.ISBN_ARG;
import static seedu.parser.resources.ParseResource.TITLE_ARG;
import static seedu.parser.resources.ParseResource.PUBLISHER_ARG;
import static seedu.parser.resources.ParseResource.TAG_ARG;
import static seedu.parser.resources.ParseResource.EDITION_ARG;
import static seedu.parser.resources.ParseResource.STATUS_ARG;
import static seedu.parser.resources.ParseResource.LINK_ARG;
import static seedu.parser.resources.ParseResource.hasUnusedSlash;
import static seedu.ui.Messages.ASSERT_STATEMENT;
import static seedu.ui.Messages.ASSERT_ARGUMENTS;
import static seedu.ui.Messages.ERROR_FORMAT_ENEWSPAPER;
import static seedu.ui.Messages.ERROR_UNUSED_SLASH;
import static seedu.ui.Messages.ERROR_INVALID_ARGUMENT;
import static seedu.ui.Messages.ERROR_EMPTY_ENEWSPAPER;


public class ParseENewspaper {
    public static String[] args = new String[7];
    public static String[] parseAddENewspaper(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        try {
            String inputPattern = "^(?=.*/i (.+?)(?=\\s/|$))(?=.*/t (.+?)(?=\\s/|$))(?=.*/p (.+?)(?=\\s/|$))" +
                    "(?=.*/tag (.+?)(?=\\s/|$))(?=.*/ed (.+?)(?=\\s/|$))(?=.*/s (.+?)(?=\\s/|$))?" +
                    "(?=.*/l (.+?)(?=\\s/|$)).*$";
            Matcher matcher = Pattern.compile(inputPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
            boolean isInputMatching = matcher.find();

            Boolean isStatusMatching = parseEMagazineArgs(statement);

            if (isInputMatching) {
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
                throw new SysLibException(ERROR_FORMAT_ENEWSPAPER);
            }

            return args;
        } catch (IllegalStateException e) {
            throw new SysLibException(ERROR_FORMAT_ENEWSPAPER);
        }
    }

    public static Boolean parseEMagazineArgs(String statement) throws SysLibException {
        assert statement != null : ASSERT_STATEMENT;

        if (hasUnusedSlash(statement)) {
            throw new SysLibException(ERROR_UNUSED_SLASH);
        }

        if (hasInvalidArgument(statement)) {
            throw new SysLibException(ERROR_INVALID_ARGUMENT);
        }

        parseIsbn(statement);
        parseTitle(statement);
        parsePublisher(statement);
        parseEdition(statement);
        parseLink(statement);

        return parseStatus(statement);
    }

    public static boolean hasInvalidArgument(String statement) {

        ArrayList<String> inputArgs = new ArrayList<String>();

        String pattern = "\\s/(\\S+)";
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        while(matcher.find()) {
            inputArgs.add(matcher.group(1).trim());
        }

        for (String inputArg : inputArgs) {
            boolean isValidArg = Objects.equals(inputArg, ISBN_OPTION) ||
                    Objects.equals(inputArg, TITLE_OPTION) ||
                    Objects.equals(inputArg, PUBLISHER_OPTION) ||
                    Objects.equals(inputArg, EDITION_OPTION) ||
                    Objects.equals(inputArg, TAG_OPTION) ||
                    Objects.equals(inputArg, STATUS_OPTION) ||
                    Objects.equals(inputArg, LINK_OPTION);

            if (!isValidArg) {
                return true;
            }
        }

        return false;
    }

    public static void checkEmptyArg(String[] args) throws SysLibException {
        for (String arg : args) {
            if (arg != null) {
                boolean hasValidArg = arg.contains(ISBN_ARG) ||
                        arg.contains(TITLE_ARG) ||
                        arg.contains(PUBLISHER_ARG) ||
                        arg.contains(EDITION_ARG) ||
                        arg.contains(TAG_ARG) ||
                        arg.contains(STATUS_ARG) ||
                        arg.contains(LINK_ARG);

                if (hasValidArg) {
                    throw new SysLibException(ERROR_FORMAT_ENEWSPAPER);
                }
            }
        }
    }

    public static void checkEmptyENewspaperArgs(String[] args) throws SysLibException {
        assert args != null : ASSERT_ARGUMENTS;

        checkEmptyArg(args);

        if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty() || args[5].isEmpty()
                || args[6].isEmpty()) {
            throw new SysLibException(ERROR_EMPTY_ENEWSPAPER);
        }
    }

    public static void resetENewspaperArgs() {
        args = new String[7];
    }
}
