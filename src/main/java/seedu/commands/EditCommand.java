package seedu.commands;


import seedu.data.GenericList;
import seedu.data.events.Event;
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


import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.SimpleFormatter;


import static seedu.commands.ListCommand.STATUS_ERROR_MESSAGE;
import static seedu.ui.MessageFormatter.formatLineSeparator;
import static seedu.ui.MessageFormatter.formatLastLineDivider;
public class EditCommand extends Command{

    public static final String INVALID_EDIT_ARGS =  formatLineSeparator("Invalid edit arguments!");
    public static final String NEWSPAPERS_ARGS_MESSAGE =
            "For Newspapers: /t TITLE /p PUBLISHER /ed EDITION /s STATUS /i ISBN" + formatLastLineDivider("For " +
                    "ENewspapers: /t TITLE /p PUBLISHER /ed EDITION /s STATUS " + "/l LINK /i ISBN");
    public static final String BOOK_ARGS_MESSAGE = "For Books: /t TITLE /a AUTHOR /g GENRES /s STATUS /i ISBN" +
            formatLastLineDivider("For EBooks: /t TITLE /a AUTHOR /g GENRES /s STATUS /l LINK /i ISBN");
    public static final String CD_ARGS_MESSAGE = formatLastLineDivider("For CDs: /t TITLE /p PUBLISHER" +
            " /ed EDITION /s STATUS /i ISBN");
    public static final String MAGAZINE_ARGS_MESSAGE = "For Magazines: /t TITLE /b BRAND /is ISSUE /s STATUS /i ISBN" +
            formatLastLineDivider("For EMagazines: /t TITLE /b BRAND /is ISSUE /s STATUS /l LINK /i ISBN");

    public static final String EDIT_SUCCESS = formatLineSeparator("Successfully updated! Your updated resource:");
    public static final String MISSING_ARG_MESSAGE =  formatLastLineDivider("Please provide at least " +
            "one detail to edit!");
    public static final String NOT_BOOK_ERROR =  formatLastLineDivider("Your resource is not a book!");
    public static final String RESOURCE_NOT_FOUND =  formatLastLineDivider("No such resource with given ID");
    private static final String NOT_CD_ERROR =  formatLastLineDivider("Your resource is not a CD!");
    private static final String NOT_NEWSPAPER_ERROR = formatLastLineDivider("Your resource is not a Newspaper!");
    private static final String NOT_MAGAZINE_ERROR = formatLastLineDivider("Your resource is not a Magazine!");

    private static final Logger EDIT_LOGGER = Logger.getLogger(EditCommand.class.getName());
    private static String feedbackToUser;
    private static int resourceIndex;

    static {

        FileHandler editFileHandler;
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
        args = new String[]{"id", "t", "a", "l", "g", "s", "c", "ty", "b", "is", "p", "ed", "i"};
        required = new boolean[]{true, false, false, false, false, false, false, false,false,false,false,false, false};
    }

    @Override
    public CommandResult execute(String statement, GenericList<Resource, Event> container)
            throws SysLibException, IllegalArgumentException {
        feedbackToUser = "";
        EDIT_LOGGER.info("Edit Command execute with " + statement);

        String[] givenParameters = parseArgument(statement);
        validateStatement(statement, givenParameters);

        List<Resource> resourcesList =  container.getResourceList();
        int givenArgsCount = countGivenArgs(givenParameters);
        int givenIDNumber = parseInt(givenParameters[0]);

        Resource foundResource = findResourceByID(givenIDNumber, resourcesList);

        if(foundResource == null){
            feedbackToUser += RESOURCE_NOT_FOUND;
            EDIT_LOGGER.warning(feedbackToUser);
            return new CommandResult(feedbackToUser);
        }

        Resource updatedResource = editResource(foundResource, givenParameters, givenArgsCount);
        assert updatedResource != null;
        assert resourceIndex < container.getResourceList().size();

        resourcesList.set(resourceIndex, updatedResource);
        feedbackToUser += EDIT_SUCCESS + formatLastLineDivider(updatedResource.toString());

        return new CommandResult(feedbackToUser);
    }

    private int countGivenArgs(String[] givenParameters) throws SysLibException {

        int argsCount = 0;

        for (int i =1; i<givenParameters.length; i++) {
            if (givenParameters[i] != null){
                argsCount++;
            }
        }

        boolean hasAtLeastOneArg = argsCount > 0;

        if(!hasAtLeastOneArg){
            EDIT_LOGGER.warning(MISSING_ARG_MESSAGE);
            throw new SysLibException(MISSING_ARG_MESSAGE);
        }
        return argsCount;
    }

    private Resource findResourceByID(int givenID, List<Resource> resourceList){

        Resource foundResource = null;

        for (int i=0;i < resourceList.size(); i++) {

            Resource tempResource = resourceList.get(i);

            int resourceID = tempResource.getId();
            if (resourceID==givenID){
                foundResource = tempResource;
                resourceIndex = i;
                break;
            }
        }
        return foundResource;
    }

