package seedu.commands;

import seedu.data.Book;
import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;
import seedu.ui.UI;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class FindCommand extends Command {
    private static final String INVALID_ARGUMENT_MESSAGE = "Please use the format 'find [/t TITLE OR "
            + "/i ISBN OR /a AUTHOR OR /id ID]'\n" + "____________________________________________________________";
    private static final String NO_RESOURCE_FOUND_MESSAGE = "There are no resources found matching the given filters.";
    private static final String RESOURCE_FOUND_MESSAGE = "Here are resources that matched the given filters:";

    protected String title;
    protected String author;
    protected String isbn;
    protected String id;
    protected UI ui;

    public FindCommand(){
        ui = new UI();
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setISBN(String isbn){
        this.isbn = isbn;
    }

    public String getISBN(){
        return this.isbn;
    }

    public void setID(String id){
        this.id = id;
    }

    public String getID(){
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
                throw new IllegalArgumentException(INVALID_ARGUMENT_MESSAGE + System.lineSeparator());
            }

        }

        if (this.title == null && this.author == null &&  this.isbn == null &&
                this.id == null) {
            throw new IllegalArgumentException(INVALID_ARGUMENT_MESSAGE + System.lineSeparator());
        }

        ArrayList<Resource> matchedResources = new ArrayList<>();
        for (Resource r: parser.resourceList){
            Book b = (Book) r;
            if (b.getTitle().equals(this.title) || b.getISBN().equals(this.isbn) || b.getAuthor().equals(this.author)){
                matchedResources.add(b);
            }
        }

        if (matchedResources.isEmpty()){
            System.out.println(NO_RESOURCE_FOUND_MESSAGE);
            ui.showLine();
        }else{
            System.out.println(RESOURCE_FOUND_MESSAGE);
            for (Resource r: matchedResources){
                System.out.println(r);
            }
            ui.showLine();
        }
    }

}
