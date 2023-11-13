package seedu.commands;

import seedu.data.GenericList;
import seedu.data.events.Event;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;

import seedu.data.resources.Book;
import seedu.data.resources.CD;
import seedu.data.resources.Magazine;
import seedu.data.resources.EBook;
import seedu.data.resources.EMagazine;
import seedu.data.resources.ENewspaper;
import seedu.data.resources.Newspaper;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Comparator;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

import static seedu.ui.Messages.ASSERT_CONTAINER;
import static seedu.ui.UI.LINEDIVIDER;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;


public class SummaryCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(SummaryCommand.class.getName());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    static {
        // remove logs from showing in stdout
        try {
            Logger rootLogger = Logger.getLogger("");
            for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
                if (handler instanceof java.util.logging.ConsoleHandler) {
                    rootLogger.removeHandler(handler);
                }
            }

            FileHandler fileHandler = new FileHandler("logs/summaryCommandLogs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to set up log file handler", e);
        }
    }
    /**
     * Executes the Summary Command to retrieve and summarize information about resources and events in the system.
     *
     * @param statement  The command statement.
     * @param container  The container that holds resources and events.
     * @return A CommandResult containing the summary information.
     * @throws SysLibException If a system library exception occurs.
     */
    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container)
            throws SysLibException {

        assert container != null : ASSERT_CONTAINER;
        if (!statement.isEmpty()){
            LOGGER.warning("SummaryCommand was given arguments when none was expected");
            throw new IllegalArgumentException("'summary' command does not require arguments!"
                    + SEPARATOR_LINEDIVIDER);
        }
        LOGGER.info("Executing Summary Command.");
        int totalResources = container.getResourcesList().size();
        LOGGER.info("Retrieved resourcelist size.");
        int totalBooks = 0;
        int totalCDs = 0;
        int totalMagazines = 0;
        int totalEBooks = 0;
        int totalEMagazines = 0;
        int totalNewspapers = 0;
        int totalENewspapers = 0;
        LOGGER.info("Initialized values.");

        LOGGER.info("Counting resources...");
        for (Resource resource : container.getResourcesList()) {
            if (resource instanceof EBook) {
                totalEBooks++;
            } else if (resource instanceof CD) {
                totalCDs++;
            } else if (resource instanceof EMagazine) {
                totalEMagazines++;
            } else if (resource instanceof Book) {
                totalBooks++;
            } else if (resource instanceof Magazine) {
                totalMagazines++;
            } else if (resource instanceof ENewspaper) {
                totalENewspapers++;
            } else if (resource instanceof Newspaper) {
                totalNewspapers++;
            }
        }

        List<Event> events = container.getEventsList();
        List<Event> upcomingEvents = getUpcomingEvents(events, 3);

        LOGGER.info("Drawing graph");
        StringBuilder graph = new StringBuilder();

        int maxCount = Math.max(totalBooks, Math.max(totalCDs, Math.max(totalMagazines, Math.max(totalEBooks,
                Math.max(totalEMagazines, Math.max(totalNewspapers, totalENewspapers))))));
        int maxBarLength = 20;
        int bookBarLength = (int) (maxBarLength * ((double) totalBooks / maxCount));
        int cdBarLength = (int) (maxBarLength * ((double) totalCDs / maxCount));
        int magazineBarLength = (int) (maxBarLength * ((double) totalMagazines / maxCount));
        int eBookBarLength = (int) (maxBarLength * ((double) totalEBooks / maxCount));
        int eMagazineBarLength = (int) (maxBarLength * ((double) totalEMagazines / maxCount));
        int newspaperBarLength = (int) (maxBarLength * ((double) totalNewspapers / maxCount));
        int eNewspaperBarLength = (int) (maxBarLength * ((double) totalENewspapers / maxCount));

        graph.append("Summary of Resources:" + System.lineSeparator());
        graph.append("Total Resources: ").append(totalResources).append(System.lineSeparator());
        graph.append("Total Books: ").append(generateBar(bookBarLength)).append(" ")
                .append(totalBooks).append(System.lineSeparator());
        graph.append("Total CDs: ").append(generateBar(cdBarLength)).append(" ")
                .append(totalCDs).append(System.lineSeparator());
        graph.append("Total Magazines: ").append(generateBar(magazineBarLength)).append(" ")
                .append(totalMagazines).append(System.lineSeparator());
        graph.append("Total E-Books: ").append(generateBar(eBookBarLength)).append(" ")
                .append(totalEBooks).append(System.lineSeparator());
        graph.append("Total E-Magazines: ").append(generateBar(eMagazineBarLength)).append(" ")
                .append(totalEMagazines).append(System.lineSeparator());
        graph.append("Total Newspapers: ").append(generateBar(newspaperBarLength)).append(" ")
                .append(totalNewspapers).append(System.lineSeparator());
        graph.append("Total E-Newspapers: ").append(generateBar(eNewspaperBarLength)).append(" ")
                .append(totalENewspapers).append(System.lineSeparator());;

        LOGGER.info("Summarizing events");

        graph.append(System.lineSeparator() + "Summary of Events:" + System.lineSeparator());

        graph.append("Total Events: ").append(events.size()).append(System.lineSeparator());

        if (!events.isEmpty()) {
            graph.append("Upcoming Events (Next 3):" + System.lineSeparator());
            for (int i = 0; i < upcomingEvents.size(); i++) {
                Event event = upcomingEvents.get(i);
                graph.append(i + 1)
                        .append(". ")
                        .append(event.getName())
                        .append(" | ")
                        .append(event.getDate().format(formatter))
                        .append(" | ")
                        .append(event.getDescription())
                        .append(System.lineSeparator());
            }
        }
        graph.append(LINEDIVIDER + System.lineSeparator());

        return new CommandResult(graph.toString());
    }

    /**
     * @param count raw count of data
     * @return number of bars in square brackets based on count
     */
    public String generateBar(int count) {
        LOGGER.info("Generate bar method called");
        final int maxBarLength = 20;
        int barLength = (int) (maxBarLength * ((double) count / 100));
        StringBuilder bar = new StringBuilder();

        for (int i = 0; i < barLength; i++) {
            bar.append("█");
        }

        return "[" + bar.toString() + "]";

    }

    /**
     * @param events list of events
     * @param count number of upcoming events to return
     * @return list of upcoming events of value count.
     */
    public List<Event> getUpcomingEvents(List<Event> events, int count) {
        LOGGER.info("Getting "+ count + "upcoming events");

        LocalDate today = LocalDate.now();

        return events.stream()
                .filter(event -> event.getDate().isAfter(today))
                .sorted(Comparator.comparing(Event::getDate))
                .limit(count)
                .collect(Collectors.toList());
    }

}
