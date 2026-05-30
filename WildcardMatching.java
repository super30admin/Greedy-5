// Time Complexity : O(min(m,n)), worst case - O(m*n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
Use 2 pointers to iterate through s and p strings and check if the characters match or equal to '?',If mismatch
happens, jump back to last *, and retry by advancing the string pointer. At the end, check if the remaining characters in
the pattern are all '*'.
 */
class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0 , j = 0;
        int sStar = -1, pStar = -1;

        while(i < s.length()) {
            if(j < p.length() && (s.charAt(i) == p.charAt(j) || (p.charAt(j) == '?'))) {
                i++;
                j++;
            }
            else if(j < p.length() && p.charAt(j) == '*') {
                sStar = i;
                pStar = j;
                j++;
            }
            else {
                if(sStar == -1)
                    return false;
                sStar++;
                i = sStar;
                j = pStar + 1;
            }
        }

        while(j < p.length()) {
            if(p.charAt(j) != '*')
                return false;
            j++;
        }
        return true;
    }
}