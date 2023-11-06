package seedu.data;

import seedu.data.resources.Book;
import seedu.data.resources.Magazine;
import seedu.data.resources.Newspaper;
import seedu.data.resources.EBook;
import seedu.data.resources.EMagazine;
import seedu.data.resources.ENewspaper;
import seedu.data.resources.CD;
import seedu.exception.SysLibException;

import static seedu.parser.Parser.getStatusFromString;

public class CreateResource {
    public static Book createBook(String[] args, int id) throws IllegalStateException, NumberFormatException,
            SysLibException {
        String isbn = args[0];
        String title = args[1];
        String author = args[2];
        Status status = getStatusFromString(args[4]);

        String genre;
        String[] genres = new String[1];
        if (args[3] != null) {
            genre = args[3];
            genres = genre.split(", ");
        }

        return new Book(title, isbn, author, genres, id, status);
    }

    public static EBook createEBook(String[] args, int id) throws IllegalStateException, NumberFormatException,
            SysLibException {
        String isbn = args[0];
        String title = args[1];
        String author = args[2];
        Status status = getStatusFromString(args[4]); // Get the status from the provided string
        String link = args[6];

        String genre;
        String[] genres = new String[1];
        if (args[3] != null) {
            genre = args[3];
            genres = genre.split(", ");
        }

        return new EBook(title, isbn, author, genres, id, status, link);
    }

    public static CD createCD(String[] args, int id) throws IllegalStateException, NumberFormatException {
        String isbn = args[0];
        String title = args[1];
        String creator = args[2];
        String type = args[3];
        Status status = getStatusFromString(args[4]);

        return new CD(title, isbn, creator, type, id, status);
    }

    public static Magazine createMagazine(String[] args, int id) throws IllegalStateException, NumberFormatException {
        String isbn = args[0];
        String title = args[1];
        String brand = args[2];
        String issue = args[3];
        Status status = getStatusFromString(args[4]);

        return new Magazine(title, isbn, brand, issue, id, status);
    }

    public static Magazine createEMagazine(String[] args, int id) throws IllegalStateException, NumberFormatException {
        String isbn = args[0];
        String title = args[1];
        String brand = args[2];
        String issue = args[3];
        Status status = getStatusFromString(args[4]);
        String link = args[6];

        return new EMagazine(title, isbn, brand, issue, id, status, link);
    }

    public static Newspaper createNewspaper(String[] args, int id) throws IllegalStateException,
            NumberFormatException {
        String isbn = args[0];
        String title = args[1];
        String publisher = args[2];
        String edition = args[3];
        Status status = getStatusFromString(args[4]);

        return new Newspaper(title, isbn, publisher, edition, id, status);
    }

    public static ENewspaper createENewspaper(String[] args, int id) throws IllegalStateException,
            NumberFormatException {
        String isbn = args[0];
        String title = args[1];
        String publisher = args[2];
        String edition = args[3];
        Status status = getStatusFromString(args[4]);
        String link = args[6];

        return new ENewspaper(title, isbn, publisher, edition, id, status, link);
    }
}
