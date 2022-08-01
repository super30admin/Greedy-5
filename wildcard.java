// Time Complexity :SlogP
// Space Complexity :constant
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        int sStar = -1;
        int sp = 0;
        int pStar = -1;
        int pp = 0;
        while (sp < sl) {
            // System.out.println("sp and pp"+sp+" and"+pp);
            // if characters are equal, go ahead
            if (pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            }
            // if we found a *, record that
            else if (pp < pl && p.charAt(pp) == '*') {
                sStar = sp;
                pStar = pp;
                pp++;
            }
            // if char not matching and no star happened, return false
            else if (sStar == -1) {
                return false;
            }
            // if character didn't match and star is there in history, start taking one case
            else {
                pp = pStar + 1;
                sp = sStar + 1;
                sStar = sp;
            }
        }
        // if s string finished but p is still left, check if we have all stars or not
        // if we get even
        // one character,return false
        while (pp < pl) {
            if (p.charAt(pp) != '*') {
                return false;
            }
            pp++;
        }
        return true;
    }
}