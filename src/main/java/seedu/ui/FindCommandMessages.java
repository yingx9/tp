package seedu.ui;

public class FindCommandMessages {
    public static final String INVALID_ARGUMENT_MESSAGE = "Please use the format 'find [/t TITLE OR "
            + "/i ISBN OR /a AUTHOR/PUBLISHER/BRAND/CREATOR OR /id ID]'" + System.lineSeparator()
            + "____________________________________________________________";
    public static final String NO_RESOURCE_FOUND_MESSAGE = "There are no resources found matching the given filters.";
    public static final String RESOURCE_FOUND_MESSAGE = "Here are resources that matched the given filters:";
}
