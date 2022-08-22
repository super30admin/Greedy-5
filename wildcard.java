// Time Complexity :SlogP
// Space Complexity :constant
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        int sStar = -1;
        int sp = 0;
        int pStar = -1;
        int pp = 0;
        while (sp < sl) {
            if (pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            }
            else if (pp < pl && p.charAt(pp) == '*') {
                sStar = sp;
                pStar = pp;
                pp++;
            }
            else if (sStar == -1) {
                return false;
            }
            else {
                pp = pStar + 1;
                sp = sStar + 1;
                sStar = sp;
            }
        }
        while (pp < pl) {
            if (p.charAt(pp) != '*') {
                return false;
            }
            pp++;
        }
        return true;
    }
} 