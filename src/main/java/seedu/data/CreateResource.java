package seedu.data;

import seedu.data.resources.*;

import static seedu.parser.Parser.getStatusFromString;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

public class CreateResource {
    public static Book createBook(String[] args) throws IllegalStateException, NumberFormatException {
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter a valid id." + SEPARATOR_LINEDIVIDER);
        }

        String title = args[1];
        String author = args[2];
        String isbn = args[4];
        Status status = getStatusFromString(args[6]);


        String genre;
        String[] genres = new String[1];
        if (args[5] != null) {
            genre = args[5];
            genres = genre.split(", ");
        }

        return new Book(title, isbn, author, genres, id, status);
    }

    public static EBook createEBook(String[] args) throws IllegalStateException, NumberFormatException {
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter a valid id." + SEPARATOR_LINEDIVIDER);
        }

        String title = args[1];
        String author = args[2];
        String isbn = args[4];
        Status status = getStatusFromString(args[6]); // Get the status from the provided string
        String link = args[7];

        String genre;
        String[] genres = new String[1];
        if (args[5] != null) {
            genre = args[5];
            genres = genre.split(", ");
        }

        return new EBook(title, isbn, author, genres, id, status, link);
    }

    public static CD createCD(String[] args) throws IllegalStateException, NumberFormatException {
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter a valid id." + SEPARATOR_LINEDIVIDER);
        }

        String title = args[1];
        String creator = args[8];
        String isbn = args[4];
        Status status = getStatusFromString(args[6]);
        String type = args[11];

        return new CD(title, isbn, creator, type, id, status);
    }

    public static Magazine createMagazine(String[] args) throws IllegalStateException, NumberFormatException {
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter a valid id." + SEPARATOR_LINEDIVIDER);
        }

        String title = args[1];
        String brand = args[9];
        String isbn = args[4];
        Status status = getStatusFromString(args[6]);
        String issue = args[12];

        return new Magazine(title, isbn, brand, issue, id, status);
    }

    public static Magazine createEMagazine(String[] args) throws IllegalStateException, NumberFormatException {
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter a valid id." + SEPARATOR_LINEDIVIDER);
        }

        String title = args[1];
        String brand = args[9];
        String isbn = args[4];
        Status status = getStatusFromString(args[6]);
        String issue = args[12];
        String link = args[7];

        return new EMagazine(title, isbn, brand, issue, id, status, link);
    }

    public static Newspaper createNewspaper(String[] args) throws IllegalStateException, NumberFormatException {
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter a valid id." + SEPARATOR_LINEDIVIDER);
        }

        String title = args[1];
        String publisher = args[10];
        String isbn = args[4];
        Status status = getStatusFromString(args[6]);
        String edition = args[13];

        return new Newspaper(title, isbn, publisher, edition, id, status);
    }

    public static ENewspaper createENewspaper(String[] args) throws IllegalStateException, NumberFormatException {
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter a valid id." + SEPARATOR_LINEDIVIDER);
        }

        String title = args[1];
        String publisher = args[10];
        String isbn = args[4];
        Status status = getStatusFromString(args[6]);
        String edition = args[13];
        String link = args[7];

        return new ENewspaper(title, isbn, publisher, edition, id, status, link);
    }
}
