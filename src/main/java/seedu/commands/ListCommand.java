package seedu.commands;

import seedu.data.Book;
import seedu.data.Resource;
import seedu.data.SysLibException;
import seedu.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ListCommand extends Command {


    private static final String LIST_REGEX_PATTERN = "/(tag|g)\\s+([^/]+)";
    private static final String TAG_MESSAGE = "Listing all resources matching given tag: ";
    private static final String GENRE_MESSAGE = "Listing all resources matching given genre: ";
    private static final String GENERIC_MESSAGE = "Listing all resources in the Library: ";
    private static final String TAG_GENRE_MESSAGE = "Listing all resources matching given tag and genre: ";
    private static Pattern pattern = Pattern.compile(LIST_REGEX_PATTERN);

    private static boolean isFilteredByTag = false;
    private static boolean isFilteredByGenre = false;

    @Override
    public void execute(String statement, Parser parser) throws SysLibException, IllegalArgumentException {
        int size = parser.resourceList.size();
        if (size == 0){
            System.out.println("There are 0 resources in the library. " + System.lineSeparator());
        } else {
            Matcher matcher = pattern.matcher(statement);
            filterResources(matcher, parser, statement);

        }
    }


    public void filterResources(Matcher matcher, Parser parser, String statement) throws SysLibException{
        List<Resource> matchedTagResources = new ArrayList<>();
        List<Resource> matchedGenreResources = new ArrayList<>();

        isFilteredByTag = false;
        isFilteredByGenre = false;

        while(matcher.find()){

            String flag = matcher.group(1);
            String keyword = matcher.group(2);
            keyword = keyword.trim();

            switch(flag){
            case "tag":
                filterByTag(matchedTagResources, parser.resourceList, keyword);
                isFilteredByTag = true;
                break;
            case "g":
                filterByGenre(matchedGenreResources, parser.resourceList, keyword);
                isFilteredByGenre = true;
                break;
            default:
                throw new SysLibException("Please enter a valid filter /tag or /g");
            }

        }

        boolean isListAllCommand = statement.equals("");
        boolean isListTagOrGenre = (isFilteredByGenre || isFilteredByTag);
        if (isListAllCommand || isListTagOrGenre){
            listResults(matchedTagResources, matchedGenreResources, parser);
        } else{
            throw new SysLibException("Please enter a valid list command!" + System.lineSeparator());
        }

    }

    public void listResults(List<Resource> matchedTagResources, List<Resource> matchedGenreResources, Parser parser){

        if(isFilteredByTag == true && isFilteredByGenre == false){
            displayResourcesDetails(matchedTagResources, TAG_MESSAGE);
        } else if (isFilteredByTag == false && isFilteredByGenre == true){
            displayResourcesDetails(matchedGenreResources, GENRE_MESSAGE);
        } else if (isFilteredByTag && isFilteredByGenre){
            System.out.println(TAG_GENRE_MESSAGE + System.lineSeparator());
            filterBothTagGenre(matchedGenreResources, matchedTagResources);
        } else{
            displayResourcesDetails(parser.resourceList, GENERIC_MESSAGE);
        }
    }

    public void filterByTag(List<Resource> matchedTagResources, List<Resource> resourcesList, String tag) throws SysLibException {


        for(int i=0;i< resourcesList.size();i++){
            Resource resource = resourcesList.get(i);
            if (resource.getTag().equals(tag)){
                matchedTagResources.add(resource);
            }
        }

    }


    public void filterByGenre(List<Resource> matchedGenreResources, List<Resource> resourcesList, String genre) throws SysLibException {


        for(int i=0;i< resourcesList.size();i++){
            Resource r = resourcesList.get(i);
            if (r instanceof Book){
                Book bookResource = (Book) r;
                boolean hasGenre = hasGenre(bookResource, genre);
                if (hasGenre){
                    matchedGenreResources.add(bookResource);
                }

            }

        }

    }

    public boolean hasGenre(Book bookResource, String genre){
        String[] genres = bookResource.getGenre();
        for(int j =0; j < genres.length; j ++){
            if (genres[j].equals(genre)){
                return true;
            }
        }
        return false;
    }

    public void filterBothTagGenre(List<Resource> matchedGenreResources, List<Resource> matchedTagResources){
        int count = 0;
        for(int i =0;i < matchedTagResources.size(); i++){

            Resource r = matchedTagResources.get(i);
            if (matchedGenreResources.contains(r)){
                System.out.println(i+1 + ". " + r.toString());
                count++;
            }

        }
        System.out.println("There are currently " + count + " resource(s)." + System.lineSeparator());
    }



    public void displayResourcesDetails(List resourcesList, String message) {
        System.out.println(message + System.lineSeparator());
        for (int i = 0; i < resourcesList.size(); i += 1) {
            String resourceDetails = resourcesList.get(i).toString();
            System.out.println(i+1 + ". " + resourceDetails);

        }
        System.out.println("There are currently " + resourcesList.size() + " resource(s)." + System.lineSeparator());
    }

}
