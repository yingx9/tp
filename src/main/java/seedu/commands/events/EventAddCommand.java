package seedu.commands.events;

import seedu.commands.Command;
import seedu.data.Event;
import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventAddCommand extends Command {

    public EventAddCommand() {
        args = new String[]{"t", "date", "desc"};
        required = new boolean[]{true, true, false};
    }
    @Override
    public void execute(String statement, Parser parser)
            throws IllegalArgumentException, IllegalStateException, SysLibException {
        String[] values = parseArgument(statement);
        Date currentDate = parseDate(values[1]);
        int index = binarySearch(parser, currentDate);
        parser.eventList.add(index, new Event(values[0], currentDate, values[2]));
        System.out.println("Event inserted at: " + index);
        System.out.println("____________________________________________________________");
    }

    public static int binarySearch(Parser parser, Date key) {
        if(parser.eventList.isEmpty()){
            return 0;
        }
        int low = 0;
        int high = parser.eventList.size() - 1;

        while (low <= high) {
            int mid = (low + high)/2;
            Date midVal = parser.eventList.get(mid).getDate();
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

    public static Date parseDate(String date) throws IllegalArgumentException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return sdf.parse(date);
        } catch (ParseException e){
            throw new IllegalArgumentException("Please enter a valid date in the format dd-mm-yyyy"
                    + System.lineSeparator()
                    + "____________________________________________________________");
        }
    }
}
