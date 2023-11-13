package seedu.data.resources;

import seedu.data.Status;

import java.util.Formatter;
import java.util.List;

public class Book extends Resource {
    public static final String BOOK_TAG = "B";
    private String author;
    private String[] genres;

    public Book(String title, String isbn, String author, String[] genres, int id, Status status) {
        super(title, isbn, id, status);
        setTag(BOOK_TAG);
        setAuthor(author);
        setGenre(genres);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String[] getGenre() {
        return genres;
    }

    public void setGenre(String[] genres) {
        this.genres = genres;
    }

    public String getGenreString() {
        return String.join(", ", genres);
    }

    @Override
    public String toString() {
        StringBuilder genreString = new StringBuilder();
        if (getGenre()[0] == null) {
            genreString.append("-");
        } else {
            genreString.append(java.util.Arrays.toString(getGenre()).replace("[", "").
                    replace("]", ""));
        }

        return "[" + getTag() + "] " + " ID: " + getId() + " Title: " + getTitle() + " ISBN: " + getISBN() + " Author: "
                + getAuthor() + " Genre: " + genreString + " Status: " + getStatus().name() + " Received Date: " +
                getDateReceived();
    }

    @Override
    public Formatter toTableFormat(String formatString, Formatter tableFormatter) {

        tableFormatter.format(formatString,  getId(),
                getTag(),getTitle(), getISBN(), getAuthor(),
                getGenreString(), "null", getStatus(),
                getDateReceived());
        return tableFormatter;
    }

    @Override
    public List<Integer> checkColumnsWidths(List<Integer> columnsWidth) {

        columnsWidth = super.checkColumnsWidths(columnsWidth);
        int authorLength = getAuthor().length();
        int genreLength = getGenreString().length();

        if (authorLength > columnsWidth.get(4)) {
            columnsWidth.set(4,authorLength+1);
        }

        if (genreLength > columnsWidth.get(5)) {
            columnsWidth.set(5,genreLength+1);
        }

        return columnsWidth;
    }

}

