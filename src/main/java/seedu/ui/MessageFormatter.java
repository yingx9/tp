package seedu.ui;

import static seedu.ui.UI.LINESEPARATOR;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

/**
 * Contains methods to format messages with commonly used line separators and dividers.
 */
public class MessageFormatter {

    public static String formatFirstLine(String message){
        return LINESEPARATOR + message + LINESEPARATOR + LINESEPARATOR;
    }
    public static String formatLineSeparator(String message){
        return message + LINESEPARATOR;
    }
    public static String formatLastLineDivider(String message){
        return LINESEPARATOR + message + SEPARATOR_LINEDIVIDER + LINESEPARATOR;
    }
    public static String formatSeparatorLineDivider(String message) {
        return message + SEPARATOR_LINEDIVIDER;
    }

    /**
     * Formats a line dashed divider with provided length.
     */
    public static String formatADivider(String dividerLength){
        return String.format(dividerLength + LINESEPARATOR, "-").replace(' ', '-');
    }
}
