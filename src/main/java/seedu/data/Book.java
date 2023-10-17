package seedu.data;

public class Book extends Resource {
    private String author;
    //private String synopsis;
    private String[] genres;
    private int id;

    public Book(String title, String isbn, String author, String[] genres, int id) {
        super(title, isbn);
        setTag("B");
        setAuthor(author);
        //setSynopsis(synopsis);
        setGenre(genres);
        setId(id);
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /*public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }*/

    public String[] getGenre() {
        return genres;
    }

    public void setGenre(String[] genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder genreString = new StringBuilder();
        if (getGenre()[0] == null) {
            genreString.append("-");
        } else {
            genreString.append(java.util.Arrays.toString(getGenre()).replace("[", "").replace("]", ""));
        }

        return "[" + getTag() + "] " + " ID: " + getId() + " Title: " + getTitle() + " ISBN: " + getISBN() + " Author: "
                + getAuthor() + " Genre: " + genreString;
    }
}

