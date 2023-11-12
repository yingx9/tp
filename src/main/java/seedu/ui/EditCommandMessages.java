package seedu.ui;

import static seedu.ui.MessageFormatter.formatLastLineDivider;
import static seedu.ui.MessageFormatter.formatLineSeparator;

public class EditCommandMessages {

    public static final String INVALID_EDIT_ARGS =  formatLineSeparator("Invalid edit arguments!");
    public static final String NEWSPAPERS_ARGS_MESSAGE =
            "For Newspapers: /t TITLE /p PUBLISHER /ed EDITION /s STATUS /i ISBN" + formatLastLineDivider("For " +
                    "ENewspapers: /t TITLE /p PUBLISHER /ed EDITION /s STATUS " + "/l LINK /i ISBN");
    public static final String BOOK_ARGS_MESSAGE = "For Books: /t TITLE /a AUTHOR /g GENRES /s STATUS /i ISBN" +
            formatLastLineDivider("For EBooks: /t TITLE /a AUTHOR /g GENRES /s STATUS /l LINK /i ISBN");
    public static final String CD_ARGS_MESSAGE = formatLastLineDivider("For CDs: /t TITLE /c CREATOR" +
            " /ty TYPE /s STATUS /i ISBN");
    public static final String MAGAZINE_ARGS_MESSAGE = "For Magazines: /t TITLE /b BRAND /is ISSUE /s STATUS /i ISBN" +
            formatLastLineDivider("For EMagazines: /t TITLE /b BRAND /is ISSUE /s STATUS /l LINK /i ISBN");

    public static final String EDIT_SUCCESS = formatLineSeparator("Successfully updated! Your updated resource:");
    public static final String MISSING_ARG_MESSAGE =  formatLineSeparator("Please provide at least " +
        "one detail to edit!") + BOOK_ARGS_MESSAGE + MAGAZINE_ARGS_MESSAGE
            + CD_ARGS_MESSAGE + NEWSPAPERS_ARGS_MESSAGE;
    public static final String NOT_BOOK_ERROR =  formatLastLineDivider("Your resource is not a book!");
    public static final String RESOURCE_NOT_FOUND =  formatLastLineDivider("No such resource with given ID");
    public static final String NOT_CD_ERROR =  formatLastLineDivider("Your resource is not a CD!");
    public static final String NOT_NEWSPAPER_ERROR = formatLastLineDivider("Your resource is not a Newspaper!");
    public static final String NOT_MAGAZINE_ERROR = formatLastLineDivider("Your resource is not a Magazine!");
}
