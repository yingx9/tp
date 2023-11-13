package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.data.events.Event;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SummaryCommandTest {

    

    @Test
    public void testGenerateBar() throws UnsupportedEncodingException {
        SummaryCommand summaryCommand = new SummaryCommand();
        String bar = summaryCommand.generateBar(50);
        assertEquals("[▓▓▓▓▓▓▓▓▓▓]", bar);

        String bar0 = summaryCommand.generateBar(0);
        assertEquals("[]", bar0);

        String bar100 = summaryCommand.generateBar(100);
        assertEquals("[▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓]", bar100);

        String bar25 = summaryCommand.generateBar(25);
        assertEquals("[▓▓▓▓▓]", bar25);
    }

    @Test
    public void testGetUpcomingEvents() {
        SummaryCommand summaryCommand = new SummaryCommand();

        // Create a list of events for testing
        List<Event> events = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate nextWeek = today.plusWeeks(1);

        events.add(new Event("Event 1", today, "Description 1"));
        events.add(new Event("Event 2", tomorrow, "Description 2"));
        events.add(new Event("Event 3", nextWeek, "Description 3"));

        // Test for 0 upcoming events
        List<Event> upcomingEvents0 = summaryCommand.getUpcomingEvents(events, 0);
        assertEquals(0, upcomingEvents0.size());

        // Test for 2 upcoming events
        List<Event> upcomingEvents2 = summaryCommand.getUpcomingEvents(events, 2);
        assertEquals(2, upcomingEvents2.size());
        assertEquals("Event 2", upcomingEvents2.get(0).getName());
        assertEquals("Event 3", upcomingEvents2.get(1).getName());

        // Test for 4 upcoming events when 3 available
        List<Event> upcomingEvents4 = summaryCommand.getUpcomingEvents(events, 4);
        assertEquals(2, upcomingEvents4.size());
    }


}
