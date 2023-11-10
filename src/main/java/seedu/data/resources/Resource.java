package seedu.data.resources;

import seedu.data.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;

public class Resource {
    private String title;
    private boolean isBorrowed;
    private Integer copies;
    private String tag;
    private String isbn;
    private int id;
    private Status status;

    private LocalDateTime dateReceived; //To keep track of when the resource was entered into the system
    

    public Resource(String title, String isbn, int id, Status status){
        setTitle(title);
        setBorrowed(false);
        setISBN(isbn);
        setCopies(1);
        setTag("");
        setId(id);
        setStatus(status);
        setReceivedDate();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateReceived(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return dateReceived.format(formatter);
    }

    public LocalDateTime getDateReceivedUnparsed(){
        return dateReceived;
    }

    public void setReceivedDate(){
        dateReceived = LocalDateTime.now();
    }

    public void setReceivedDateCustom(LocalDateTime ldt){
        dateReceived = ldt;
    }

    public String toString() {
        return "[" + tag + "] " + title;
    }

    /**
     * Constructs and returns a string of resource details formatted with given format specifiers.
     *
     * @param formatString String containing format specifiers and messages to display.
     * @param tableFormatter Formatter storing formatted string for display.
     */

    public Formatter toTableFormat(String formatString, Formatter tableFormatter) {
        tableFormatter.format(formatString, "null",
                id, title, isbn, "null",
                "null", status,
                getDateReceived());
        return tableFormatter;
    }
    public static boolean hasGenre(Resource resource, String genre){
        Book bookResource;

        if (resource instanceof Book) {
            bookResource = (Book) resource;
            String[] genres = bookResource.getGenre();
            if (genres[0] == null ){
                return false;
            }

            for (String s : genres) {
                if (s.equals(genre)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void setStatus(Status status){
        this.status = status;
    }

    public Status getStatus(){
        return status;
    }

    /**
     * Checks if attributes length are longer than the current width of columns for a table displaying its details.
     * Updates columnsWidth if it is longer to ensure alignment is correct.
     *
     * @param columnsWidth List containing column width of each resource attribute.
     */
    public List<Integer> checkColumnsWidths(List<Integer> columnsWidth){
        int titleLength = getTitle().length();
        if (titleLength > columnsWidth.get(2)){
            columnsWidth.set(2,titleLength+1);
        }
        return columnsWidth;
    }

}
