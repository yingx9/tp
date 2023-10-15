package seedu.commands;

import seedu.data.Book;
import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class FindCommand extends Command {
    protected String title;
    protected String author;
    protected String isbn;
    protected String id;

    public FindCommand(){}

    private void setTitle(String title){
        this.title = title;
    }

    private String getTitle(){
        return this.title;
    }

    private void setAuthor(String author){
        this.author = author;
    }

    private String getAuthor(){
        return this.author;
    }

    private void setISBN(String isbn){
        this.isbn = isbn;
    }

    private String getISBN(){
        return this.isbn;
    }

    private void setID(String id){
        this.id = id;
    }

    private String getID(){
        return this.id;
    }

    @Override
    public void execute(String statement, Parser parser) throws IllegalArgumentException, SysLibException {
        Matcher matcher = parser.parseFindCommand(statement);

        while (matcher.find()) {
            String flag = matcher.group(1);
            String value = matcher.group(2);
            switch (flag) {
            case "t":
                this.setTitle(value);
                break;
            case "a":
                this.setAuthor(value);
                break;
            case "i":
                this.setISBN(value);
                break;
            case "id":
                this.setID(value);
                break;
            default:
                throw new IllegalArgumentException("Please use the format 'find [/t TITLE OR " +
                "/i ISBN OR /a AUTHOR OR /id ID]'");
            }

        }

        if (this.title == null && this.author == null &&  this.isbn == null &&
                this.id == null) {
            throw new IllegalArgumentException("Please use the format 'find [/t TITLE OR " +
                    "/i ISBN OR /a AUTHOR OR /id ID]'");
        }

        ArrayList<Resource> matchedResources = new ArrayList<>();
        for (Resource r: parser.resourceList){
            Book b = (Book) r;
            if (b.getTitle().equals(this.title) || b.getISBN().equals(this.isbn) || b.getAuthor().equals(this.author)){
                matchedResources.add(b);
            }
        }

        if (matchedResources.isEmpty()){
            System.out.println("There are no resources found matching the given filters.");
        }else{
            System.out.println("Here are resources that matched the given filters: ");
            for (Resource r: matchedResources){
                System.out.println(r);
            }
        }
    }

}
