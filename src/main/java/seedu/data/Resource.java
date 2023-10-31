package seedu.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Resource {
    private String title;
    private boolean isBorrowed;
    private Integer copies;
    private String tag;
    private String isbn;
    private Status status;

    private LocalDateTime dateReceived; //To keep track of when the resource was entered into the system
    

    public Resource(String title, String isbn, Status status){
        setTitle(title);
        setBorrowed(false);
        setISBN(isbn);
        setCopies(1);
        setTag("");
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

    public String getDateReceived(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return dateReceived.format(formatter);
    }

    public void setReceivedDate(){
        dateReceived = LocalDateTime.now();
    }

    public String toString() {
        return "[" + tag + "] " + title;
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
}
