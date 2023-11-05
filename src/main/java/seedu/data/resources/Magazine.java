package seedu.data.resources;

import seedu.data.Status;

import java.util.Formatter;

public class Magazine extends Resource {
    public static final String MAGAZINE_TAG = "M";
    private String brand;
    private String issue;

    public Magazine(String title, String isbn, String brand, String issue, int id, Status status) {
        super(title, isbn, id, status);
        setTag(MAGAZINE_TAG);
        setBrand(brand);
        setIssue(issue);
        setId(id);
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "[" + getTag() + "] " + " ID: " + getId() + " Title: " + getTitle() + " ISBN: " + getISBN() + " Brand: "
                + getBrand() + " Issue: " + getIssue() + " Status: " + getStatus().name();
    }

    @Override
    public Formatter toTableFormat(String formatString, Formatter tableFormatter) {
        tableFormatter.format(formatString,  getId(),
                getTag(),getTitle(), getISBN(), getBrand(),
                getIssue(), "null", getStatus(),
                getDateReceived());
        return tableFormatter;
    }
}
