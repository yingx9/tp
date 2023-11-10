package seedu.data.resources;

import seedu.data.Status;

import java.util.Formatter;
import java.util.List;

public class EBook extends Book{
    public static final String EBOOK_TAG = "EB";
    private String link;

    public EBook(String title, String isbn, String author, String[] genres, int id, Status status, String link) {
        super(title, isbn, author, genres, id, status);
        setTag(EBOOK_TAG);
        setLink(link);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        StringBuilder genreString = new StringBuilder();
        if (getGenre()[0] == null) {
            genreString.append("-");
        } else {
            genreString.append(java.util.Arrays.toString(getGenre()).replace("[", "").replace("]", ""));
        }

        return "[" + getTag() + "] " + " ID: " + getId() + " Title: " + getTitle() + " ISBN: " + getISBN() + " Author: "
                + getAuthor() + " Genre: " + genreString + " Link: " + getLink();
    }

    @Override
    public Formatter toTableFormat(String formatString, Formatter tableFormatter) {
        tableFormatter.format(formatString,  getId(),
                getTag(),getTitle(), getISBN(), getAuthor(),
                getGenreString(), getLink(), getStatus(),
                getDateReceived());
        return tableFormatter;
    }

    public List<Integer> checkColumnsWidths(List<Integer> columnsWidth){

        int linkLength = getLink().length();

        columnsWidth = super.checkColumnsWidths(columnsWidth);

        if(linkLength > columnsWidth.get(6)){
            columnsWidth.set(6,linkLength+1);

        }
        return columnsWidth;
    }
}
