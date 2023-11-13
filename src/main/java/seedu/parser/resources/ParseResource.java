package seedu.parser.resources;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseResource {
    public static final String TITLE_ARG = "/t ";
    public static final String AUTHOR_ARG = "/a ";
    public static final String TAG_ARG = "/tag ";
    public static final String ISBN_ARG = "/i ";
    public static final String GENRE_ARG = "/g ";
    public static final String STATUS_ARG = "/s ";
    public static final String LINK_ARG = "/l ";
    public static final String CREATOR_ARG = "/c ";
    public static final String BRAND_ARG = "/b ";
    public static final String PUBLISHER_ARG = "/p ";
    public static final String TYPE_ARG = "/ty ";
    public static final String ISSUE_ARG = "/is ";
    public static final String EDITION_ARG = "/ed ";

    public static int countDuplicate(String statement, String pattern) {
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(statement);

        int count = 0;
        while(matcher.find()) {
            count++;
        }

        return count;
    }

    public static boolean hasUnusedSlash(String statement) {
        String begPattern = "^/\\s";
        Matcher begMatcher = Pattern.compile(begPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean hasBegSlash = begMatcher.find();

        String midPattern = "\\s/\\s";
        Matcher midMatcher = Pattern.compile(midPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean hasMidSlash = midMatcher.find();

        String etyPattern = "/\\s";
        Matcher etyMatcher = Pattern.compile(etyPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean hasEtySlash = etyMatcher.find();

        String endPattern = "/$";
        Matcher endMatcher = Pattern.compile(endPattern, Pattern.CASE_INSENSITIVE).matcher(statement);
        boolean hasEndSlash = endMatcher.find();

        return hasBegSlash | hasMidSlash | hasEtySlash | hasEndSlash;
    }
}
