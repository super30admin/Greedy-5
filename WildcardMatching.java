// Time Complexity : O(s log p)
// Space Complexity : O(1)

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*"))
            return true;
        int sl = s.length();
        int pl = p.length();
        int sp = 0; int pp = 0;
        int sStar = -1; int pStar = - 1;
        while(sp < sl) {
            if(pp < pl && (p.charAt(pp) == s.charAt(sp) || p.charAt(pp) == '?')) {
                pp++;
                sp++;
            }
            else if(pp < pl && p.charAt(pp) == '*') {
                sStar = sp;
                pStar = pp;
                //zero case
                pp++;
            }
            else if(pStar == -1) {
                return false;
            }
            else {
                sStar++;
                sp = sStar;
                pp = pStar + 1;
            }
        }
        while(pp < pl){
            if(p.charAt(pp) != '*')
                return false;
            pp++;
        }
        return true;
    }
}