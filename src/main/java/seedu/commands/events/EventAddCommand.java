package seedu.commands.events;

import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

public class EventAddCommand extends Command {

    private static String feedbackToUser;
    public EventAddCommand() {
        args = new String[]{"t", "date", "desc"};
        required = new boolean[]{true, true, false};
    }

    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container)
            throws IllegalArgumentException, IllegalStateException, SysLibException {

        feedbackToUser = "";
        String[] values = parseArgument(statement);
        LocalDate currentDate = parseDate(values[1]);
        int index = binarySearch(container, currentDate);
        container.getEventList().add(index, new Event(values[0], currentDate, values[2]));
        System.out.println("Event inserted at: " + index);
        System.out.println("____________________________________________________________");

        return new CommandResult(feedbackToUser);
    }

    public static int binarySearch(GenericList<Resource, Event> container, LocalDate key) {
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
                    + System.lineSeparator()
                    + "____________________________________________________________");
        }
    }

    public static String checkDate(String dateStr) throws IllegalArgumentException {
        String[] temp = dateStr.split(" ");
        int first = parseInt(temp[0]);
        if(first < 10){
            return "0" + dateStr;
        }
        return dateStr;
    }
}
