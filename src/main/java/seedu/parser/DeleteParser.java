package seedu.parser;

import seedu.data.SysLibException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteParser {
    public static String parseDelete(String statement) throws SysLibException {
        try {
            String inputPattern = "/id (\\d+)";
            Pattern pattern = Pattern.compile(inputPattern);
            Matcher matcher = pattern.matcher(statement);
            boolean matchFound = matcher.find();
            if (matchFound){
                return matcher.group(1).trim();
            } else {
                throw new SysLibException("Please use the format " +
                        "'delete /id ID");
            }
        } catch (IllegalStateException | SysLibException e) {
            throw new SysLibException("Please use the format " +
                    "'delete /id ID");
        }
    }
}
