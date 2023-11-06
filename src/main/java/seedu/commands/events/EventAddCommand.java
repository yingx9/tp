package seedu.commands.events;

import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.data.Event;
import seedu.exception.SysLibException;
import seedu.parser.Parser;

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
    public CommandResult execute(String statement, Parser parser)
            throws IllegalArgumentException, IllegalStateException, SysLibException {

        feedbackToUser = "";
        String[] values = parseArgument(statement);
        validateStatement(statement, values);
        LocalDate currentDate = parseDate(values[1]);
        int index = binarySearch(parser, currentDate);
        parser.eventList.add(index, new Event(values[0], currentDate, values[2]));
        System.out.println("Event inserted at: " + index);
        System.out.println("____________________________________________________________");

        return new CommandResult(feedbackToUser);
    }

    public static int binarySearch(Parser parser, LocalDate key) {
        if(parser.eventList.isEmpty()){
            return 0;
        }
        int low = 0;
        int high = parser.eventList.size() - 1;

        while (low <= high) {
            int mid = (low + high)/2;
            LocalDate midVal = parser.eventList.get(mid).getDate();
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
        if(temp.length != 3){
            throw new IllegalArgumentException("Please enter a valid date in the format 'dd MMM yyyy'"
                    + System.lineSeparator()
                    + "____________________________________________________________");
        }
        int first = parseInt(temp[0]);
        if(first < 10){
            return "0" + dateStr;
        }
        return dateStr;
    }

}
