// Time Complexity: Best Case O(m + n), average case O(S log P) and worst case O(m * n)
// Space complexity: O(1) since no additional space is used

public class WildCardMatchingGreedy {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        int i = 0, j = 0;
        int starIndex = -1, sIndex = -1;

        while (i < m) {
            if (j < n && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?')) {
                // Characters match or '?' matches any single character
                i++;
                j++;
            } else if (j < n && p.charAt(j) == '*') {
                // '*' matches zero or more characters
                starIndex = j;
                sIndex = i;
                j++;
            } else if (starIndex != -1) {
                // Mismatch, but there was a previous '*', backtrack
                j = starIndex + 1;
                sIndex++;
                i = sIndex;
            } else {
                // Mismatch and no '*' to fall back on
                return false;
            }
        }

        // Check if remaining pattern characters are all '*'
        while (j < n && p.charAt(j) == '*') {
            j++;
        }

        return j == n;
    }

    public static void main(String[] args) {
        WildCardMatchingGreedy solution = new WildCardMatchingGreedy();

        // Test cases
        System.out.println(solution.isMatch("adceb", "*a*b")); // Output: true
        System.out.println(solution.isMatch("aa", "a"));       // Output: false
        System.out.println(solution.isMatch("cb", "?a"));      // Output: false
        System.out.println(solution.isMatch("acdcb", "a*c?b")); // Output: false
    }
}
