package seedu.commands.events;

import seedu.commands.Command;
import seedu.commands.CommandResult;
import seedu.data.Event;
import seedu.exception.SysLibException;
import seedu.parser.Parser;

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

public class EventAddCommand extends Command {

    private static String feedbackToUser;
    private static final Logger LOGGER = Logger.getLogger(EventAddCommand.class.getName());

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
    public EventAddCommand() {
        args = new String[]{"t", "date", "desc"};
        required = new boolean[]{true, true, false};
        LOGGER.info("EventAdd Command is created");
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
        LOGGER.info("Successfully added an event");

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
            LOGGER.info("failed date parsing");
            throw new IllegalArgumentException("Please enter a valid date in the format 'dd MMM yyyy'"
                    + System.lineSeparator()
                    + "____________________________________________________________");
        }
    }

    public static String checkDate(String dateStr) throws IllegalArgumentException {
        String[] temp = dateStr.split(" ");
        if(temp.length != 3){
            LOGGER.info("failed checkDate function");
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
