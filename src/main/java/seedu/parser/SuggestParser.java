package seedu.parser;

import java.util.List;

public class SuggestParser {
    public static String suggest(String input, List<String> commands) {
        int minDistance = 2;
        String closestMatch = null;

        for (String command : commands) {
            int distance = levenshteinDistance(input, command);
            if (distance < minDistance) {
                minDistance = distance;
                closestMatch = command;
            }
        }
        return closestMatch;
    }

    public static int levenshteinDistance(String first, String second) {
        int[][] array = new int[first.length() + 1][second.length() + 1];

        for (int firstPointer = 0; firstPointer <= first.length(); firstPointer++) {
            for (int secondPointer = 0; secondPointer <= second.length(); secondPointer++) {
                if (firstPointer == 0) {
                    array[firstPointer][secondPointer] = secondPointer;
                } else if (secondPointer == 0) {
                    array[firstPointer][secondPointer] = firstPointer;
                } else {
                    int temp = 1;
                    if(first.charAt(firstPointer - 1) == second.charAt(secondPointer - 1) ){
                        temp = 0;
                    }
                    int firstMin = Math.min(array[firstPointer - 1][secondPointer - 1] + temp,
                            array[firstPointer - 1][secondPointer] + 1);
                    array[firstPointer][secondPointer] = Math.min(firstMin,
                            array[firstPointer][secondPointer - 1] + 1);
                }
            }
        }
        return array[first.length()][second.length()];
    }

}
