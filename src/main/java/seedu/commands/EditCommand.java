package seedu.commands;


import seedu.data.resources.Book;
import seedu.data.resources.EBook;
import seedu.data.resources.CD;

import seedu.data.resources.EMagazine;
import seedu.data.resources.ENewspaper;
import seedu.data.resources.Magazine;
import seedu.data.resources.Newspaper;
import seedu.data.resources.Resource;
import seedu.data.Status;
import seedu.exception.SysLibException;
import seedu.parser.Parser;


import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.SimpleFormatter;

import static seedu.common.FormatMessages.formatLastLineDivider;
import static seedu.common.FormatMessages.formatLineSeparator;

public class EditCommand extends Command{
    public static final String MISSING_ARG_MESSAGE =  formatLastLineDivider("Please provide at least " +
            "one detail to edit!");
    public static final String NOT_BOOK_ERROR =  formatLastLineDivider("Your resource is not a book!");
    public static final String RESOURCE_NOT_FOUND =  formatLastLineDivider("No such resource with given ISBN");
    public static final String EDIT_SUCCESS = formatLineSeparator("Successfully updated! Your updated resource:");
    private static final Logger EDIT_LOGGER = Logger.getLogger(EditCommand.class.getName());
    private static final String NOT_CD_ERROR =  formatLastLineDivider("Your resource is not a CD!");
    private static final String NOT_NEWSPAPER_ERROR = formatLastLineDivider("Your resource is not a Newspaper!");
    private static final String NOT_MAGAZINE_ERROR = formatLastLineDivider("Your resource is not a Magazine!");
    private static String feedbackToUser;

    private static int resourceIndex;

