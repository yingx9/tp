package seedu.commands;

import seedu.data.Book;
import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;
import static seedu.ui.UI.SEPARATOR_LINEDIVIDER;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListCommand extends Command {


    private static boolean isFilteredByTag;
    private static boolean isFilteredByGenre;
    private static String tagKeyword;
    private static String genreKeyword;

    private static String messageToPrint;

    public ListCommand(){
        args = new String[]{"tag", "g"};
        aliasArgs = new String[]{"tag", "genre"};
        required = new boolean[]{false, false};
    }

    public void resetVariables(){
        isFilteredByTag = false;
        isFilteredByGenre = false;
        tagKeyword = "";
        genreKeyword ="";
        messageToPrint = "Listing all resources in the Library:";
    }

    @Override
    public void execute(String statement, Parser parser) throws SysLibException, IllegalArgumentException {
        resetVariables();
        String[] values = parseArgument(statement);
        validate(statement, values);
        setListFilters(statement);

        filterResources(parser.resourceList);

    }


    public void filterResources(List<Resource> resourceList) throws SysLibException{

        if (isFilteredByGenre || isFilteredByTag){
            List<Resource> matchedResources = new ArrayList<>();
            boolean isTagEqualToKeyword = true;
            boolean isGenreEqualToKeyword = true;


            for (int i=0; i <resourceList.size(); i++){

                Resource resource = resourceList.get(i);

                if(isFilteredByTag){
                    String resourceTag = resource.getTag();
                    isTagEqualToKeyword = resourceTag.equals(tagKeyword);
                }

                if(isFilteredByGenre){
                    isGenreEqualToKeyword = hasGenre(resource, genreKeyword);
                }

                if (isTagEqualToKeyword && isGenreEqualToKeyword){
                    matchedResources.add(resource);
                }

            }
            displayResourcesDetails(matchedResources, messageToPrint);
        } else{
            displayResourcesDetails(resourceList, messageToPrint);
        }


    }

    public boolean hasGenre(Resource resource, String genre){
        Book bookResource;

        if (resource instanceof Book) {
            bookResource = (Book) resource;
            String[] genres = bookResource.getGenre();
            if (genres[0] == null ){
                return false;
            }

            for(int j =0; j < genres.length; j ++){
                if (genres[j].equals(genre)){
                    return true;
                }
            }
        }
        return false;


    }



    public void displayResourcesDetails(List<Resource> resourcesList, String message) {

        System.out.println(message + System.lineSeparator());
        if (resourcesList.isEmpty()){
            System.out.println("There are currently 0 resources." +
                    SEPARATOR_LINEDIVIDER);
        } else {

            for (int i = 0; i < resourcesList.size(); i += 1) {
                String resourceDetails = resourcesList.get(i).toString();
                System.out.println(i+1 + ". " + resourceDetails);
            }
            System.out.println(System.lineSeparator() + "There are currently " + resourcesList.size() +
                    " resource(s)." + SEPARATOR_LINEDIVIDER);
        }
    }


    public static void setListFilters(String statement) throws SysLibException {

        Pattern pattern = Pattern.compile("/(tag|g)\\s+([^/]+)");
        Matcher matcher = pattern.matcher(statement);

        while(matcher.find()){

            String flag = matcher.group(1);
            String keyword = matcher.group(2).trim();
            switch(flag){
            case "tag":
                isFilteredByTag = true;
                tagKeyword = keyword;
                break;
            case "g":
                isFilteredByGenre = true;
                genreKeyword = keyword;
                break;
            default:
                throw new SysLibException("Please enter a valid filter /tag or /g");
            }
            messageToPrint = "Listing resources matching given filters: ";
        }

    }

}
