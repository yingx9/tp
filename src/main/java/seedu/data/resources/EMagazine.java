package seedu.data.resources;

import seedu.data.Status;

public class EMagazine extends Magazine {
    public static final String EMAGAZINE_TAG = "EM";
    private String link;

    public EMagazine(String title, String isbn, String creator, String type, int id, Status status, String link) {
        super(title, isbn, creator, type, id, status);
        setTag(EMAGAZINE_TAG);
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
        return "[" + getTag() + "] " + " ID: " + getId() + " Title: " + getTitle() + " ISBN: " + getISBN() + " Brand: "
                + getBrand() + " Issue: " + getIssue() + " Link: " + getLink();
    }
}
