package data;

public class Book extends Resource {
    private String author;
    //private String[] genres;
    private String synopsis;
    public Book(String title, String isbn, String author, String synopsis) {
        super(title, isbn);
        setTag("B");
        setAuthor(author);
        setSynopsis(synopsis);
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}

