package seedu.ui;

import static seedu.ui.MessageFormatter.formatSeparatorLineDivider;

public class Messages {
    public static final String ASSERT_STATEMENT = "Statement should not be null";
    public static final String ASSERT_CONTAINER = "Container should not be null";
    public static final String ASSERT_ARGUMENTS = "Arguments should not be null";
    public static final String ASSERT_ID = "ID should be greater than 0";

    public static final String ERROR_FORMAT_BOOK = formatSeparatorLineDivider("Please use the format " +
            "'add /i ISBN /t TITLE /a AUTHOR /tag TAG [/g GENRE /s STATUS]'.");
    public static final String ERROR_FORMAT_EBOOK = formatSeparatorLineDivider("Please use the format " +
            "'add /i ISBN /t TITLE /a AUTHOR /tag TAG /l LINK [/g GENRE /s STATUS]'.");
    public static final String ERROR_FORMAT_CD = formatSeparatorLineDivider("Please use the format " +
            "'add /i ISBN /t TITLE /c CREATOR /ty TYPE /tag TAG [/s STATUS]'.");
    public static final String ERROR_FORMAT_MAGAZINE = formatSeparatorLineDivider("Please use the format " +
            "'add /i ISBN /t TITLE /b BRAND /is ISSUE /tag TAG [/s STATUS]'.");
    public static final String ERROR_FORMAT_EMAGAZINE = formatSeparatorLineDivider("Please use the format " +
            "'add /i ISBN /t TITLE /b BRAND /is ISSUE /tag TAG /l LINK [/s STATUS]'.");
    public static final String ERROR_FORMAT_NEWSPAPER = formatSeparatorLineDivider("Please use the format " +
            "'add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag TAG [/s STATUS]'.");
    public static final String ERROR_FORMAT_ENEWSPAPER = formatSeparatorLineDivider("Please use the format " +
            "'add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag TAG /l LINK [/s STATUS]'.");

    public static final String ERROR_ISBN = formatSeparatorLineDivider("Please enter a valid ISBN with 13 digits.");
    public static final String ERROR_TITLE = formatSeparatorLineDivider("Please enter a valid title.");
    public static final String ERROR_AUTHOR = formatSeparatorLineDivider("Please enter a valid author.");
    public static final String ERROR_TAG = formatSeparatorLineDivider("Please enter a valid tag.");
    public static final String ERROR_GENRE = formatSeparatorLineDivider("Please enter a valid genre.");
    public static final String ERROR_CREATOR = formatSeparatorLineDivider("Please enter a valid creator.");
    public static final String ERROR_TYPE = formatSeparatorLineDivider("Please enter a valid type.");
    public static final String ERROR_BRAND = formatSeparatorLineDivider("Please enter a valid brand.");
    public static final String ERROR_ISSUE = formatSeparatorLineDivider("Please enter a valid issue.");
    public static final String ERROR_PUBLISHER = formatSeparatorLineDivider("Please enter a valid publisher.");
    public static final String ERROR_EDITION = formatSeparatorLineDivider("Please enter a valid edition.");
    public static final String ERROR_LINK = formatSeparatorLineDivider("Please enter a valid link.");
    public static final String ERROR_STATUS = formatSeparatorLineDivider("Please enter a valid status.");

    public static final String ATTENTION_STATUS = "Attention: Status is not stated. Status set to default: AVAILABLE.";
    public static final String ATTENTION_GENRE = "Attention: Genre is not stated. Genre not set.";

    public static final String ERROR_EMPTY_BOOK = formatSeparatorLineDivider("Please enter the ISBN, title, author, " +
            "and tag.");
    public static final String ERROR_EMPTY_EBOOK = formatSeparatorLineDivider("Please enter the ISBN, title, author, " +
            "link, and tag.");
    public static final String ERROR_EMPTY_CD = formatSeparatorLineDivider("Please enter the ISBN, title, creator, " +
            "type, and tag.");
    public static final String ERROR_EMPTY_MAGAZINE = formatSeparatorLineDivider("Please enter the ISBN, title, " +
            "brand, issue, and tag.");
    public static final String ERROR_EMPTY_EMAGAZINE = formatSeparatorLineDivider("Please enter the ISBN, title, " +
            "brand, issue, link, and tag.");
    public static final String ERROR_EMPTY_NEWSPAPER = formatSeparatorLineDivider("Please enter the ISBN, title, " +
            "publisher, edition, and tag.");
    public static final String ERROR_EMPTY_ENEWSPAPER = formatSeparatorLineDivider("Please enter the ISBN, title, " +
            "publisher, edition, link, and tag.");

    public static final String ERROR_DUPLICATE_ISBN = formatSeparatorLineDivider("Please enter only 1 ISBN.");
    public static final String ERROR_DUPLICATE_TITLE = formatSeparatorLineDivider("Please enter only 1 title.");
    public static final String ERROR_DUPLICATE_AUTHOR = formatSeparatorLineDivider("Please enter only 1 author.");
    public static final String ERROR_DUPLICATE_TAG = formatSeparatorLineDivider("Please enter only 1 tag.");
    public static final String ERROR_DUPLICATE_GENRE = formatSeparatorLineDivider("Please enter only 1 genre.");
    public static final String ERROR_DUPLICATE_CREATOR = formatSeparatorLineDivider("Please enter only 1 creator.");
    public static final String ERROR_DUPLICATE_TYPE = formatSeparatorLineDivider("Please enter only 1 type.");
    public static final String ERROR_DUPLICATE_BRAND = formatSeparatorLineDivider("Please enter only 1 brand.");
    public static final String ERROR_DUPLICATE_ISSUE = formatSeparatorLineDivider("Please enter only 1 issue.");
    public static final String ERROR_DUPLICATE_PUBLISHER = formatSeparatorLineDivider("Please enter only 1 publisher.");
    public static final String ERROR_DUPLICATE_EDITION = formatSeparatorLineDivider("Please enter only 1 edition.");
    public static final String ERROR_DUPLICATE_LINK = formatSeparatorLineDivider("Please enter only 1 link.");
    public static final String ERROR_DUPLICATE_STATUS = formatSeparatorLineDivider("Please enter only 1 status.");

    public static final String ERROR_UNUSED_SLASH = formatSeparatorLineDivider("Please avoid unused '/'.");
    public static final String ERROR_INVALID_ARGUMENT = formatSeparatorLineDivider(
            "Please enter only valid arguments.");
    public static final String ERROR_INVALID_GENRE_CHARACTER = formatSeparatorLineDivider(
            "'[' and ']' are not allowed in genres.");
    public static final String ERROR_INVALID_START = formatSeparatorLineDivider(
            "Please use '/' to indicate the type of data entered.");
}
