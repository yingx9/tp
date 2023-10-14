package seedu.commands;

import data.Book;
import data.SysLibException;
import seedu.parser.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCommand extends Command{
    public static String[] validateAdd(String statement) throws SysLibException, IllegalStateException {
        String inputPattern = "/t (.+?) /a (.+?) /tag (.+?) /i (.+)";
        String genrePattern = "(.+) /g (.+)";

        Pattern pattern = Pattern.compile(inputPattern);
        Matcher matcher = pattern.matcher(statement);
        boolean matchFound = matcher.find();

        Pattern gPattern = Pattern.compile(genrePattern);
        Matcher gMatcher = gPattern.matcher(matcher.group(4));
        boolean gMatchFound = gMatcher.find();

        String[] args = new String[5];

        if (matchFound) {
            args[0] = matcher.group(1).trim(); // title
            args[1] = matcher.group(2).trim(); // author
            args[2] = matcher.group(3).trim(); // tag
            if (gMatchFound) {
                args[3] = gMatcher.group(1).trim(); // isbn
                args[4] = gMatcher.group(2).trim(); // genre
            } else {
                args[3] = matcher.group(4).trim(); // isbn
            }

            if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty()) {
                throw new SysLibException("Please state the title, author, tag, and ISBN.");
            }
        } else {
            throw new SysLibException("Please use the format 'add /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE]'.");
        }
        return args;
    }

    @Override
    public void execute(String statement, Parser parser) throws SysLibException, IllegalStateException {
        String[] args = validateAdd(statement);

        String title = args[0]; // title
        String author = args[1]; // author
        String tag = args[2]; // tag
        String isbn = args[3]; // isbn

        String genre;
        String[] genres = new String[1];
        if (args[4] != null) {
            genre = args[4]; // genre
            genres = genre.split(", ");
        }

        System.out.println("This book is added: " + title);
        parser.resourceList.add(new Book(title, isbn, author, genres));
    }

}
