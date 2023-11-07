package seedu.commands;

import seedu.data.resources.Resource;
import seedu.parser.Parser;

import seedu.data.resources.Book;
import seedu.data.resources.CD;
import seedu.data.resources.Magazine;
import seedu.data.resources.EBook;
import seedu.data.resources.EMagazine;
import seedu.data.resources.ENewspaper;
import seedu.data.resources.Newspaper;


public class SummaryCommand extends Command {
    @Override
    public CommandResult execute(String statement, Parser parser) {
        int totalResources = parser.getResourceList().size();
        int totalBooks = 0;
        int totalCDs = 0;
        int totalMagazines = 0;
        int totalEBooks = 0;
        int totalEMagazines = 0;
        int totalNewspapers = 0;
        int totalENewspapers = 0;

        for (Resource resource : parser.getResourceList()) {
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

        StringBuilder graph = new StringBuilder();
        graph.append("Summary of Resources:\n");
        graph.append("Total Resources: ").append(totalResources).append("\n");

        // Create a bar graph representation
        graph.append("Total Books: ").append(generateBar(totalBooks)).append(" ").append(totalBooks).append("\n");
        graph.append("Total CDs: ").append(generateBar(totalCDs)).append(" ").append(totalCDs).append("\n");
        graph.append("Total Magazines: ").append(generateBar(totalMagazines)).append(" ").append(totalMagazines).append("\n");
        graph.append("Total E-Books: ").append(generateBar(totalEBooks)).append(" ").append(totalEBooks).append("\n");
        graph.append("Total E-Magazines: ").append(generateBar(totalEMagazines)).append(" ").append(totalEMagazines).append("\n");
        graph.append("Total Newspapers: ").append(generateBar(totalNewspapers)).append(" ").append(totalNewspapers).append("\n");
        graph.append("Total E-Newspapers: ").append(generateBar(totalENewspapers)).append(" ").append(totalENewspapers);

        String summaryMessage = "Summary of Resources:\n" +
                "Total Resources: " + totalResources + "\n" +
                "Total Books: " + totalBooks + "\n" +
                "Total CDs: " + totalCDs + "\n" +
                "Total Magazines: " + totalMagazines + "\n" +
                "Total E-Books: " + totalEBooks + "\n" +
                "Total E-Magazines: " + totalEMagazines + "\n" +
                "Total Newspapers: " + totalNewspapers + "\n" +
                "Total E-Newspapers: " + totalENewspapers;

        return new CommandResult(summaryMessage);
    }
    private String generateBar(int count) {
        final int maxBarLength = 20; // Maximum length of the bar
        int barLength = (int) (maxBarLength * ((double) count / 100)); // Adjust for bar length
        StringBuilder bar = new StringBuilder();

        for (int i = 0; i < barLength; i++) {
            bar.append("█"); // Use "█" to represent bars
        }

        return "[" + bar.toString() + "]";
    }
}
