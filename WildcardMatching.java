// Time Complexity : O(m * n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : NA


// Your code here along with comments explaining your approach

class Solution {
    public boolean isMatch(String s, String p) {
        
        if(s.equals(p) || p.equals("*")) return true;
        
        int sp = 0, pp = 0, sStar = -1, pStar = -1;
        
        int sl = s.length(), pl = p.length();
        
        while(sp < sl) {
            if(pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            }
            else if(pp < pl && p.charAt(pp) == '*') {
                sStar = sp;
                pStar = pp;
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
        
        while(pp < pl) {
            if(p.charAt(pp) != '*') {
                return false;
            }
            pp++;
        }
        return true;
    }
}