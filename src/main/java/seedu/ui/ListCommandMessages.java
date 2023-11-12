package seedu.ui;

import static seedu.ui.MessageFormatter.formatFirstLine;
import static seedu.ui.MessageFormatter.formatLastLineDivider;

public class ListCommandMessages {

    public static final String FILTER_MESSAGE  = formatFirstLine("Listing resources matching given filters: ");
    public static final String GENERIC_MESSAGE =  formatFirstLine("Listing all resources in the Library:");
    public static final String ZERO_RESOURCES_MESSAGE =  formatLastLineDivider("There are currently 0 resources.");

    public static final String STATUS_ERROR_MESSAGE =  formatLastLineDivider("Invalid Status! Status must be: " +
            "AVAILABLE, BORROWED, OR LOST");
}
