
// Time Complexity : O(slogp)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :NO

/*
Approach
this is a pointer approach where intial we consider "*" not important and move on but once we find a char mismatch
we come bakc to the start (assuming all the chars including the mismatch char will be taken care by start)
we do this until we are dome matching 
*/

class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        int pStar = -1;
        int sStar = -1;
        int pp = 0;
        int sp = 0;
        while (sp < sl) {
            if (pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            } else if (pp < pl && p.charAt(pp) == '*') {
                pStar = pp;
                sStar = sp;
                pp++;
            } else if (pStar == -1) {
                return false;
            } else {
                pp = pStar + 1;
                sp = sStar + 1;
                sStar = sp;
            }
        }
        while (pp < pl) {
            if (p.charAt(pp) != '*') {
                return false;
            } else {
                pp++;
            }
        }
        return true;
    }
}