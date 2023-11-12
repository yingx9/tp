package seedu.data.resources;

import seedu.data.Status;

import java.util.Formatter;
import java.util.List;

public class CD extends Resource {
    public static final String CD_TAG = "CD";
    private String creator;
    private String type;

    public CD(String title, String isbn, String creator, String type, int id, Status status) {
        super(title, isbn, id, status);
        setTag(CD_TAG);
        setCreator(creator);
        setType(type);
        setId(id);
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "[" + getTag() + "] " + " ID: " + getId() + " Title: " + getTitle() + " ISBN: " + getISBN()
                + " Creator: " + getCreator() + " Type: " + getType() + " Status: " + getStatus().name();
    }

    @Override
    public Formatter toTableFormat(String formatString, Formatter tableFormatter) {
        tableFormatter.format(formatString,  getId(),
                getTag(),getTitle(), getISBN(), getCreator(),
                getType(), "null", getStatus(),
                getDateReceived());
        return tableFormatter;
    }

    @Override
    public List<Integer> checkColumnsWidths(List<Integer> columnsWidth){
        columnsWidth = super.checkColumnsWidths(columnsWidth);
        int creatorLength = getCreator().length();
        int typeLength = getType().length();

        if (creatorLength > columnsWidth.get(4)) {
            columnsWidth.set(4,creatorLength+1);
        }

        if (typeLength > columnsWidth.get(5)) {
            columnsWidth.set(5,typeLength+1);
        }

        return columnsWidth;
    }
}