    private Resource editResource(Resource foundResource, String[] givenParameters, int givenArgsCount)
            throws SysLibException {

        //All resource type needs to check Title, Status, and ISBN.
        if(givenParameters[1] != null){
            foundResource.setTitle(givenParameters[1]);
        }
        if (givenParameters[5] != null){
            foundResource.setStatus(getStatusFromString(givenParameters[5]));
        }
        if (givenParameters[12] != null){
            if (givenParameters[12].length() != 13){
                throw new SysLibException("ISBN must be 13 characters!");
            }
            foundResource.setISBN(givenParameters[12]);
        }

        //Call respective edit functions based on resource type

        String resourceTag = foundResource.getTag();

        switch(resourceTag){

        case "B":
            //fallthrough
        case "EB":
            validateBookParameters(givenParameters, resourceTag, givenArgsCount);
            foundResource = editBook(foundResource, givenParameters);
            break;
        case "CD":
            validateCDParameters(givenParameters, resourceTag, givenArgsCount);
            foundResource = editCD(foundResource, givenParameters);
            break;
        case "M":
            //fallthrough
        case "EM":
            validateMagazineParameters(givenParameters, resourceTag, givenArgsCount);
            foundResource = editMagazine(foundResource, givenParameters);
            break;
        case "N":
            //fallthrough
        case "EN":
            validateNewspaperParameters(givenParameters, resourceTag, givenArgsCount);
            foundResource = editNewspapers(foundResource, givenParameters);
            break;
        default:
            throw new SysLibException("Invalid Resource!");
        }

        return foundResource;
    }

    private void validateBookParameters(String[] givenParameters, String resourceTag, int givenArgsCount)
            throws SysLibException {

        if (resourceTag.equals("B")  && givenParameters[3] != null){
            throw new SysLibException(INVALID_EDIT_ARGS + BOOK_ARGS_MESSAGE);
        }

        int[] indexToCheck = {1,2,3,4,5,12};

        checkGivenParameters(givenArgsCount,givenParameters, indexToCheck, BOOK_ARGS_MESSAGE);

    }

    private void validateCDParameters(String[] givenParameters, String resourceTag, int givenArgsCount)
            throws SysLibException {

        int[] indexToCheck = {1,5,6,7,12};
        checkGivenParameters(givenArgsCount,givenParameters, indexToCheck, CD_ARGS_MESSAGE);
    }

    private void validateNewspaperParameters(String[] givenParameters, String resourceTag, int givenArgsCount)
            throws SysLibException {

        if (resourceTag.equals("N") && givenParameters[3] != null){
            throw new SysLibException(INVALID_EDIT_ARGS + NEWSPAPERS_ARGS_MESSAGE);
        }

        int[] indexToCheck = {1,3,5,10,11,12};
        checkGivenParameters(givenArgsCount,givenParameters, indexToCheck, NEWSPAPERS_ARGS_MESSAGE);
    }

    private void validateMagazineParameters(String[] givenParameters, String resourceTag, int givenArgsCount)
            throws SysLibException {

        if (resourceTag.equals("M") && givenParameters[3] != null){
            throw new SysLibException(INVALID_EDIT_ARGS + MAGAZINE_ARGS_MESSAGE);
        }

        int[] indexToCheck = {1,3,5,8,9,12};

        checkGivenParameters(givenArgsCount,givenParameters, indexToCheck, MAGAZINE_ARGS_MESSAGE);

    }

    private void checkGivenParameters(int givenArgsCount,  String[] givenParameters,int[] indexToCheck,
                                      String argsMessage) throws SysLibException {
        int argsCount = 0;

        for(int i=0;i<indexToCheck.length;i++){
            int index = indexToCheck[i];
            if (givenParameters[index] != null){
                argsCount++;
            }
        }

        if(argsCount != givenArgsCount){
            throw new SysLibException(INVALID_EDIT_ARGS + argsMessage);
        }
    }
    private Book editBook(Resource foundResource, String[] givenParameters) throws SysLibException {
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
    private CD editCD(Resource foundResource, String[] givenParameters) throws SysLibException {
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
    private Magazine editMagazine(Resource foundResource, String[] givenParameters) throws SysLibException {
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

    private Newspaper editNewspapers(Resource foundResource, String[] givenParameters) throws SysLibException {

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

    private Status getStatusFromString(String statusString) throws SysLibException {
        statusString = statusString.toLowerCase().trim();
        if (statusString.equals("borrowed")) {
            return Status.BORROWED;
        } else if (statusString.equals("lost")) {
            return Status.LOST;
        } else if (statusString.equals("available")){
            return Status.AVAILABLE;
        } else {

            throw new SysLibException(STATUS_ERROR_MESSAGE);
        }

    }

}
