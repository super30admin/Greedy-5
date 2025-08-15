// TC: O(n)
// SC: O(1)
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        int sp = 0;
        int pp = 0;
        int sstar = -1;
        int pstar = -1;

        while (sp < sl) {
            if (pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            } else if (pp < pl && p.charAt(pp) == '*') {
                sstar = sp;
                pstar = pp;
                pp++; // 0 Case
            } else if (pstar == -1) {
                return false; // no wild card and mis-match
            } else {
                sstar++; // wildcard match;
                sp = sstar;
                pp = pstar +1;
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