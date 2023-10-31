package seedu.data;

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
}
