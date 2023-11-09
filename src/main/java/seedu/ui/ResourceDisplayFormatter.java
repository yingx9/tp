package seedu.ui;

import seedu.data.resources.Resource;

import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

import static seedu.ui.MessageFormatter.formatADivider;
import static seedu.ui.UI.LINESEPARATOR;

/** Handles display and formatting of resources to show to users as a table **/
public class ResourceDisplayFormatter {

    protected Formatter bookDisplayFormatter;
    protected Formatter magazineDisplayFormatter;
    protected Formatter cdDisplayFormatter;
    protected Formatter newspaperDisplayFormatter;
    private String customDivider;

    private String displayFormat;

    private List<Boolean> hasResourceTypeList;

    public ResourceDisplayFormatter(List<Resource> resourcesList){
        displayFormat = buildDisplayHeader(resourcesList);
        bookDisplayFormatter = buildBookFormatter(displayFormat);
        magazineDisplayFormatter = buildMagazineFormatter(displayFormat);
        cdDisplayFormatter = buildCDFormatter(displayFormat);
        newspaperDisplayFormatter = buildNewspaperFormatter(displayFormat);

        hasResourceTypeList = Arrays.asList(false, false, false, false);
    }

    public void setBookDisplayFormatter(Resource resource) {
        bookDisplayFormatter = resource.toTableFormat(displayFormat, bookDisplayFormatter);
        hasResourceTypeList.set(0,true);
    }

    public void setMagazineDisplayFormatter(Resource resource){
        magazineDisplayFormatter = resource.toTableFormat(displayFormat, magazineDisplayFormatter);
        hasResourceTypeList.set(1,true);
    }

    public void setCDDisplayFormatter(Resource resource){
        cdDisplayFormatter = resource.toTableFormat(displayFormat, cdDisplayFormatter);
        hasResourceTypeList.set(2,true);
    }
    public void setNewspaperDisplayFormatter(Resource resource){
        newspaperDisplayFormatter = resource.toTableFormat(displayFormat, newspaperDisplayFormatter);
        hasResourceTypeList.set(3,true);
    }

    private Formatter buildBookFormatter(String displayFormat){
        Object[] bookArgs = {"ID", "Tag", "Title", "ISBN", "Author", "Genre", "Link", "Status", "Received Date"};
        String headerPadding = "%" + (customDivider.length()/2 + 6) + "s" + LINESEPARATOR;
        String bookHeader = String.format(headerPadding, "[BOOKS]");
        Formatter bookDisplayFormatter = buildDisplayFormatter(displayFormat, bookArgs, bookHeader);

        return bookDisplayFormatter;
    }
    private Formatter buildMagazineFormatter(String displayFormat){
        Object[] magazineArgs = {"ID", "Tag", "Title", "ISBN", "Brand", "Issue", "Link", "Status", "Received Date"};
        String headerPadding = "%" + (customDivider.length()/2 + 10) + "s" + LINESEPARATOR;
        String magazineHeader = String.format(headerPadding, "[MAGAZINES]");
        Formatter magazineDisplayFormatter = buildDisplayFormatter(displayFormat, magazineArgs, magazineHeader);
        return magazineDisplayFormatter;
    }

    private Formatter buildCDFormatter(String displayFormat){
        Object[] cdArgs = { "ID", "Tag", "Title", "ISBN", "Creator", "Type", "Link", "Status", "Received Date"};
        String headerPadding = "%" + (customDivider.length()/2 + 5) + "s" + LINESEPARATOR;
        String cdHeader = String.format(headerPadding, "[CDS]");
        Formatter cdDisplayFormatter = buildDisplayFormatter(displayFormat, cdArgs, cdHeader);
        return cdDisplayFormatter;
    }

    private Formatter buildNewspaperFormatter(String displayFormat){
        Object[] newspaperArgs = {"ID", "Tag", "Title", "ISBN", "Publisher", "Edition", "Link",
            "Status", "Received Date"};
        String headerPadding = "%" + (customDivider.length()/2 + 10) + "s" + LINESEPARATOR;
        String newspaperHeader = String.format(headerPadding, "[NEWSPAPERS]");
        Formatter newspaperFormatter = buildDisplayFormatter(displayFormat, newspaperArgs, newspaperHeader);
        return newspaperFormatter;
    }
    public Formatter buildDisplayFormatter(String displayFormat, Object[] displayArgs, String header){

        Formatter displayFormatter = new Formatter();
        displayFormatter.format(header);
        displayFormatter.format(customDivider);
        displayFormatter.format(displayFormat, displayArgs);
        displayFormatter.format(customDivider);
        return displayFormatter;

    }

    public String buildDisplayHeader(List<Resource> resourcesList){

        //Check columns at index 2, 4, 5, 6 as length is unrestricted
        //Columns represent:
        // ID, Tag, Title, ISBN, Author/Brand/Creator/Publisher,
        // Genre/Issue/Type/Edition, Link, Status, Received Date
        List<Integer> columnsWidth = Arrays.asList(7,5,20,14,25,20,15,10,15);

        int paddingLength = 0;

        for (Resource resource : resourcesList) {
            columnsWidth = resource.checkColumnsWidths(columnsWidth);

        }

        String displayFormat = "";

        for (int i= 0; i<columnsWidth.size();i++){
            displayFormat += "%-" + columnsWidth.get(i) + "s";
            paddingLength += columnsWidth.get(i);
        }

        displayFormat += LINESEPARATOR;

        customDivider = formatADivider("%-" + paddingLength + "s");

        return displayFormat;
    }

    /**
     * Constructs the final display message by adding a resource type only if it contains at least one resource to show.
     *
     * @return messageToDisplay
     */
    public String getFinalDisplayFormat() {

        String messageToDisplay ="";
        if(hasResourceTypeList.get(0)){
            messageToDisplay += bookDisplayFormatter + LINESEPARATOR;
        }

        if(hasResourceTypeList.get(1)){
            messageToDisplay += magazineDisplayFormatter+ LINESEPARATOR;
        }

        if(hasResourceTypeList.get(2)){
            messageToDisplay += cdDisplayFormatter+ LINESEPARATOR;
        }

        if(hasResourceTypeList.get(3)){
            messageToDisplay += newspaperDisplayFormatter+ LINESEPARATOR;
        }
        return messageToDisplay;
    }

}
