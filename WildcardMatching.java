

/**
 * Determines if a string matches a pattern with wildcards '?' and '*'.
 * Uses dynamic programming to fill a DP table for matching states.
 * Returns true if the pattern matches the entire string, otherwise false.
 */
// Time Complexity: O(M * N), where M is the length of the pattern and N is the length of the string
// Space Complexity: O(M * N), for the DP table storing match states
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (p.equals(s)) {
            return true;
        }

        if (pLen > 0 && p.chars().allMatch(c -> c == '*')) {
            return true;
        }

        if (p.isEmpty() || s.isEmpty()) {
            return false;
        }

        boolean[][] d = new boolean[pLen + 1][sLen + 1];
        d[0][0] = true;

        for (int pIdx = 1; pIdx <=pLen; pIdx++) {
            if (p.charAt(pIdx - 1) == '*') {
                int sIdx = 1;
                while ((!d[pIdx - 1][sIdx - 1]) && (sIdx < sLen + 1)) {
                    sIdx++;
                }

                d[pIdx][sIdx - 1] = d[pIdx - 1][sIdx - 1];

                while (sIdx < sLen + 1) {
                    d[pIdx][sIdx++] = true;
                }
            } else if (p.charAt(pIdx - 1) == '?') {
                for (int sIdx = 1; sIdx <=sLen; sIdx++) {
                    d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1];
                }
            } else {
                for (int sIdx = 1; sIdx <=sLen; sIdx++) {
                    d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1] && (p.charAt(pIdx - 1) == s.charAt(sIdx - 1));
                }
            }
        }

        return d[pLen][sLen];
    }
    
}
