package seedu.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import seedu.parser.Parser;
import seedu.util.TestUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SummaryCommandTest {

    private static List<Resource> testResourcesList = new ArrayList<>();
    private static List<Event> testEventsList = new ArrayList<>();
    private static final Parser parser = new Parser();
    private final TestUtil testUtil = new TestUtil();
    private final Command summaryCommand = new SummaryCommand();
    @BeforeAll
    public static void setup() throws SysLibException {
        testResourcesList = TestUtil.fillTestResourcesList();
        testEventsList = TestUtil.fillTestEventsList();
        parser.container.setResourcesList(testResourcesList);
        parser.container.setEventsList(testEventsList);
    }

    @Test
    public void testExcecute() throws SysLibException {
        executeSummaryBehaviour("");
    }

    public void executeSummaryBehaviour(String argument) throws SysLibException {
        String outputMessage = testUtil.getSummaryOutputMessage(summaryCommand, argument, parser.container);
        String expectedMessage = "Summary of Resources:" + System.lineSeparator() +
                "Total Resources: 9" + System.lineSeparator() +
                "Total Books: [████] 3" + System.lineSeparator() +
                "Total CDs: [█] 1" + System.lineSeparator() +
                "Total Magazines: [█] 1" + System.lineSeparator() +
                "Total E-Books: [█] 1" + System.lineSeparator() +
                "Total E-Magazines: [█] 1" + System.lineSeparator() +
                "Total Newspapers: [█] 1" + System.lineSeparator() +
                "Total E-Newspapers: [█] 1" + System.lineSeparator()
                + System.lineSeparator() +
                "Summary of Events:" + System.lineSeparator() +
                "Total Events: 5" + System.lineSeparator() +
                "Upcoming Events (Next 3):" + System.lineSeparator() +
                "1. New Year 2024 | 01 Jan 2024 | Happy New Year" + System.lineSeparator() +
                "2. April Fools | 01 Apr 2024 | Time for mischief" + System.lineSeparator() +
                "3. Children's Day | 01 Oct 2024 | null" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedMessage, outputMessage);

    }
    @Test
    public void testExcecutewithArguments() throws IllegalArgumentException {
        Parser parser2 = new Parser();
        assertThrows(IllegalArgumentException.class, () -> summaryCommand.execute("/t hi", parser2.container));
    }
    @Test
    public void testGenerateBar() {
        SummaryCommand summaryCommand = new SummaryCommand();
        String bar = summaryCommand.generateBar(50);
        assertEquals("[██████████]", bar);

        String bar0 = summaryCommand.generateBar(0);
        assertEquals("[]", bar0);

        String bar100 = summaryCommand.generateBar(100);
        assertEquals("[████████████████████]", bar100);

        String bar25 = summaryCommand.generateBar(25);
        assertEquals("[█████]", bar25);
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
