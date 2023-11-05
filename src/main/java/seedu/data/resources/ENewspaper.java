package seedu.data.resources;

import seedu.data.Status;

import java.util.Formatter;

public class ENewspaper extends Newspaper{
    public static final String ENEWSPAPER_TAG = "EN";
    private String link;

    public ENewspaper(String title, String isbn, String creator, String type, int id, Status status, String link) {
        super(title, isbn, creator, type, id, status);
        setTag(ENEWSPAPER_TAG);
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
        return "[" + getTag() + "] " + " ID: " + getId() + " Title: " + getTitle() + " ISBN: " + getISBN()
                + " Publisher: " + getPublisher() + " Edition: " + getEdition() + " Link: " + getLink();
    }

    @Override
    public Formatter toTableFormat(String formatString, Formatter tableFormatter) {
        tableFormatter.format(formatString,  getId(),
                getTag(),getTitle(), getISBN(), getPublisher(),
                getEdition(), getLink(), getStatus(),
                getDateReceived());
        return tableFormatter;
    }
}
