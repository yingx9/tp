package seedu.data.resources;

import seedu.data.Status;

import java.util.Formatter;

public class Newspaper extends Resource {
    public static final String NEWSPAPER_TAG = "N";
    private String publisher;
    private String edition;

    public Newspaper(String title, String isbn, String publisher, String edition, int id, Status status) {
        super(title, isbn, id, status);
        setTag(NEWSPAPER_TAG);
        setPublisher(publisher);
        setEdition(edition);
        setId(id);
    }
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "[" + getTag() + "] " + " ID: " + getId() + " Title: " + getTitle() + " ISBN: " + getISBN()
                + " Publisher: " + getPublisher() + " Edition: " + getEdition() + " Status: " + getStatus().name();
    }

    @Override
    public Formatter toTableFormat(String formatString, Formatter tableFormatter) {
        tableFormatter.format(formatString,  getId(),
                getTag(),getTitle(), getISBN(), getPublisher(),
                getEdition(), "null", getStatus(),
                getDateReceived());
        return tableFormatter;
    }

}
