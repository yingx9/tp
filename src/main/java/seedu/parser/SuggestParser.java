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

    public static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1),
                                    dp[i - 1][j] + 1),
                            dp[i][j - 1] + 1);
                }
            }
        }
        return dp[a.length()][b.length()];
    }

}
