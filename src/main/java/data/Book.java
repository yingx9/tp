package data;

public class Book extends Resource {
    private String author;
    //private String synopsis;
    private String[] genres;

    public Book(String title, String isbn, String author, String[] genres) {
        super(title, isbn);
        setTag("B");
        setAuthor(author);
        //setSynopsis(synopsis);
        setGenre(genres);
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

    @Override
    public String toString() {
        StringBuilder genreString = new StringBuilder();
        if (getGenre()[0] == null) {
            genreString.append("-");
        } else {
            genreString.append(java.util.Arrays.toString(getGenre()).replace("[", "").replace("]", ""));
        }

        return "[" + getTag() + "] " + getTitle() + " ISBN: " + getISBN() + " Author: "
                + getAuthor() + " Genre: " + genreString + System.lineSeparator();
    }
}

