package seedu.common;

import static seedu.ui.UI.LINESEPARATOR;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class Messages {

    public static String formatFirstLine(String message){
        return LINESEPARATOR + message + LINESEPARATOR + LINESEPARATOR;
    }
    public static String formatLineSeparator(String message){
        return message + LINESEPARATOR;
    }
    public static String formatLastLineDivider(String message){
        return LINESEPARATOR + message + SEPARATOR_LINEDIVIDER + LINESEPARATOR;
    }
}
