package seedu.util;

import seedu.commands.Command;
import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Book;
import seedu.data.resources.CD;
import seedu.data.resources.EBook;
import seedu.data.resources.EMagazine;
import seedu.data.resources.ENewspaper;
import seedu.data.resources.Magazine;
import seedu.data.resources.Newspaper;
import seedu.data.resources.Resource;
import seedu.data.Status;
import seedu.exception.SysLibException;
import seedu.parser.Parser;
import seedu.commands.CommandResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static seedu.commands.Command.parseInt;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class TestUtil {


    public String getOutputMessage(Command c, String m, List<Resource> resourceList) throws SysLibException {
        Parser parser = new Parser();
        parser.container.setResourcesList(resourceList);
        CommandResult commandResult = c.execute(m, parser.container);
        return commandResult.feedbackToUser;
    }

    public String getSummaryOutputMessage(Command c, String m, GenericList<Resource, Event> container)
            throws SysLibException {
        CommandResult commandResult = c.execute(m, container);
        return commandResult.feedbackToUser;
    }

    public static String getCurrentDate(){
        LocalDateTime dateReceived = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = dateReceived.format(formatter);
        return formattedDate;
    }

    public static List<Resource> fillTestResourcesList() {
        List<Resource> testResourceList = new ArrayList<>();
        String[] genres = {"Horror", "Fiction"};
        String[] genresAdventure = {"Adventure"};
        String[] genresNull = {null};


        Book testBook = new Book("title2", "TMOBM00000001", "author", genres, 2,
                Status.AVAILABLE);
        Book testBook2 = new Book("title3", "TMOBM00000001", "author", genresAdventure, 3,
                Status.AVAILABLE);
        Book testBook3 = new Book("title3", "TMOBM00000001", "author", genresNull, 4,
                Status.AVAILABLE);

        EBook testEBook = new EBook("title4", "TMOBM00000001", "author", genresNull, 5,
                Status.AVAILABLE, "abc.com");

        Magazine testMagazine = new Magazine("title5", "TMOBM00000001", "brand", "issue", 6,
                Status.AVAILABLE);
        EMagazine testEMagazine = new EMagazine("title6", "TMOBM00000001", "brand", "issue", 7,
                Status.AVAILABLE, "def.com");

        CD testCD = new CD("title7", "TMOBM00000001", "creator", "type", 8,
                Status.AVAILABLE);

        Newspaper testNewspaper = new Newspaper("title8", "TMOBM00000001", "publisher",
                "edition", 9, Status.AVAILABLE);

        ENewspaper testENewspaper = new ENewspaper("title8", "TMOBM00000001", "publisher",
                "edition",10,Status.AVAILABLE, "xyz.com");



        testResourceList.add(testBook);
        testResourceList.add(testBook2);
        testResourceList.add(testBook3);
        testResourceList.add(testEBook);
        testResourceList.add(testMagazine);
        testResourceList.add(testEMagazine);
        testResourceList.add(testCD);
        testResourceList.add(testNewspaper);
        testResourceList.add(testENewspaper);

        return testResourceList;
    }

    public static List<Event> fillTestEventsList() {
        List<Event> testEventsList = new ArrayList<>();

        Event event1 = new Event("New Year 2024", parseDate("1 Jan 2024"), "Happy New Year");
        Event event2 = new Event("April Fools", parseDate("1 Apr 2024"), "Time for mischief");
        Event event3 = new Event("Children's Day", parseDate("1 Oct 2024"), null);
        Event event4 = new Event("Meeting", parseDate("23 Oct 2024"), "Board Meeting w CEO");
        Event event5 = new Event("New Year 2025", parseDate("1 Jan 2025"), "Happy New Year");

        testEventsList.add(event1);
        testEventsList.add(event2);
        testEventsList.add(event3);
        testEventsList.add(event4);
        testEventsList.add(event5);

        return testEventsList;
    }

    public List<Resource> addDummyResource(List<Resource> resourceList){
        Resource dummyResource = new Resource("title", "TMOBM00000001",1, Status.AVAILABLE);
        resourceList.add(dummyResource);
        List<Resource> dummyList = resourceList;
        return dummyList;
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
        if (temp.length != 3) {
            throw new IllegalArgumentException("Please enter a valid date in the format 'dd MMM yyyy'"
                    + SEPARATOR_LINEDIVIDER);
        }
        int first = parseInt(temp[0]);
        if (first < 10) {
            return "0" + dateStr;
        }
        return dateStr;
    }

}
