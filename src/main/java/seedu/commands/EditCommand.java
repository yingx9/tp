package seedu.commands;


import seedu.data.Book;
import seedu.data.Resource;
import seedu.data.Status;
import seedu.data.SysLibException;
import seedu.parser.Parser;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.SimpleFormatter;

import static seedu.common.Messages.formatLastLineDivider;
import static seedu.common.Messages.formatLineSeparator;

public class EditCommand extends Command{
    public static final String MISSING_ARG_MESSAGE =  formatLastLineDivider("Please provide at least" +
            " one detail to edit!");
    public static final String NOT_BOOK_ERROR =  formatLastLineDivider("Your resource is not a book!");
    public static final String RESOURCE_NOT_FOUND =  formatLastLineDivider("No such resource with given ISBN");
    public static final String EDIT_SUCCESS = formatLineSeparator("Successfully updated! Your updated resource:");

    private static final Logger EDIT_LOGGER = Logger.getLogger(EditCommand.class.getName());

    private static final FileHandler EDIT_FILEHANDLER;

    private static String feedbackToUser;

    private static int resourceIndex;


    static {
        try {
            EDIT_FILEHANDLER = new FileHandler("logs/editCommandLogs.log", true);
            EDIT_FILEHANDLER.setFormatter(new SimpleFormatter());
            EDIT_LOGGER.addHandler(EDIT_FILEHANDLER);
        } catch (IOException e){
            EDIT_LOGGER.warning("Failed to set up Logging File Handler");
            throw new RuntimeException();
        }
    }

    public EditCommand(){
        args = new String[]{"i", "t", "a", "tag", "g", "s"};
        required = new boolean[]{true, false, false, false, false, false};
    }


    @Override
    public void execute(String statement, Parser parser) throws SysLibException, IllegalArgumentException {
        feedbackToUser = "";
        EDIT_LOGGER.info("Edit Command execute with " + statement);

        String[] givenParameters = parseArgument(statement);
        validateStatement(statement, givenParameters);

        if (hasOneArg(givenParameters)){
            String givenISBN = givenParameters[0];
            Resource foundResource = findResourceByISBN(givenISBN, parser.resourceList);

            if(foundResource != null){
                Resource updatedResource = editResource(foundResource, givenParameters);
                assert updatedResource != null;
                assert resourceIndex < parser.resourceList.size();

                parser.resourceList.set(resourceIndex, updatedResource);
                feedbackToUser += EDIT_SUCCESS + formatLastLineDivider(updatedResource.toString());
                EDIT_LOGGER.info("Edit success");
            } else{
                feedbackToUser += RESOURCE_NOT_FOUND;
                EDIT_LOGGER.warning(feedbackToUser);
            }

        } else{
            EDIT_LOGGER.warning(MISSING_ARG_MESSAGE);
            throw new SysLibException(MISSING_ARG_MESSAGE);
        }

        System.out.println(feedbackToUser);
    }

    public boolean hasOneArg(String[] givenParameters){

        for (int i =1; i<givenParameters.length; i++){
            if (givenParameters[i] != null){
                return true;
            }
        }
        return false;

    }

    public Resource findResourceByISBN(String givenISBN, List<Resource> resourceList){

        Resource foundResource = null;

        for (int i=0;i < resourceList.size(); i++){

            Resource tempResource = resourceList.get(i);

            String resourceISBN = tempResource.getISBN();
            if (resourceISBN.equals(givenISBN)){
                foundResource = tempResource;
                resourceIndex = i;
                break;
            }

        }

        return foundResource;
    }

    public Resource editResource(Resource foundResource, String[] givenParameters) throws SysLibException {

        String newValue;

        for(int i=1; i<givenParameters.length;i++){

            if(givenParameters[i] == null){
                continue;
            } else{
                newValue = givenParameters[i];
            }

            switch(i){

            case 1:
                foundResource.setTitle(newValue);
                break;
            case 2:
                Book bookResource= castResourceToBook(foundResource);
                assert bookResource != null;
                bookResource.setAuthor(newValue);
                foundResource = bookResource;
                break;
            case 3:
                foundResource.setTag(newValue);
                break;
            case 4:
                Book book= castResourceToBook(foundResource);
                String[] newGenres = newValue.split(", ");
                book.setGenre(newGenres);
                foundResource = book;
                break;
            case 5:
                if (foundResource instanceof Book) {
                    Book bookStatus = castResourceToBook(foundResource);
                    bookStatus.setStatus(getStatusFromString(newValue));
                    foundResource = bookStatus;
                }
                break;
            default:
                throw new SysLibException("Input error");

            }

        }

        return foundResource;
    }


    public Book castResourceToBook(Resource resource) throws SysLibException {

        Book book;

        if (resource instanceof Book) {
            book = (Book) resource;
        } else{
            EDIT_LOGGER.warning(NOT_BOOK_ERROR);
            throw new SysLibException(NOT_BOOK_ERROR);
        }

        return book;
    }

    public Status getStatusFromString(String statusString) {
        statusString = statusString.toLowerCase().trim();
        if (statusString.equals("borrowed")) {
            return Status.BORROWED;
        } else if (statusString.equals("lost")) {
            return Status.LOST;
        } else {
            return Status.AVAILABLE;
        }
    }

}