    static {

        FileHandler editFileHandler = null;
        try {
            String loggingDirectoryPath = System.getProperty("user.dir") + "/logs";
            String logFilePath = loggingDirectoryPath + "/editCommandLogs.log";
            File directory = new File(loggingDirectoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            editFileHandler = new FileHandler(logFilePath, true);

        } catch (IOException e) {
            EDIT_LOGGER.log(Level.SEVERE, "Failed to initialize edit logging handler.");
            throw new RuntimeException(e);
        }
        editFileHandler.setFormatter(new SimpleFormatter());
        EDIT_LOGGER.addHandler(editFileHandler);
    }


    public EditCommand(){
        args = new String[]{"i", "t", "a", "l", "g", "s", "c", "ty", "b", "is", "p", "ed"};
        required = new boolean[]{true, false, false, false, false, false, false, false,false,false,false,false};
    }

    @Override
    public CommandResult execute(String statement, Parser parser) throws SysLibException, IllegalArgumentException {
        feedbackToUser = "";
        EDIT_LOGGER.info("Edit Command execute with " + statement);

        String[] givenParameters = parseArgument(statement);
        validateStatement(statement, givenParameters);

        if (hasOneArg(givenParameters)) {
            String givenISBN = givenParameters[0];
            Resource foundResource = findResourceByISBN(givenISBN, parser.resourceList);

            if(foundResource != null) {
                Resource updatedResource = editResource(foundResource, givenParameters);
                assert updatedResource != null;
                assert resourceIndex < parser.resourceList.size();

                parser.resourceList.set(resourceIndex, updatedResource);
                feedbackToUser += EDIT_SUCCESS + formatLastLineDivider(updatedResource.toString());
                EDIT_LOGGER.info("Edit success");
            } else {
                feedbackToUser += RESOURCE_NOT_FOUND;
                EDIT_LOGGER.warning(feedbackToUser);
            }

        } else {
            EDIT_LOGGER.warning(MISSING_ARG_MESSAGE);
            throw new SysLibException(MISSING_ARG_MESSAGE);
        }
        return new CommandResult(feedbackToUser);
    }

    public boolean hasOneArg(String[] givenParameters){

        for (int i =1; i<givenParameters.length; i++) {
            if (givenParameters[i] != null){
                return true;
            }
        }
        return false;
    }

    public Resource findResourceByISBN(String givenISBN, List<Resource> resourceList){

        Resource foundResource = null;

        for (int i=0;i < resourceList.size(); i++) {

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

        //First we check what type of Resource and call the appropriate function for it
        //Disallow updating of tag as it completely changes the type of resource

        //All resource type needs to check Title. So we check that first
        if(givenParameters[1] != null){
            foundResource.setTitle(givenParameters[1]);
        }

        //Now we call respective edit functions based on resource type

        String resourceTag = foundResource.getTag();

        switch(resourceTag){

        case "B":
            //fallthrough
        case "EB":
            foundResource = editBook(foundResource, givenParameters);
            break;
        case "CD":
            foundResource = editCD(foundResource, givenParameters);
            break;
        case "M":
            //fallthrough
        case "EM":
            foundResource = editMagazine(foundResource, givenParameters);
            break;
        case "N":
            //fallthrough
        case "EN":
            foundResource = editNewspapers(foundResource, givenParameters);
            break;
        default:
            throw new SysLibException("Invalid Resource!");


        }

        if (givenParameters[5] != null){
            foundResource.setStatus(getStatusFromString(givenParameters[5]));
        }


        return foundResource;
    }

    private Newspaper editNewspapers(Resource foundResource, String[] givenParameters) throws SysLibException {

        //Newspapers/ENewspapers: Publisher [10], Edition [11]
        String newLink = givenParameters[3];
        String newPublisher = givenParameters[10];
        String newEdition = givenParameters[11];
        Newspaper newspaperResource;
        try {
            newspaperResource = (Newspaper) foundResource;
        } catch (ClassCastException e){
            EDIT_LOGGER.warning(NOT_NEWSPAPER_ERROR);
            throw new SysLibException(NOT_NEWSPAPER_ERROR);
        }

        if(newPublisher != null){
            newspaperResource.setPublisher(newPublisher);
        }

        if (newEdition != null){
            newspaperResource.setEdition(newEdition);
        }

        if (newspaperResource instanceof ENewspaper){
            if(newLink != null){
                ENewspaper eNewspaperResource = (ENewspaper) newspaperResource;
                eNewspaperResource.setLink(newLink);
                newspaperResource = eNewspaperResource;
            }
        }
        return newspaperResource;
    }

    private Magazine editMagazine(Resource foundResource, String[] givenParameters) throws SysLibException {
        //Magazine/EMagazine: Brand [8], Issue [9], Link [3]
        String newLink = givenParameters[3];
        String newBrand = givenParameters[8];
        String newIssue = givenParameters[9];
        Magazine magazineResource;
        try {
            magazineResource = (Magazine) foundResource;
        } catch (ClassCastException e){
            EDIT_LOGGER.warning(NOT_MAGAZINE_ERROR);
            throw new SysLibException(NOT_MAGAZINE_ERROR);
        }

        if(newBrand != null){
            magazineResource.setBrand(newBrand);
        }

        if (newIssue != null){
            magazineResource.setIssue(newIssue);
        }

        if (magazineResource instanceof EMagazine){
            if(newLink != null){
                EMagazine eMagazineResource = (EMagazine) magazineResource;
                eMagazineResource.setLink(newLink);
                magazineResource = eMagazineResource;
            }
        }
        return magazineResource;
    }

    private CD editCD(Resource foundResource, String[] givenParameters) throws SysLibException {

        //CD: Creator [6], Type [7]

        String newCreator = givenParameters[6];
        String newType = givenParameters[7];
        CD cdResource;
        try {
            cdResource= (CD) foundResource;
        } catch (ClassCastException e){
            EDIT_LOGGER.warning(NOT_CD_ERROR);
            throw new SysLibException(NOT_CD_ERROR);
        }

        if(newCreator != null){
            cdResource.setCreator(newCreator);
        }

        if (newType != null){
            cdResource.setType(newType);
        }
        return cdResource;
    }

    private Book editBook(Resource foundResource, String[] givenParameters) throws SysLibException {
        //Book/eBook: Author [2], Genres [4], Link [3]

        String newAuthor = givenParameters[2];
        String newLink = givenParameters[3];
        Book bookResource;
        try {
            bookResource= (Book) foundResource;
        } catch (ClassCastException e){
            EDIT_LOGGER.warning(NOT_BOOK_ERROR);
            throw new SysLibException(NOT_BOOK_ERROR);
        }


        if(newAuthor != null){
            bookResource.setAuthor(newAuthor);
        }

        if (givenParameters[4] != null){
            String[] newGenres = givenParameters[4].split(", ");
            bookResource.setGenre(newGenres);
        }

        if (bookResource instanceof EBook){
            if(newLink != null){
                EBook eBookResource = (EBook) bookResource;
                eBookResource.setLink(newLink);
                bookResource = eBookResource;
            }
        }

        return bookResource;

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
