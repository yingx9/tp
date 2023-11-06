package seedu.data.resources;

import seedu.data.Status;

import java.util.Formatter;
import java.util.List;

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

    @Override
    public Formatter toTableFormat(String formatString, Formatter tableFormatter, int index) {
        tableFormatter.format(formatString, index,
                getTag(),getTitle(), getISBN(), getBrand(),
                getIssue(), getLink(), getStatus(),
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
