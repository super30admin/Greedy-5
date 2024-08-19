/* 
    LC - 44. Wildcard Matching
    TC - O(min(s, p))
    SC - O(1)
*/

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) {
            return true;
        }
        if(p == null || p.length() == 0) {
            return false;
        }
        int sp = 0; // pointer in s
        int pp = 0; // pointer in p
        int sStar = -1;
        int pStar = -1;
        while(sp < s.length()) {
            if(pp < p.length() && (s.charAt(sp) == p.charAt(pp) || (p.charAt(pp) == '?'))) {
                sp++;
                pp++;
            }
            else if(pp < p.length() && p.charAt(pp) == '*') {
                pStar = pp;
                sStar = sp;
                pp++;
            }
            else if(pStar == -1) {
                return false;
            } 
            else {
                pp = pStar;
                sp = sStar;
                pp++;
                sp++;
                sStar = sp;
            }
        }
        while(pp < p.length()) {
            if(p.charAt(pp) != '*') {
                return false;
            }
            pp++;
        }
        return true;
    }
}