//TC: O(m + n)
//SC: O(1)
class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int starIdx = -1, sTmpIdx = -1;
        int m = s.length(), n = p.length();
        
        while (i < m) {
            // Case 1: Characters match or pattern has '?'
            if (j < n && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?')) {
                i++;
                j++;
            }
            // Case 2: Pattern has '*', try to match any sequence
            else if (j < n && p.charAt(j) == '*') {
                starIdx = j;  // Remember the position of '*'
                sTmpIdx = i;  // Remember the position in the string
                j++;          // Move the pattern pointer past '*'
            }
            // Case 3: Last pattern was '*', so we backtrack
            else if (starIdx != -1) {
                j = starIdx + 1;  // Reset pattern to the position after '*'
                sTmpIdx++;        // Increment string pointer
                i = sTmpIdx;      // Match one more character from the string
            }
            // Case 4: No match and no '*' to backtrack to
            else {
                return false;
            }
        }
        
        // Check for remaining '*' in pattern
        while (j < n && p.charAt(j) == '*') {
            j++;
        }
        
        // If we processed all the pattern, then it's a match
        return j == n;
    }
}
