// Time Complexity : O(min(m,n))
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) {
            return true;
        }
        
        int sl = s.length();
        int pl = p.length();
        
        int sStar = -1, pStar = -1;
        int sp = 0, pp = 0;
        
        while(sp < sl) {
            if(pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            }
            else if(pp < pl && p.charAt(pp) == '*') {
                pStar = pp;
                pp++;
                sStar = sp;
            }
            else if(pStar == -1) {
                return false;
            } else {
                pp = pStar;
                sStar++;
                sp = sStar;
            }
        }
        
        while(pp < pl) {
            if(p.charAt(pp++) != '*') {
                return false;
            }
        }
        
        return true;
    }
}