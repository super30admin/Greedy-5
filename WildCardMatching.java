// Time Complexity: O(m * n)
// Space Complexity: O(1)
class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) return true;

        int m = p.length();
        int n = s.length();

        int sp = 0;
        int pp = 0;
        int sStar = -1;
        int pStar = -1;

        while (sp < n) {
            if (pp < m && (p.charAt(pp) == s.charAt(sp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            } else if (pp < m && p.charAt(pp) == '*') {
                sStar = sp;
                pStar = pp;
                pp++;
            } else if (pStar == -1) {
                return false;
            } else {
                sp = sStar + 1;
                pp = pStar;
                sStar = sp;
            }
        }

        while (pp < m) {
            if (p.charAt(pp) != '*') {
                return false;
            }
            pp++;
        }
        return true;
    }
}