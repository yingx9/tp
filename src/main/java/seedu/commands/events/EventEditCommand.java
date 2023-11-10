/**
 * EventEditCommand represents a command to edit an event in a list of events.
 * It allows users to update the title, date, or description of an existing event.
 * If no changes are specified, it informs the user that nothing was edited.
 */
package seedu.commands.events;

import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class EventEditCommand extends Command {

    private static String feedbackToUser;

    private static final Logger LOGGER = Logger.getLogger(EventEditCommand.class.getName());

    static {
        // remove logs from showing in stdout
        try {
            Logger rootLogger = Logger.getLogger("");
            for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
                if (handler instanceof java.util.logging.ConsoleHandler) {
                    rootLogger.removeHandler(handler);
                }
            }

            FileHandler fileHandler = new FileHandler("logs/eventCommandLogs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to set up log file handler", e);
        }
    }

    /**
     * Constructs an EventEditCommand with default arguments and sets up logging.
     */
    public EventEditCommand() {
        args = new String[]{"i", "t", "date", "desc"};
        required = new boolean[]{true, false, false, false};
        LOGGER.info("EventEdit Command created");
    }

    /**
     * Executes the EventEditCommand to edit an event in the provided container.
     *
     * @param statement   The user's input statement.
     * @param container   The container containing events to be edited.
     * @return            A CommandResult containing feedback to the user.
     * @throws IllegalArgumentException  If the input statement or index is invalid.
     * @throws IllegalStateException     If the container is in an invalid state.
     * @throws SysLibException           If a system library exception occurs.
     */
    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container)
            throws IllegalArgumentException, IllegalStateException, SysLibException {
        LOGGER.info("Executing EventEditCommand");
        feedbackToUser = "";
        String[] values = parseArgument(statement);
        validateStatement(statement, values);

        int index = parseInt(values[0]);
        if (index < 0 || index >= container.getEventList().size()) {
            throw new IllegalArgumentException("Invalid event index" + SEPARATOR_LINEDIVIDER + "\n");
        }
        LOGGER.info("Getting old event");
        Event oldEvent = container.getEventList().get(index);

        String title = values[1] != null ? values[1] : oldEvent.getName();
        LOGGER.info("Processed title change.");

        LocalDate date = values[2] != null ? parseDate(values[2]) : oldEvent.getDate();
        LOGGER.info("Processed date change.");

        String description = values[3] != null ? values[3] : oldEvent.getDescription();
        LOGGER.info("Processed description change.");

        Event editedEvent = new Event(title, date, description);

        container.getEventList().remove(index);
        LOGGER.info("Old event removed.");

        int idx = binarySearch(container, date);
        container.getEventList().add(idx,editedEvent);
        LOGGER.info("New event added.");

        feedbackToUser = "";

        if (values[1] == null && values[2] == null && values[3] == null){
            LOGGER.info("Print nothing changed.");
            System.out.println("Event was not edited as nothing was changed." + SEPARATOR_LINEDIVIDER);
        } else {
            LOGGER.info("Print event changed.");
            if (idx != index){
                LOGGER.info("Index changed");
                System.out.println("Event index has changed as the date was changed.");
            }
            System.out.println("Event edited successfully. New event details:" + System.lineSeparator()
                    + idx + ": " + editedEvent.toString() + SEPARATOR_LINEDIVIDER);
        }

        return new CommandResult(feedbackToUser);
    }

    /**
     *
     * @param container Contains ResourceList and EventList.
     * @param key date to search for.
     * @return index to insert to.
     */
    public static int binarySearch(GenericList<Resource, Event> container, LocalDate key) {
        LOGGER.info("binary search method activated.");
        if(container.getEventList().isEmpty()){
            return 0;
        }
        int low = 0;
        int high = container.getEventList().size() - 1;

        while (low <= high) {
            int mid = (low + high)/2;
            LocalDate midVal = container.getEventList().get(mid).getDate();
            int cmp = midVal.compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return low;
    }

    /**
     * Parses a date string into a LocalDate object with a specific format.
     *
     * @param dateStr   The date string to be parsed.
     * @return          The parsed LocalDate object.
     * @throws IllegalArgumentException  If the date string is in an invalid format.
     */
    public static LocalDate parseDate(String dateStr) throws IllegalArgumentException {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd MMM yyyy")
                .toFormatter(Locale.ENGLISH)
                .withResolverStyle(ResolverStyle.SMART);

        try {

            dateStr = checkDate(dateStr);
            return LocalDate.parse(dateStr, formatter);

        } catch (DateTimeParseException e) {

            throw new IllegalArgumentException("Please enter a valid date in the format 'dd MMM yyyy'"
                    + SEPARATOR_LINEDIVIDER);

        }

    }

    /**
     * Checks and formats a date string to ensure it is in the correct format.
     *
     * @param dateStr   The date string to be checked.
     * @return          The formatted date string.
     * @throws IllegalArgumentException  If the date string is in an invalid format.
     */
    public static String checkDate(String dateStr) throws IllegalArgumentException {
        String[] temp = dateStr.split(" ");
        if(temp.length != 3){

            throw new IllegalArgumentException("Please enter a valid date in the format 'dd MMM yyyy'"
                    + SEPARATOR_LINEDIVIDER);

        }

        int first = parseInt(temp[0]);
        if(first < 10){

            return "0" + dateStr;

        }

        return dateStr;
    }

}
